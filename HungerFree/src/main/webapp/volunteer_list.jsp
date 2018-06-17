<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<br><div style="text-align:left">
<a href="controller?rType=logout" style="color:yellow">Log out</a></div>
<br><br><br><br>

<body style="text-align:center" text=yellow>
<table align="center">
	<th>Name</th><th>Location</th><th>Email</th><th>MobileNo</th>
	<c:forEach items="${volDTO}" var="vol">
		<tr style="color:white">
			<td>${vol.name}</td>
			<td>${vol.location}</td>
			<td>${vol.email}</td>
			<td>${vol.mobile}</td>
		</tr>
	</c:forEach>
</table>




</body>