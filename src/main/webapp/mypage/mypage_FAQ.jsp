<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM | FAQ</title>
    <link rel="stylesheet" href="./mypage/css/mypage_FAQ.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="./mypage/js/FAQ.js"></script>
</head>

<body>
<!--(위까지 헤더)@@@@@@@@ 여기서부터 본문 내용 입니다! @@@@@@@@-->


                <h2>자주 묻는 질문</h2>
                <section class="fqa_blocks">
                    <ul class="blocks_list">
                        <li class="blocks_item selected" id="fqa1">
                            <a href="#answer1" class="blocks_item_link selected">
                                회원관련 문의
                            </a>
                        </li>

                        <li class="blocks_item" id="fqa2">
                            <a href="#answer2" class="blocks_item_link">
                                이용정지 문의
                            </a>
                        </li>
                        <li class="blocks_item" id="fqa3">
                            <a href="#answer3" class="blocks_item_link">
                                콘텐츠 관련문의
                            </a>
                        </li>
                        <li class="blocks_item" id="fqa4">
                            <a href="#answer4" class="blocks_item_link">
                                기타 문의
                            </a>
                        </li>
                    </ul>
                </section>

                <section class="faq_answer">
                    <article class="faq_answer_blocks" id="answer1">
                        <ul class="faq_answer_list">
                            <li id="answer1_1">
                                Q. 비밀번호를 잊어버렸어요.
                                <button
                                    class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                            <p>A. MOVIVUM 고객센터 E-Mail을 통해 연락해주세요.</p>
                            </div>
                        </ul>
                        <ul class="faq_answer_list">
                            <li id="answer1_2">
                                Q. 부적절한 댓글은 어떻게 신고하나요?
                                <button
                                    class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                            <p>A. 부적절한 코멘트/댓글은 해당 글 내의 우측 '신고' 버튼 터치를 통해 신고가 가능합니다. 허위신고가 반복될 경우 허위신고자에게도 이용정지가 가능합니다. </p>
                            </div>
                        </ul>
                    </article>

                    <article class="faq_answer_blocks" id="answer2">
                        <ul class="faq_answer_list">
                            <li id="answer2_1">
                                Q. 제 리뷰 또는 댓글이 신고되었어요.
                                <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                                A. 본인의 리뷰나 댓글이 타인에게 불쾌감을 주지 않았는 지 다시 한번 생각해보세요 :( <br>
                                이외 자세한 문의는 MOVIVUM 고객센터 E-Mail을 통해 연락 바랍니다.
                            </div>
                        </ul>
                        <ul class="faq_answer_list">
                            <li id="answer2_2">
                                Q. 이용정지 상태는 어떻게 풀 수 있나요?
                                <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                                A. 자세한 문의는 MOVIVUM 고객센터 E-Mail을 통해 연락 바랍니다.
                            </div>
                        </ul>
                    </article>

                    <article class="faq_answer_blocks" id="answer3">
                        <ul class="faq_answer_list">
                            <li id="answer3_1">
                                Q. 찾는 영화가 없어요.
                                <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                                A. 자세한 문의는 MOVIVUM 고객센터 E-Mail을 통해 연락 바랍니다.
                            </div>
                        </ul>
                        <ul class="faq_answer_list">
                        <li id="answer3_2">
                            Q. 영화정보가 올바르지 않아요.
                            <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                        </li>
                        <div class="faq_answer_list_answer">
                            A. 불편을 드려 죄송합니다:(<br>고객센터나 MOVIVUM 고객센터 E-Mail을 통해 해당내용을 작성하시어 문의 부탁드립니다.
                        </div>
                        </ul>
                        <ul class="faq_answer_list">
                            <li id="answer3_3">
                                Q. 영화 평점은 몇번 등록 가능한가요?
                                <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                                A. 1개의 영화당 1개의 평점만 등록 가능합니다.
                            </div>
                        </ul>
                    </article>

                    <article class="faq_answer_blocks" id="answer4">
                        <ul class="faq_answer_list">
                            <li id="answer4_1">
                                Q. 도전과제의 기준은 어떻게 되나요?
                                <button class="answerbtn"><i class="morebtn fa-solid fa-angle-down"></i></button>
                            </li>
                            <div class="faq_answer_list_answer">
                                A. 홈페이지 도전과제 탭에서 확인이 가능합니다:)
                            </div>
                        </ul>
                    </article>
                </section>

                <section class="faq_last">
                    <span class="faq_nomore">
                        찾으시는 내용이 없나요?
                    </span>
                    <span class="faq_write">
                    <form action="faqPro.do" method="POST">
                    <!-- /faq.do , FaqAction.java-->
                    <input type="hidden" name="session_id" value="${sessionScope.user_no}"/>
                        <button type="submit" class="faq_btn" value="submit">
                            문의하기
                        </button>
                        </form>
                    </span>
                </section> 
    
<!-- @@@@@@@@@@@@@@ 본문내용 끝입니다 @@@@@@@@@@@@@@ -->
</body>
</html>