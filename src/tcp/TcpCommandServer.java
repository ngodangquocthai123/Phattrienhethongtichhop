package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TcpCommandServer {
    private static final int PORT = 5000;
    
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("TCP server dang lang nghe tai cong " + PORT);
            while (true) {
                try (Socket socket = server.accept()) {
                    serve(socket);
                } catch (IOException e) {
                    System.err.println("Loi phien client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Khong mo duoc server: " + e.getMessage());
        }
    }

    static void serve(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            String request;
            while ((request = in.readLine()) != null) {
                String response = process(request);
                out.println(response);
                if (request.equalsIgnoreCase("QUIT")) break;
            }
        }
    }

    static String process(String request) {
        String trimmed = request.trim();
        if (trimmed.equalsIgnoreCase("PING")) return "OK PONG";
        if (trimmed.equalsIgnoreCase("QUIT")) return "OK BYE";
        
        // --- BÀI 3: Dịch vụ ngày giờ (DATE, TIME, DATETIME) ---
        if (trimmed.equalsIgnoreCase("DATE")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "OK " + LocalDate.now().format(dtf);
        }
        if (trimmed.equalsIgnoreCase("TIME")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH mm ss");
            return "OK " + LocalTime.now().format(dtf);
        }
        if (trimmed.equalsIgnoreCase("DATETIME")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");
            return "OK " + LocalDateTime.now().format(dtf);
        }

        // --- BÀI 2: Đổi chữ số thành chữ (0 đến 9) ---
        if (trimmed.length() == 1 && Character.isDigit(trimmed.charAt(0))) {
            char digit = trimmed.charAt(0);
            switch (digit) {
                case '0': return "OK KHONG";
                case '1': return "OK MOT";
                case '2': return "OK HAI";
                case '3': return "OK BA";
                case '4': return "OK BON";
                case '5': return "OK NAM";
                case '6': return "OK SAU";
                case '7': return "OK BAY";
                case '8': return "OK TAM";
                case '9': return "OK CHIN";
            }
        }

        if (trimmed.regionMatches(true, 0, "UPPER ", 0, 6)) {
            return "OK " + trimmed.substring(6).toUpperCase(Locale.ROOT);
        }
        return "ERR INVALID_DIGIT";
    }
}