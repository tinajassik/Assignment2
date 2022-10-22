package server.network;

import shared.transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable{

    private Socket socket;
    private Server server;
//    private ConnectionPool connectionPool;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    private int id;

    public ServerSocketHandler(Socket socket, int id, Server server){
        this.socket = socket;
        this.server = server;
        this.id = id;

        try {
            inFromClient = new ObjectInputStream(socket.getInputStream());
            outToClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            outToClient.writeObject(id);

            while (true) {

                Message message = (Message) inFromClient.readObject();
                System.out.println(message.getMessage() +" from: "+ message.getId());

                server.getPool().broadcast(message.getMessage(), message.getId());

//                Message read = (Message) inFromClient.readObject();
//                System.out.println("Received from client: " + read);
////                if (read.getMessageBody().equalsIgnoreCase("exit")) {
////                    socket.close();
////                    connectionPool.removeClient(this);
////                    System.out.println("Client disconnected");
////                    break;
////                }
//
//                String result = read.getMessageBody().toUpperCase();
//                Message m = new Message(result);
//                sendMessage(m);
            }
        }catch (IOException | ClassNotFoundException e) {

        }
    }

    public void sendMessage(Message message) {
        try {
            outToClient.writeObject(message);
        } catch (IOException e) {

        }
    }
}
