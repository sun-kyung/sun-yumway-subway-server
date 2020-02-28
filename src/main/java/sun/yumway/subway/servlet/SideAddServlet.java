package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.SideDao;
import sun.yumway.subway.domain.Side;

public class SideAddServlet implements Servlet {
  SideDao sideDao;

  public SideAddServlet(SideDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Side side = (Side) in.readObject();
    if (sideDao.insert(side) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 사이드가 있습니다");
    }
  }

}
