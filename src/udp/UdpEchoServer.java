package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UdpEchoServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        byte[] buffer = new byte[4096];
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP server listening on port " + PORT);
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(),
                        request.getOffset(), request.getLength(),
                        StandardCharsets.UTF_8);
                
                String trimmed = message.trim();
                String text;

                // Xử lý các lệnh ngày giờ cho Bài 3 (UDP)
                if (trimmed.equalsIgnoreCase("DATE")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
                    text = "ACK " + LocalDate.now().format(dtf);
                } else if (trimmed.equalsIgnoreCase("TIME")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH mm ss");
                    text = "ACK " + LocalTime.now().format(dtf);
                } else if (trimmed.equalsIgnoreCase("DATETIME")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");
                    text = "ACK " + LocalDateTime.now().format(dtf);
                } else {
                    text = "ACK " + trimmed.toUpperCase(Locale.ROOT);
                }

                byte[] responseData = text.getBytes(StandardCharsets.UTF_8);
                DatagramPacket response = new DatagramPacket(
                        responseData, responseData.length,
                        request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (IOException e) {
            System.err.println("UDP server error: " + e.getMessage());
        }
    }
}