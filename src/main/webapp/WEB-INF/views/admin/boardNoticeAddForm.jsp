<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.util.Locale" %>
<%@ page import="kr.pe.playnote.com.dto.MemberDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String language = (String)session.getAttribute("language");
    MemberDto sessionInfo = (MemberDto)session.getAttribute("SESSION_INFO");
    
    String uuid = "";
    String username = "";
    String email = "";
    if( sessionInfo != null){ 
    	uuid = (String)sessionInfo.getUuid();
    	username = (String)sessionInfo.getUsername();
    	email = (String)sessionInfo.getEmail();
    }
    
%>
<c:set  var="language"  value="<%=language %>" scope="page" />
<c:set  var="username"  value="<%=username %>" scope="page" />
<c:set  var="email"  value="<%=email %>" scope="page" />


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
    <script src="${pageContext.request.contextPath}/resources/js/comm.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
    
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />  
    <script type="text/javascript">
	    var uf = '';
	    function sw_file_add(size, ext)
	    {
	        eval('sw_file_add_form' + uf).innerHTML += "<input type=file name=attach_file"+(uf+1)+" id=attach_file"+(uf+1)+" onchange='checkFile(this,"+(uf+1)+")' size='" + size + "' " + ext + ">"
	                                                +"<div id='sw_file_add_form"+(uf+1)+"'></div>";
	        uf++;
	    }
	    function checkFile(el,index )
	    {
	    	// files 로 해당 파일 정보 얻기.
	    	var file = el.files;
	    	var fileSize = inputNumberWithComma(file[0].size);
	    	$("#sw_file_add_form"+index+"").text("file size: "+fileSize+"byte");
	    	
	    	// 사이즈체크
	        var maxSize  = 10 * 1024 * 1024    //10MB
	        
	    	// file[0].size 는 파일 용량 정보입니다.
	    	if(file[0].size > maxSize){
	    		addModal("파일사이즈 : "+ file[0].size +", 최대파일사이즈 : "+maxSize);
	    	}
	    }
	    
        function save() {			
        	var title = $("#title").val();
        	var content = $("#content").val();
        	console.log("==== title.length ==>"+title.length);
        	console.log("==== content.length ==>"+content.length);
        	/**/
        	if( title.length < 1){
        		var modal = document.getElementById("alertModal");
                modal.style.display = "block";
                $("p").text("제목을 입력하세요");
      		    //document.form.title.focus();
      		}
        	if( content.length < 1){
        		var modal = document.getElementById("alertModal");
                modal.style.display = "block";
                $("p").text("내용을 입력하세요");
                //document.form.content.focus();
      		}
        	
        	var file = document.form.attach_file;
        	console.log("==== file ==>"+file);
        	if (file){
        		if(!LimitAttach()){
        		}
			}
        	
        	console.log("==== 체크로직 성공 ==>");
        	console.log("==== 체크로직 성공 ==>");
        	
		}
        
	    extArray = new Array(".gif", ".jpg", ".png",".bmp",".pdf",".xlsx",".doc"); // 업로드 할 수 있는 파일 확장자를 설정 합니다
		function LimitAttach() {			
	        
			var file = document.form.attach_file;
			
			while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
			ext = file.slice(file.indexOf(".")).toLowerCase();
			for (var i = 0; i < extArray.length; i++) {
			if (extArray[i] == ext) { allowSubmit = true; break; }
			}
			
			if (allowSubmit){
			    return true;
			}else{
			    var modal = document.getElementById("alertModal");
	            modal.style.display = "block";
	            $("p").text("업로드 가능한 파일은  "+ (extArray.join("  ")) + "   입니다\n\n다시 선택 해 주세요");
	            
				return false;
			}
		}

        function close(){
            var modal = document.getElementById("alertModal");
            modal.style.display = "none";
        }

        function addModal(mag){
            var modal = document.getElementById("alertModal");
	        modal.style.display = "block";
	        $("p").text(mag);
        }
        
    </script>
  <body>
        <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
        <jsp:include page="../topMenu.jsp" flush="true">
		     <jsp:param name="language" value="<%= language %>"/>
		</jsp:include>
        <!--/ TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU     TOP MENU         -->
        
    <form name="form" method="post" enctype="multipart/form-data">
        <div class="container-fluid">
          <div class="row">
            <!--/ LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU         -->
            <div class="col-sm-3 col-md-2 sidebar">
              <ul class="nav nav-sidebar">
                <li><a href="javascript:go_it('HOME');">HOME</a></li>
                <li class="active"><a href="javascript:go_it('BOARD_NOTICE');"><spring:message code="notice" text="default text" /><span class="sr-only">(current)</span></a></li>
                <li><a href="javascript:go_it('BOARD_USER');"><spring:message code="community" text="default text" /></a></li>
            </div>
            <!--/ LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU     LEFT MENU         -->
            
            <!--/ BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY     BODY          -->
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
              <h1 class="page-header">Play Note  <img src="${pageContext.request.contextPath}/resources/image/icons-motorcycle-01.png" width="50" height="50" border="1" alt=""></h1>
              
              <h2 class="sub-header"><spring:message code="notice" text="default text" /></h2>
              
              <!-- Modal 창    Modal 창    Modal 창    Modal 창    Modal 창    Modal 창    Modal 창    Modal 창         block display:none display:block -->
			  <div id="alertModal"class="alert alert-warning alert-dismissible fade in" style="display:none;">
			    <a href="javascript:close();" class="close" aria-label="close">&times;</a>
			    <strong>Warning!</strong><p></p>
			  </div>
              
            <!--/ ============================================================================================================================================ -->
            <!--/ <div class="container"> -->
            
            <div class="table-responsive">
		        <input type="hidden" name="pageNum" value="pageNum">
		        <input type="hidden" name="articleNumber" value="article.articleNumber">
		         
		        <table class="table table-striped table-bordered">
		          <thead>
		            <tr>
		              <th width="15%"><spring:message code="writer" text="default text" /></th>
		              <td width="20%"><c:out value="${username}" /></td>
                      <th width="15%"><spring:message code="email" text="default text" /></th>
                      <td width="20%"><c:out value="${email}" /></td>
                      <th width="10%"><spring:message code="hit" text="default text" /></th>
                      <td width="20%">0</td>
		            </tr>
		            <tr>
		              <th style="padding-top: 15px"><spring:message code="title" text="default text" /></th>
		              <td colspan="5"><input type="text" name="title" id="title" value="article.title"
		                    class="form-control" aria-describedby="basic-addon1"></td>
		                    
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td colspan="6">
		                <textarea class="form-control" rows="20" name="content" id="content" ></textarea>
		              </td>
		            </tr>
		            <tr>
		              <th style="padding-top: 15px"><spring:message code="attach.file" text="default text" /></th>
		              <td colspan="5">
		                  <a href="javascript:sw_file_add(100, 'class=input_write');">파일 추가</a>
					      <!-- 여기에 추가가 된다. -->
					      <div id="sw_file_add_form"></div>
		              </td>
		            </tr>
		          </tbody>
		        </table>
		      <div class="btn-group btn-group-sm" role="group" aria-label="...">
		        <input type="button" class="btn btn-default" onclick="save()" value="<spring:message code="save" text="default text" />" >
		        <input type="button" class="btn btn-default" value="<spring:message code="cancel" text="default text" />" onclick="document.location.href='/bbs/content.bbs?articleNumber=articleNumber&pageNum=pageNum'">
		      </div>
		    </div> 
            <!--/ ============================================================================================================================================ -->              
            </div>
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



