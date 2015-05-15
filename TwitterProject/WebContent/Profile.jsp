<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="twitter4j.Twitter" %>
<%@ page import="twitter4j.User" %>
<%  
    Twitter t=(Twitter)request.getSession().getAttribute("twitter");
    User u=t.showUser(t.getId());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="stileProgetto.css" rel="stylesheet" type="text/css" />
<title>Profile of <%=t.getScreenName() %></title>
</head>
<body>
<% if(t != null){%>
<p> Twitter ID: <%=t.getId() %> </p>
<p> Twitter Screen Name: <%=t.getScreenName() %> </p>
<img src="<%=u.getProfileImageURL() %>" />
<form method=GET action="/TwitterProject/visualizza.jsp">
<input type="submit" value="Get Friends" />
</form>
<% } %>
</body>
</html>