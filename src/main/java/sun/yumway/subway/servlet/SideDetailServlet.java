package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.SideObjectFileDao;
import sun.yumway.subway.domain.Side;

public class SideDetailServlet implements Servlet {
  SideObjectFileDao sideDao;

  public SideDetailServlet(SideObjectFileDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    Side side = sideDao.findByNo(no);
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
