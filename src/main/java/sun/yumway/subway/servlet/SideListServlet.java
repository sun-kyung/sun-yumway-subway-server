package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.SideObjectFileDao;

public class SideListServlet implements Servlet {
  SideObjectFileDao sideDao;

  public SideListServlet(SideObjectFileDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(sideDao.findAll());
  }

}
