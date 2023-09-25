
	const urlParams = new URL(location.href).searchParams;
	const mov_no = urlParams.get('mov_no');
    const url ='https://api.themoviedb.org/3/movie/';
    const api_key ='?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko'
    fetch(url+mov_no+api_key)
    .then((response) => response.json())
    .then((data) => {
        console.log(data);
        let results ='';
        for( let i=0; i<data.genres.length; i++ ){
         results += data.genres[i].name+' ';
        }
        let overview = data.overview ? data.overview : "등록 된 줄거리가 없습니다.";
        let card = `<dl class="title_wrap">
        <dt class="original_title">원제</dt>
        <dd>${data.original_title}</dd>
    </dl>
    <dl class="release_date_wrap">
        <dt class="release_date">개봉일</dt>
        <dd> ${data.release_date}</dd>
    </dl>
    <dl class="country_wrap">
        <dt class="country">국가</dt>
        <dd> ${data.production_countries[0].name}</dd>
    </dl>
    <dl class="ganre_wrap">
        <dt class="ganre">장르</dt>
        <dd> ${results}</dd>
    </dl>
    <dl class="runtime_wrap">
        <dt class="runtime">상영시간</dt>
        <dd>${data.runtime} 분 </dd>
    </dl>
    <dl class="overview_wrap">
        <dt class="overview">내용</dt>
        <dd>${overview}</dd>
    </dl>`;
        let wrap = document.querySelector('.info_wrap');
        wrap.innerHTML = card;
           })
 



