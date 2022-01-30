<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*, es.main.products.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products List</title>

<style type="text/css">
	.cabecera{
		border-bottom: solid #F00 1px;
	}
</style>

</head>

<%
	// Obtener productos del servlet:
	List<Product> prod_list = (List<Product>) request
								.getAttribute("product_list");
%>

<body>
	<table>
		<tr>
			<td class="cabecera">C�digo Art�culo</td>
			<td class="cabecera">Nombre Art�culo</td>
			<td class="cabecera">Secci�n</td>
			<td class="cabecera">Precio</td>
			<td class="cabecera">Pa�s de Origen</td>
		</tr>
		
		<% for(Product prod : prod_list) { %>
		<tr>
			<td><%= prod.getCodProduct() %></td>
			<td><%= prod.getProductName() %></td>
			<td><%= prod.getSection() %></td>
			<td><%= prod.getPrice() %></td>
			<td><%= prod.getOriginCountry() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>