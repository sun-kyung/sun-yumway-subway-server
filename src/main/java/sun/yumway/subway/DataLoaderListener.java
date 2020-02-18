package sun.yumway.subway;

import java.util.Map;
import sun.yumway.subway.context.ApplicationContextListener;
import sun.yumway.subway.dao.BoardObjectFileDao;
import sun.yumway.subway.dao.OrderObjectFileDao;
import sun.yumway.subway.dao.SideObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다");

    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser");
    OrderObjectFileDao orderDao = new OrderObjectFileDao("./order.ser");
    SideObjectFileDao sideDao = new SideObjectFileDao("./side.ser");

    context.put("boardDao", boardDao);
    context.put("orderDao", orderDao);
    context.put("sideDao", sideDao);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다");

  }

}
