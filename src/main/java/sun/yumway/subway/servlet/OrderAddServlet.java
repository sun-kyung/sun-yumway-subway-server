package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderAddServlet implements Servlet {
  List<Order> orders;

  public OrderAddServlet(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
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

  }

}
