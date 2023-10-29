package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerFimDeJogo;

public class TelaFimDeJogo extends JFrame{
	
	private JLabel lGameOver;
	private JLabel resultado;
	
	public TelaFimDeJogo(ControllerFimDeJogo controller, String mensagem){
		setConfiguracoesDeTela();
		
		lGameOver = setLabel("Fim de Jogo", 200, 100, 200, 30);
		resultado = setLabel(mensagem, 250, 300, 300, 30);
		
		setVisible(true);
	}
	
    private void setConfiguracoesDeTela() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);
        setTitle("Game Over");
    }
    
    private JLabel setLabel(String mensagem, int xLabel, int yLabel, int width, int height) {
    	JLabel lbl = new JLabel();
    	lbl = new JLabel(mensagem);
    	lbl.setBounds(xLabel,yLabel,width,height);
    	lbl.setFont(new Font("Serif", Font.PLAIN, 20));
    	add(lbl);
    	return lbl;
    }
}
