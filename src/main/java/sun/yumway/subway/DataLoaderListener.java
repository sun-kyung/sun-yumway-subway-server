package sun.yumway.subway;

import java.util.Map;
import sun.yumway.subway.context.ApplicationContextListener;
import sun.yumway.subway.dao.json.BoardJsonFileDao;
import sun.yumway.subway.dao.json.OrderJsonFileDao;
import sun.yumway.subway.dao.json.SideJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다");

    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");
    OrderJsonFileDao orderDao = new OrderJsonFileDao("./order.json");
    SideJsonFileDao sideDao = new SideJsonFileDao("./side.json");

    context.put("boardDao", boardDao);
    context.put("orderDao", orderDao);
    context.put("sideDao", sideDao);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다");

  }

}
