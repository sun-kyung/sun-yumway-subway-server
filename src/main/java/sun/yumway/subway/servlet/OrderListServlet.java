package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderListServlet implements Servlet {
  List<Order> orders;

  public OrderListServlet(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(orders);
  }

}
