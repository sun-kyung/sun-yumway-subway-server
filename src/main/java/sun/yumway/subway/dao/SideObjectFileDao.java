package sun.yumway.subway.dao;

import java.util.List;
import sun.yumway.subway.domain.Side;

public class SideObjectFileDao extends AbstractObjectFileDao<Side> {


  public SideObjectFileDao(String filename) {
    super(filename);
  }

  public int insert(Side side) throws Exception {
    if (indexOf(side.getNo()) > -1) {
      return 0;
    }
    list.add(side);
    saveData();
    return 1;
  }

  public List<Side> findAll() throws Exception {
    return list;
  }

  public Side findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Side side) throws Exception {
    int index = indexOf(side.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, side);
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


