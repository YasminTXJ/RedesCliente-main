package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.Personagem;
import model.*;

import view.TelaDeEspera;
import view.TelaFimDeJogo;
import view.TelaPrincipal;

public class ControllerMain implements ActionListener{
	private Socket cliente;
	private TelaDeEspera telaEspera;
	private TelaPrincipal telaPrincipal;
	private TelaFimDeJogo telaFimDeJogo;
	private List<Personagem> listaDePersonagens;
	private Integer personagemSorteado;
	private Boolean suaJogada;
	private ControllerTelaPrincipal controlPrincipal;
	
	public ControllerMain(Socket cliente) {
    	this.cliente = cliente;
    	this.suaJogada = false;
    }
	
	public void setControlPrincipal(ControllerTelaPrincipal controller) {
		this.controlPrincipal = controller;
	}
	
    public void mandaMensagem(String mensagem) throws IOException {
    	PrintWriter out = null;
        OutputStream outputStream = null;
		try {
			outputStream = cliente.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out = new PrintWriter(outputStream, true);
    	
    	System.out.println(mensagem);
    	if(mensagem.equals("%esperandoJogador%")) {
    		setTelaEspera();
    	}
    	if(mensagem.equals("%esperandoPronto%")) {
    		telaEspera.mostrarButton();
    	}
    	if(mensagem.contains("%listaPersonagens%")) {
    		personagemSorteado = retornaPersonagemSorteado(mensagem);
    		listaDePersonagens = decodificaPersonagens(mensagem);
    		if(listaDePersonagens.size() == 16) {
    			out.println("%todosPersonagensRecebidos%");
    		}
    	}
    	if(mensagem.equals("%iniciarJogo%")) {
    		telaEspera.dispose();
    		setTelaPrincipal(listaDePersonagens,personagemSorteado);
    	}
    	if(mensagem.equals("%esperandoJogada%")) {
    		setTelaSuaVezDeJogar();
    		suaJogada = true;
    	}
    	if(mensagem.contains("%repassaJogada%")) {
    		if(mensagem.contains("%repassaPergunta%")) {
    			int inicio = mensagem.indexOf("Pergunta");
    			int fim = mensagem.lastIndexOf("repassaPergunta");
    			inicio = inicio + 9;
    			fim = fim- 2;
    			String  msg = mensagem.substring(inicio,fim);
    			System.out.println("mensagem recebida do servidor "+ msg);
    			controlPrincipal.mudaTelaResponder(msg); // muda a tela para aparecer a pergunta e os botoes de sim ou nao
    			
    		}
    		if(mensagem.contains("%repassaChute%")) {
    			int inicioNumero = mensagem.indexOf("%repassaChute%") + 14;
    			int fimNumero = mensagem.indexOf("%/repassaChute%");
    			Integer numero = Integer.parseInt(mensagem.substring(inicioNumero, fimNumero));
    			Boolean resultadoChute = verificaChute(numero);
    			if(resultadoChute == true) {
    				String respostaChute = "%respostaChute%%chuteCorreto%%/respostaChute%";
    				out.println(respostaChute);
    			} else {
    				String respostaChute = "%respostaChute%%chuteErrado%%/respostaChute%";
    				out.println(respostaChute);
    				telaPrincipal.telaDeChute("Seu adversário tentou chutar e ERROU!, sua vez de jogar");
    			}
    			suaJogada = true;
    		}

    	}
    	if(mensagem.contains("%respostaPergunta%")) {
			System.out.println("Recebi a resposta");
			int inicioResposta = mensagem.indexOf("%respostaPergunta%")+18;
			int fimResposta = mensagem.indexOf("%/respostaPergunta%");
			String resposta = mensagem.substring(inicioResposta,fimResposta);
			System.out.println("Resposta recebida: "+ resposta);
			controlPrincipal.mudaRespostaAnterior(resposta);
			setSuaJogada(false);
		}
    	if(mensagem.contains("%respostaChute%")) {
    		if(mensagem.contains("%chuteErrado%")) {
    			setSuaJogada(false);
    			controlPrincipal.mudaButtonsAdvinhar();
    			telaPrincipal.telaDeChute("Você errou o chute! perdeu a vez.");
    		}
    	}
    	if(mensagem.contains("%jogada%")) {
    		out.println(mensagem);
    	}
    	if(mensagem.contains("%fimDeJogo%")) {
    		if(mensagem.contains("%voceVenceu%")) {
    			telaPrincipal.dispose();
    			setTelaFimDeJogo("Você Venceu!");
    		}
    		if(mensagem.contains("%vocePerdeu%")) {
    			telaPrincipal.dispose();
    			setTelaFimDeJogo("Você Perdeu!");
    		}
    	}
    }
    
    public void setTelaEspera() throws IOException {
    	ControllerTelaDeEspera controller = new ControllerTelaDeEspera(cliente);
		FactoryScreens factory = new FactoryScreens();
		this.telaEspera = factory.chamaTelaDeEspera(controller);
    }
    
    public void setTelaPrincipal(List<Personagem> listaPersonagens, Integer personagemSorteado) throws IOException {
    	ControllerTelaPrincipal controller = new ControllerTelaPrincipal(cliente);
    	setControlPrincipal(controller);
        FactoryScreens factory = new FactoryScreens();
        this.telaPrincipal = factory.chamaTelaPrincipal(listaPersonagens, personagemSorteado, controller, this);
    }
    
    public List<Personagem> decodificaPersonagens(String mensagem){
    	List<Personagem> lista;
    	List<String> stringPersonagens = criaListaComAsInformacoesDosPersonagensSeparadas(mensagem);
    	for(String item: stringPersonagens) {
    		System.out.println(item);
    	}
    	lista = criaPersonagensComListaDeStrings(stringPersonagens);
    	return lista;
    }
    
    public Integer retornaPersonagemSorteado(String mensagem) {
    	int inicio = mensagem.indexOf("%personagemSorteado%");
    	int inicioPalavra = inicio + 20;
    	int fim = mensagem.indexOf("%/personagemSorteado%");
    	int numeroSorteado = Integer.parseInt(mensagem.substring(inicioPalavra, fim));
    	return numeroSorteado;
    }
    
    public List<String> criaListaComAsInformacoesDosPersonagensSeparadas(String mensagem){
    	List<String> stringPersonagens = new ArrayList<String>();
    	while(mensagem.contains("%personagem%")){
    		int inicio = mensagem.indexOf("%personagem%");
        	int inicioPalavra = inicio + 12;
        	int fim = mensagem.indexOf("%/personagem%");
        	String infoPersonagem = mensagem.substring(inicioPalavra, fim);
        	stringPersonagens.add(infoPersonagem);
        	mensagem = mensagem.substring(fim + 13);
    	}
    	return stringPersonagens;
    }
    
    public List<Personagem> criaPersonagensComListaDeStrings(List<String> stringPersonagens){
    	List<Personagem> listaPersonagens = new ArrayList<Personagem>();
    	for(String string:stringPersonagens) {
    		int inicioNome = string.indexOf("%nome%") + 6;
    		int fimNome = string.indexOf("%/nome%");
    		String nome = string.substring(inicioNome, fimNome);
    		listaPersonagens.add(FactoryPersonagem.criaPersonagem(nome));
    	}
    	return listaPersonagens;
    }
    
    public Boolean verificaChute(Integer numero) {
    	if (numero == personagemSorteado) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void setTelaSuaVezDeJogar() {
    	FactoryScreens factory = new FactoryScreens();
        factory.chamaTelaSuaVezDeJogar();
    }
    
    public void enviaChute(Personagem personagem) throws IOException {
    	int chute = listaDePersonagens.indexOf(personagem);
    	String enviaChute = "%jogada%%chute%" + chute + "%/chute%%/jogada%";
    	mandaMensagem(enviaChute);
    	suaJogada = false;
    }
    
    public void setTelaFimDeJogo(String mensagem) {
    	ControllerFimDeJogo controller = new ControllerFimDeJogo();
        FactoryScreens factory = new FactoryScreens();
        this.telaFimDeJogo = factory.chamaTelaFimDeJogo(controller, mensagem);
    }
    
	public Boolean getSuaJogada() {
		return suaJogada;
	}

	public void setSuaJogada(Boolean suaJogada) {
		this.suaJogada = suaJogada;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
