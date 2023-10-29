package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaSuaVezDeJogar extends JFrame{
	
	private JLabel label1;
	private JLabel label2;
	
	public TelaSuaVezDeJogar() {
		setConfiguracoesDeTela();
		
		label1 = setLabel("Sua vez de jogar!", 100, 30, 100, 30);
		label2 = setLabel("Faça uma pergunta ou tente acertar", 40, 80, 220, 30);
		label2 = setLabel(" o personagem do seu adversário!", 45, 100, 200, 20);
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
    private void setConfiguracoesDeTela() {
    	setLayout(null);
        setSize(300, 200);
        setTitle("Waiting Screen");
    }
    
    private JLabel setLabel(String mensagem, int xLabel, int yLabel, int width, int height) {
    	JLabel lbl = new JLabel();
    	lbl = new JLabel(mensagem);
    	lbl.setBounds(xLabel,yLabel,width,height);
    	add(lbl);
    	return lbl;
    }
}
