package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.OrderObjectFileDao;

public class OrderListServlet implements Servlet {
  OrderObjectFileDao orderDao;

  public OrderListServlet(OrderObjectFileDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(orderDao.findAll());
  }

}
