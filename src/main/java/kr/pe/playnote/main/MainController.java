package kr.pe.playnote.main;

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
import kr.pe.playnote.com.dto.MemberDto;
import kr.pe.playnote.com.service.MemberService;
import kr.pe.playnote.main.dto.BoardDto;
import kr.pe.playnote.main.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@Autowired
    private BoardService boardService;

	@Autowired
    private MemberService memberService;
	
    @Autowired SessionLocaleResolver localeResolver; 
    @Autowired MessageSource messageSource;
	
    

    /** 
     * 회원저장
     * 2021-04-13
     * 이응규
     * 
     */
	@RequestMapping(value = "/main/memberSave", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberSave(Locale locale, Model model, HttpServletRequest request, MemberDto dto) {
		
		String email      = dto.getEmail();
		String password = dto.getPassword();
		String username = dto.getUsername();
		
		String email1 = request.getParameter("email");
		logger.info("email["+email+"]  password["+password+"]  username["+username+"]  ");
		
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("EMAIL", email);
		paramMap.put("EMAIL", email);
		paramMap.put("EMAIL", email);
		
		int result = memberService.memberSave(dto);
		String msgCode = "";
		String msgContent = "";
		
		if( result == 1) {
			
			msgCode = "SUCCESS";
			MemberDto memberDto = memberService.detail(paramMap);
			HttpSession session = request.getSession(true);
			session.setAttribute(Code.SESSION_INFO, memberDto);
			
		
		}else {
            msgCode = "FAIL";
            msgContent = messageSource.getMessage("mag_004", null, "default text", locale);
        }
		
        JSONObject jsonObject1 = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
        
        
        finalJsonObject1.put("msgCode", msgCode);
        finalJsonObject1.put("msgContent", msgContent);
        
        String json = finalJsonObject1.toString();

        System.out.println(json);
	    request.setAttribute("data", json);
		return "comm/json";
		
	}
    
    /** 
     * 이메일 중복 검사
     * 2021-04-12
     * 이응규
     * 
     */
	@RequestMapping(value = "/main/emailDuplicateCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public String emailDuplicateCheck(Locale locale, Model model, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		logger.info("email["+email+"]  ");
		
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("EMAIL", email);
		
		MemberDto dto = memberService.emailDuplicateCheck(paramMap);
		String msgCode = "";
		String msgContent = "";
		
		if( dto != null) {
			if("N".equals(dto.getEmailChk()) ) {
				msgCode = "SUCCESS";
				MemberDto memberDto = memberService.detail(paramMap);
				HttpSession session = request.getSession(true);
				session.setAttribute(Code.SESSION_INFO, memberDto);
				
			}else {
	            msgCode = "FAIL";
	            msgContent = messageSource.getMessage("mag_004", null, "default text", locale);
			}
		}else {
			msgCode = "FAIL";
			msgContent = messageSource.getMessage("mag_004", null, "default text", locale);
		}
		
        JSONObject jsonObject1 = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
        
        
        finalJsonObject1.put("msgCode", msgCode);
        finalJsonObject1.put("msgContent", msgContent);
        
        String json = finalJsonObject1.toString();

        System.out.println(json);
	    request.setAttribute("data", json);
		return "comm/json";
		
	}
	
    /** 
     * 회원가입 화면
     * 2021-04-03
     * 이응규
     * 
     */
	@RequestMapping(value = "/main/registerForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String registerForm(Locale locale, Model model,HttpServletRequest request) {
		
		return "main/registerForm";
		
	}
	
	
	/**
	 * 홈화면
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(Locale locale, Model model,HttpServletRequest request) {
	    
	    logger.info("home locale.toString {}.", locale.toString()); // localeResolver �κ��� Locale �� ����� ���ϴ�.
	    HttpSession session = request.getSession();
	    String language = locale.getLanguage();
	    
	    session.setAttribute("language", language);
	    
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("BOARD_CODE", Code.BOARD_NOTICE);
		
		List<BoardDto> boardList = boardService.getBoardList(paramMap);
        model.addAttribute("noticeList", boardList);
        
        HashMap<String, Object> userMap =  new HashMap<String, Object>();
        userMap.put("BOARD_CODE", Code.BOARD_USER);
        List<BoardDto> userList = boardService.getBoardList(userMap);
        model.addAttribute("userList", userList);
        
		return "index";
	}

	
	/** 
    * 비밀번호 암호화 테스트
    * 2021-04-03
    * 이응규
    * 
    */
   @RequestMapping(value = "/main/passwordTest", method = {RequestMethod.GET, RequestMethod.POST})
   public String passwordTest(Locale locale, Model model,HttpServletRequest request) {
       
//	   String raw = "1111";
//	   MessageDigest md = MessageDigest.getInstance("SHA-256");
//	   md.update(raw.getBytes());
//	   String hex = String.format("%064x", new BigInteger(1, md.digest()));
//	   
//       logger.info("Welcome i18n! The client locale is {}.", locale); // localeResolver �κ��� Locale �� ����� ���ϴ�. 
//       logger.info("locale.toString {}.", locale.toString()); // localeResolver �κ��� Locale �� ����� ���ϴ�.
//       logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
//       logger.info("site.title : {}", messageSource.getMessage("site.title", null, "default text", locale));
//       logger.info("site.count : {}", messageSource.getMessage("site.count", new String[] {"ù��°"}, "default text", locale));
//       logger.info("not.exist : {}", messageSource.getMessage("not.exist", null, "default text", locale)); //logger.info("not.exist �⺻�� ���� : {}", messageSource.getMessage("not.exist", null, locale)); 
//        
//       model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale)); 
       return "index";

   }
	

   /** 
   * 언어 바꾸기
   * 2021-04-03
   * 이응규
   * 
   */
    @RequestMapping(value = "/main/messageChange", method = {RequestMethod.GET, RequestMethod.POST})
    public String messageChange(Locale locale, Model model,HttpServletRequest request) {
        
        logger.info("Welcome i18n! The client locale is {}.", locale);  
        logger.info("locale.toString {}.", locale.toString());
        logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
        logger.info("site.title : {}", messageSource.getMessage("site.title", null, "default text", locale));
        logger.info("site.count : {}", messageSource.getMessage("site.count", new String[] {"ù��°"}, "default text", locale));
        logger.info("not.exist : {}", messageSource.getMessage("not.exist", null, "default text", locale)); 
         
        model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale)); 
        return "redirect:/";

    }
    
	/**
	 * 게시판
	 */
	@RequestMapping(value = "/main/boardNotice", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardNotice(Locale locale, Model model,HttpServletRequest request) {
		
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
        pagemaker.setTotalcount(totalCount);
        pagemaker.setPagenum(cpagenum-1);
        pagemaker.setContentnum(Code.PAGE_LIMIT_10);
        pagemaker.setCurrentblock(cpagenum);
        pagemaker.setLastblock(pagemaker.getTotalcount());
        
        pagemaker.prevnext(cpagenum);
        pagemaker.setStartPage(pagemaker.getCurrentblock());
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        
		
		paramMap.put("PAGE_NUM", pagemaker.getPagenum()*10);
		paramMap.put("CONTENT_NUM", pagemaker.getContentnum());
		
		System.out.println("==== PAGE_NUM ===>"+pagemaker.getPagenum()*10);
		System.out.println("==== CONTENT_NUM ===>"+pagemaker.getContentnum());
		
		List<BoardDto> boardList = boardService.getPageList(paramMap);
		
        model.addAttribute("noticeList", boardList);
        model.addAttribute("page", pagemaker);
		return "main/boardNotice";
		
	}
	
	/**
	 * 게시판 상세
	 *  /main/boardNoticeDetail?uuid="+uuid;
	 */
	@RequestMapping(value = "/main/boardNoticeDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardNoticeDetail(Locale locale, Model model,HttpServletRequest request) {
		
		String uuid = request.getParameter("uuid");
		
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("UUID", uuid);
		
		BoardDto dto = boardService.getNoticeDetail(paramMap);
		
        model.addAttribute("dto", dto);

		return "main/boardNoticeDetail";
		
	}
	
	/**
	 * �Խ��� 
	 */
	@RequestMapping(value = "/main/boardUser", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardUser(Locale locale, Model model) {
		

        HashMap<String, Object> userMap =  new HashMap<String, Object>();
        userMap.put("BOARD_CODE", Code.BOARD_USER);
        userMap.put("PAGE_LIMIT", Code.PAGE_LIMIT_10);
		
        List<BoardDto> userList = boardService.getBoardList(userMap);
        model.addAttribute("userList", userList);
        
		return "main/boardUser";
		
	}

	@RequestMapping(value = "/mysqlTest", method = RequestMethod.GET)
	public String mysqlTest(Locale locale, Model model) {

		return "mysqlTest";
	}
	
	
}