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
	
	// �ٱ���
	@Autowired SessionLocaleResolver localeResolver; 
	@Autowired MessageSource messageSource;

	/**
	 * �α����� ȭ��
	 */
	@RequestMapping(value = "/admin/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Locale locale, Model model, HttpServletRequest request) {
		
	    //return "admin/noticeList";
	    System.out.println("admin/loginForm");
		return "admin/loginForm";
		
	}
	
	/**
	 * �α��� @ResponseBody  
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
        JSONObject jsonObject1 = new JSONObject(); // �߰�ȣ�� �� �Ӽ� ���� { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // ���ȣ ���� [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // �߰�ȣ�� ���� ���ȣ�� �̸��� ������ { "c" : [{  "a" : "1", "b" : "2" }] }
        
        
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
     * ���û��� ����Ʈ
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
                /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
        pagemaker.setTotalcount(totalCount);//��ü �Խñ� ���� �����Ѵ�
        pagemaker.setPagenum(cpagenum-1);//���� �������� ������ ��ü�� �ٽ� �������ش�//��� ���������� PageMaker�� �����Ѵ�
        pagemaker.setContentnum(Code.PAGE_LIMIT_10);//�� �������� ��� �������� �����Ѵ�
        pagemaker.setCurrentblock(cpagenum);//���� ����������� ������� ���� ������ ��ȣ�� ���ؼ� �����Ѵ�
        pagemaker.setLastblock(pagemaker.getTotalcount());//������ ��� ��ȣ�� ��ü �Խñ� ���� ���ؼ� ���Ѵ�
        /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
        pagemaker.prevnext(cpagenum);//���� ������ ��ȣ�� ȭ��ǥ ��Ÿ���� �����Ѵ�
        pagemaker.setStartPage(pagemaker.getCurrentblock());//���������� ��ȣ�� ���� ������ ������� ���Ѵ�
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
        //���� ��� ��ȣ�� ������ ��� ��ȣ�� ������ �����ϰ� ������ ����� ������ ��ȣ�� �����Ѵ�
        
        
        paramMap.put("PAGE_NUM", pagemaker.getPagenum()*10);
        paramMap.put("CONTENT_NUM", pagemaker.getContentnum());
        
        System.out.println("==== PAGE_NUM ===>"+pagemaker.getPagenum()*10);
        System.out.println("==== CONTENT_NUM ===>"+pagemaker.getContentnum());
        
        List<BoardDto> boardList = boardService.getPageList(paramMap);
        
        model.addAttribute("noticeList", boardList);
        model.addAttribute("page", pagemaker);//������ ��ȣ ��ü .jsp�������� ����
       
        return "admin/noticeList";
        
    }
	@RequestMapping(value = "/admin/message", method = RequestMethod.GET) 
	public String i18n(Locale locale, HttpServletRequest request, Model model) {
	    
	    // RequestMapingHandler�� ���� ���� Locale ��ü�� ����� ���ϴ�. 
	    logger.info("Welcome i18n! The client locale is {}.", locale); // localeResolver �κ��� Locale �� ����� ���ϴ�. 
	    logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
	    logger.info("site.title : {}", messageSource.getMessage("site.title", null, "default text", locale));
	    logger.info("site.count : {}", messageSource.getMessage("site.count", new String[] {"ù��°"}, "default text", locale));
	    logger.info("not.exist : {}", messageSource.getMessage("not.exist", null, "default text", locale)); //logger.info("not.exist �⺻�� ���� : {}", messageSource.getMessage("not.exist", null, locale)); 
	    // JSP ���������� EL �� ����ؼ� arguments �� ���� �� �ֵ��� ���� ������. 
	    model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale)); 
	    return "admin/message";
	}

}