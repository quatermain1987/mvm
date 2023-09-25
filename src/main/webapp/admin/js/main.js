

$(function() {
	$('tr > td').addClass(function(index) {
		return 'td' + (index % $('thead > tr').children().length);
	})
})

now = new Date
expireDate = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 23, 59, 59)
hitCount = eval(cookieVal("pageHit"))
hitCount++
document.cookie = "pageHit=" + hitCount + "; expires=" + expireDate
function cookieVal(cookieName) {
	thisCookie = document.cookie.split("; ")
	for (i = 0; i < thisCookie.length; i++) {
		if (cookieName == thisCookie[i].split("=")[0]) {
			return thisCookie[i].split("=")[1]
		}
	}
	return 0
}
/*alert(hitCount)*/
$(function(){
	$('#visitCount').text(hitCount)
})

