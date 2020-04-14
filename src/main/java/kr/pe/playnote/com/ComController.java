package kr.pe.playnote.com;


import java.io.File;
import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.pe.playnote.com.Code;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ComController {
	
	private static final Logger logger = LoggerFactory.getLogger(ComController.class);
	
	/**
	 * 첨부파일 다운로드
	 */
	@RequestMapping(value = "/com/downloadFile", method = {RequestMethod.GET, RequestMethod.POST})
	public void downloadFile(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		System.out.println("downloadFile");
		//Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
        String original_File_Name = "1586787418769_20190820_124215.jpg";//(String)map.get("ORIGINAL_FILE_NAME");
        String stored_File_Name = "1586787418769_20190820_124215.jpg";//(String)map.get("STORED_FILE_NAME");
        
        request.setCharacterEncoding("UTF-8");
        // 파일경로
        String root = request.getSession().getServletContext().getRealPath("/");
        System.out.println("root["+root+Code.PATH_NOTICE+"] stored_File_Name["+stored_File_Name+"]");
        
        byte[] fileByte = FileUtils.readFileToByteArray(new File(root+Code.PATH_NOTICE+stored_File_Name));
        
        //FileUtils.readFileToByteArray(arg0)
        
        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(original_File_Name,"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);
          
        response.getOutputStream().flush();
        response.getOutputStream().close();
	}

	
}