package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideDetailServlet implements Servlet {
  List<Side> sides;

  public SideDetailServlet(List<Side> sides) {
    this.sides = sides;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    Side side = null;
    for (Side m : sides) {
      if (m.getNo() == no) {
        side = m;
        break;
      }
    }
    if (side != null) {
      out.writeUTF("OK");
      out.writeObject(side);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 사이드가 없습니다");
    }
    System.out.println("사이드 정보를 저장하였습니다");
  }

}
