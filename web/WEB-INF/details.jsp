<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Details</title>
    </head>
    <body>
        <h2><c:out value="${customer.name}"/></h2>
        <h3><c:out value="${customer.email}"/></h3>

        
        <a href="/customers">HOME</a>
    </body>
</html>
