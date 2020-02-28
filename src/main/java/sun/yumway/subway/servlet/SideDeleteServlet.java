package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.SideDao;

public class SideDeleteServlet implements Servlet {
  SideDao sideDao;

  public SideDeleteServlet(SideDao sideDao) {
    this.sideDao = sideDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    if (sideDao.delete(no) > 0) { // 삭제하려는 번호의 게시물을 찾았다면
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 사이드가 없습니다");
    }
  }

}
