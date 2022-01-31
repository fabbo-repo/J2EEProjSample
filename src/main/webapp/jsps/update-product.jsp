<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Insertion</title>
</head>
<body>
	<h1 style="text-align: center">Actualizar Producto:</h1>
	<!-- En form, en el campo action va el nombre del servlet segun la web
	o mapping configurado, en este caso el ProductController es products -->
	<form name="form1" method="get" action="products">
		<input type="hidden" name="instruction" value="productUpdate">
		<input type="hidden" name="cod_product" value="${prod.codProduct}">
		<table width="50%" border="0">
			<!-- <tr>
				<td width="27%">Código Artículo:</td>
				<td width="73%">
					<label for="cod_product"></label>
					<input type="text" name="cod_product" id="cod_product">
				</td>
			</tr> -->
			<tr>
				<td>Nombre Artículo:</td>
				<td>
					<label for="product_name"></label>
					<input type="text" name="product_name" id="product_name"
						value="${prod.productName}">
				</td>
			</tr>
			<tr>
				<td>Seccion:</td>
				<td>
					<label for="section"></label>
					<input type="text" name="section" id="section"
						value="${prod.section}">
				</td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td>
					<label for="price"></label>
					<input type="text" name="price" id="price"
						value="${prod.price}">
				</td>
			</tr>
			<tr>
				<td>Fecha Origen:</td>
				<td>
					<label for="origin_date"></label>
					<input type="text" name="origin_date" id="origin_date"
						value="${prod.originDate}">
				</td>
			</tr>
			<tr>
				<td>Pais Origen:</td>
				<td>
					<label for="origin_country"></label>
					<input type="text" name="origin_country" id="origin_country"
						value="${prod.originCountry}">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="send" id="send" value="Enviar">
				</td>
				<td>
					<input type="reset" name="erase" id="erase" value="Borrar">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>