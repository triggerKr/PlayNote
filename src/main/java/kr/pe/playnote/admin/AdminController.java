package kr.pe.playnote.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.pe.playnote.com.Code;
import kr.pe.playnote.com.PageMaker;
import kr.pe.playnote.com.ServletUtils;
import kr.pe.playnote.com.dto.MemberDto;
import kr.pe.playnote.com.service.MemberService;
import kr.pe.playnote.main.MainController;
import kr.pe.playnote.main.dto.BoardDto;
import kr.pe.playnote.main.service.BoardService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    
    @Autowired
    private BoardService boardService;
	@Autowired
    private MemberService memberService;
	
	// 다국어
	@Autowired SessionLocaleResolver localeResolver; 
	@Autowired MessageSource messageSource;

	/**
	 * 로그인폼 화면
	 */
	@RequestMapping(value = "/admin/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Locale locale, Model model, HttpServletRequest request) {
		
	    //return "admin/noticeList";
	    System.out.println("admin/loginForm");
		return "admin/loginForm";
		
	}
	
	/**
	 * 로그인 @ResponseBody  
	 */
	@RequestMapping(value = "/admin/signIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String signIn(Locale locale, Model model, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email["+email+"]   password["+password+"]   ");
		
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("EMAIL", email);
		paramMap.put("PASSWORD", password);
		
		MemberDto dto = memberService.loginChk(paramMap);
		String msgCode = "";
		String msgContent = "";
		
		if( dto != null) {
			if("Y".equals(dto.getEmailChk()) && "Y".equals(dto.getLoginChk())) {
				msgCode = "SUCCESS";
				MemberDto memberDto = memberService.detail(paramMap);
				HttpSession session = request.getSession(true);
				session.setAttribute(Code.SESSION_INFO, memberDto);
				
			}else if("Y".equals(dto.getEmailChk()) && "N".equals(dto.getLoginChk())) {
			    msgCode = "FAIL";
			    msgContent =  messageSource.getMessage("mag_001", null, "default text", locale);
			}else {
	            msgCode = "FAIL";
	            msgContent = messageSource.getMessage("mag_002", null, "default text", locale);
			}
		}else {
			msgCode = "FAIL";
			msgContent = messageSource.getMessage("mag_002", null, "default text", locale);
		}
		
		HashMap<String, Object> myHashMap1 = new HashMap<String, Object>();
        JSONObject jsonObject1 = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
        
        
        finalJsonObject1.put("msgCode", msgCode);
        finalJsonObject1.put("msgContent", msgContent);
        
        System.out.println(finalJsonObject1);
        
        String totalPages = "5";
	    //String json = "{\"msgCode\" :" + msgCode + ",\"totalPages\" :" + totalPages + "}";
        //String json = "{\"msgCode\" :" + msgCode + ",\"msgContent\" :" + msgContent +",\"totalPages\" :" + totalPages + "}";
        String json = finalJsonObject1.toString();

        System.out.println(json);
	    request.setAttribute("data", json);
		return "comm/json";
		
	}
	

    /**
     * 공시사항 리스트
     */
    @RequestMapping(value = "/admin/noticeList", method = {RequestMethod.GET, RequestMethod.POST})
    public String noticeList(Locale locale, Model model, HttpServletRequest request) {
        
        if( !ServletUtils.aliveSession(request)){ 
            return "index";
        }
        
        
        HashMap<String, Object> paramMap =  new HashMap<String, Object>();
        paramMap.put("BOARD_CODE", Code.BOARD_NOTICE);
        paramMap.put("PAGE_LIMIT", Code.PAGE_LIMIT_10);
        
        List<BoardDto> totalCountList = boardService.getTotalCount(paramMap);
        int totalCount = 0;
        if( totalCountList != null) {
            BoardDto dto = new BoardDto();
            for(int i =0; i <totalCountList.size(); i++) {
                dto = totalCountList.get(i);
                totalCount = dto.getTotalCount();
            }
        }
        
        PageMaker pagemaker = new PageMaker();
        // Paging Calculation
        int cpagenum = 1;        
        
        if (request.getParameter("pagenum") != null)
            cpagenum = Integer.parseInt(request.getParameter("pagenum"));
                /*---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------*/
        pagemaker.setTotalcount(totalCount);//전체 게시글 개수 지정한다
        pagemaker.setPagenum(cpagenum-1);//현재 페이지를 페이지 객체에 다시 지정해준다//몇번 페이지인지 PageMaker에 세팅한다
        pagemaker.setContentnum(Code.PAGE_LIMIT_10);//한 페이지에 몇개씩 보여줄지 세팅한다
        pagemaker.setCurrentblock(cpagenum);//현재 페이지블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
        pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다
        /*---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------*/
        pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표 나타낼지 결정한다
        pagemaker.setStartPage(pagemaker.getCurrentblock());//시작페이지 번호를 현재 페이지 블록으로 정한다
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //현재 블록 번호와 마지막 블록 번호를 보내서 대조하고 페이지 블록의 마지막 번호를 지정한다
        
        
        paramMap.put("PAGE_NUM", pagemaker.getPagenum()*10);
        paramMap.put("CONTENT_NUM", pagemaker.getContentnum());
        
        System.out.println("==== PAGE_NUM ===>"+pagemaker.getPagenum()*10);
        System.out.println("==== CONTENT_NUM ===>"+pagemaker.getContentnum());
        
        List<BoardDto> boardList = boardService.getPageList(paramMap);
        
        model.addAttribute("noticeList", boardList);
        model.addAttribute("page", pagemaker);//페이지 번호 객체 .jsp페이지로 전달
       
        return "admin/noticeList";
        
    }
	@RequestMapping(value = "/admin/message", method = RequestMethod.GET) 
	public String i18n(Locale locale, HttpServletRequest request, Model model) {
	    
	    // RequestMapingHandler로 부터 받은 Locale 객체를 출력해 봅니다. 
	    logger.info("Welcome i18n! The client locale is {}.", locale); // localeResolver 로부터 Locale 을 출력해 봅니다. 
	    logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
	    logger.info("site.title : {}", messageSource.getMessage("site.title", null, "default text", locale));
	    logger.info("site.count : {}", messageSource.getMessage("site.count", new String[] {"첫번째"}, "default text", locale));
	    logger.info("not.exist : {}", messageSource.getMessage("not.exist", null, "default text", locale)); //logger.info("not.exist 기본값 없음 : {}", messageSource.getMessage("not.exist", null, locale)); 
	    // JSP 페이지에서 EL 을 사용해서 arguments 를 넣을 수 있도록 값을 보낸다. 
	    model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale)); 
	    return "admin/message";
	}

}