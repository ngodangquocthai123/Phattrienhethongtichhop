import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            String serverName = "127.0.0.1";
            int port = 7;
            Socket socket = new Socket(serverName, port);
            System.out.println("Ket noi thanh cong toi Server!");

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            String message = "Hello TCP Server!";
            out.write(message.getBytes());

            int ch;
            System.out.print("Phản hồi từ Server: ");
            while ((ch = in.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
            socket.close();
        } catch (IOException e) {
            System.out.println("Lỗi kết nối: " + e);
        }
    }
}