<%--
  Created by IntelliJ IDEA.
  User: Anis
  Date: 24/05/2016
  Time: 08:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Step2: Display</title>
    <jsp:useBean id="myUser" scope="session" class="step2.model.UserModelBean"/>
    <jsp:setProperty name="myUser" property="surname"/>
    <jsp:setProperty name="myUser" property="lastname"/>
    <jsp:setProperty name="myUser" property="age"/>
    <jsp:setProperty name="myUser" property="login"/>
    <jsp:setProperty name="myUser" property="pwd"/>
</head>
<body>
// TO DO Afficher l’ensemble des propriétés de l’utilisateur courant
</body>
</html>