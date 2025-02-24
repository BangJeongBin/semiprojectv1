// 상황별 에러메세지 출력(정규표현식 사용)
const errorMessages = [
    '아이디는 소문자로 시작하고, 영문자와 숫자만 사용 가능합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호가 일치하지 않습니다',
    '이름은 한글 또는 영문자만 사용 가능합니다',
    '올바른 이메일 형식이 아닙니다',
    '자동가입방지를 확인하세요'];
const patterns = [
    /^[a-z][a-z0-9]{5,17}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^[가-힣]{2,5}|[a-zA-Z]{2,10}$/,
    /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/
];


// 모든 error-message 요소의 내용을 초기화
function clearErrorMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent = '');
}


// 입력 요소 유효성 검사
// 화살표 함수 사용 - 위에 처럼 사용해도 되지만 이게 최신의 방법, 익명함수, 함수 자체를 변수로 사용
const validInputs = (form) => {
    let isValid = true;

    // 회원가입 폼 안에 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {    // input 요소를 하나씩 순회하며 검사
        if (!input.checkValidity()) {    // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, errorMessages[idx]);
            isValid = false;
        }
    });

    // 비밀번호 일치 여부 검사
    if (inputs[1].value !== inputs[2].value) {
        displayErrorMessages(inputs[2], errorMessages[2]);
        isValid = false;
    }

    return isValid;
}


// 오류메세지 출력
const displayErrorMessages = (input, message) => {
    let error = document.createElement('div');  // error-messaga가 들어갈 <div> 요소 생성
    error.className = 'error-message'   // 생성한 <div> 요소 class 지정 - css 적용을 위함
    error.textContent = message; // 에러메세지 지정
    input.parentElement.appendChild(error); // 지정한 위치에 자식요소로 추가
}