/* psh */



/* api를 불러오기 위한 page값을 저장하기 위한 전역변수 */
let pageNumber = 0;
/* 검색어를 저장하기 위한 전역변수 */
const url = new URL(window.location);
const urlParams = url.searchParams;
let searchtext = urlParams.get('search');

/* API를 통해 영화를 불러오는 함수 */
const getMovieForList = async (searchtext) => {
	pageNumber++;
	await fetch("https://api.themoviedb.org/3/search/movie?api_key=8fa86b0ec5fd3b36692e7fcc7627d54a&query=" + searchtext + "&page=" + pageNumber + "&language=ko")
		.then((response) => response.json())
		.then((data) => {
			if (data.total_results > 0) {
				for (let i = 0; i < data.results.length; i++) {
					let poster = data.results[i].poster_path;
					let title = data.results[i].title;
					let release = data.results[i].release_date.substr(0, 4);
					let vote = "★ " + data.results[i].vote_average;
					let id = data.results[i].id;
					$('.movie_list_wrap').append(addMovieForList(poster, title, release, vote, id));
				}
				//alert(pageNumber);
				if (pageNumber >= data.total_pages) {
					$('.more').hide();
				}
			} else {
				$('.movie_list_wrap').append(resultNone(searchtext));
				$('.more').hide();
			}
		})
};
/* API를 통해 영화를 불러오는 함수 (슬라이더용 = 최대20개까지만 출력) */
const getMovieForSwiper = async (searchtext) => {
	await fetch("https://api.themoviedb.org/3/search/movie?api_key=8fa86b0ec5fd3b36692e7fcc7627d54a&query=" + searchtext + "&page=1&language=ko")
		.then((response) => response.json())
		.then((data) => {
			if (data.total_results > 0) {
				for (let i = 0; i < data.results.length; i++) {
					let poster = data.results[i].poster_path;
					let title = data.results[i].title
					let id = data.results[i].id;
					/*문자열 길이가 길 경우 자르기..*/
					if (data.results[i].title.length > 16) {
						title = title.substr(0, 16) + "..";
					}
					$('.swiper-wrapper').append(addMovieForSwiper(poster, title, id));
				}
			}
		})
};

/* swiper */
var swiper = new Swiper(".mySwiper", {
	slidesPerView: 5,
	spaceBetween: 20,
	centeredSlides: false,
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	},
});

/* swiper 태그 생성 */
function addMovieForSwiper(poster, title, id) {
	return "<div class='swiper-slide'><a href='http://localhost:8090/Movivum/detail.do?mov_no=" + id + "'><img src='https://image.tmdb.org/t/p/w500"
		+ poster + "'><span>" + title + "</span></a></div>";
}
/* List 태그 생성 */
function addMovieForList(poster, title, release, vote, id) {
	return "<div class='movie_list'><a href='http://localhost:8090/Movivum/detail.do?mov_no=" + id + "'><div class='poster_wrap'><img src='https://image.tmdb.org/t/p/w500"
		+ poster + "'></div><div><span>" + title + "</span><span>" + release + " ㆍ " + vote + "</span></div></a></div>";
}
/* 검색결과가 없을 경우의 태그 */
function resultNone(searchtext) {
	return "<div class='forResultNone'>'" + searchtext + "'에 대한 검색 결과가 없습니다.</div>";
}

/* 페이지 실행 시 영화목록 불러오기 */
$(document).ready(function() {
	getMovieForSwiper(searchtext);
	getMovieForList(searchtext);
})

/* 더보기 버튼 클릭 시 영화목록 추가로 불러오기 */
$('.more').on('click', function() {
	getMovieForList(searchtext);
})