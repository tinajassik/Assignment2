package client;

import client.core.ViewHandler;
import client.network.Client;
import client.network.SocketClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.SocketException;

public class ChatApplication extends Application {

    private Client client = new SocketClient();

    public void start(Stage stage)
        {
                client.startClient();
                ViewHandler viewHandler = new ViewHandler(client);
                viewHandler.start();

        }
}


