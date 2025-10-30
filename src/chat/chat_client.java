package chat;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class chat_client {
    private   JTextArea msg_area;
    private   JPanel panel1;
    private   JTextField msg_text;
    private   JButton msg_btn;
    private   JLabel server;
    private DataOutputStream mouse;


    public chat_client(){
        msg_btn.addActionListener(actionEvent -> sendMessage());
    }

    private void connectToServer(){
        try  {
            Socket guest = new Socket("localhost",8080);

            DataInputStream ear = new DataInputStream(guest.getInputStream());
            mouse = new DataOutputStream(guest.getOutputStream());

            // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Thread clientReader = new Thread(()->{
                try {
                    String msgFromServer;
                    while ((msgFromServer = ear.readUTF()) != null){

                        System.out.println("Recieved from Server: "+msgFromServer);
                        String finalMsg = msgFromServer;
                        SwingUtilities.invokeLater(() ->
                                msg_area.append("Server: "+ finalMsg+ "\n")
                                );

                        if ("exit".equalsIgnoreCase(msgFromServer)) break;

                    }

                }catch (Exception exception){
                    System.out.println("Client closed connection.");
                }

            },"client-reader");



           /* Thread clientWriter = new Thread(()->{
                try {
                    String response;
                    //reader.readLine()
                    while ((response= msg_text.getText())!= null){
                        String finalResponse = response;
                        msg_btn.addActionListener(action ->

                        {
                            try {
                                mouse.writeUTF(finalResponse);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });


                        mouse.flush();
                    }

                }catch (Exception exception){
                    System.out.println(exception.getLocalizedMessage());
                }

            },"server-writer"); */

            clientReader.start();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel1,
                    "Cannot connect to server!\nMake sure server is running first.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

private void sendMessage(){
    String message = msg_text.getText().trim();
    if (!message.isEmpty() && mouse != null){
       try {
           mouse.writeUTF(message);
           mouse.flush();
           msg_area.append("You: " + message + "\n");
           msg_text.setText("");
       }catch (IOException e){
           e.printStackTrace();
       }


    }


    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("Chat Client");
        chat_client client = new chat_client();
        frame.setContentPane(client.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);

        client.connectToServer();





    }


}
