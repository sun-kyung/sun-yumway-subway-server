package sun.yumway.subway;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("서버 맛있는 서브웨이 시스템입니다");

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중 ... ");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음");

        processRequest(socket);
        System.out.println("--------------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }
  }

  static void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음");
      String message = in.nextLine();

      System.out.println("클라이언트가 보낸 메시지를 수신하였음");

      System.out.println("클라이언트: " + message);

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
    }
  }
}
