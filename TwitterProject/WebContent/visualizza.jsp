<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="twitter4j.*" %>
<%  Twitter t=(Twitter)request.getSession().getAttribute("twitter");
    User u=t.showUser(t.getId());
    long lista = -1;
    PagableResponseList<User> followers = null;  
%>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="stileProgetto.css" rel="stylesheet" type="text/css" />
<title>Logged In</title>
</head>
<body>
Followers:
<form action=GET method="/TwitterProject/calcolaFollowers">
<ul>
    <%
    while(lista!=0)
    {
      followers=t.getFollowersList(t.getId(), lista);
      
      for(int i=0; i<followers.size(); i++){%>
     <li> 
          <img src="<%=followers.get(i).getProfileImageURL() %>" /> <%=followers.get(i).getScreenName() %>
          <input type="checkbox" name="follower" />
     </li>
    <%} 
       lista=followers.getNextCursor(); 
     }
    %> 	
</ul>
<input type="submit" value="Get Followers" />
</form>
</body>
</html>