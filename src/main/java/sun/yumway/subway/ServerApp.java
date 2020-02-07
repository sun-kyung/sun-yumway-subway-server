package sun.yumway.subway;

import java.io.IOException;
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

  List<Board> boards;
  List<Order> orders;
  List<Side> sides;

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

  @SuppressWarnings("unchecked")
  public void service() {

    notifyApplicationInitialized();

    orders = (List<Order>) context.get("orderList");
    sides = (List<Side>) context.get("sideList");
    boards = (List<Board>) context.get("boardList");

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

          case "/board/list":
            listBoard(out);
            break;


          case "/board/add":
            addBoard(in, out);
            break;
          case "/board/detail":
            detailBoard(in, out);
            break;
          case "/board/update":
            updateBoard(in, out);
            break;
          case "/board/delete":
            deleteBoard(in, out);
            break;
          case "/order/list":
            listOrder(out);
            break;


          case "/order/add":
            addOrder(in, out);
            break;
          case "/order/detail":
            detailOrder(in, out);
          case "/order/update":
            updateOrder(in, out);
            break;
          case "/order/delete":
            deleteOrder(in, out);
            break;
          case "/side/list":

            listSide(out);
            break;
          case "/side/add":
            addSide(in, out);
            break;
          case "/side/detail":
            detailSide(in, out);
            break;
          case "/side/update":
            updateSide(in, out);
            break;
          case "/side/delete":
            deleteSide(in, out);
            break;
          default:
            notFound(out);
        }
        out.flush();
        System.out.println("클라이언트로 메세지를 전송하였음");
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

  private void deleteSide(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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

  private void updateSide(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void detailSide(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void addSide(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void listSide(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(context.get(sides));
  }

  private void deleteOrder(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void updateOrder(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void detailOrder(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void addOrder(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void listOrder(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(context.get(orders));
  }

  private void deleteBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void updateBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void detailBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void addBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
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
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  private void listBoard(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(context.get(boards));
  }

  public static void main(String[] args) {
    System.out.println("서버 맛있는 서브웨이 시스템입니다");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
