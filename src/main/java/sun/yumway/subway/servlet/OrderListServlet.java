package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.json.OrderJsonFileDao;

public class OrderListServlet implements Servlet {
  OrderJsonFileDao orderDao;

  public OrderListServlet(OrderJsonFileDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(orderDao.findAll());
  }

}
