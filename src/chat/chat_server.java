package chat;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class chat_server {
    private  JTextArea msg_area;
    private JPanel panel1;
    private JTextField msg_text;
    private JButton msg_send;
    private JLabel server;
    private DataOutputStream mouse;


    public chat_server(){
        msg_send.addActionListener(actionEvent -> sendMessage());
    }
    private void sendMessage(){
        String message = msg_text.getText().trim();
        if (!message.isEmpty() && mouse!= null){
            try {
                mouse.writeUTF(message);
                mouse.flush();
                msg_area.append("You: "+ message + "\n");
                msg_text.setText("");
            }catch (IOException e){
                e.getLocalizedMessage();
            }
        }
    }

    private void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            msg_area.append("Server started on port 8080...\n");
            msg_area.append("Waiting for client...\n");

            Socket handleTheGuest = serverSocket.accept();
            msg_area.append("Client connected!\n");

            DataInputStream ear = new DataInputStream(handleTheGuest.getInputStream());
            mouse = new DataOutputStream(handleTheGuest.getOutputStream());

            Thread serverReader = new Thread(()->{
                try {
                    String msgFromClient;
                    while ((msgFromClient = ear.readUTF()) != null){

                        String finalMsg = msgFromClient;
                        SwingUtilities.invokeLater(() ->
                                msg_area.append("Client: "+ finalMsg +"\n"));

                        System.out.println("Recieved from Client: "+msgFromClient);

                        if ("exit".equalsIgnoreCase(msgFromClient)) break;
                    }

                }catch (Exception exception){
                     SwingUtilities.invokeLater(()->
                             msg_area.append("Client disconnected.\n"));
                }

            },"server-reader");

            serverReader.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    JFrame frame = new JFrame("Chat Server");
    chat_server server = new chat_server();
    frame.setContentPane(server.panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,400);
    frame.setVisible(true);

    server.startServer();

    }

}
