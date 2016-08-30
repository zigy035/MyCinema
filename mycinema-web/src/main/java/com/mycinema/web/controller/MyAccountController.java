package com.mycinema.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycinema.web.model.MovieBroadcast;
import com.mycinema.web.model.Ticket;
import com.mycinema.web.service.TicketService;

@Controller
@RequestMapping("/myaccount")
public class MyAccountController extends AbstractController {
		
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showMyAccount(Model model) {
		
		Map<MovieBroadcast, List<Ticket>> broadcastTicketMap = new HashMap<MovieBroadcast, List<Ticket>>();
		List<Ticket> tickets = ticketService.getTicketsByAuthUser(getAuthUser().getId());
		for (Ticket ticket : tickets) {
			MovieBroadcast broadcast = ticket.getMovieBroadcast();
			List<Ticket> broadcastTicketList = getBroadcastTicketList(tickets, broadcast);
			broadcastTicketMap.put(broadcast, broadcastTicketList);
		}
		
		model.addAttribute("broadcastTicketMap", broadcastTicketMap);
		return "myAccount";
	}

	private List<Ticket> getBroadcastTicketList(List<Ticket> tickets, MovieBroadcast broadcast) {
		List<Ticket> broadcastTickets = new ArrayList<Ticket>();
	    for (Ticket ticket : tickets) {
	        if (ticket != null && ticket.getMovieBroadcast().getId().equals(broadcast.getId())) {
				broadcastTickets.add(ticket);
	        }
	    }
	    
	    Collections.sort(broadcastTickets, new Comparator<Ticket>() {

			public int compare(Ticket t1, Ticket t2) {
//				int row1 = Character.getNumericValue(t1.getSeatRow().get);
				String s1 = t1.getSeatRow() + t1.getSeatColumn();
				String s2 = t2.getSeatRow() + t2.getSeatColumn();
				return s1.compareTo(s2);
			}
		});
	    
	    return broadcastTickets;
	}
	
}
