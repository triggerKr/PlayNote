package kr.pe.playnote.com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtils {

    /**
     * 이 메소드는 세션값이 Alive한지를 체크하는 메소드이다
     * @param str 변경할 문자열 값
     * @return String
     */ 

    public static boolean aliveSession(HttpServletRequest req){

        HttpSession session = req.getSession(false);

        if (session == null ) return false;

        if( session.getAttribute(Code.SESSION_INFO) == null ) {
            return false;  
        }else {
            return true;  
        } 

    }
}
