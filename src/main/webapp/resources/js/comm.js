
// 천단위 이상의 숫자에 콤마( , )를 삽입하는 함수
function inputNumberWithComma(obj) {
	obj = obj.toString().replace(/[^0-9]/g,'');   // 입력값이 숫자가 아니면 공백
	obj = obj.toString().replace(/,/g,'');          // ,값 공백처리
	return obj.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}


// 콤마( , )가 들어간 값에 콤마를 제거하는 함수
function inputNumberRemoveComma(str) {

    str = String(str);
    return str.replace(/[^\d]+/g, "");
}