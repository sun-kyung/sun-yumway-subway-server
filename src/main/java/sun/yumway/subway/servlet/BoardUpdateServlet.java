package sun.yumway.subway.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.yumway.subway.dao.json.BoardJsonFileDao;
import sun.yumway.subway.domain.Board;

public class BoardUpdateServlet implements Servlet {
  BoardJsonFileDao boardDao;

  public BoardUpdateServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Board board = (Board) in.readObject();
    if (boardDao.update(board) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다");
    }
  }

}
