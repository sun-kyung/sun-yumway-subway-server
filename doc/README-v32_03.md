# 32_3 - LMS 관리 데이터를 파일에서 로딩하고 파일로 저장하기

## 학습목표

- 데이터 파일을 읽고 쓸 수 있다

## 실습 소스 및 결과

- src/main/java/sun/yumway/subway/domain 패키지 생성
- src/main/java/sun/yumway/subway/domain/Board.java 추가
- src/main/java/sun/yumway/subway/domain/Lesson.java 추가
- src/main/java/sun/yumway/subway/domain/Member.java 추가
- src/main/java/sun/yumway/subway/context 패키지 생성
- src/main/java/sun/yumway/subway/context/ApplicationContextListener.java 추가
- src/main/java/sun/yumway/subway/DataLoaderListener.java 추가
- src/main/java/sun/yumway/subway/ServerApp.java 변경


## 실습  

### 훈련 1: 31번 프로젝트에서 데이터를 저장하고 로딩하는 코드를 가져오라

- sun.yumway.subway.domain 패키지를 생성한다
- sun.yumway.subway.domain.* 클래스를 가져온다
- sun.yumway.subway.context 패키지를 생성한다
- sun.yumway.subway.context.ApplicationContextListener 를 가져온다
- sun.yumway.subway.DataLoaderListener 를 가져온다
- ServerApp.java 변경
  - 옵저버를 등록하고 호출하는 코드를 적용한다

### 훈련 2: 클라이언트가 보낸 메세지를 읽고 응답 메세지를 전송하라

- ServerApp.java 변경
