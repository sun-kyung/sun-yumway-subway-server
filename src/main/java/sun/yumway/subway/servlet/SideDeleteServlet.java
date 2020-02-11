package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideDeleteServlet implements Servlet {
  List<Side> sides;

  public SideDeleteServlet(List<Side> sides) {
    this.sides = sides;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    int index = -1;
    for (int i = 0; i < sides.size(); i++) {
      if (sides.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index != -1) { // 삭제하려는 번호의 게시물을 찾았다면
      sides.remove(index);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 사이드가 없습니다");
    }
  }

}
