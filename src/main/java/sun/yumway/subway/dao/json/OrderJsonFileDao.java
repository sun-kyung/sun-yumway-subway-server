package sun.yumway.subway.dao.json;

import java.util.List;
import sun.yumway.subway.domain.Order;

public class OrderJsonFileDao extends AbstractJsonFileDao<Order> {

  public OrderJsonFileDao(String filename) {
    super(filename);
  }

  public int insert(Order order) throws Exception {
    if (indexOf(order.getNo()) > -1) {
      return 0;
    }
    list.add(order);
    saveData();
    return 1;
  }

  public List<Order> findAll() throws Exception {
    return list;
  }

  public Order findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Order order) throws Exception {
    int index = indexOf(order.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, order);
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    list.remove(index);
    saveData();
    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }
}


