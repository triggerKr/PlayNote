<%@ page import="java.sql.*, javax.sql.*, java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>연동 테스트</title>
</head>
<body>
<% 

	Class.forName("com.mysql.jdbc.Driver"); 
	String dbURL ="jdbc:mysql://localhost:3306/kbntt?characterEncoding=utf8&amp;useSSL=false";
	String dbID= "kbntt";
    String dbPassword="optech8775";
    
	Connection conn = DriverManager.getConnection(dbURL, dbID , dbPassword);
	
	Statement stmt = conn.createStatement(); 
	ResultSet rs = stmt.executeQuery("select * from  BOARD "); 

	while (rs.next()) { 
		String userId = rs.getString("USER_ID");
        System.out.println("== userId["+userId+"] ==");
	} 
	rs.close();
	stmt.close();
	conn.close();
%>
</body>
</html>