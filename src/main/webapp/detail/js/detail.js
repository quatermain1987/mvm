//동영상 오류
document.cookie = "safeCookie1=foo; SameSite=Lax"; 
document.cookie = "safeCookie2=foo";  
document.cookie = "crossCookie=bar; SameSite=None; Secure";



//영화 타이틀 

function input_mov_title(value) {
  const input = document.createElement("input");
  input.setAttribute("type", "hidden");
  input.setAttribute("name", "mov_title");
  input.setAttribute("value", value);
  return input;
}

function input_genre(genreId, targetFormId){
   const input = document.createElement("input");
  input.setAttribute("type", "hidden");
  input.setAttribute("name", "genres");
  input.setAttribute("value", genreId);
  document.querySelector(`#${targetFormId}`).appendChild(input);

}


//영화상세 api + 모달 영화제목
const urlParams = new URL(location.href).searchParams;
const mov_no = urlParams.get('mov_no');
const url ='https://api.themoviedb.org/3/movie/';
const api_key ='?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko';

fetch(url+mov_no+api_key)
.then((response) => response.json())
.then((data) => {
   
   let url=`https://image.tmdb.org/t/p/original/${data.backdrop_path}`;
   const still_cut = document.querySelector(".stillcut_img").style.backgroundImage = `url(${url})`;
   
   const poster =document.createElement("img");
   poster.src=`https://image.tmdb.org/t/p/w500/${data.poster_path}`;
   const content = document.createElement("div");
    if (data.overview === "") {
  	content.textContent = "등록된 줄거리가 없습니다";
  	content.className ="empty_overview";
	} else {
  	content.textContent = data.overview;
  		content.className ="overview";
	}
   const title = document.createElement("h2");
   title.className = 'movie_title';
   const date = document.createElement("p");
   date.className = 'movie_date';
   const genre = document.createElement("p");
   genre.className = 'movie_genre';
   title.textContent = data.title;
   date.textContent = data.release_date.substr(0,4);
   let results ="";
   for( let i=0; i<data.genres.length; i++ ){
    results += data.genres[i].name+"・";  
   }
   genre.textContent = results.slice(0, -1);
   
   document.querySelector(".movie_poster").appendChild(poster);
   
   const info = document.querySelector(".moive_info");
      info.appendChild(date);
      info.appendChild(title);
      info.appendChild(genre);
      
   document.querySelector(".moive_content").appendChild(content);
   	
  	//모달 제목
  const write_modal_title = document.createElement("span");
 	write_modal_title.className = 'title';
 	write_modal_title.textContent = data.title;
 	document.querySelector(".write_title").appendChild(write_modal_title);
 	
 	const update_modal_title= document.createElement("span");
 	update_modal_title.className = 'title';
 	update_modal_title.textContent = data.title;
 	document.querySelector(".update_title").appendChild(update_modal_title);
  
     
    
    //무비타이틀 히든
    const write_title = input_mov_title(data.title);
    const wish_title = 	input_mov_title(data.title);
    const grade_title = input_mov_title(data.title);
    
    
    document.querySelector("#writeform").appendChild(write_title);
    document.querySelector("#wishform").appendChild(wish_title);
    document.querySelector("#gradeform").appendChild(grade_title);
    
    //장르 히든
    for (let i = 0; i < data.genres.length; i++) {
  		input_genre(data.genres[i].id, "writeform");
  		input_genre(data.genres[i].id, "gradeform");
  		input_genre(data.genres[i].id,  "wishform");
	}
     
 }) //fetch 끝

//배역 api
fetch(url+mov_no+'/credits'+api_key)
    .then((response) => response.json())
    .then((data) => {
		let hasCast= false;
        let length = data.cast.length < 10 ? data.cast.length : 10;
        for (let i = 0; i < length; i++) {
            let results = data.cast[i];
            if (results && results.profile_path) {
                let card = ` <div class="swiper-slide">
                    <div class="cast_wrap">
                        <div class="cast_picture_wrap">
                            <img src="${'https://image.tmdb.org/t/p/w500/'+results.profile_path}">
                        </div>
                        <div class="actor_name">${results.character}</div>
                        <div class="actor_cast">${results.name}</div>
                    </div>
                </div>`; 
                let wrap = document.querySelector('#moive_cast .inner .swiper-wrapper');
                wrap.innerHTML = wrap.innerHTML + card;
                hasCast = true;
            }//if  
          }//for문
        if(!hasCast){
			 const nocastMsg = `<div class="empty_cast">등록된 출연진이 없습니다.</div>`;
		     const wrap = document.querySelector('#moive_cast .inner .swiper-wrapper');
		     wrap.innerHTML = wrap.innerHTML + nocastMsg;	
			}
    });  //fetch 끝

 // 동영상 api
		fetch(url + mov_no + '/videos' + api_key)
		  .then((response) => response.json())
		  .then((data) => {
		    let hasTrailer = false;
		    for (let i = 0; i < data.results.length; i++) {
		      let youtubeId = data.results[i].key;
		      if (youtubeId) {
		        let card = `<div class="swiper-slide"> 
		          <div class="trailer_wrap">
		            <iframe width="400" height="300" src="https://www.youtube.com/embed/${youtubeId}autoplay=1"></iframe>
		          </div>
		        </div>`; 
		        let wrap = document.querySelector('#trailer .inner .swiper .swiper-wrapper');
		        wrap.innerHTML = wrap.innerHTML + card;
		        hasTrailer = true;
		      }
		    }
		    if (!hasTrailer) {
		      const noTrailerMsg = `<div class="empty_trailer">등록된 트레일러가 없습니다.</div>`;
		      const wrap = document.querySelector('#trailer .inner .swiper .swiper-wrapper');
		      wrap.innerHTML = wrap.innerHTML + noTrailerMsg;
		    }
		  })//fetch 끝

 

$('.write_savebtn').on('click',function(){
	let value = $('#newWrite').val()
	if(value==''){
		alert("리뷰를 입력해주세요.");
	}else{
		$('#writeform').submit();
	}
})

$('.update_savebtn').on('click',function(){
	
	let value = $('#reWrite').val();
	let prevValue = $('#newWrite').val();
	if(value==''){
		alert("리뷰를 입력해주세요.");
	}else if(value == prevValue){
		let con = confirm('변경사항이 없습니다.\r\n이대로 저장하시겠습니까?');
		if(con){
			$('#updateform').submit();
		}
	}else{
		$('#updateform').submit();
	}
})



//삭제
const deletebtn = document.querySelector('#deletebtn');
if (deletebtn) {
  deletebtn.addEventListener('click', () => {
    if (confirm("정말로 삭제하시겠습니까?")) {
      document.querySelector('#deleteform').submit();
    }
  });
}

//로그인 확인

function alertLogin() {
 	alert("로그인이 필요합니다.");
   // window.location.href = ""; 로그인 경로
   }


//리뷰작성모달

$(function(){
  $(document).on("click","#openbtn",function(){
      $('.blackbg').fadeIn();
      $('.write_box_write').show(); 
  });
  
  $('#blackbg, .write_closebtn').on("click",function(){
     $('.blackbg').fadeOut();
     $('.write_box_write').hide(); 
  });

  //리뷰 수정 모달
  $(document).on("click","#openbtn2",function(){
      $('.blackbg').fadeIn();
      $('.write_box_update').show(); 
      $('.write_content').val($('.review_update_content').text());
  });
  $('#blackbg, .write_closebtn2').on("click",function(){
     $('.blackbg').fadeOut();
     $('.write_box_update').hide();
  });


/* 리뷰 작성 ~ 보고싶어요 비동기식으로 처리시 

$(document).on("click",".write_savebtn",function(){
  if (validateForm()) {
    $.ajax({
      url: '/Movivum/write.do',
      type: 'post',
      datatype:'json',
      async : true,
      data: $('#writeform').serialize(),
        success: function(response) {
   	  if (response.result === "success") {
  		alert("리뷰가 작성되었습니다.");
    } else {
      alert("오류가 발생하였습니다.");
    }
  } 
    });
  }
});

$(document).on("click",".update_savebtn",function(){
  if (validateForm()) {
    $.ajax({
      url: '/Movivum/update.do',
      type: 'post',
      datatype:'json',
      async : false,
      data: $('#writeform').serialize(),
        success: function(response) {
   	  if (response.result === "success") {
  		alert("리뷰가 수정되었습니다.");	
    } else {
      alert("오류가 발생하였습니다.");
    }
  } 
    });
  }
});
*/

//보고싶어요 클릭

$(document).on("click","#wish",function(){
  $.ajax({
    url: 'detail/detailWish.jsp',
    type: 'post',
    datatype:'json',
    async : false,
    data: $('#wishform').serialize(),
      success: function(response) {
   if (response.result === "success") {
  		alert("좋아요가 반영되었습니다.");
  		location.reload();
    } else {
      alert("오류가 발생하였습니다.");
   	 }
   } 
  });
});


// 별점전송
$("input[name='reviewStar']").change(function() {
	if (!$("#user_no").val()) {
  		alertLogin();
  		return;}
  console.log($("#user_no").val()); // user_no 값 출력
  let rating = $("input[name='reviewStar']:checked").val();
  let confirmMsg = (rating === '0') ? '취소하시겠습니까?' : `${rating} 저장하시겠습니까?`;
  if (confirm(confirmMsg)) {
    $.ajax({
      url: 'detail/grade.jsp',
      type: 'post',
      dataType: 'json',
      data: $('#gradeform').serialize() + '&rating=' + rating,
      success: function(response) {
   if (response.result === "success") {
  		alert("별점이 반영되었습니다.");
  		location.reload();
    } else {
      alert("오류가 발생하였습니다.");
    }
  } 
    });
  }
});


 // 슬라이드
   let swiper = new Swiper(".swiper", {
    slidesPerView: 5,
    spaceBetween: 20,
    centeredSlides: false,
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});

//별점차트

const grade1 = document.querySelector('#grade1').value;
const grade2 = document.querySelector('#grade2').value;
const grade3 = document.querySelector('#grade2').value;
const grade4 = document.querySelector('#grade2').value;
const grade5 = document.querySelector('#grade2').value;


const ctx = document.querySelector('#myChart');
new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ['1', '2', '3', '4', '5'],
    datasets: [{
      label: '평균',
      data: [grade1, grade2, grade3, grade4, grade5],
      borderWidth: 1,
      backgroundColor: [
	      'rgb(206, 208, 234)',
		  'rgb(191, 157, 228)',
		  'rgb(177, 105, 222)',
		  'rgb(162, 53, 217)',
		  'rgb(148, 0, 211)'
      ],
      //hoverOffset: 4
    }]
  },
  options: {
    legend: {
      display: false
    },
    scales: {
      y: {
        beginAtZero: false,
      }
    }
    
  }
}); //차트끝

}) //끝
