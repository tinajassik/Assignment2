package client.network;

import client.views.chatView.ChatViewController;
import shared.transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClient implements Client{

    private PropertyChangeSupport support;
    private int id;
    private ClientSocketHandler clientSocketHandler;
    private ArrayList<Message> allMessages;

    private ChatViewController chatViewController;

    public SocketClient() {
        support = new PropertyChangeSupport(this);
        allMessages = new ArrayList<>();
    }



    public void messageReceived(Message msg)
    {
        System.out.println(msg.getId()+ ": " + msg.getMessage());
        allMessages.add(msg);
       chatViewController.updateMessages();

    }

    public void sendMessage(Message message)
    {
        clientSocketHandler.sendMessageToServer(message);
    }

    @Override
    public String getList() {
        String returnStr = "";
        for (int i = 0; i < allMessages.size(); i++)
        {
            Message message = allMessages.get(i);
            returnStr+=message+ "\n";
        }

        return returnStr;
    }

    @Override
    public void setController(ChatViewController chatViewController) {
        this.chatViewController = chatViewController;
    }

    @Override
    public void startClient() {
        try {
           Socket socket = new Socket("localhost", 1236);
           clientSocketHandler = new ClientSocketHandler(socket,this);
        Thread thread = new Thread(clientSocketHandler);
        thread.setDaemon(true);
        thread.start();
        }
        catch (IOException e) {
            System.out.println("exception");
        }
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        try {

            while(true) {
                Message message = (Message)inFromServer.readObject();
                support.firePropertyChange("Message Received", null, message.getMessage());
            }

        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName,listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
