package sun.yumway.subway;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였음");

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<Order> orders = (List<Order>) context.get("orderList");
        List<Side> sides = (List<Side>) context.get("sideList");
        List<Board> boards = (List<Board>) context.get("boardList");
        if (request.equals("/board/list")) {

          out.writeUTF("OK");
          out.reset();
          // 기존에 출력했던 List<Board>객체의 직렬화 데이터를 무시하고 새로 직렬화를 수행한다
          out.writeObject(context.get("boardList"));


        } else if (request.equals("/board/add")) {
          try {

            Board board = (Board) in.readObject();
            int i = 0;
            for (; i < boards.size(); i++) {
              if (boards.get(i).getNo() == board.getNo()) {
                break;
              }
            }
            if (i == boards.size()) {
              boards.add(board);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다");
            }


          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/board/detail")) {
          try {

            int no = in.readInt();
            Board board = null;
            for (Board b : boards) {
              if (b.getNo() == no) {
                board = b;
                break;
              }
            }
            if (board != null) {
              out.writeUTF("OK");
              out.writeObject(board);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다");
            }
            System.out.println("게시물을 저장하였습니다");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/board/update")) {
          try {

            Board board = (Board) in.readObject();
            int index = -1;
            for (int i = 0; i < boards.size(); i++) {
              if (boards.get(i).getNo() == board.getNo()) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              boards.set(index, board);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/board/delete")) {
          try {

            int no = in.readInt();
            int index = -1;
            for (int i = 0; i < boards.size(); i++) {
              if (boards.get(i).getNo() == no) {
                index = i;
                break;
              }
            }
            if (index != -1) { // 삭제하려는 번호의 게시물을 찾았다면
              boards.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/order/list")) {

          out.writeUTF("OK");
          out.reset();
          out.writeObject(context.get("orderList"));


        } else if (request.equals("/order/add")) {
          try {

            Order order = (Order) in.readObject();
            int i = 0;
            for (; i < orders.size(); i++) {
              if (orders.get(i).getNo() == order.getNo()) {
                break;
              }
            }
            if (i == orders.size()) {
              orders.add(order);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 주문이 있습니다");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/order/detail")) {
          try {

            int no = in.readInt();
            Order order = null;
            for (Order o : orders) {
              if (o.getNo() == no) {
                order = o;
                break;
              }
            }
            if (order != null) {
              out.writeUTF("OK");
              out.writeObject(order);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 주문이 없습니다");
            }
            System.out.println("주문을 저장하였습니다");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/order/update")) {
          try {
            Order order = (Order) in.readObject();
            int index = -1;
            for (int i = 0; i < orders.size(); i++) {
              if (orders.get(i).getNo() == order.getNo()) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              orders.set(index, order);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 주문이 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/order/delete")) {
          try {

            int no = in.readInt();
            int index = -1;
            for (int i = 0; i < orders.size(); i++) {
              if (orders.get(i).getNo() == no) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              orders.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 주문이 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/side/list")) {

          out.writeUTF("OK");
          out.reset();
          out.writeObject(context.get("sideList"));
        } else if (request.equals("/side/add")) {
          try {

            Side side = (Side) in.readObject();
            int i = 0;
            for (; i < sides.size(); i++) {
              if (sides.get(i).getNo() == side.getNo()) {
                break;
              }
            }
            if (i == sides.size()) {
              sides.add(side);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 사이드가 있습니다");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/side/detail")) {
          try {

            int no = in.readInt();
            Side side = null;
            for (Side m : sides) {
              if (m.getNo() == no) {
                side = m;
                break;
              }
            }
            if (side != null) {
              out.writeUTF("OK");
              out.writeObject(side);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 사이드가 없습니다");
            }
            System.out.println("사이드 정보를 저장하였습니다");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/side/update")) {
          try {

            Side side = (Side) in.readObject();
            int index = -1;
            for (int i = 0; i < sides.size(); i++) {
              if (sides.get(i).getNo() == side.getNo()) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              sides.set(index, side);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 사이드가 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if (request.equals("/side/delete")) {
          try {

            int no = in.readInt();
            int index = -1;
            for (int i = 0; i < sides.size(); i++) {
              if (sides.get(i).getNo() == no) {
                index = i;
                break;
              }
            }
            if (index != -1) { // 삭제하려는 번호의 게시물을 찾았다면
              sides.remove(index);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 사이드가 없습니다");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        }

        else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리할 수 없습니다");
        }
        out.flush();
      }
      System.out.println("클라이언트로 메세지를 전송하였음");

      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println("서버 맛있는 서브웨이 시스템입니다");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
