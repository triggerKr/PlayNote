<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page session="true" %>
<%@ page import="java.util.Locale" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/image/icons-motorcycle-01.png">

    <title>Play Note</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css" >
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/docs/examples/dashboard/dashboard.css" rel="stylesheet">
    <!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dist/css/signin.css"> -->
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/resources/docs/assets/js/ie-emulation-modes-warning.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>
    
  
    
  </head>

    <script type="text/javascript">

    function go_it(type){
         
         var email = $("#inputEmail").val();
         var password = $("#inputPassword").val();
         var data = { "email": email, "password": password };
         console.log(data);
         
         $.ajax({
             type : "POST", //전송방식을 지정한다 (POST,GET)
             url : "/admin/signIn",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
             data: data, 
             async: true,
             dataType : "json",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
             success : function(data) { 
                 console.log("========================================");
                 console.log("==== data ==>"+data);
                 console.log("==== data.msgCode ==>"+data.msgCode);
                 console.log("==== data.msgContent ==>"+data.msgContent);
                 if( data.msgCode == "SUCCESS"){
                     success(); 
                  
                     var modal = document.getElementById("alertModal");
                     modal.style.display = "none";
                 }else{
                    
                     var modal = document.getElementById("alertModal");
                     modal.style.display = "block";
                     $("p").text(data.msgContent);
                 }
              }, 
              error: function(jqXHR, textStatus, errorThrown) { 
                  var modal = document.getElementById("alertModal");
                  modal.style.display = "block";
                  $("p").text("ajax  error");
                  console.log("ajax  error");  
              }                  
         });
    }  
        
        
    email pwd ConfirmPwd name
  </script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
 
 
  <body>
      <form name="form" method="post">
		    <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
		    <jsp:include page="../topMenu.jsp" flush="true"/>
		    <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
		    <div class="container">
			  <h2>Stacked form</h2>
			    <div class="form-group">
			      <label for="email" >Email:</label>
			      <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" >
			    </div>
			    <div class="form-group">
			      <label for="pwd">Password:</label>
			      <input type="password" class="form-control" id="pwd" name="pswd" placeholder="Enter password" >
			    </div>
			    <div class="form-group">
			      <label for="pwd">Confirm Password:</label>
			      <input type="password" class="form-control" id="ConfirmPwd" name="ConfirmPwd" placeholder="Enter password" >
			    </div>
			    <div class="form-group">
			      <label for="email">Name:</label>
			      <input type="email" class="form-control" id="name" name="name" placeholder="Enter Name" >
			    </div>
			    <button type="submit" class="btn btn-primary" onclick="javascript:go_it('JOIN_MEMBER');">Submit</button>
		    </div>
		</form>    
    <!-- /container -->
    
  </body>
  
</html>



