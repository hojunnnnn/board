# :paperclip: Basic board Project
> 전반적인 웹의 기본 소양이 되는 게시판 프로젝트입니다.

![메인 화면](https://user-images.githubusercontent.com/59757689/149616313-dbeace05-67dc-4d70-b6a1-630d601b6455.PNG)

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

- 마치며
  - 프로젝트 보완사항
  - 후기

## 들어가며
**1. 프로젝트 소개**

프로젝트를 시작하게 된 계기는 웹 프로그래밍의 기본 소양이라 할 수 있는 게시판을 직접 만들어보며 배우고자 시작하게 되었습니다.   
독학으로 관련 기술들을 학습한 이후 제작한 개인 프로젝트이기 때문에 개인적인 만족감을 가지고 있는 프로젝트입니다.

**2. 프로젝트 기능**

프로젝트의 주요 기능은 다음과 같습니다.
- **게시판 -** CRUD 기능, 조회수, 페이징 및 검색 처리
- **사용자 -** Security 회원가입 및 로그인, OAuth 2.0 구글, 네이버 로그인, 회원정보 수정, 회원가입시 유효성 검사 및 중복 검사
- **댓글 -** CRUD 기능

**3. 개발 환경**

- Java 11
- SpringBoot 2.5.6
- Gradle 7.2
- MySQL 8.0.19
- JPA(Spring Data JPA)
- Spring Security
- OAuth 2.0
- Mustache


## 구조 및 설계   
**1. 패키지 구조**
   
<details>
  
<summary>보기</summary>   
 

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
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsIndexController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
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
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsApiControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostsRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardApplicationTests.java
 ```
  
 </details>
     
     
 **2. DB 설계**

![erd 3차 2022-01-03](https://user-images.githubusercontent.com/59757689/148910882-2ac9ec57-c339-4bef-a6d5-13025a8d9ac9.PNG)   
![posts 테이블 db 설계](https://user-images.githubusercontent.com/59757689/148910938-c6a99c8e-fefc-467b-a2af-a68a00e01a11.PNG)   
![user 테이블 db 설계](https://user-images.githubusercontent.com/59757689/149279956-b0a184da-9b19-4bcf-9ce8-6c001ef81f1d.PNG) 
![comment 테이블 db 설계](https://user-images.githubusercontent.com/59757689/148910946-02280553-97ce-4d82-bbda-9c911ea89bd4.PNG)   
created_date와 modified_date는 날짜 포맷을 적용해주기 위해 datetime > varchar로 변경했습니다.   
   
<br/>

**3. API 설계**

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
- <a href="https://dev-coco.tistory.com/136" target="_blank">[리팩토링]Dto Class를 Inner Class로 한번에 관리하기</a>

## 마치며
**1. 프로젝트 보완사항**

프로젝트 초기에 구상한 기능은 기본적인 CRUD 즉, 게시판에 올라오는 게시글을 대상으로 Create, Read, Update, Delete가 가능한 게시판이었습니다.   
또, MVC 설계에서 Controller, Service, Repository, View의 역할에 대한 구분도 명확하게 하고 싶다는 생각이 들었습니다.   
템플릿 엔진으로 Mustache를 선택했는데, 그 이유는 Mustache는 단순히 화면에 데이터를 렌더링 하는 엔진이고 Logic-less 하기 때문에   
View의 역할과 서버의 역할이 명확하게 분리되어 OOP의 5원칙 중 하나인 SRP를 지킬 수 있었고,   
xss를 기본적으로 방지(이스케이프)할 수 있으며, 다른 템플릿에 비해 빠른 로딩 속도를 자랑했기에 Mustache를 사용하게 되었습니다.   
그러나 게시판 CRUD 기능이 완성되어 갈 때 쯤, 아쉬운 부분이 계속해서 생겨 몇몇 기능들을 추가하게 되었습니다.   
그래서 데이터를 렌더링 하기 전 서버에서 전처리를 하거나 화면에 표시된 후에 자바스크립트로 후처리를 해줬지만   
보완해야 할 부분이 아직 몇 개 남아있다고 생각합니다.   
<details>
  <summary>보완사항 보기</summary>
     
  
- 페이징 처리 및 검색 페이징에서 페이지 번호 활성화
- 페이지 번호는 10페이지 단위로 보여주기
- 페이지 처음, 끝으로 이동하는 버튼
- 생성, 수정시간 format 설정 varchar > datetime
- 다른 사용자와 자신의 댓글이 댓글란에 있을때 자신의 댓글만 수정,삭제 버튼 보이기
- 쿠키나 세션을 이용해 조회수 중복 카운트 방지
- 파일 업로드 기능 추가
- 좋아요 기능 추가
  
</details>   

추후에 브랜치를 나눠 Mustache에서 Thymeleaf로 조금씩 바꾸며 프로젝트 완성도를 높이고, 고도화 할 계획에 있습니다.   


**2. 후기**

혼자 독학하며 처음 만들어본 프로젝트이기 때문에,   
공부한 내용을 사용해보는 설렘만큼이나 부족한 부분에 대한 아쉬움도 많이 남았습니다.   
효율적인 설계를 위해 고민하고 찾아보며 실제로 많이 공부할 수 있었던 부분도 많았습니다.   
책이나 블로그, 강의로 공부한 예제에서 납득했던 부분들은 실제로 코드를 짜면서 다양한 애로 사항을 마주했고   
'이 로직은 이 단계에서 처리하는게 맞는가', '각 레이어간 데이터 전달은 어떤 방식이든 DTO로 하는게 맞는가' 등   
여러 고민에 빠져 헤맨적도 있었지만, 다행히 결과는 대부분 최선을 찾았었던 것 같습니다.   
그리고 내가 만든 코드를 남에게 보여줬을 때, 누군가 코드의 근거를 물어본다면   
과연 자신 있게 나의 생각을 잘 얘기할 수 있을까 라는 생각을 굉장히 많이 하게 되었습니다.   
그래서 하나를 구현할 때 '이렇게 구현 하는 것이 과연 최선인가', '더 나은 Best Practice는 없을까'   
스스로 의심하고 고민하게 되는 습관을 가지게 되었습니다.   

두 번째로 기술적인 부분에서 더 공부하고 싶은 '방향'을 찾을 수 있었습니다.   
이번 프로젝트는 저에게 좋은 경험이 되었고, 저의 부족한 부분을 스스로 알 수 있는 좋은 계기가 되었습니다.   
부족한 부분에 대해 스스로 인지하고 있고, 더 깊게 공부하며 스스로 발전할 수 있는 '방향'을 다시한번 찾을 수 있게 되었습니다.   
이를 통해 더 나은 웹 애플리케이션을 만들 수 있을 것 같다는 자신감도 생겼습니다.   

끝까지 읽어주셔서 감사합니다.

ps. 이 프로젝트는 지속적인 수정을 거치고 있습니다.
