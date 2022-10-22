package client.views.chatView;

import client.network.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import shared.transferobjects.Message;

public class ChatViewController {

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatSpace;
    private Client client;

    public void init(Client client)
    {
        this.client = client;
    }

    @FXML
    public void sendText(ActionEvent actionEvent)
    {
        Message message = new Message(messageField.getText(), client.getId());

        client.sendMessage(message);


    }
    public void updateMessages()
    {
        chatSpace.clear();
        String allMessages = client.getList();
        chatSpace.appendText(allMessages);
    }
}
