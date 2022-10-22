package client.network;

import shared.transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable{

    private Socket socket;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Client client;


    public ClientSocketHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        try {
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try
        {
            int id = (int) inFromServer.readObject();
            client.setId(id);

            while (true)
            {

                Message messFromServer = (Message)inFromServer.readObject();
                System.out.println("Read message " + messFromServer.getMessage());
                client.messageReceived(messFromServer);


            }
    }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void sendMessageToServer(Message msg)
    {

        try
        {
            outToServer.writeObject(msg);
            System.out.println(msg.getId() + msg.getMessage());

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
