package client.core;

import client.network.Client;
import client.views.chatView.ChatViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Scene scene;

    private Stage stage;

    private Client client;

    public ViewHandler(Client client) {
        this.client = client;
    }

    public void start()
    {
        stage = new Stage();
        openView();

    }

    public void openView()
    {
        if(scene == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/chatView/chatview.fxml"));
                Parent root = loader.load();

                ChatViewController ctrl = loader.getController();
                ctrl.init(client);
                client.setController(ctrl);

                stage.setTitle("Chat");
                scene = new Scene(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(scene);
        stage.show();

    }
}
