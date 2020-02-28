package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.json.OrderJsonFileDao;

public class OrderDeleteServlet implements Servlet {
  OrderJsonFileDao orderDao;

  public OrderDeleteServlet(OrderJsonFileDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    if (orderDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 주문이 없습니다");
    }
  }

}
