package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientTcpServer {
    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 20;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Multi-client server dang chay tai cong " + PORT);
            while (true) {
                Socket socket = server.accept();
                pool.submit(() -> {
                    String client = String.valueOf(socket.getRemoteSocketAddress());
                    System.out.println("Da ket noi: " + client);
                    try (socket) {
                        TcpCommandServer.serve(socket);
                    } catch (IOException e) {
                        System.err.println("Client " + client + " bi loi: " + e.getMessage());
                    } finally {
                        System.out.println("Ngat ket noi: " + client);
                    }
                });
            }
        } catch (IOException e) {
            System.err.println("Loi server: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}