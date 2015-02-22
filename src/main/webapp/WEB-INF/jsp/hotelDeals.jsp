<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="false"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!doctype html>

<html lang="en">
    <head>
    	
		<script type="text/javascript">
		
		function getRecommendedHotels(first,second,destination)
		{
			
			$.ajax({
					type : "GET",
					contentType: "application/json",         
					dataType: "json",
					url : "${pageContext.request.contextPath}/api/getRecHotels?"
							+"checkInDate="+first+"&checkOutDate="+second+
							"&destination="+destination,
					cache : false,
					error: function (request, status, error) {
						unloadLightBoxLoader();
		      		  alert(status+" _"+error+"_"+request);
			            }
					}).done(function(data)
					{   
					$("#deals").html("");   
					if(null != data)
					{
			 			if(data.count < 1){             
			 				alert("No Deals for this date !! Select appropriate dates");
							$("#deals").html("<tr><td colspan='7'><span>No Recommended Hotels</span></td></tr>");         
						}else {
							insertdata(data.count,
									data.data);
						
						}
					}
					});
		}


       
		function insertdata(count, data) {
			 
			
			//$("#hotelDealTable").children('tbody').remove();
			document.getElementById("deals").style.display="table";
			$("#deals").html("");
			detached_data = $("#hotelDealTable").children('tbody').detach();
			var $tbody = $("#hotelDealTable");  

			if(count>0)
			{
				for (var item=0;item<count;item++)
				{
					$tbody.append("<tr><td align=\"center\">"+data[item].longDestinationName+"</td><td align=\"center\">"+data[item].name+"</td><td align=\"center\">"+
					"<img src=\""+data[item].imageUrl+"\"/></td><td align=\"justify\">"+data[item].description+
							"</td><td align=\"center\">"+data[item].baseRate+" "+data[item].taxesAndFees+" "+data[item].currency+
							"</td><td align=\"center\">"+data[item].promotionDescription+"</td><td align=\"center\">"+data[item].starRating+"</td></tr>");
				}
			}
			
		}
 
		</script>
    </head> 
    <body>
    
</body>
</html>

