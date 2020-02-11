package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderUpdateServlet implements Servlet {
  List<Order> orders;

  public OrderUpdateServlet(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
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
  }

}
