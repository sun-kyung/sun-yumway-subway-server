# 32_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화 시키기

## 학습목표

- DAO(Data Access Object)의 역할과 이점을 이해한다.
- 데이터 처리 코드를 DAO로 분리할 수 있다. 

### DAO(Data Access Object)

- 데이터 처리 역할을 수행하는 객체이다.
- 데이터 처리 방식을 캡슐화(=추상화=클래스로 정의)하여 객체의 사용을 일관성 있게 만든다.
  - 즉 데이터 처리 방식(배열, 스택, 큐, 맵, 파일, 데이터베이스 등)을 
    클래스로 포장(캡슐화)하면 데이터 처리 방식에 상관없이 메서드 사용을 통일할 수 있다.
 

## 실습 소스 및 결과

- src/main/java/sun/yumway/subway/dao 패키지 생성
- src/main/java/sun/yumway/subway/dao/BoardObjectFileDao.java 추가
- src/main/java/sun/yumway/subway/dao/OrderObjectFileDao.java 추가
- src/main/java/sun/yumway/subway/dao/SideObjectFileDao.java 추가
- src/main/java/sun/yumway/subway/ServerApp.java 변경

## 실습  

### 훈련 1: 게시물 데이터를 처리하는 DAO 클래스를 정의하라.

- sun.yumway.subway.dao 패키지를 생성한다.
- sun.yumway.subway.dao.BoardObjectFileDao 클래스를 정의한다.

### 훈련 2: BoardObjectFileDao 객체를 적용하라.

- sun.yumway.subway.DataLoaderListener 를 변경한다.
  - 게시물 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 BoardObjectFileDao 객체를 생성한다.
- sun.yumway.subway.ServerApp 을 변경한다.
  - Map에서 BoardObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- BoardXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 BoardObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 BoardObjectFileDao 객체를 통해 처리한다.
  
  
### 훈련 3: 샌드위치 데이터를 처리하는 DAO 클래스를 정의하고 적용하라.

- sun.yumway.subway.dao.OrderObjectFileDao 클래스를 정의한다.
- sun.yumway.subway.DataLoaderListener 를 변경한다.
  - 수업 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 OrderObjectFileDao 객체를 생성한다.
- com.eomcs.lms.ServerApp 을 변경한다.
  - Map에서 OrderObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- OrderXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 OrderObjectFileDao 객체를 받는다.
  - 샌드위치를 저장하고, 조회하고, 변경하고, 삭제할 때 OrderObjectFileDao 객체를 통해 처리한다.

### 훈련 4: 사이드 데이터를 처리하는 DAO 클래스를 정의하고 적용하라.

- sun.yumway.subway.dao.SideObjectFileDao 클래스를 정의한다.
- sun.yumway.subway.DataLoaderListener 를 변경한다.
  - 회원 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 SideObjectFileDao 객체를 생성한다.
- sun.yumway.subway.ServerApp 을 변경한다.
  - Map에서 SideObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- SideXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 SideObjectFileDao 객체를 받는다.
  - 사이드를 저장하고, 조회하고, 변경하고, 삭제할 때 SideObjectFileDao 객체를 통해 처리한다.
  
  