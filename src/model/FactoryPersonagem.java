package model;



public class FactoryPersonagem {
	
	public static Personagem criaPersonagem(String nome) {
		Personagem personagem = new Personagem(nome);
		return personagem;
	}
}
