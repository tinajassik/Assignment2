package server.network;

import shared.transferobjects.Message;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private List<ServerSocketHandler> connections = new ArrayList<>();

    public synchronized void addClient(ServerSocketHandler ssh) {
        connections.add(ssh);
    }

    public synchronized void removeClient(ServerSocketHandler ssh) {
        if (connections.contains(ssh)) {
            connections.remove(ssh);
        }
    }

    public synchronized void broadcast(String msg, int id)
    {
        Message message = new Message(msg, id);
        for(ServerSocketHandler conn: connections)
        {
            conn.sendMessage(message);
        }
    }





}
