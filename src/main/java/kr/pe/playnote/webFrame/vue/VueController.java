package kr.pe.playnote.webFrame.vue;

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
public class VueController {
	
	private static final Logger logger = LoggerFactory.getLogger(VueController.class);
	
	
	@Autowired
    private BoardService boardService;
    // �ٱ���
    @Autowired SessionLocaleResolver localeResolver; 
    @Autowired MessageSource messageSource;
	
	/**
	 * 메인화면 
	 */
	@RequestMapping(value = "/webFrame/vueMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String vueMain(Locale locale, Model model,HttpServletRequest request) {
	    
	    logger.info("home locale.toString {}.", locale.toString()); 
	    HttpSession session = request.getSession();
	    String language = locale.getLanguage();
	    
	    session.setAttribute("language", language);
	    
	    return "webFrame/vue/main";
	}

	
	
	
}