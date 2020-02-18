package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.OrderObjectFileDao;
import sun.yumway.subway.domain.Order;

public class OrderAddServlet implements Servlet {
  OrderObjectFileDao orderDao;

  public OrderAddServlet(OrderObjectFileDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Order order = (Order) in.readObject();
    if (orderDao.insert(order) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 주문이 있습니다");
    }

  }

}
