package client.network;

import client.views.chatView.ChatViewController;
import shared.transferobjects.Message;
import shared.util.Subject;

public interface Client extends Subject {

//    void startClient();

    void setId(int id);
    int getId();
    void messageReceived(Message message);
    void startClient();
    void sendMessage(Message message);
    String getList();
    void setController(ChatViewController ctrl);
}
