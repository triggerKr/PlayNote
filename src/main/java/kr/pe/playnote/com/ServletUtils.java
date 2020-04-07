package kr.pe.playnote.com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtils {

    /**
     * �� �޼ҵ�� ���ǰ��� Alive������ üũ�ϴ� �޼ҵ��̴�
     * @param str ������ ���ڿ� ��
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
