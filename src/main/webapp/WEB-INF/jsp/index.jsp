<%@page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" session="false"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!doctype html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hotel Deals Web Application</title>
<link rel="stylesheet" type="text/css"
	href="css/common/headerFooter.css" />

<link rel="stylesheet" href="css/jquery-ui/jquery-ui-lightness.css" type="text/css"
	media="screen" title="UI">
<link rel="stylesheet" href="css/pagination.css" type="text/css"
	media="screen" title="Pagination">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen" title="">
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css" type="text/css"
	media="screen" title="">
<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css"
	media="screen" title="">
<link rel="stylesheet" type="text/css" href="css/common/hotelDeals.css" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jssor.js"></script>
<script type="text/javascript" src="js/jssor.slider.js"></script>
<script type="text/javascript" src="js/jquery-ui/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="js/tablePagination.js"></script>
<script type="text/javascript" src="js/tableUpdatedPagination.js"></script>
<script type="text/javascript" src="js/hotelDeals.js"></script>
<%@include file="hotelDeals.jsp"%>
</head>

 <body onload="" style="background-image: url('images/bgImage.jpg');">

	<div id="pageContainer" style="width:100%" >
		<header id="pageHeader">
			<%@include file="common/header.jsp"%>
		</header>
		<div id="leftMenu" style="background-color:#aaa;height:300px;width:300px;float:left;">
			<br/><br/>
			<table style="border-spacing: 10px;">
				<tr>
					<td><B><U><I>Book domestic and internation Hotels</I></U></B></td>
				</tr>
				
				<tr>
					<td>Destination
					<div class="dropdown">
						<select id="destination" name="destination" class="dropdown-select">
							<c:forEach items="${destinationList}" var="destination">
								<c:if test="${destinationList!=null}">in
							<option value="${destination.key}">${destination.value}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					
					
					</td>
				</tr>
				
				<tr>
				<td>
					<p>Check In<input type="text" class="datepicker" id="datepicker-1" name="datepicker-1" /></p>
				</td>
				</tr>
				
				<tr>
				<td>
					<p>Check Out<input type="text" class="datepicker" name="datepicker-2" id="datepicker-2"/></p>
				</td>
				</tr>  
			</table>
			
			<div id="lightbox"
				style="background: none; z-index: 100; position: absolute; display: none; top: 200px; left: 540px; width:600px;">
				<div
					style="height: 390px; width: 600px;overflow-x:auto;overflow-y:auto; background-color: white; padding: 1px;">
					<b><u>HotelDeals</u></b>
					<table id="deals" style="padding: 1em;">
						
						
					</table>
				</div>
			</div>
			<div id="overLay"
				style="position: fixed; height: 100%; width: 100%; background: #000; opacity: 0.6; top: 0px; left: 0px; display: none;"
				onclick="unloadLightBox();"></div>
			<br/>
		
			<button id="check" onclick="javascript:check()">Check Availability</button>
		</div>
		
		
		<div id="availableDeals" style="background-color:#eee;height:500px;width:1020px;float:right;">
		<table>
			<c:if test="${not empty hotelDealsList}">
    		<table id="hotelDealTable" >
    			<thead style="font-size: 20px;border: 1px;border-color: black;">
            	<tr>
            	 <th>Destination</th>
                <th>Hotel Name</th>
                  <th>Image</th>
                <th>Description</th>
                <th>Base Rate</th>
                <th>Offer</th>
                <th>Star Rating</th>
              
       			</tr>
        	</thead>
         	<tfoot>
            	<tr>
            	  <th align="center">Destination</th>
               	<th align="center">Hotel Name</th>
                <th align="center">Image</th>
                <th align="center">Description</th>
                <th align="center">Base Rate</th>
                <th align="center">Offer</th>
                <th align="center">Star Rating</th>
              
            	</tr>
        	</tfoot>
        	<tbody id="hotelDetilsList" style="font-size: 15px;border: 1px;border-color: black;">
        		<c:forEach var="o" items="${hotelDealsList}">
            	<tr>
            		 <td align="center">${o.longDestinationName}</td>
	                <td align="center">${o.name}</td>
	                  <td align="center"><img src="${o.imageUrl}"/></td>  
    	            <td align="justify" >${o.description}</td>
        	        <td align="center">${o.baseRate} + ${o.taxesAndFees} ${o.currency}</td>   
        	        <td align="center">${o.promotionDescription}</td>  
        	        <td align="center">${o.starRating}</td>
        	      
        	        
            	</tr>
        		</c:forEach>
        	</tbody>
    		</table>
    		   		
			</c:if>
		</table>
		</div>
		
		<div id="rightMenu" style="height:220px;width:100%;top:600px;position: absolute;">
		<div style="background: white;position: relative; left:50px;top:0px;width: 300px; height: 180px">
			<ul>
			<li>Seasonal deal: save 15%</li>
			<li>Europe : Save 3 nights and get 1 night stay for free!!!</li>
			<li>USA : Free breakfast and dinner combo pack</li>
			<li>FLorence : save 12%</li>
			<li>Get upto 50% off on your next stay</li>
			</ul>
		</div>
		<div id="slider1_container" style="position: relative; top:-200px;width: 500px;height: 200px;">
        <!-- Slides Container -->
        <div id="slides" u="slides" style="cursor: move; left:450px;top:20px;width: 300px; height: 180px;
            overflow: hidden;">
            	<c:forEach var="recHotel" items="${recHotelList}">
            		<div>
            		<img u="image" title="Click to Display" src="${recHotel.imageUrl}" alt="${recHotel.imageUrl} Image" />
            		</div>
		 		</c:forEach>
        </div>
    	</div>
		
		</div>

		<div id="footer" style="height:50px;width:100%;top:840px;position: absolute;">
			<%@include file="common/footer.jsp"%>
		</div>
	</div>

</body>
</html>

