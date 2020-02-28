package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.json.SideJsonFileDao;
import sun.yumway.subway.domain.Side;

public class SideUpdateServlet implements Servlet {
  SideJsonFileDao sideDao;

  public SideUpdateServlet(SideJsonFileDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Side side = (Side) in.readObject();
    if (sideDao.update(side) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 사이드가 없습니다");
    }
  }

}
