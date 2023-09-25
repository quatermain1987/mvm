<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./mypage/css/profile_edit.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="./mypage/js/profile_edit.js"></script>
<title>Document</title>
</head>
<body>
	<!-- hidden방식으로 xhr객체를 이용해 값을 전달하면 js에서 참조할수있음 이 passwd는 mypage.js에서 사용예정-->
	<form action="myprofileedit.do" enctype="multipart/form-data" method="post">
		<div class="portrait">
			<c:if test="${empty user.userPic}">
				<img src="img/User_cicrle_light.svg" alt="유저이미지" width="150"
					id="userImage" />
			</c:if>
			<c:if test="${not empty user.userPic}">
				<img src="${user.userPic}" alt="유저이미지" width="150" id="userImage" />
			</c:if>
			<label for="file_uploade" class="button_submit">이미지 변경</label>
			<input
				type="file" name="file1" id=file_uploade style="display: none"
				onchange="readURL(this);" accept="image/*">
			<!-- 미구현 <button class="button_submit">이미지 삭제</button> -->
		</div>
		<div class="profile">
			<div class="profile_info">
				<h2>프로필 정보</h2>
				<div class="row">
					<div class="half_row">
						<label for="nick">닉네임</label>
						<!-- id값을 nickname으로 주면 기능을 안해서 불가피하게 지음 -->
						<input type="text" id="nick" placeholder="닉네임"
							value="${user.userNickname}" name="nick">
						<p id="error_text_nickname">닉네임은 최소 2자, 최대 20자까지 입력이 가능해요</p>
					</div>
				</div>

				<div class="row">
					<div class="self_intro">
						<label for="self_intro">자기소개</label>
						<c:if test="${empty user.userIntroduce}">
							<textarea id="self_intro" rows="5" placeholder="여기에 자기소개해 보세요"
								name="introduce"></textarea>
						</c:if>
						<c:if test="${not empty user.userIntroduce}">
							<textarea id="self_intro" rows="5" name="introduce">${user.userIntroduce}</textarea>
						</c:if>
					</div>
				</div>
			</div>

			<div class="personal_info">
				<h2>개인 정보</h2>
				<div class="row">
					<div class="half_row">
						<label for="user_email">이메일</label> <input type="text"
							id="user_email" placeholder="이메일" value="${user.userEmail}"
							disabled name="email">
					</div>
				</div>

				<div class="row">
					<div class="half_row">
						<label for="user_name">이름</label> <input type="text"
							id="user_name" placeholder="이름" value="${user.userName}"
							name="name">
					</div>
				</div>

				<div class="row">
					<div class="half_row">
						<label for="user_birth">생년월일</label> <input type="text"
							id="birthdate" value="${user.userBirth}"
							name="birth" disabled>
					</div>
				</div>

				<div class="row">
					<div class="half_row">
						<label for="user_password">비밀번호</label> <input type="password"
							id="user_password" placeholder="비밀번호" name="passwd">
						<p id="error_text_password">필수 입력사항이에요</p>
					</div>

					<div class="half_row">
						<label for="password_check">비밀번호 확인</label> <input type="password"
							id="password_check" placeholder="비밀번호 확인">
						<p id="error_text_passcheck">필수 입력사항이에요</p>
					</div>
				</div>
			</div>

			<div class="btn_container">
				<input type="button" class="delete" value="회원탈퇴" onclick="popupDelete(event)">
				<input type="reset" value="취소">
				<input type="submit" value="완료" id="submit_btn" disabled>
			</div>
		</div>
	</form>
</body>
</html>