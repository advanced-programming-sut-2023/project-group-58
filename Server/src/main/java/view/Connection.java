package view;


import controller.PacketController;
import model.Packet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {

    public static ArrayList<Connection> allConnections = new ArrayList<>();
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private String token;
    public boolean userChanged = false;


    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        allConnections.add(this);
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                Packet packet;
                packet = Packet.receivePacket(dataInputStream);
                PacketController packetController = new PacketController(packet , this);
                packetController.handle();
            } catch (Exception e){

            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public static DataOutputStream getOutputByUsername(String username) {
        String token =null;
        for (int i = 0; i < allConnections.size(); i++) {
            Connection connection = allConnections.get(i);
            if (connection.getToken().equals(token))
                return connection.dataOutputStream;
        }
        return null;
    }

    public static Connection getConnectionByUsername(String username) {
        String token = null;
        for (int i = 0; i < allConnections.size(); i++) {
            Connection connection = allConnections.get(i);
            if (connection.getToken().equals(token))
                return connection;
        }
        return null;
    }
}