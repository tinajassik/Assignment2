package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ConnectionPool pool = new ConnectionPool();


    public void start()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(2237);

            int id= 0;

            while(true)
            {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                System.out.println(
                        "Client connected from " + socket.getInetAddress().getHostAddress() + " " + socket.getLocalPort());

                ServerSocketHandler serverSocketHandler = new ServerSocketHandler(
                        socket,id, this);
                pool.addClient(serverSocketHandler);

                Thread thread = new Thread(serverSocketHandler);
                thread.start();

                id++;


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    public ConnectionPool getPool()
    {
        return pool;
    }
}
