package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import view.TelaDeEspera;

public class ControllerTelaDeEspera implements ActionListener{
	
	Socket cliente;
	TelaDeEspera tela;
	
	public ControllerTelaDeEspera(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void setTela(TelaDeEspera tela) {
		this.tela = tela;
	}
	
	public TelaDeEspera getTela() {
		return tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
    	PrintWriter out = null;
        OutputStream outputStream = null;
		try {
			outputStream = cliente.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out = new PrintWriter(outputStream, true);
		if(e.getSource() == tela.getButton()) {
			out.println("%pronto%");
			tela.esconderButton();
			
		}
	}
	
}
