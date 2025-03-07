const galleryMessages = [
    '제목을 올바르게 입력하세요',
    '본문내용을 올바르게 입력하세요',
    '첨부할 이미지파일을 올바르게 선택하세요',
    '자동가입 방지를 올바르게 입력하세요'
];


// 모든 error-message 요소의 내용을 초기화
function clearErrorMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent = '');
}


// 오류메세지 출력
const displayErrorMessages = (input, message) => {
    let error = document.createElement('div');  // error-messaga가 들어갈 <div> 요소 생성
    error.className = 'error-message'   // 생성한 <div> 요소 class 지정 - css 적용을 위함
    error.textContent = message; // 에러메세지 지정
    input.parentElement.appendChild(error); // 지정한 위치에 자식요소로 추가
}


const validGallery = (form) => {
    let isValid = true;

    // 글쓰기 폼 안에 readonly가 아닌 input 요소 수집
    const inputs = form.querySelectorAll('input:not([readonly]), textarea:not([readonly])');
    inputs.forEach((input, idx) => {    // input 요소를 하나씩 순회하며 검사
        if (!input.checkValidity()) {    // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, galleryMessages[idx]);
            isValid = false;
        }
    });
    const ggrecaptch = grecaptcha.getResponse();
    if (ggrecaptch === "") {
        const recaptch = document.querySelector('#recaptcha');
        displayErrorMessages(recaptch, galleryMessages[3]);
        isValid = false;
    }
    /*console.log(ggrecaptch);*/

    return isValid;
}


const submitGalleryFrm = async (frm) => {
    const formData = new FormData(frm);

    fetch('/gallery/write', {
        method: 'post',
        body: formData
    }).then(async response => {
        if (response.ok) {  // 로그인을 성공했다면
            alert('게시글이 등록되었습니다.')
            location.href = '/gallery/list'
        } else if (response.status === 400){
            alert(await response.text());
        } else {
            alert('게시글 등록에 실패했습니다. 다시 시도해 주세요')
        }
    }).catch(error => {
        console.error('gallery write error:', error)
        alert('서버와 통신 실패!')
    });
}

