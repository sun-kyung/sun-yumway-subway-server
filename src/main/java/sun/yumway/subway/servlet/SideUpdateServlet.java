package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideUpdateServlet implements Servlet {
  List<Side> sides;

  public SideUpdateServlet(List<Side> sides) {
    this.sides = sides;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Side side = (Side) in.readObject();
    int index = -1;
    for (int i = 0; i < sides.size(); i++) {
      if (sides.get(i).getNo() == side.getNo()) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      sides.set(index, side);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 사이드가 없습니다");
    }
  }

}
