$(function(){
    $(".blocks_item_link").on('click',function(){
        $(".blocks_item_link").removeClass("selected");
        $(this).addClass("selected");
        $(".faq_answer_blocks").hide();
        $($(this).attr("href")).show();
        //console.log("hello");
    })
})

$(function(){
    $(".faq_answer_list>li").click(function(){
        const clicked = $(this).next("div").slideToggle(200);
        $(this).parent("ul").siblings("ul").children("div").slideUp(200);
    });

});