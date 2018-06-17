<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<a href="controller?rType=logout" style="color:yellow;text-align:left">Logout</a>


<table align="center" style="width: 1200;color:yellow">
<font face="calibri" size="4" color="white">
<h3 align="center">
Today's Donors List</h3></font>
	<th>Name</th><th>Address</th><th>FoodItems</th><th>Quantity</th><th>Prepared Time</th><th>MobileNo</th><br>
	<br>
	<c:forEach items="${listDonors}" var="ld">
		<tr style="color:white">
			<td>${ld.name}</td>
			<td>${ld.address}</td>
			<td>${ld.foodItems}</td>
			<td>${ld.qty}</td>
			<td>${ld.preparedTime}</td>
			<td>${ld.mobile}</td>
		</tr>
	</c:forEach>
</table>
