<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


		Find Patient By PID :${pd[0].map.patient_id}
		
		<form action="/helloworld/clinical/pdsearch" method="get">
			<input name="mrNo" type="text">
			<input type="submit" value="Submit">
		</form>
		<table border="2">
			
			<tr>
				<c:forEach var="p" items="${pd}">
					
					<th>Time Stamp</th>
					<th>${p.map.param_label}</th>
					
					
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="p" items="${pd}">
					<td>${p.map.date_time}</td>
					<td>${p.map.param_value}</td>
					
				</c:forEach>
			</tr>
			
		</table>
</body>
</html>