package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.ClienteRecebe;
import view.CardPersonagem;
import view.TelaPrincipal;

public class ControllerTelaPrincipal implements ActionListener {
    private TelaPrincipal tela;
    private Socket cliente;
    private ControllerMain controlMain;
    
    public ControllerTelaPrincipal(Socket cliente) {
    	this.cliente = cliente;
    }
    
    public TelaPrincipal getTela(){
        return tela;
    }

    public void setTela(TelaPrincipal tela){
        this.tela = tela;
    }
    
    
    public ControllerMain getControlMain() {
		return controlMain;
	}

	public void setControlMain(ControllerMain controlMain) {
		this.controlMain = controlMain;
	}
	
	
	public void mudaButtonsAdvinhar() {
		for(CardPersonagem card: tela.getCards()) {
			card.addButtonAdvinhar();
		}
	}
	public void mudaPerguntaAResponder(String pergunta) {
		tela.setPerguntaAResp(pergunta);
	}
	
	public void mudaPerguntaAnterior(String pergunta) {
		System.out.println("Mudando pergunta anterior");
		tela.setlPerguntaAnterior(pergunta);
	}
	public void mudaRespostaAnterior(String resposta) {
		System.out.println("Mudando resposta anterior");
		tela.setlRespostaAnterior(resposta);
	}
	
	public void mudaTelaResponder(String pergunta) {
		tela.getPerguntaAResp().setVisible(true);
		this.mudaPerguntaAResponder(pergunta);
		tela.getBtnSim().setVisible(true);
		tela.getBtnNao().setVisible(true);
	 
        tela.getlPerguntaAnterior() .setVisible(false);
        tela.getlRespostaAnterior().setVisible(false);
        tela. getButtonAdvinhar().setVisible(false);
        tela.getlPergunta().setVisible(false);
        tela.getButtonEnviar().setVisible(false);
        tela.getTxt().setVisible(false);
	}
	
	public void mudaTelaPerguntar() {
		tela.getPerguntaAResp().setVisible(false);
		tela.getBtnSim().setVisible(false);
		tela.getBtnNao().setVisible(false);
	 
        tela.getlPerguntaAnterior() .setVisible(true);
        tela.getlRespostaAnterior().setVisible(true);
        tela.getButtonAdvinhar().setVisible(true);
        tela.getlPergunta().setVisible(true);
        tela.getButtonEnviar().setVisible(true);
        tela.getTxt().setVisible(true);
	}

	@Override
    public void actionPerformed(ActionEvent e){
    	PrintWriter out = null;
        OutputStream outputStream;
		try {
			outputStream = cliente.getOutputStream();
			out = new PrintWriter(outputStream, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

    	if(e.getSource()== tela.getButtonEnviar()  && !tela.getTPergunta().getText().isEmpty()) {
    		
    		if(controlMain.getSuaJogada() == true) {
    			
    			PrintStream saida;
    			try {
    				saida = new PrintStream(cliente.getOutputStream());
    				String perguntaAnt = tela.getTPergunta().getText();
    				String msg = "%jogada%%pergunta%";
    				msg = msg + tela.getTPergunta().getText();
    				msg = msg + "%/pergunta%%/jogada%";
    				saida.println(msg);
    				this.mudaPerguntaAnterior(perguntaAnt);
    				this.mudaRespostaAnterior(" ");
    				tela.setTxt("");
    			} catch (IOException e1) {
    				System.out.println("Problema na criacao de conexao com o servidor e enviar msg");
    			}
    		}else {
    			if(controlMain.getSuaJogada() == false) 
   				 JOptionPane.showMessageDialog(null, "Não é sua vez ! \n Aguarde..", "Aviso", JOptionPane.INFORMATION_MESSAGE);
   				
   			}
   			
		}else {
	    	if(e.getSource()== tela.getButtonEnviar()  && tela.getTPergunta().getText().isEmpty()) {
					 JOptionPane.showMessageDialog(null, "Mensagem vazia! \n Favor digitar uma pergunta.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	    	}
		}
    	if(e.getSource() == tela.getButtonAdvinhar()) {
    		if(controlMain.getSuaJogada() == true) {
    			mudaButtonsAdvinhar();
    		}else {
    			if(controlMain.getSuaJogada() == false) {
    				 JOptionPane.showMessageDialog(null, "Não é sua vez ! \n Aguarde..", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    			}
    		}
    	}
		if (e.getSource() == tela.getBtnSim()) {

			PrintStream saida;
			try {
				saida = new PrintStream(cliente.getOutputStream());
				String resposta = "%respostaPergunta%Sim%/respostaPergunta%";
				saida.println(resposta);
				controlMain.setSuaJogada(true);
				mudaTelaPerguntar();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Erro ao enviar resposta da pergunta realizada");
			}
		}
		if (e.getSource() == tela.getBtnNao()) {
			PrintStream saida;
			try {
				saida = new PrintStream(cliente.getOutputStream());
				String resposta = "%respostaPergunta%Nao%/respostaPergunta%";
				System.out.println(resposta);
				saida.println(resposta);
				controlMain.setSuaJogada(true);
				mudaTelaPerguntar();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Erro ao enviar resposta da pergunta realizada");
			}

		}
    }
}
