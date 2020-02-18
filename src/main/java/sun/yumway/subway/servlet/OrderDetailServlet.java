package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.OrderObjectFileDao;
import sun.yumway.subway.domain.Order;

public class OrderDetailServlet implements Servlet {
  OrderObjectFileDao orderDao;

  public OrderDetailServlet(OrderObjectFileDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    Order order = orderDao.findByNo(no);
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
