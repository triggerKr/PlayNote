<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ page session="true" %>
<%@ page import="java.util.Locale" %>
<% 
    String language = request.getParameter("language");
%>
<!-- scope 속성은 선택적이며 page로 기본 설정되어 있다.page, request, session, application -->
<c:set  var="language"  value="<%=language %>" scope="page" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
	   
        function topMenuGo(url,subMenu){
            switch (url){
                case "HOME" :
                    url = "/home/index";
                    break;
                case "BOARD_NOTICE" :
                    url = "/main/boardNotice?pagenum=1&contentnum=0";
                    break;
                case "BOARD_USER" :
                    url = "/main/boardUser";
                    break;
                case "ADMIN" :
                    url = "/admin/loginForm";
                    break;
                case "ADMIN_NOTICE_LIST" :
                    url = "/admin/noticeList";
                    break;
                case "WEB_FRAME_VUE_MAIN" :
                    url = "/webFrame/vueMain";
                    break;
                case "JAVA_BASE_MAIN" :
                    url = "/java/base?page=page_001&subMenu="+subMenu;
                    break;    
                    
                default :
                    url = "/";
            } 
            document.topform.action  = url;                                                   
            document.topform.method = "POST";
            document.topform.submit();
        }  
        
        function topMenuLanguage(language){
        	var url = "/main/messageChange?lang="+language;
            document.topform.action  = url;                                            
            document.topform.method = "POST";
            document.topform.submit();
        }  
        
    </script>
    <style> 
        #language_btn1{ margin-right:4px; margin-left:3px; margin-top:4px; padding: 10px 2px;}
        #language_btn2{ margin-right:4px; margin-left:3px; margin-top:4px; padding: 10px 2px;}
    </style>

    <style>
        body {
          padding-top: 40px;
          padding-bottom: 40px;
          background-color: #eee;
        }
        .div-signin {
          max-width: 330px;
          padding: 15px;
          margin: 0 auto;
        }
        .div-signin .div-signin-heading,
        .div-signin .checkbox {
          margin-bottom: 10px;
        }
        .div-signin .checkbox {
          font-weight: normal;
        }
        .div-signin .div-control {
          position: relative;
          height: auto;
          -webkit-box-sizing: border-box;
             -moz-box-sizing: border-box;
                  box-sizing: border-box;
          padding: 10px;
          font-size: 16px;
        }
        .div-signin .div-control:focus {
          z-index: 2;
        }
        .div-signin input[type="email"] {
          margin-bottom: -1px;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
        }
        .div-signin input[type="password"] {
          margin-bottom: 10px;
          border-top-left-radius: 0;
          border-top-right-radius: 0;
        }
    </style>
    <form name="topform" method="post">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="javascript:topMenuGo('HOME','');">Play Note</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-left">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">HOME <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:topMenuGo('HOME','');">HOME</a></li>
                <li><a href="javascript:topMenuGo('BOARD_NOTICE','');"><spring:message code="notice" text="default text" /></a></li>
                <li><a href="javascript:topMenuGo('BOARD_USER','');"><spring:message code="community" text="default text" /></a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">JAVA <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:topMenuGo('JAVA_BASE_MAIN','');">BASE</a></li>
                <li><a href="#">GUI</a></li>
                <li><a href="#">JSP</a></li>
              </ul>
            </li>
            <li><a href="#">Android</a></li>
            <li><a href="#">C#</a></li>
            <li><a href="#">Project</a></li>
            
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">WEB FRAME<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:topMenuGo('WEB_FRAME_VUE_MAIN','');">Vue</a></li>
                <li><a href="#">React</a></li>
              </ul>
            </li>
			
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">ADMIN <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:topMenuGo('ADMIN_NOTICE_LIST','');">공지사항</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
	            <c:if test="${language eq 'ko'}">
	                <li><button type="submit"  class="btn .btn-default" id="language_btn1" onclick="topMenuLanguage('en')">ENG</button></li>
	                <li><button type="submit"  class="btn btn-success" id="language_btn2" onclick="topMenuLanguage('ko')">KOR</button></li>
	            </c:if>
	            <c:if test="${language eq 'en'}">
		            <li><button type="submit" class="btn btn-success" id="language_btn1" onclick="topMenuLanguage('en')">ENG</button></li>
		            <li><button type="submit" class="btn .btn-default" id="language_btn2" onclick="topMenuLanguage('ko')">KOR</button></li>
	            </c:if>
            <li><a href="javascript:topMenuGo('ADMIN','');">ADMIN</a></li>
          </ul>
      </div>
    </nav>
    </form>