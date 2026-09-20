package network;
import java.net.InetAddress;
import java.net.URI;

public class UriInspector {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java network.UriInspector <hostname> <uri>");
            return;
        }
        try {
            InetAddress address = InetAddress.getByName(args[0]);
            System.out.println("Host IP: " + address.getHostAddress());
            System.out.println("Loopback: " + address.isLoopbackAddress());

            URI uri = new URI(args[1]);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
        } catch (Exception e) {
            System.err.println("Loi: " + e.getMessage());
        }
    }
}