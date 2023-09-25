
//평균값
const avgrating = document.querySelector('#avgrating').value;

$(function(){
//영화정보 받아오기

const urlParams = new URL(location.href).searchParams;
const mov_no = urlParams.get('mov_no');
const url ='https://api.themoviedb.org/3/movie/';
const api_key ='?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko';

fetch(url+mov_no+api_key)
.then((response) => response.json())
.then((data) => {
	console.log(data)
	 let results ="";
   	for( let i=0; i<data.genres.length; i++ ){
   	 results += data.genres[i].name+"・";  
  	 }
	 $(".moive_information").html('');
	
	            let page=`
	           <a href="/Movivum/detail.do?mov_no=${data.id}">
           	  <div class="moive_poster">
           	  <img src="${'https://image.tmdb.org/t/p/w342/'+data.poster_path}" class="moive_img"/>
           	  </div></a>
                    <span class="moive_text">
                        <h2 class="moive_title">${data.title}</h2>
                        <span class="movie_year">${data.release_date.substr(0,4)}</span>
                        <span>|</span>
                        <span class="movie_genre">${results.slice(0, -1)}</span>
                        <div class="moive_rating"> 평균 <i class="fa-solid fa-star"></i>${avgrating}</div>
                    </span>
            `
                $(".moive_information").append(page);
 			  	
 			  	let title = data.title;
   				 $('.mov_title').each(function(){
				 $(this).append("<b>"+title+"</b>");
						
			})
      })

//선택값 가져오기

$("select").on('change', function() {
  // 선택된 옵션의 값 가져오기
  let isSelectedVal = $(this).val();
  
  // 폼에 hidden input 추가하여 전송
  $(this).append(`<input type='hidden' name='select' value='${isSelectedVal}'>`);
  $("#selectform").submit();
});


/*
$("select").on('change', function(){
 	  let isSelected = $(this).addClass("selected");
 	  let isSelectedVal= isSelected.val();
 	  if(isSelected){
		$(this).append(`<input type='hidden' name='select' value='${isSelectedVal}'>`);
		$("#selectform").submit();	   
	   }
});
*/


})//레디
