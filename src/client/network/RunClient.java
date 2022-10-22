package client.network;

public class RunClient {
    public static void main(String[] args) {
        Client client = new SocketClient();
        client.startClient();
    }
}
