package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerTelaDeEspera;

public class TelaDeEspera extends JFrame{

	JLabel texto;
	JButton button;
	
	ControllerTelaDeEspera controller;
	
	public TelaDeEspera(ControllerTelaDeEspera ctrl) {
		controller = ctrl;
		setConfiguracoesDeTela();
		
		texto = setLabel("Esperando Por Jogadores ...", 175, 100, 250, 30);
		button = setButton("Pronto", 175,150, 250, 50);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
    private void setConfiguracoesDeTela() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(null);
        setSize(600, 400);
        setTitle("Waiting Screen");
    }
    
    private JLabel setLabel(String mensagem, int xLabel, int yLabel, int width, int height) {
    	JLabel lbl = new JLabel();
    	lbl = new JLabel(mensagem);
    	lbl.setBounds(xLabel,yLabel,width,height);
    	lbl.setFont(new Font("Serif", Font.PLAIN, 20));
    	add(lbl);
    	return lbl;
    }
    
    public JButton setButton(String mensagem, Integer x, Integer y, Integer width, Integer height) {
    	JButton button = new JButton(mensagem);
    	button.setBounds(x, y, width, height);
    	button.addActionListener(controller);
    	add(button);
    	button.setVisible(false);
    	return button;
    }
    
    public void mostrarButton() {
    	button.setVisible(true);
    }
    
    public void esconderButton() {
    	button.setVisible(false);
    }
    
    public JButton getButton() {
    	return button;
    }
}
