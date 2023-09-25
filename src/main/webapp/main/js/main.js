$(function(){
    // 인기순
    $.ajax({
        url:"https://api.themoviedb.org/3/movie/popular?api_key=ce8ce557f85eb5b4175ee66f08002107&page=1&language=ko-KR",
        data:{"key":"value"},
        type:"post",
        success:function(json){
            console.log(json);
            $("#popular_wrap").html('')
            let movie_list=json.results;

        for(let i=0;i<10;i++){
            let page=`
            <div class="swiper-slide">
                <a href="${'/Movivum/detail.do?mov_no='+movie_list[i].id}" class="movie_poster">
                    <img src="${'https://image.tmdb.org/t/p/w342/'+movie_list[i].poster_path}">
                </a>
                <div class="movie_info">
                    <span class="movie_title">${movie_list[i].title}</span>
                    <span class="movie_review">
                        <span class="material-symbols-outlined star">grade</span>${movie_list[i].vote_average}
                        &nbsp;|&nbsp;리뷰(999)
                    </span>
                </div>
            </div>`
                $("#popular_wrap").append(page);
            }
        }
    })
    // 현재상영순
    $.ajax({
        url:"https://api.themoviedb.org/3/movie/now_playing?api_key=ce8ce557f85eb5b4175ee66f08002107&page=2&language=ko-KR",
        data:{"key":"value"},
        type:"post",
        success:function(json){
            console.log(json);
            $("#now_playing_wrap").html('')
            let movie_list=json.results;
        for(let i=0;i<10;i++){
            let page=`
            <div class="swiper-slide">
                <a href="${'/Movivum/detail.do?mov_no='+movie_list[i].id}" class="movie_poster">
                    <img src="${'https://image.tmdb.org/t/p/w342/'+movie_list[i].poster_path}">
                </a>
                <div class="movie_info">
                    <span class="movie_title">${movie_list[i].title}</span>
                    <span class="movie_review">
                        <span class="material-symbols-outlined star">grade</span>${movie_list[i].vote_average}
                        &nbsp;|&nbsp;리뷰(999)
                    </span>
                </div>
            </div>`
                $("#now_playing_wrap").append(page);
            }
        }
    })

    // 특정 장르 width_genres=장르번호 =>콤마로 나열 가능 
    $.ajax({
        url:"https://api.themoviedb.org/3/discover/movie?api_key=ce8ce557f85eb5b4175ee66f08002107&with_genres=28,14,16&page=1&language=ko-KR",
        data:{"key":"value"},
        type:"get",
        success:function(json){
            console.log(json);
            $("#recommend_wrap").html('');
            let movie_list=json.results;
            console.log(movie_list);
        for(let i=0;i<10;i++){
            let page=`
            <div class="swiper-slide">
                <a href="${'/Movivum/detail.do?mov_no='+movie_list[i].id}" class="movie_poster">
                    <img src="${'https://image.tmdb.org/t/p/w342/'+movie_list[i].poster_path}">
                </a>
                <div class="movie_info">
                    <span class="movie_title">${movie_list[i].title}</span>
                    <span class="movie_review">
                        <span class="material-symbols-outlined star">grade</span>${movie_list[i].vote_average}
                        &nbsp;|&nbsp;리뷰(999)
                    </span>
                </div>
            </div>`
                $("#recommend_wrap").append(page);
            }
        }
    })
    // 슬라이드
    var swiper = new Swiper(".swiper", {
        slidesPerView: 5,
        spaceBetween: 20,
        centeredSlides: false,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

	$('.mymenu_btn').on('click',function(){
        $('.mymenu_sub').toggle();
    })

    //트레일러 안에 연관 영화 슬라이드
    var swiper = new Swiper("#trailer_swiper", {
        slidesPerView: 7,
        slidesPerGroup: 7, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
        spaceBetween: 10,
        slidesOffsetBefore: 335,
        loopFillGroupWithBlank: true,
        // 반응형
        breakpoints: {
            // 1280: {
            //     slidesPerView: 3,
            //     slidesPerGroup: 3,
            // },
            // 720: {
            //     slidesPerView: 1,
            //     slidesPerGroup: 1,
            // }
        }
      });

      $.ajax({
        url:"https://api.themoviedb.org/3/search/movie?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko-KR&query=%EC%A1%B4%20%EC%9C%85&page=1&include_adult=true",
        data:{"key":"value"},
        type:"get",
        success:function(json){
            console.log(json);
            $("#trailer_wrap").html('')
            let movie_list=json.results;

        for(let i=0;i<5;i++){
            let page=`
            <div class="swiper-slide">
                <a href="${'/Movivum/detail.do?mov_no='+movie_list[i].id}" class="movie_poster">
                    <img src="${'https://image.tmdb.org/t/p/w342/'+movie_list[i].poster_path}">
                </a>
            </div>`
                $("#trailer_wrap").append(page);
            }
        }
    })

    // play아이콘 선택하면 나타나는 트레일러 팝업
    $('#trailer .playbtn').on('click', function(){
        $('.blackbg').fadeIn();
        $('.trailer_video').show();
    })
    $('.blackbg, .closebtn>img').on('click',function(){
        $('.blackbg').fadeOut();
        $('.trailer_video').hide();
    })    

	$(document).mouseup(function(e){
		let target=$('.mymenu_sub');
	    if(target.has(e.target).length==0) {
	        target.hide();
	    } 
	});    
})

