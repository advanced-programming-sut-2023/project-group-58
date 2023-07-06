package controller;

import com.google.gson.GsonBuilder;
import model.Packet;
import view.Connection;

import java.io.DataOutputStream;
import java.io.IOException;

public class PacketController {
    Packet packet;
    Connection connection;


    public PacketController(Packet packet, Connection connection) {
        this.packet = packet;
        this.connection = connection;
    }


    public void sendPacket(Packet packet) throws IOException {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(packet);
        connection.getDataOutputStream().writeUTF(json);
    }

    public void sendPacket(Packet packet, DataOutputStream outputStream) throws IOException {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(packet);
        outputStream.writeUTF(json);
    }



    public void handle() throws IOException, ClassNotFoundException{


    }

    public boolean validateAuthenticationToken() throws IOException {
        if (packet.handler == null) return false;
        if (packet.handler.equals("login") || packet.handler.equals("register") || packet.command.equals("make a fake token")) {
            return true;
        }
        return false;
    }
}
