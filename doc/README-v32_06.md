# 32_6 - 커맨드 패턴을 적용하여 요청 처리 메서드를 객체화 하기 

## 학습목표

- 커맨드 패턴의 동작 원리를 인해한다.
- 커맨드 패턴을 코드에 적용할 수 있다. 

## 실습 소스 및 결과

- src/main/java/sun/yumway/subway/servlet 패키지 생성
- src/main/java/sun/yumway/subway/servlet/Servlet.java 추가
- src/main/java/sun/yumway/subway/servlet/BoardListServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/BoardAddServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/BoardDetailServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/BoardUpdateServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/BoardDeleteServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/OrderListServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/OrderAddServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/OrderUpdateServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/OrderDetailServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/OrderDeleteServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/SideListServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/SideAddServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/SideUpdateServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/SideDetailServlet.java 추가
- src/main/java/sun/yumway/subway/servlet/SideDeleteServlet.java 추가
- src/main/java/sun/yumway/subway/ServerApp.java 변경

## 실습  

### 훈련 1: 커맨드 패턴의 인터페이스 정의하라.

- com.eomcs.lms.servlet 패키지를 생성한다.
- com.eomcs.lms.servlet.Servlet 인터페이스를 정의한다.

### 훈련 2: 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스를 정의하라.
 
- listBoard()를 BoardListServlet 클래스로 정의한다.
- addBoard()를 BoardAddServlet 클래스로 정의한다.
- detailBoard()를 BoardDetailServlet 클래스로 정의한다.
- updateBoard()를 BoardUpdateServlet 클래스로 정의한다.
- deleteBoard()를 BoardDeleteServlet 클래스로 정의한다.
- listOrder()를 OrderListServlet 클래스로 정의한다.
- addOrder()를 OrderAddServlet 클래스로 정의한다.
- updateOrder()를 OrderUpdateServlet 클래스로 정의한다.
- deleteOrder()를 OrderDeleteServlet 클래스로 정의한다.
- detailOrder()를 OrderDetailServlet 클래스로 정의한다.
- listSide()를 SideListServlet 클래스로 정의한다.
- addSide()를 SideAddServlet 클래스로 정의한다.
- updateSide()를 SideUpdateServlet 클래스로 정의한다.
- deleteSide()를 SideDeleteServlet 클래스로 정의한다.
- detailSide()를 SideDetailServlet 클래스로 정의한다.
