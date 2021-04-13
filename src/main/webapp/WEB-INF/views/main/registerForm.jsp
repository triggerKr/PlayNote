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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css" >
    <link href="${pageContext.request.contextPath}/resources/docs/examples/dashboard/dashboard.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    
    <script src="${pageContext.request.contextPath}/resources/docs/assets/js/ie-emulation-modes-warning.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/common.js"></script>      
    
  </head>

    <script type="text/javascript">
    function go_register(){
    	
    	console.log('go_register');
    	if(!checkValue()){
    		return;
    	}
    	console.log('체크완료');
    	go_checkId();
    }
    
    // 입력값 체크
    function checkValue(){
    	
    	var modal = document.getElementById("alertModal");
        var email = $("#email").val();
        console.log('email['+email+']');
        console.log('email.length['+email.length+']');
        
        // 이메일 체크
        if( email.length < 10){
        	modal.style.display = "block";
            $("p").text("이메일을 확인 하세요.1");
        	return false;
        }
     
        // 이메일 체크
        if(!checkEmail(email)){
            modal.style.display = "block";
            $("p").text("이메일을 확인 하세요.");
            return false;
        }
        
        var pwd = $("#pwd").val();
        var confirmPwd = $("#confirmPwd").val();
        
        console.log("pwd["+pwd+"]   confirmPwd["+confirmPwd+"]   ");
        console.log("pwd.length["+pwd.length+"]    ");
        
        
        // 이메일 체크
        if( (pwd.length < 7) || (pwd.length > 20) ){
            modal.style.display = "block";
            $("p").text("비밀번호의 글자수를 확인 하세요.");
            return false;
        }
     
        // 비밀번호 정합성 체크
        if(!(pwd == confirmPwd)){

            modal.style.display = "block";
            $("p").text("비밀번호를 확인 하세요.");
            return false;
        }
        return true;
    }

    function checkEmail(str){                                                 

        console.log('checkEmail1['+str+']');
        
         var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
         if(!reg_email.test(str)) {                            
              return false;         
         }                            
         else {                       
              return true;         
         }
    }                           

    
    function go_checkId(type){
         
        var email = $("#email").val();
    	
        
        var data = { "email": email };
        console.log(data);
         
        $.ajax({
             type : "POST", //전송방식을 지정한다 (POST,GET)
             url : "/main/emailDuplicateCheck",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
             data: data, 
             async: true,
             dataType : "json",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
             success : function(data) { 
                 console.log("========================================");
                 console.log("==== data ==>"+data);
                 console.log("==== data.msgCode ==>"+data.msgCode);
                 console.log("==== data.msgContent ==>"+data.msgContent);
                 if( data.msgCode == "SUCCESS"){
                     save(); 
                  
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

    function save(){
        
        var email = $("#email").val();
        var password = $("#pwd").val();
        var username = $("#name").val();
        
        var data = { "email": email, "password": password, "username": username };
        
        console.log(data);
         
        $.ajax({
             type : "POST",
             url : "/main/memberSave",
             data: data, 
             async: true,
             dataType : "json",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
             success : function(data) { 
                 console.log("========================================");
                 console.log("==== data ==>"+data);
                 console.log("==== data.msgCode ==>"+data.msgCode);
                 console.log("==== data.msgContent ==>"+data.msgContent);
                 if( data.msgCode == "SUCCESS"){
                     save(); 
                  
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
    
    function success(){
        location.href  = "/";  
    }  
    
    function warningClose(){
    	var modal = document.getElementById("alertModal");
    	modal.style.display = "none";
    }

  </script>
 
 
  <body>
	    
	    <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
	    <jsp:include page="../topMenu.jsp" flush="true"/>
	    <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
	    <form name="form" method="post">
	    <div class="container">
		  <h2>회원가입</h2>
		    <div class="form-group">
		      <label style="color:red">Email:</label>
		      <input type="text" class="form-control" id="email" name="email" placeholder="Enter email" maxlength=100 >
		    </div>
		    <div class="form-group">
		      <label for="pwd"  style="color:red" >Password: 영문대소 문자, 숫자 7~20</label>
		      <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password"  maxlength=20>
		    </div>
		    <div class="form-group">
		      <label for="pwd" style="color:red" >Confirm Password:</label>
		      <input type="password" class="form-control" id="confirmPwd" name="confirmPwd" placeholder="Enter password"   maxlength=20>
		    </div>
		    <div class="form-group">
		      <label style="color:red">Name:</label>
		      <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" >
		    </div>
			<button  type="button" class="btn btn-lg btn-primary btn-block" onclick="javascript:go_register();">Submit</button>
				    
	        <!-- Modal 창 -->
	        <div id="alertModal" class="alert alert-warning alert-dismissible fade in" style="display:none;">
	          <a href="javascript:warningClose();" class="close" aria-label="close">&times;</a>
	          <strong>Warning!</strong><p></p>
	        </div>
	    </div>
        </form>
  </body>
  
</html>



