<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<% 
    String subMenu = request.getParameter("subMenu");
    System.out.println("leftmenu.jsp subMenu ==>"+subMenu);    

%>
<!-- scope 속성은 선택적이며 page로 기본 설정되어 있다.page, request, session, application -->
<c:set  var="subMenu"  value="<%=subMenu %>" scope="page" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

	$(document).ready(function(){
        var subMenu = '<c:out value="${subMenu}"/>';

        switch (subMenu){
	        case "A1" :
	        	$("#A1").attr('class','active');
	            break;
	        case "A2" :
	        	$("#A2").attr('class','active');
	            break;
	        case "A3" :
	        	$("#A3").attr('class','active');
	            break;
	        case "A4" :
	        	$("#A4").attr('class','active');
	            break;
            case "B1" :
                $("#B1").attr('class','active');
                break;
            case "B2" :
                $("#B2").attr('class','active');
                break;
            case "B3" :
                $("#B3").attr('class','active');
                break;
            case "B4" :
                $("#B4").attr('class','active');
                break;
            case "B5" :
                $("#B5").attr('class','active');
                break;
            case "C1" :
                $("#C1").attr('class','active');
                break;
            case "C2" :
                $("#C2").attr('class','active');
                break;
            case "C3" :
                $("#C3").attr('class','active');
                break;
	            
	        default :
	            
	    } 
	});
</script>
	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
	            <li id="A1" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','A1');">jdk 설치 </a></li>
	            <li id="A2" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','A2');">eclipse 설치</a></li>
	            <li id="A3" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','A3');">Analytics</a></li>
	            <li id="A4" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','A4');">Export</a></li>
	          </ul>
	          <ul class="nav nav-sidebar">
	            <li id="B1" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','B1');">Nav item</a></li>
	            <li id="B2" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','B2');">Nav item again</a></li>
	            <li id="B3" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','B3');">One more nav</a></li>
	            <li id="B4" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','B4');">Another nav item</a></li>
	            <li id="B5" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','B5');">More navigation</a></li>
	          </ul>
	          <ul class="nav nav-sidebar">
	            <li id="C1" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','C1');">Nav item again</a></li>
	            <li id="C2" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','C2');">One more nav</a></li>
	            <li id="C3" class=""><a href="javascript:topMenuGo('JAVA_BASE_MAIN','C3');">Another nav item</a></li>
	          </ul>
	          
	          
	        </div>