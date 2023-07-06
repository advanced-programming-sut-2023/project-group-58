package model;

import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Packet {
    public String command;
    public String token;
    public String handler;
    public HashMap<String, Object> attributes = new HashMap<>();

    public Packet(String command) {
        this.command = command;
    }

    public Packet(String command, String handler) {
        this.command = command;
        this.handler = handler;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    public static Packet receivePacket(DataInputStream dataInputStream) throws IOException {
        String receivingPacket = dataInputStream.readUTF();
        System.out.println("receive:");
        System.out.println(receivingPacket);
        Packet packet = null;
        try {
            packet = new GsonBuilder().setPrettyPrinting().create().fromJson(receivingPacket, Packet.class);
        } catch (Exception e) {
        }
        return packet;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}