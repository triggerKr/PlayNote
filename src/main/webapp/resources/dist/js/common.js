
/** 2021-04-10
 * 이메일이 잘못되었는지 확인하는 함수
 * @param1 : email
 * @return : 
 * 사용예 
 *  <input type="email" id="email" name="email" onKeyDown="nextFocus(this.form, 'pwd');">
 *  <input type="text"   id="pwd" name="pwd"   >
 */
function CheckEmail(str){                                                 

     var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(!reg_email.test(str)) {                            
          return false;         
     }                            
     else {                       
          return true;         
     }
}                           

/** 2021-04-10
 * 엔터 입력시 다음 focus 이동
 * @param1 : 다음 focus
 * @return : 
 * 사용예 
 *  <input type="email" id="email" name="email" onKeyDown="nextFocus(this.form, 'pwd');">
 *  <input type="text"   id="pwd" name="pwd"   >
 */
function nextFocus(frm, next)
{
        var keycode = event.keyCode;
        if( keycode == 13 ){
        	document.getElementById(next).focus();
        }
}
/**
 *========================================
 * 폼 입력값 검사
 *========================================
 * @param1 : 객체명
 * @param2 : 필수항목여부(필수항목:Y, 선택항목:N)
 * @param3 : 제한길이(제한길이가 없을 경우엔 '0'으로 입력)
 * @return : 폼입력값 유효여부 ( 예 : true / 아니오 : false)
 *----------------------------------------
 * 사용법  : if ( !isValid( form.apply_type, 'Y', 0 ) ) return ;
 *----------------------------------------
 */

/** 
 * 공문자열 제거
 * @param1 : 검사할 문자열
 * @return : 공문자 제거된 문자열
 */
    function trim( strTmp )
    {
    	strTmp = strTmp.replace(/\s+/gi, "");  // remove leading white spaces
    	return strTmp;
    }

    /**
     * 입력내용의 길이를 반환한다.
     * @param1 : 입력내용
     * return : 문자열 길이
     */	
    	function getByteLength( strTmp )
        {
    		var nLen = 0;
    		if ( strTmp == null ) 
    		{
    			return 0;
    		}
    		
    		for( var i = 0 ; i < strTmp.length ; i++ )
    		{
    			var char = escape(strTmp.charAt(i));
    			if ( char.length == 1 ) 
    			{
    				nLen ++;
    			}
    			else if ( char.indexOf("%u") != -1 ) 
    			{
    				nLen += 2;
    			}
    			else if ( char.indexOf("%") != -1 ) 
    			{
    				nLen += char.length / 3;
    			}
    		}
    		
    		return nLen;
    	}
        
    	/**
    	 *========================================
    	 * 패턴이 적용되어있는지 검사
    	 *========================================
    	 * @param1 : 검사할 폼객체명
    	 * @param2 : 검사할 패턴 종류
    	 * @return : 패턴 적용 여부 ( 예 : true / 아니오 : false )
    	 *----------------------------------------
    	 * 사용법  : if ( !checkPattern( form.korname, "regHangulOnly" )) return ;
    	 *----------------------------------------
    	 */
    		function checkPattern( objField, strPattern )
    		{
    			var regNum = /^[0-9]+$/;										// 숫자
    			var regPhone = /^[0-9]{2,3}-[0-9]{3,4}-[0,9]{4}$/;				// 전화번호
    			var regMail = /^[_a-zA-Z0-9-]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;	// 이메일
    			var regDomain = /^[.a-zA-Z0-9-]+.[a-zA-Z]+$/;					// 도메인
    			var regAlpha = /^[a-zA-Z]+$/;									// 영문자
    			var regHangul = /[가-힣]/;										// 한글
    			var regHangulEng = /[가-힣a-zA-Z]/;								// 한글 & 영문자
    			var regHangulOnly = /^[가-힣]*$/;								// 한글만
    			var regId = /^[a-zA-Z]{1}[a-zA-Z0-9_-]{4,15}$/;					// 아이디(영문+숫자, 길이:4~15)
    			var regId2 = /^[a-zA-Z]{1}[a-zA-Z0-9_-]{3,10}$/;					// 아이디(영문+숫자, 길이:3~10)
    			var regDate = /^[0-9]{4}-[0,9]{2}-[0-9]{2}$/;					// 날짜 (yyyy-mm-dd)
    			var regFloat = /^[0-9]+.[0-9]+$/;									// 실수
    			var regBank = /^[0-9,-]+$/;										// 계좌번호(숫자,- 혼용)
    			
    			strPattern = eval(strPattern);
    			
    			if ( !strPattern.test( trim(objField.value)) )
    			{
    				alert( objField.alt + "을 바르게 기재하세요." );	
    				objField.select();		
    				return false;
    			}
    			
    			return true;
    		}

    		/**
    		 *========================================
    		 * 제한 길이 입력후 다음으로 포커스 이동
    		 *========================================
    		 * @param1 : 입력길이
    		 * @param2 : 입력대상객체
    		 * @param3 : 포커스 이동할 다음 대상 객체명
    		 * @return : 포커스 이동
    		 *----------------------------------------
    		 * 사용법  : <input class="input" name="jumin1" alt="주민번호"  size=15 maxlength=6 onKeyUp="moveFocus(6, this, 'jumin2');">
    		 *----------------------------------------
    		 */		
    			function moveFocus( nLen, objFrom, strTo)
    			{
    				var nInputLen = objFrom.value.length;
    				var toObject = eval( objFrom.form.name + "." + strTo );
    				if( nInputLen == nLen)
    				{
    				   toObject.focus();
    				}
    			}
    			
    			function maxlength(element, maxvalue){
    				var q = CalculateMsgLen(eval(element+".value"));
    				var r = q - maxvalue; 
    				var msg = "입력필드에 " + maxvalue + " 자를 초과하여 입력할 수 없습니다.\r\n초과된 문자는 삭제됩니다.";
    				if (q > maxvalue) {
    					alert(msg);
    					var str = AssertMsgLen(eval(element+".value"), maxvalue);
    					str = replace(str, "'" ,"\\'");
    					str = replace(str, "\r","\\r");
    					str = replace(str, "\n","\\n");        
    					eval(element+".value='" + str +  "'");
    					return false;
    				}
    				return true;
    			}
    			
    			function AssertMsgLen(message, maximum) {
    				var inc = 0;
    				var nbytes = 0;
    				var msg = "";
    				var msglen = message.length;
    			
    				for (i=0; i<msglen; i++) {
    					var ch = message.charAt(i);
    					if (escape(ch).length > 4) {
    						inc = 2;
    					} else if (ch != '\r') {
    						inc = 1;
    					}
    					if ((nbytes + inc) > maximum) {
    						break;
    					}
    					nbytes += inc;
    					msg += ch;
    				}
    				return msg;
    			}
    			
    			function CalculateMsgLen(message) {
    				var nbytes = 0;
    			
    				for (i=0; i<message.length; i++) {
    					var ch = message.charAt(i);
    					if (escape(ch).length > 4) {
    						nbytes += 2;
    					} else if (ch != '\r') {
    						nbytes++;
    					}
    				}
    			
    				return nbytes;
    			}
    			
    			function replace(string,text,by) {
    			    var strLength = string.length, txtLength = text.length;
    			    if ((strLength == 0) || (txtLength == 0)) return string;
    			
    			    var i = string.indexOf(text);
    			    if ((!i) && (text != string.substring(0,txtLength))) return string;
    			    if (i == -1) return string;
    			
    			    var newstr = string.substring(0,i) + by;
    			
    			    if (i+txtLength < strLength)
    			        newstr += replace(string.substring(i+txtLength,strLength),text,by);
    			
    			    return newstr;
    			}
    			
    			
    		function numOnly(obj,isCash){
    			if (event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39) return;
    			var returnValue = "";
    			var strlen =  eval(obj + ".value.length");
    			for (var i = 0; i < strlen; i++){
    				var str1 = eval(obj + ".value.charAt(" + i + ")");
    				if (str1 >= "0" && str1 <= "9"){
    					returnValue += str1;
    				}else{
    					returnValue += "";
    				}
    			}
    			
    			if (isCash){
    				returnValue = cashReturn(returnValue);
    				returnValue = replace(returnValue, "'" ,"\\'");
    				eval(obj + ".value='" + returnValue + "'");
    				return;
    			}
    			returnValue = replace(returnValue, "'" ,"\\'");
    			eval(obj + ".value='" + returnValue + "'");
    		}
    		function percOnly(obj,frm){
    			if (event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39) return;
    			var returnValue = "";
    			for (var i = 0; i < obj.value.length; i++){
    				if (parseInt(obj.value.charAt(i)) >= 0 && parseInt(obj.value.charAt(i)) <= 9){
    					returnValue += obj.value.charAt(i);
    				}else if (obj.value.charAt(i) == '.'){
    					returnValue += obj.value.charAt(i);
    				}else{
    				}
    			}

    			obj.value = returnValue;
    		}
    		function cashReturn(numValue){
    			var cashReturn = "";
    			for (var i = numValue.length-1; i >= 0; i--){
    				cashReturn = numValue.charAt(i) + cashReturn;
    				if (i != 0 && i%3 == numValue.length%3) cashReturn = "," + cashReturn;
    			}
    			
    			return cashReturn;
    		}
    		function removeComma(cash){
    			var returnValue = "";
    			for (var i = 0; i < cash.length; i++){
    				if (cash.charAt(i) != ","){
    					returnValue += cash.charAt(i);
    				}
    			}
    			return returnValue;
    		}	    		
    	     	        