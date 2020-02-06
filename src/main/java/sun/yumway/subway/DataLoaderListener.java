package sun.yumway.subway;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sun.yumway.subway.context.ApplicationContextListener;
import sun.yumway.subway.domain.Board;
import sun.yumway.subway.domain.Order;
import sun.yumway.subway.domain.Side;

public class DataLoaderListener implements ApplicationContextListener {
  List<Order> orderList = new ArrayList<>();
  List<Side> sideList = new ArrayList<>();
  List<Board> boardList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다");
    loadOrderData();
    loadSideData();
    loadBoardData();

    context.put("boardList", boardList);
    context.put("orderList", orderList);
    context.put("sideList", sideList);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다");
    saveOrderData();
    saveSideData();
    saveBoardData();

  }

  @SuppressWarnings("unchecked")
  private void loadOrderData() {
    File file = new File("./order.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      orderList = (List<Order>) in.readObject();
      System.out.printf("총 %d개의 샌드위치 데이터를 로딩했습니다\n", orderList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveOrderData() {
    File file = new File("./order.ser");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(orderList);
      System.out.printf("총 %d개의 샌드위치 데이터를 저장했습니다\n", orderList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadSideData() {
    File file = new File("./side.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      sideList = (List<Side>) in.readObject();

      System.out.printf("총 %d개의 사이드 데이터를 로딩했습니다\n", sideList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveSideData() {
    File file = new File("./side.ser");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(sideList);
      System.out.printf("총 %d개의 사이드 데이터를 저장했습니다\n", sideList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("총 %d개의 게시물 데이터를 로딩했습니다\n", boardList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);
      System.out.printf("총 %d개의 게시물 데이터를 저장했습니다\n", boardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

}
