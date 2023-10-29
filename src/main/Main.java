package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.ControllerMain;
import controller.ControllerTelaPrincipal;
import model.Cliente;
import model.*;


public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
       
    
		Cliente cl = new Cliente("127.0.0.1",12345);
    	Socket cliente = new Socket(cl.getHost(), cl.getPorta());
        System.out.println("O cliente se conectou ao servidor!");
        
        ControllerMain controller = new ControllerMain(cliente);
        InputStream inputStream = cliente.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        
        while (true) {
            String respostaServidor = in.readLine();
            if (respostaServidor == null) {
                System.out.println("Servidor desconectado.");
                break;
            }
            controller.mandaMensagem(respostaServidor);
        }
           
	
    }
}