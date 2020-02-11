package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideListServlet implements Servlet {
  List<Side> sides;

  public SideListServlet(List<Side> sides) {
    this.sides = sides;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(sides);
  }

}
