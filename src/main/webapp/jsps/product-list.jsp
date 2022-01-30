<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Tags añadidas para jsp en las librerias jstl, 
el prefijo "c" es para tagas de tipo "core"  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products List</title>

<!-- Estilos css: -->
<style type="text/css">
	.cabecera{
		font-size: 1.2em;
		font-weight: bold;
		color: #FFFFFF;
		background-color: #08088A;
	}
	.filas{
		text-align: center;
		background-color: #5882FA;
	}
	table{
		/* Tabla queda flotante a la izquierda,
		lo que se encuentre a continuación ira 
		a la derecha de la tabla */
		float: left;
	}
	#button_cont{
		margin-left: 540px;
	}
</style>

</head>


<body>
	<table>
		<tr>
			<td class="cabecera"> Código Artículo </td>
			<td class="cabecera"> Nombre Artículo </td>
			<td class="cabecera"> Sección </td>
			<td class="cabecera"> Precio </td>
			<td class="cabecera"> Fecha </td>
			<td class="cabecera"> País de Origen </td>
		</tr>
		<c:forEach var="prod" items="${product_list}">
			<tr>
				<!-- Nota: los atributos los obtiene de los metodos
				getters, por ejemplo para getCodProduct el atributo 
				es codProduct, quitando get y el primer caracter en
				minusculas  -->
				<td class="filas">${prod.codProduct}</td>
				<td class="filas">${prod.productName}</td>
				<td class="filas">${prod.section}</td>
				<td class="filas">${prod.price}</td>
				<td class="filas">${prod.originDate}</td>
				<td class="filas">${prod.originCountry}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="button_cont">
		<input type="button" value="Insertar Registro"
			onclick="window.location.href='jsps/product_insert.jsp'"/>
	</div>
</body>
</html>