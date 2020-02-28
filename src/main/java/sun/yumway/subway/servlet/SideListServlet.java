package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.SideDao;

public class SideListServlet implements Servlet {
  SideDao sideDao;

  public SideListServlet(SideDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(sideDao.findAll());
  }

}
