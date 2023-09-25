/* psh */



/* 로컬스토리지 객체 선언 */
const storage = window.localStorage;
// 검색어 목록
function prevSearchDiv(key){return `<i class="fa-solid fa-magnifying-glass"></i><div class='prevSearchDiv'>${key}</div>`}
// 삭제버튼 생성
function delBtn(key){return `<button id='${key}' class='delBtn'><i class="fa-regular fa-x"></i></button>`}

// 배열에 검색어를 저장하는 함수
function setArray(key, value){
    let str = storage.getItem(key);
    let arr = [];
    if(str!==null){
        arr = JSON.parse(str);
    }
    //배열에 저장하려는 값이 이미 존재하는지 체크
    const check = arr.find(function(element){
        if(element===value){
            return true;
        }
    });
    //값이 존재한다면 해당 값을 반환, 없다면 undefined 반환
    //console.log(check);
    if(check===undefined){  //값이 없다면 배열에 추가
        arr.push(value);
        storage.setItem(key, JSON.stringify(arr));
    }else{                  //값이 있다면 삭제하고 배열에 새로 추가
        let filtered = arr.filter(function(element){return element!=value});
        filtered.push(value);
        storage.setItem(key, JSON.stringify(filtered));
    }
}

// 검색어 입력 시 setArray()호출
$('#search_form').on('submit',function(){
    if($('#search_input').val().trim()!=''){
        setArray('prevSearchText',$('#search_input').val().trim());
    }
})

// 배열에 저장된 검색어를 삭제하는 함수
function resetArray(key, value){
    let str = storage.getItem(key);
    let arr = [];

    if(str!==null){
        arr = JSON.parse(str);
    }
    // 삭제값을 제외한 배열을 생성
    let filtered = arr.filter(function(element){return element!=value});
    //console.log(filtered);
    storage.setItem(key, JSON.stringify(filtered));
}

// 삭제버튼 클릭 시 resetArray()호출
$(document).on('click','.delBtn',function(e){
    e.stopPropagation();
    const value = $(this).attr('id');
    resetArray('prevSearchText',value); //resetArray함수를 통해 배열에서 해당 값 삭제
    $(this).closest('li').remove(); //생성된 태그 삭제
})

// 전체삭제 버튼 클릭 시 이전 검색어 전체 삭제
$('#delAll').on('click',function(e){
    const con = confirm("모든 검색어를 삭제하시겠습니까?");
    if(con){
        storage.clear();
        $('#prevSearch').hide();
    }else{
        e.stopPropagation();
    }
})
//
$('#delAll_wrap').on('click',function(e){
    e.stopPropagation();
})

/* 로컬스토리지에 저장된 검색어 배열을 li태그로 생성 */
function setPrevList(){
    $('#PSul').empty();
    let arr = [];
    let str = storage.getItem('prevSearchText');
    //console.log(str);
    if(str!==null){ arr = JSON.parse(str); }
    for(let i=arr.length-10; i<arr.length;i++){ //10개 까지만 출력
        if(i>=0){ // 0이상의 정상적인 인덱스 번호만 출력 (음수의 경우 undefined)
            let list = `<li class='prevSearchList'>${prevSearchDiv(arr[i])}${delBtn(arr[i])}</li>`;
            $('#PSul').prepend(list);
        }
    }
}
// 빈 검색창 클릭 시 setPrevList()를 호출하여 이전검색어 창 띄우기
$('#search_input').on('click',function(e){
    let arr = JSON.parse(storage.getItem('prevSearchText'));
    //if($('#search_input').val()=='' && arr!==null && arr.length>0){
    if(arr!==null && arr.length>0){
        e.stopPropagation();
        setPrevList();
        $('#prevSearch').toggle();
    }
})
// 이전검색어 마우스오버 시 selected클래스 추가
$(document).on('mouseenter','.prevSearchList',function(){
    if($('.prevSearchList').hasClass('selectedPrevSearchList') === true){ //선택된 리스트가 있다면
        $('.selectedPrevSearchList').removeClass('selectedPrevSearchList'); //selected클래스 제거
    }
    $(this).addClass('selectedPrevSearchList'); // 마우스오버 항목 selected클래스 추가
})
// 이전 검색어 클릭 시 검색inputbox에 해당 검색어 등록
$(document).on('click','.prevSearchList',function(){
    $('#search_input').val($(this).text());
    $('#search_input').focus();
})
// 이전검색어 창이 띄워져 있을 경우 어느곳이든 클릭 시 창 숨기기
$(document).on('click',function(){
    if($('#prevSearch').css('display')!=='none'){
        $('#prevSearch').hide();
    }
})
// 검색어 입력 시 이전검색어 창 숨기기
$('#search_input').on('input',function(){
    $('#prevSearch').hide();
})

// 방향키로 이전 검색어 선택
$('#search_input').on('keydown',function(e){
    if($('#prevSearch').css('display')==='none'){ // 검색어 목록이 띄워져있지 않은 상태라면
        $('.selectedPrevSearchList').removeClass('selectedPrevSearchList'); // 선택해제
        if(e.keyCode == 38 || e.keyCode == 40){
            $('#prevSearch').show(); // 검색어 목록 띄우기
        }
    }else{ // 검색어 목록이 띄워져있다면
        if(e.keyCode == 40){ // 위 방향키 입력
            if($('.prevSearchList').hasClass('selectedPrevSearchList') === true){ // 선택된 리스트가 있다면
                $('.selectedPrevSearchList').removeClass('selectedPrevSearchList').next().addClass('selectedPrevSearchList');
            }else{ //선택된 리스트가 없다면
                $('.prevSearchList').first().addClass('selectedPrevSearchList'); // 첫번째 항목 선택
            }
            $('#search_input').val($('.selectedPrevSearchList').text()); // 인풋박스에 해당 검색어 입력
        }else if(e.keyCode == 38){ // 아래 방향키 입력
            e.preventDefault();
            if($('.prevSearchList').hasClass('selectedPrevSearchList') === true){ // 선택된 리스트가 있다면
                $('.selectedPrevSearchList').removeClass('selectedPrevSearchList').prev().addClass('selectedPrevSearchList');
            }else{ //선택된 리스트가 없다면
                $('.prevSearchList').last().addClass('selectedPrevSearchList'); // 마지막 항목 선택
            }
            $('#search_input').val($('.selectedPrevSearchList').text()); // 인풋박스에 해당 검색어 입력
        }else if(e.keyCode == 27){ // esc 입력
            $('#prevSearch').hide(); // 리스트 숨기기
        }
    }
})

// 검색아이콘 클릭 시 폼전송
$('.search_icon').on('click',function(){
    $('#search_form').submit();
})






/**
 * header
 */
$(function(){
	/* 로그인 - 서브메뉴 */
	$(document).mouseup(function(e){
		let target=$('.mymenu_sub');
	    if(target.has(e.target).length==0) {
	        target.hide();
	    } 
	});   
})
