<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/css/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/styles/js/jquery-1.12.1.min.js"></script>		
<script type="text/javascript" src="<%=request.getContextPath()%>/styles/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//jQuery ready is quicker than onload
		$(".stripeMe tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
		$(".stripeMe tr:even").addClass("alt");
	});
</script>