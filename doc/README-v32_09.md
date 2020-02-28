# 32_9 - 파일에 데이터를 저장할 때 JSON 형식을 사용하기

## 학습목표

- JSON(JavaScript Object Notation) 형식을 이해한다.
- Gson 라이브러리를 이용하여 JSON 형식의 데이터를 다룰 수 있다.

## 실습 소스 및 결과

- src/main/java/sun/yumway/subway/dao/json 패키지 추가
- src/main/java/sun/yumway/subway/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/sun/yumway/subway/dao/json/BoardJsonFileDao.java 변경
- src/main/java/sun/yumway/subway/dao/json/OrderJsonFileDao.java 변경
- src/main/java/sun/yumway/subway/dao/json/SideJsonFileDao.java 변경
- src/main/java/sun/yumway/subway/servlet/BoardXxxServlet.java 변경
- src/main/java/sun/yumway/subway/servlet/OrderXxxServlet.java 변경
- src/main/java/sun/yumway/subway/servlet/SideXxxServlet.java 변경
- src/main/java/sun/yumway/subway/DataLoaderListener.java 변경
- src/main/java/sun/yumway/subway/ServerApp.java 변경

## 실습  

### 훈련 1: JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스를 정의하라.

- sun.yumway.subway.dao.json 패키지를 생성한다.
- sun.yumway.subway.dao.json.AbstractJsonFileDao 클래스를 생성한다.

### 훈련 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- sun.yumway.subway.dao.BoardObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 3: OrderObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- sun.yumway.subway.dao.OrderObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 4: SideObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- sun.yumway.subway.dao.SideObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스를 변경하라.

- sun.yumway.subway.servlet.BoardXxxServlet 변경한다.
- sun.yumway.subway.servlet.LessonXxxServlet 변경한다.
- sun.yumway.subway.servlet.MemberXxxServlet 변경한다.

### 훈련 6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao를 준비하라.

- sun.yumway.subway.DataLoaderListener 변경한다.

### 훈련 7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입하라.

- sun.yumway.subway.ServerApp 변경한다.
 



  
  