# :paperclip: Basic board Project
> 전반적인 웹의 기본 소양이 되는 게시판 프로젝트입니다.

![메인 화면](https://user-images.githubusercontent.com/59757689/148895846-80887fe7-c3a4-488b-ae26-dd2ca0b78e17.PNG)

## 목차
- 들어가며
  - 프로젝트 소개
  - 프로젝트 기능
  - 개발 환경

- 구조 및 설계
  - 패키지 구조
  - DB 설계
  - API 설계

- 개발 내용
___
## 들어가며
1. **프로젝트 소개**

프로젝트를 시작하게 된 계기는 웹 프로그래밍의 기본 소양이라 할 수 있는 게시판을 만들어보며 배우고자 시작하게되었습니다.   
독학으로 관련 기술들을 학습한 이후 제작한 개인 프로젝트이기 때문에 자신은 없지만 개인적인 만족감을 가지고 있는 프로젝트입니다.

2. **프로젝트 기능**

프로젝트의 주요 기능은 다음과 같습니다.
- **게시판 -** CRUD 기능, 조회수, 페이징 및 검색 처리
- **사용자 -** Security 회원가입 및 로그인, OAuth 2.0 구글, 네이버 로그인, 회원정보 수정, 회원가입시 유효성 검사 및 중복 검사
- **댓글 -** CRUD 기능

3. **개발 환경**

- Java 11
- SpringBoot 2.5.6
- Gradle 7.2
- MySQL 8.0.19
- JPA(Spring Data JPA)
- Spring Security
- OAuth 2.0
- Mustache


## 구조 및 설계   
1. **패키지 구조**
```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂coco
 ┃ ┃ ┃ ┃ ┗ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomAuthFailureHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetails.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LoginUserArgumentResolver.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂oauth
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomOAuth2UserService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OAuthAttributes.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Comment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂posts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Posts.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Role.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TimeEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂validator
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AbstractValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailCheckValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NicknameCheckValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UsernameCheckValidator.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂web
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsIndexController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDto.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┗ 📜app.css
 ┃ ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┃ ┗ 📜naver.ico
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┗ 📜app.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┣ 📜form.mustache
 ┃ ┃ ┃ ┃ ┗ 📜list.mustache
 ┃ ┃ ┃ ┣ 📂layout
 ┃ ┃ ┃ ┃ ┣ 📜footer.mustache
 ┃ ┃ ┃ ┃ ┗ 📜header.mustache
 ┃ ┃ ┃ ┣ 📂posts
 ┃ ┃ ┃ ┃ ┣ 📜posts-page.mustache
 ┃ ┃ ┃ ┃ ┣ 📜posts-read.mustache
 ┃ ┃ ┃ ┃ ┣ 📜posts-search.mustache
 ┃ ┃ ┃ ┃ ┣ 📜posts-update.mustache
 ┃ ┃ ┃ ┃ ┗ 📜posts-write.mustache
 ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┣ 📜user-join.mustache
 ┃ ┃ ┃ ┃ ┣ 📜user-login.mustache
 ┃ ┃ ┃ ┃ ┗ 📜user-modify.mustache
 ┃ ┃ ┃ ┗ 📜index.mustache
 ┃ ┃ ┣ 📜application-oauth.properties
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂coco
 ┃ ┃ ┃ ┃ ┗ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfigTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂posts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂posts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂web
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsApiControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardApplicationTests.java
 ```
 
 2. **DB 설계**

![erd 3차 2022-01-03](https://user-images.githubusercontent.com/59757689/148910882-2ac9ec57-c339-4bef-a6d5-13025a8d9ac9.PNG)   
![posts 테이블 db 설계](https://user-images.githubusercontent.com/59757689/148910938-c6a99c8e-fefc-467b-a2af-a68a00e01a11.PNG)   
![user 테이블 db 설계](https://user-images.githubusercontent.com/59757689/148910944-280c26c6-0fdf-4a12-8547-16688127b1ff.PNG)   
![comment 테이블 db 설계](https://user-images.githubusercontent.com/59757689/148910946-02280553-97ce-4d82-bbda-9c911ea89bd4.PNG)   
created_date와 modified_date는 날짜 포맷을 적용해주어 datetime > varchar로 변경되었습니다.

3. **API 설계**

![게시글 관련 API 설계](https://user-images.githubusercontent.com/59757689/148911404-cea959a0-0753-4548-9d87-1c94bc9572b9.PNG)   
![회원 관련 API 설계 (2)](https://user-images.githubusercontent.com/59757689/148911411-0cfb65ee-5782-4f04-a7c9-7dcc84abfed8.PNG)   
![댓글 관련 API 설계](https://user-images.githubusercontent.com/59757689/148911410-9a7729af-bb3c-49e3-b180-c52ea12ee75c.PNG)   

## 개발 내용

- <a href="https://dev-coco.tistory.com/113" target="_blank">게시판 조회수 기능 추가</a>
- <a href="https://dev-coco.tistory.com/114" target="_blank">게시판 페이징 처리 구현</a>
- <a href="https://dev-coco.tistory.com/115" target="_blank">게시판 검색처리 및 페이징 구현</a>
- <a href="https://dev-coco.tistory.com/120" target="_blank">Security 회원가입 및 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/124" target="_blank">회원가입 Validation 유효성 검사</a>
- <a href="https://dev-coco.tistory.com/125" target="_blank">회원가입 Validation 커스터마이징 중복 검사</a>
- <a href="https://dev-coco.tistory.com/126" target="_blank">Security 로그인 실패시 메시지 출력하기</a>
- <a href="https://dev-coco.tistory.com/127" target="_blank">Security 회원정보 수정(ajax)</a>
- <a href="https://dev-coco.tistory.com/128" target="_blank">OAuth 2.0 구글 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/129" target="_blank">OAuth 2.0 네이버 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/130" target="_blank">JPA 연관관계 매핑으로 글 작성자만 수정, 삭제 가능하게 하기</a>
- <a href="https://dev-coco.tistory.com/132" target="_blank">게시판 댓글 작성 및 조회 구현</a>
- <a href="https://dev-coco.tistory.com/134" target="_blank">게시판 댓글 수정 및 삭제 구현</a>
- <a href="https://dev-coco.tistory.com/136" target="_blank">게시판 댓글 작성자만 수정, 삭제 가능하게 하기</a>
