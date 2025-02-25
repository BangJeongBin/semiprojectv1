// 상황별 에러메세지 출력(정규표현식 사용)
const errorMessages = [
    '아이디는 소문자로 시작하고, 영문자와 숫자만 사용 가능합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호가 일치하지 않습니다',
    '이름은 한글 또는 영문자만 사용 가능합니다',
    '올바른 이메일 형식이 아닙니다',
    '자동가입방지를 확인하세요'];

const loginMessages = [
    '아이디를 올바르게 입력하세요',
    '비밀번호를 올바르게 입력하세요',
];

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


// 비밀번호 해싱
// async와 await를 사용해야 하는 이유는 hashPassword 함수가 비동기적으로 동작하기 때문
// 비동기 함수는 일반적으로 네트워크 요청, 파일 I/O, 암호화 작업 등 시간이 오래 걸리는 작업을 수행할 때 사용
// 이러한 작업은 완료될 때까지 기다려야 하며, 이때 async와 await를 사용하여 코드의 가독성을 높이고 동기적인 코드처럼 작성할 수 있음
const hashPassword = async (passwd) => {
    // 문자열을 Uint8Array로 변환
    const encoder = new TextEncoder();  // TextEncoder 객체 생성
    const data = encoder.encode(passwd); // 비밀번호를 Uint8Array로 변환

    // SHA-256 해시 생성
    // crypto.subtle.digest는 비동기 함수이므로 await 또는 .then()을 사용하여 결과를 처리해야 함
    // SHA-256은 암호학적으로 안전한 해시 함수이지만, 비밀번호 해시에는 추가적으로 솔트(salt)와 키 스트레칭(key stretching)을 사용하는 것이 좋음
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);

    // 해시 결과를 16진수 문자열로 변환
    const hashArray = Array.from(new Uint8Array(hashBuffer)); // Uint8Array를 배열로 변환
    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join(''); // 16진수 문자열로 변환

    return hashHex;
}


// submit vs fetch
// <form> submit : 페이지 새로고침 발생, 에러처리는 브라우저에 의존
// fetch 방식 : 비동기 처리 가능, 에러처리는 js로 세밀하게 조절 가능, 요청데이터를 JSON으로 전송 가능,
//             REST API/SPA/비동기요청 에서 주로 사용

// promise : 자바스크립트에서 비동기 작업을 처리하기 위한 객체
//           주로 비동기 작업의 성공/실패를 관리하고 결과를 처리하는데 사용
//           ES6에서 처음 도입 - 콜백 지옥을 해결, 비동기처리 코드를 깔끔하게 작성

// async : 비동기 함수임을 선언 - 반환값은 promise
// await : 비동기 함수의 처리가 완료될때 까지 기다렸다가 결과를 받아옴
const submitJoinFrm = async (frm) => {
    // Web Crypto API로 비밀번호 암호화
    frm.passwd.value = await hashPassword(frm.passwd.value);
    console.log(frm.passwd.value);


    // 폼에 입력된 데이터를 formData객체로 초기화
    const formData = new FormData(frm);

    fetch('/member/join', {
        method: 'post',
        body: formData
    }).then(async response => {
        if (response.ok) {  // 회원가입이 정상적으로 처리되었다면
            alert('회원가입이 완료되었습니다.')
            location.href = '/member/login'
        } else if (response.status === 400){
            alert(await response.text());
        } else {
            alert('회원가입에 실패했습니다.')
        }
    }).catch(error => {
        console.error('join error:', error)
        alert('서버와 통신 실패!')
    });
} // submitJoinFrm


// 로그인 폼 유효성 검사
const validLogin = (form) => {
    let isValid = true;

    // 로그인 폼 안에 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {    // input 요소를 하나씩 순회하며 검사
        if (!input.checkValidity()) {    // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, loginMessages[idx]);
            isValid = false;
        }
    });

    return isValid;
}


// 로그인 폼 제출
const submitLoginFrm = async (frm) => {
    // Web Crypto API로 비밀번호 암호화
    frm.passwd.value = await hashPassword(frm.passwd.value);

    const formData = new FormData(frm);

    fetch('/member/login', {
        method: 'post',
        body: formData
    }).then(async response => {
        if (response.ok) {  // 로그인을 성공했다면
            alert('로그인을 성공했습니다.')
            location.href = '/member/myinfo'
        } else if (response.status === 400){
            alert(await response.text());
        } else {
            alert('로그인에 실패했습니다. 다시 시도해 주세요')
        }
    }).catch(error => {
        console.error('join error:', error)
        alert('서버와 통신 실패!')
    });
} // submitLoginFrm