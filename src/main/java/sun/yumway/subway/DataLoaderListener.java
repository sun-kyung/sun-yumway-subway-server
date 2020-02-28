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


    context.put("boardDao", new BoardJsonFileDao("./board.json"));
    context.put("orderDao", new OrderJsonFileDao("./order.json"));
    context.put("sideDao", new SideJsonFileDao("./side.json"));

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다");

  }

}
