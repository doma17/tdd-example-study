## tdd-example-study

TDD 연습용 프로젝트

### 프로젝트 개요

- 기술 스택: Java, Spring Boot, Gradle, MariaDB
- 테스트 유형: 단위 테스트, 통합 테스트
- 주요 기능: 게시물 생성 및 조회 API

### 고민 사항
아래에 대한 내용을 고민하며 진행한 프로젝트입니다.

- Spring Security 사용시 MockMvc 동작
- 통합 테스트에서 MockMvc와 실제 DB 연동
- TDD 적용을 위한 테스트 작성 순서

### 주요 구성 요소

도메인 모델

- PostEntity: 게시물 엔티티
- PostRepository: 게시물 데이터 접근 계층
- PostService: 게시물 비즈니스 로직 처리

API

- PostController: 게시물 REST API
- PageController: 웹 페이지 컨트롤러

테스트 종류

1. 단위 테스트
   - ValidationUtilTest: 이메일 유효성 검증
   - PostServiceTest: 서비스 계층 테스트
2. 웹 계층 테스트
   - PostControllerTest: 컨트롤러 단위 테스트
   - PageControllerTest: 페이지 컨트롤러 테스트
3. 통합 테스트
   - PostControllerIntegration: 실제 DB 연동한 통합 테스트

TDD 적용 사례

- 요구사항에 대한 실패 테스트 먼저 작성
- 테스트 통과를 위한 최소한의 코드 구현
- 테스트가 통과하면 리팩토링 수행
- 단계별 구현으로 견고한 코드베이스 구축
- 위의 과정을 반복하여 기능 추가 및 개선

### 개발 환경 설정

#### Docker를 이용한 MariaDB 설정

```bash
docker run --name mariadb \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=tdd_example_study \
-p 3306:3306 \
-d mariadb:latest
```