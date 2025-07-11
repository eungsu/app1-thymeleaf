# spring boot 애플리케이션
spring boot, spring security, thymeleaf, h2, mybatis 등을 활용한 샘플 애플리케이션이다.

## 프로젝트 정의
### 프로젝트 설정
- spring boot : 3.3.13
- name : app1-thymeleaf
- type : maven
- packaging : jar
- java version : 21
- language : java
- group : com.example
- artifact : app1-thymeleaf

### 의존성
#### spring-boot-starter 의존성
- web
- spring security
- thymeleaf
- h2
- mybatis
- validation
- lombok
- devTools
#### 기타 의존성
- thymeleaf-layout-dialect
- modelmapper
- commonmark, commonmark-ext-gfm-tables, commonmark-ext-image-attributes (마크다운 지원 라이브러리)

## 프로젝트 구조
```pgsql
app1-thymeleaf
  ├── src/main/java/
  └── src/main/resources/
    └── db/
      └── h2/
        ├── schema.sql               # 테이블 스키마 정의, 애플리케이션 실행시 테이블을 생성한다.
        └── data.sql                 # 초기 데이터 정의, 애플리케이션 실행시 초기 데이터를 저장한다.
    ├── mapper/                      # mybatis SQL 매퍼 파일
    ├── static                       # 정적 HTML 컨텐츠 폴더
    └── templates/                   # thymeleaf 뷰 템플릿 폴더
      ├── fragments/                 # thymeleaf fragment 폴더, 여러 뷰 템플릿에서 공통으로 사용되는 뷰 템플릿
      ├── layout/                    # thymeleaf 레이아웃 뷰 템플릿 폴더
      ├── posts/                     # 게시글 관련 뷰 템플릿 폴더
      ├── index.html                 # 홈 화면 뷰 템플릿
      ├── error-page.html            # 오류 화면 뷰 템플릿
      ├── login.html                 # 로그인 화면 뷰 템플릿
      └── signup.html                # 회원가입 화면 뷰 템플릿
    └── application.properties       # spring boot 환경설정 파일
```








