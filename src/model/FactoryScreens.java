package model;

import java.io.IOException;
import java.util.List;

import controller.ControllerFimDeJogo;
import controller.ControllerMain;
import controller.ControllerTelaDeEspera;
import controller.ControllerTelaPrincipal;
import view.TelaDeEspera;
import view.TelaFimDeJogo;
import view.TelaPrincipal;
import view.TelaSuaVezDeJogar;

public class FactoryScreens {
    public TelaPrincipal chamaTelaPrincipal(List<Personagem> listaPersonagens, Integer personagemSorteado, ControllerTelaPrincipal ctrl, ControllerMain controlMain) throws IOException{
        TelaPrincipal tela = new TelaPrincipal(listaPersonagens,personagemSorteado,  ctrl, controlMain);
        ctrl.setTela(tela);
        ctrl.setControlMain(controlMain);
        return tela;
    }
    
    public TelaDeEspera chamaTelaDeEspera(ControllerTelaDeEspera ctrl) throws IOException{
        TelaDeEspera tela = new TelaDeEspera(ctrl);
        ctrl.setTela(tela);
        return tela;
    }
    
    public TelaSuaVezDeJogar chamaTelaSuaVezDeJogar() {
    	TelaSuaVezDeJogar tela = new TelaSuaVezDeJogar();
    	return tela;
    }

	public TelaFimDeJogo chamaTelaFimDeJogo(ControllerFimDeJogo controller, String mensagem) {
		TelaFimDeJogo tela = new TelaFimDeJogo(controller, mensagem);
		controller.setTela(tela);
		return tela;
	}
}
