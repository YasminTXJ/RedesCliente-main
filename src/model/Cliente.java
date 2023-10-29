package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private String host;
	private int porta;
	private String mensagem;
	private List<Personagem> personagens = new ArrayList<Personagem>();
	
    public Cliente(String host, int porta){
        this.setHost(host);
        this.setPorta(porta);
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	

   

}