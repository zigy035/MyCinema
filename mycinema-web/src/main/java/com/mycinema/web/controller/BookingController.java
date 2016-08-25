package com.mycinema.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycinema.web.enumeration.BookingStatus;
import com.mycinema.web.form.BookingMatrixForm;
import com.mycinema.web.model.Ticket;
import com.mycinema.web.service.TicketService;
import com.mycinema.web.validator.BookingMatrixValidator;

@Controller
@RequestMapping("/book")
public class BookingController extends AbstractController {
	
	private static final Logger LOG = Logger.getLogger(BookingController.class);
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private BookingMatrixValidator bookingMatrixValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showBookForm(Model model, @RequestParam(value="mbid", required=true) String broadcastId, 
			@RequestParam(value="error", required=false) String error) {
		
		List<Ticket> tickets = ticketService.getTicketsByBroadcast(broadcastId);
		if (CollectionUtils.isEmpty(tickets)) {
			return "error";
		}
		
		model.addAttribute("error", error);
		
		Map<String, List<Ticket>> ticketMap = new HashMap<String, List<Ticket>>();
		for (Ticket ticket : tickets) {
			String seatRow = ticket.getSeatRow();
			List<Ticket> seatRowList = getSeatRowList(tickets, seatRow);
			ticketMap.put(seatRow, seatRowList);
		}
		
		model.addAttribute("ticketMap", ticketMap);
		model.addAttribute("rowLength", ticketMap.get("A").size());
		
		BookingMatrixForm matrixForm = new BookingMatrixForm();
		matrixForm.setMovieBroadcastId(broadcastId);
		model.addAttribute("matrixForm", matrixForm);
		
		return "bookingForm";
	}
	
	private List<Ticket> getSeatRowList(List<Ticket> tickets, String seatRow) {
		List<Ticket> seatRowList = new ArrayList<Ticket>();
	    for (Ticket ticket : tickets) {
	        if (ticket != null && ticket.getSeatRow().equals(seatRow)) {
				seatRowList.add(ticket);
	        }
	    }
	    
	    Collections.sort(seatRowList, new Comparator<Ticket>() {

			public int compare(Ticket t1, Ticket t2) {
				return t1.getSeatColumn().compareTo(t2.getSeatColumn());
			}
		});
	    
	    return seatRowList;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitBookForm(Model model, BookingMatrixForm matrixForm, BindingResult result) {
		
		bookingMatrixValidator.validate(matrixForm, result);
		if (result.hasErrors())
		{
			// UI hack on Movie Broadcast ID
			int mbIdErrorCount = result.getFieldErrorCount("movieBroadcastId");
			if (mbIdErrorCount > 0) {
				LOG.info("Movie Broadcast ID is invalid");
				return "error";
			}
			
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				if (error.getCode().equals("please.select.seats")) {
					LOG.info("No seats selected");
					return getErrorUrl(matrixForm.getMovieBroadcastId(), BookingStatus.NO_SEATS_SELECTED);
				}
			}
			LOG.info("Max allowed seats number exceeded");
			return getErrorUrl(matrixForm.getMovieBroadcastId(), BookingStatus.MAX_ALLOWED_EXCEEDED);
		}

		// start booking
		BookingStatus bookingStatus = bookSelectedTickets(matrixForm);
		if (bookingStatus.equals(BookingStatus.ERROR) || 
				bookingStatus.equals(BookingStatus.NOT_AVAILABLE)) {
			return getErrorUrl(matrixForm.getMovieBroadcastId(), bookingStatus);
		}

		LOG.info("Seats booked successfully");
		model.addAttribute("matrixForm", matrixForm);
		return "confirmBooking";
	}
	
	private String getErrorUrl(String mbId, BookingStatus status) {
		return "redirect:/book?mbid=" + mbId + "&error=" + status.ordinal();
	}
	
	public synchronized BookingStatus bookSelectedTickets(BookingMatrixForm matrixForm) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (String seat : matrixForm.getSeats()) {
			String broadcastId = matrixForm.getMovieBroadcastId();
			String [] positions = seat.split("-");
			Ticket ticket;
			if (positions.length != 2) {
				// UI hacked
				return BookingStatus.ERROR;
			} else {
				String row = positions[0];
				String column = positions[1];
				ticket = ticketService.getAvailableTicket(broadcastId, row, column);
				if (ticket == null) {
					// UI hack on seats
					return BookingStatus.ERROR;
				} else if (ticket.getAuthUser() != null) {
					// Already booked
					return BookingStatus.NOT_AVAILABLE;
				}
			}
			ticket.setAuthUser(getAuthUser());
			tickets.add(ticket);
		}
		
		ticketService.bookTickets(tickets);
		return BookingStatus.SUCCESS;
	}
	
}
