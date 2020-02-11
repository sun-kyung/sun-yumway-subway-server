package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import sun.yumway.subway.domain.Board;

public class BoardListServlet implements Servlet {
  List<Board> boards;

  public BoardListServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boards);
  }

}
