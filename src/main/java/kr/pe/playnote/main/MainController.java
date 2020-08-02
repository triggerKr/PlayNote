package kr.pe.playnote.main;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    // �ٱ���
    @Autowired SessionLocaleResolver localeResolver; 
    @Autowired MessageSource messageSource;
	
	/**
	 * ����ȭ��
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
     * ����
     */
    @RequestMapping(value = "/main/messageChange", method = {RequestMethod.GET, RequestMethod.POST})
    public String messageChange(Locale locale, Model model,HttpServletRequest request) {
        
        // RequestMapingHandler�� ���� ���� Locale ��ü�� ����� ���ϴ�. 
        logger.info("Welcome i18n! The client locale is {}.", locale); // localeResolver �κ��� Locale �� ����� ���ϴ�. 
        logger.info("locale.toString {}.", locale.toString()); // localeResolver �κ��� Locale �� ����� ���ϴ�.
        logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
        logger.info("site.title : {}", messageSource.getMessage("site.title", null, "default text", locale));
        logger.info("site.count : {}", messageSource.getMessage("site.count", new String[] {"ù��°"}, "default text", locale));
        logger.info("not.exist : {}", messageSource.getMessage("not.exist", null, "default text", locale)); //logger.info("not.exist �⺻�� ���� : {}", messageSource.getMessage("not.exist", null, locale)); 
        // JSP ���������� EL �� ����ؼ� arguments �� ���� �� �ֵ��� ���� ������. 
        model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale)); 
        return "redirect:/";

    }
	/**
	 * ��������
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
		return "main/boardNotice";
		
	}
	
	/**
	 * �������� ��
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