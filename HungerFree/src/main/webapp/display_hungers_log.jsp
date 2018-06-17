<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div align="left"><a href="controller?rType=logout" style="color:yellow;text-align:left">Logout</a>
</div>

<body style="text-align:center" text=yellow>
<br><br><br><br>
<table align="center">
	<th>Type</th><th>Location</th><th>People count</th><th>Email</th><th>MobileNo</th>
	<c:forEach items="${hplaces}" var="hp">
		<tr style="color:white">
			<td>${hp.type}</td>
			<td>${hp.location}</td>
			<td>${hp.peopleCount}</td>
			<td>${hp.email}</td>
			<td>${hp.mobile}</td>
		</tr>
	</c:forEach>
</table>




</body>