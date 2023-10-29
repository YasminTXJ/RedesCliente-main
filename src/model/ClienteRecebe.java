package model;

import java.io.*;
import java.net.*;

import view.TelaPrincipal;

public class ClienteRecebe implements Runnable {
    private Socket socket;
    private TelaPrincipal tela;
    
    public ClienteRecebe(Socket socket) {
        this.socket = socket;
    }
    
    public ClienteRecebe(Socket socket, TelaPrincipal tela) {
        this.socket = socket;
        this.tela = tela;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
            while (true) {
                  String message = in.readLine();
                  System.out.println("Mensagem do servidor: " + message);
                  if(this.tela!=null ) {
                    tela.setlPerguntaAnterior(message);
                    System.out.println("tela mudada");
                    break;
                  }
                }
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

