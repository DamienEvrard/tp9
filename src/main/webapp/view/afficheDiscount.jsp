<%-- 
    Document   : afficheDiscount
    Created on : 5 nov. 2019, 13:49:19
    Author     : damie
--%>
<!– JSP --!>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>		
        <h1>Edition des taux de remise</h1>
        <form method='post'>
                Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br/>
                Taux : <input name="taux" type="number" step="0.1" min="0.0" max="99.9" size="5"><br/>
                <input type="hidden" name="action" value="ADD">
                <input type="submit" value="Ajouter">
        </form>
        
        <table border="1">
            <tr><td>Code</td><td>Taux</td><td>Action</td></tr>
            <c:forEach var="dc" items="${Discount}">
                <tr>
                    <form method='post'>
                        <td>"${dc.discount_code}"</td>
                        <td>"${dc.rate}"</td>
                        <td><input type="hidden" name="action" value="DEL">
                            <input type="hidden" name="DC" value="${dc.discount_code}">
                            <input type="submit" name="delete" value="Delete"/></td>
                    </form>
                </tr>
            </c:forEach>
            
        </table>
        
    </body>
</html>
