<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Tags añadidas para jsp en las librerias jstl, 
el prefijo "c" es para tagas de tipo "core"  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!-- Recorrer array que llega del servlet controller 
	del servidor -->
	<c:forEach var="tmpProduct" items="${product_list}">
		${tmpProduct}<br>
	</c:forEach>

</body>
</html>