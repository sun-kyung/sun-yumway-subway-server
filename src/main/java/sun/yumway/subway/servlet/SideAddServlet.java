package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideAddServlet implements Servlet {
  List<Side> sides;

  public SideAddServlet(List<Side> sides) {
    this.sides = sides;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Side side = (Side) in.readObject();
    int i = 0;
    for (; i < sides.size(); i++) {
      if (sides.get(i).getNo() == side.getNo()) {
        break;
      }
    }
    if (i == sides.size()) {
      sides.add(side);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 사이드가 있습니다");
    }
  }

}
