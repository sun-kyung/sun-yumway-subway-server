package sun.yumway.subway;

import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sun.yumway.subway.context.ApplicationContextListener;
import sun.yumway.subway.domain.Board;
import sun.yumway.subway.domain.Order;
import sun.yumway.subway.domain.Side;

public class ServerApp {
  
  Set<ApplicationContextListener> listeners = new HashSet<>();
  
  Map<String, Object> context = new HashMap<>();
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
  
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }
  
  private void notifyApplicationInitialized() {
    for(ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }
  
  private void notifyApplicationDestroyed() {
    for(ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }
  
  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitialized();
    
    List<Order> orderList = (List<Order>) context.get("orderList");
    List<Side> sideList = (List<Side>) context.get("sideList");
    List<Board> boardList = (List<Board>) context.get("boardList");
    
    notifyApplicationDestroyed();
  }

  public static void main(String[] args) {
    System.out.println("서버 맛있는 서브웨이 시스템입니다");
    
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
/*
    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중 ... ");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음");

        processRequest(socket);
        System.out.println("--------------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }*/
  }

  static void processRequest(Socket clientSocket) {
    /*
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음");
      String message = in.nextLine();

      System.out.println("클라이언트가 보낸 메시지를 수신하였음");

      System.out.println("클라이언트: " + message);

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
    }
    */
  }
}
