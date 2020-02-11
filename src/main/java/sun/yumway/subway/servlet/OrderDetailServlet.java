package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderDetailServlet implements Servlet {
  List<Order> orders;

  public OrderDetailServlet(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
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
  }

}
