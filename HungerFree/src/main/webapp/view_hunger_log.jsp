<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div align="left"><a href="controller?rType=logout" style="color:yellow;text-align:left">Logout</a>
</div>

<body style="text-align:center" text=yellow>
	<form action="controller" method="get">
		Select Location : <select name="loc">
			<c:forEach items="${loc}" var="location">
				<option value="${location}">${location}</option>
			</c:forEach>
		</select> <br>
		<br> <input type="submit" value="viewHungers" name="rType" />
	</form>
</body>