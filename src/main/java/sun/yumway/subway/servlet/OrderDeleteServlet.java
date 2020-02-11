package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderDeleteServlet implements Servlet {
  List<Order> orders;

  public OrderDeleteServlet(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
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
  }

}
