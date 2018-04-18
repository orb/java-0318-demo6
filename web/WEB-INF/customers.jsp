<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List</title>
    </head>
    <body>
        <h1>Customer List</h1>
        <ul>
            <c:forEach var="customer" items="${customers}">
                <li> <a href="/db/customer?id=${customer.id}">
                        <c:out value="${customer.name}" /> </a>
                    (<c:out value="${customer.email}" />)</li>
                </c:forEach>
        </ul>
    </body>
</html>
