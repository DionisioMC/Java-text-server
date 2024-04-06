package io.codeforall.javatars;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSide implements Runnable {

    private Socket clientSocket;
    private String name;
    private BufferedOutputStream out;
    private BufferedReader in;

    private String[] commands = {
            "whisper- Allows you to send a private message to someone using the format whisper username message",
            "list- Lists you every user that is online",
            "quit- Lets you quit the chat server, duh",
            "commmands- You just used it, dumbass"
    };

    public ClientSide(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new BufferedOutputStream(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        getLoggedIn();
    }

    public void getLoggedIn() throws IOException {
        out.write("Username? ".getBytes());
        out.flush();
        name = in.readLine();
        if (userNameTaken(name)) {
            out.write(("\n" +
                    "The username you selected is already in use, please chose another one\n").getBytes());
            getLoggedIn();
        }
    }

    public String getName() {
        return name;
    }

    public BufferedOutputStream getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    @Override
    public void run() {
        try {
            greet(name);
            while (true) {
                String message = in.readLine();
                if (message == null || message.equals("quit")) {
                    goodbye(name);
                    in.close();
                    out.close();
                    clientSocket.close();
                    Server.clientList.remove(this);
                    break;
                }

                String[] messageType = message.split(" ");
                if (messageType[0].equals("whisper")) {
                    sendTo(messageType[2], messageType[1]);
                    continue;
                }

                if (messageType[0].equals("list")) {
                    onlineUsers();
                    continue;
                }

                if (messageType[0].equals("commands")) {
                    commands();
                    continue;
                }
                sendAll(message + "\n");
                System.out.println(name + ": " + message);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized void sendAll(String message) throws IOException {
        for (ClientSide client : Server.clientList) {
            client.out.write((client.name + ": " + message).getBytes());
            client.out.flush();
        }
    }


    public synchronized void sendTo(String message, String name) throws IOException {
        for (ClientSide client : Server.clientList) {
            if (client.name.equals(name)) {
                client.out.write(("Private message from " + this.name + ": " + message + "\n").getBytes());
                client.out.flush();
            }

        }
    }


    public static synchronized void greet(String name) throws IOException {
        for (ClientSide client : Server.clientList) {
            client.out.write((name + " has entered the chat\n" +
                    "Write commands on the console to see what you can do\n").getBytes());
            client.out.flush();
        }
    }

    public static synchronized void goodbye(String name) throws IOException {
        for (ClientSide client : Server.clientList) {
            client.out.write((name + " has left the chat\n").getBytes());
            client.out.flush();
        }
    }

    public synchronized void onlineUsers() throws IOException {
        String names = "\n";
        for (ClientSide client : Server.clientList) {
            names += client.name + "\n" +
                    "----------\n";
        }
        out.write((names + "\n\n").getBytes());
        out.flush();
    }

    public synchronized void commands() throws IOException {
        String names = "\n";
        for (String sentence : commands) {
            out.write((sentence + "\n" +
                    "----------\n").getBytes());
            out.flush();
        }
    }

    public boolean userNameTaken(String name) {
        for (ClientSide client : Server.clientList) {
            if (name.equals(client.getName())) {
                return true;
            }
        }
        return false;
    }
}
