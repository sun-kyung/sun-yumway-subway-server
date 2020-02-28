package sun.yumway.subway;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import sun.yumway.subway.context.ApplicationContextListener;
import sun.yumway.subway.dao.json.BoardJsonFileDao;
import sun.yumway.subway.dao.json.OrderJsonFileDao;
import sun.yumway.subway.dao.json.SideJsonFileDao;
import sun.yumway.subway.servlet.BoardAddServlet;
import sun.yumway.subway.servlet.BoardDeleteServlet;
import sun.yumway.subway.servlet.BoardDetailServlet;
import sun.yumway.subway.servlet.BoardListServlet;
import sun.yumway.subway.servlet.BoardUpdateServlet;
import sun.yumway.subway.servlet.OrderAddServlet;
import sun.yumway.subway.servlet.OrderDeleteServlet;
import sun.yumway.subway.servlet.OrderDetailServlet;
import sun.yumway.subway.servlet.OrderListServlet;
import sun.yumway.subway.servlet.OrderUpdateServlet;
import sun.yumway.subway.servlet.Servlet;
import sun.yumway.subway.servlet.SideAddServlet;
import sun.yumway.subway.servlet.SideDeleteServlet;
import sun.yumway.subway.servlet.SideDetailServlet;
import sun.yumway.subway.servlet.SideListServlet;
import sun.yumway.subway.servlet.SideUpdateServlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {

    notifyApplicationInitialized();

    BoardJsonFileDao boardDao = (BoardJsonFileDao) context.get("boardDao");
    OrderJsonFileDao orderDao = (OrderJsonFileDao) context.get("orderDao");
    SideJsonFileDao sideDao = (SideJsonFileDao) context.get("sideDao");

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    servletMap.put("/order/list", new OrderListServlet(orderDao));
    servletMap.put("/order/add", new OrderAddServlet(orderDao));
    servletMap.put("/order/detail", new OrderDetailServlet(orderDao));
    servletMap.put("/order/update", new OrderUpdateServlet(orderDao));
    servletMap.put("/order/delete", new OrderDeleteServlet(orderDao));

    servletMap.put("/side/list", new SideListServlet(sideDao));
    servletMap.put("/side/add", new SideAddServlet(sideDao));
    servletMap.put("/side/detail", new SideDetailServlet(sideDao));
    servletMap.put("/side/update", new SideUpdateServlet(sideDao));
    servletMap.put("/side/delete", new SideDeleteServlet(sideDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중 ... ");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음");

        if (processRequest(socket) == 9) {
          break;
        }
        System.out.println("--------------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }
    notifyApplicationDestroyed();
  }

  int processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였음");

        switch (request) {
          case "quit":
            quit(out);
            return 0;

          case "/server/stop":
            quit(out);
            return 9;
        }
        Servlet servlet = servletMap.get(request);

        if (servlet != null) {

          try {
            servlet.service(in, out);
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            System.out.println("클라이언트 요청처리 중 오류 발생: ");
            e.printStackTrace();
          }
        } else {
          notFound(out);
        }
        out.flush();
        System.out.println("클라이언트에게 응답하였음");
        System.out.println("------------------------------------");
      }
    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 맛있는 서브웨이 시스템입니다");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
