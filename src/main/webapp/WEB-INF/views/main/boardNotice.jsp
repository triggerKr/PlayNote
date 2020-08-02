<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/docs/examples/dashboard/dashboard.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/resources/docs/assets/js/ie-emulation-modes-warning.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
	  
	<script type="text/javascript">
	
	    function go_it(url){
	        switch (url){
	            case "HOME" :
	                url = "/";
	                break;
	            case "BOARD_NOTICE" :
	                url = "/main/boardNotice?pagenum=1&contentnum=0";
	                break;
	            case "BOARD_USER" :
	                url = "/main/boardUser";
	                break;
	            default :
	                url = "/";
	        }
	        
	        document.form.action  = url;                                                   
	        document.form.method = "POST";
	        document.form.submit();
	    }  
	    
	    function page(idx){
	        var pagenum = idx;              
	        location.href="${pageContext.request.contextPath}/main/boardNotice?pagenum="+pagenum;    
	    }
	    

        function goDetail(uuid){            
            
            url = "${pageContext.request.contextPath}/main/boardNoticeDetail?uuid="+uuid; 
            
            document.form.action  = url;                                                   
            document.form.method = "POST";
            document.form.submit();
        
        }
	</script>
  <body>
    <form name="form" method="post">
        <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
	    <jsp:include page="../topMenu.jsp" flush="true"/>
	    <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
	    
	    <div class="container-fluid">
	      <div class="row">
	        <!--/ LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU         -->
	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
	            <li><a href="javascript:go_it('HOME');">HOME</a></li>
	            <li class="active"><a href="javascript:go_it('BOARD_NOTICE');">공지사항<span class="sr-only">(current)</span></a></li>
                <li><a href="javascript:go_it('BOARD_USER');">게시판</a></li>
	        </div>
	        <!--/ LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU         -->
	        
	        <!--/ BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY          -->
	        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	          <h1 class="page-header">Play Note  <img src="${pageContext.request.contextPath}/resources/image/icons-motorcycle-01.png" width="50" height="50" border="1" alt=""></h1>
	          
	          <h2 class="sub-header">공지사항</h2>
	          <div class="table-responsive">
	            <table class="table table-striped">
	              <thead>
	                <tr>
                      <th><spring:message code="number" text="default text" /></th>
                      <th><spring:message code="writer" text="default text" /></th>
                      <th><spring:message code="title" text="default text" /></th>
                      <th><spring:message code="hit" text="default text" /></th>
                      <th><spring:message code="create_date" text="default text" /></th>
	                </tr>
	              </thead>
	              <tbody>
	                <c:forEach items="${noticeList}" var="dto">
						<tr>
							<td>${dto.rnum}</td>
							<td>${dto.nickName}</td>
							<td><a href="javascript:goDetail('${dto.uuid}');">${dto.subject}</a></td>
							<td>${dto.hits}</td>
							<td>${dto.createDatetime}</td>
						</tr>
					</c:forEach>
	              </tbody>
	            </table>
	          </div>
	        </div>
	        
          <nav>
            <ul class="pager">
              <!-- 왼쪽 화살표 -->
	          <c:if test="${page.prev}">
	              <li><a href="javascript:page(${page.getStartPage()-1});">Previous</a></li>
	          </c:if>
	          <!-- 페이지 숫자 표시 -->
              <c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
                  <c:if test="${page.getPagenum()+1 == idx}">
                      <li><a href="javascript:page(${idx});"><b>[ ${idx} ]<b></a></li>
                  </c:if>
                  <c:if test="${page.getPagenum()+1 != idx}">
                      <li><a href="javascript:page(${idx});">${idx}</a></li>
                  </c:if>
              </c:forEach>
              
            <!-- 오른쪽 화살표 -->
            <c:if test="${page.next}">
                <li><a href="javascript:page(${page.getEndPage()+1});">Next</a></li>
            </c:if>
            </ul>
          </nav>
	        <!--/ BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY          -->
	        
	      </div>
	    </div>
    </form>
    <!--  ============================= Bootstrap core JavaScript  ============================= -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/docs/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/docs/assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/resources/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>



