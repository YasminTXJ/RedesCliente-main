package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ControllerCardPersonagem;
import model.Personagem;

public class CardPersonagem extends JPanel {
    Integer posX;
    Integer posY;
    BufferedImage foto;
    ImageIcon iconX;
    ImageIcon iconQuestion;
    String nomePersonagem;
    JButton buttonControle;
    JButton buttonAdvinhar;
    ControllerCardPersonagem controller;
    Integer flag;
    Integer flagAdv;
    
    public CardPersonagem(BufferedImage foto, Personagem personagem, ControllerCardPersonagem controller) {
        this.posX = 10;
        this.posY = 10;
        this.foto = foto;
        this.nomePersonagem = personagem.getNome();
        this.controller = controller;
        controller.setPersonagem(personagem);
        flag = 1;
        
        iconX = new ImageIcon("./src/img/sair30.png");
        iconQuestion = new ImageIcon("./src/img/advinhar.png");

        buttonControle = new JButton("", iconX);
        buttonControle.addActionListener(controller);
        buttonAdvinhar = new JButton("", iconQuestion);
        buttonAdvinhar.addActionListener(controller);
        add(buttonControle);
        add(buttonAdvinhar);
        buttonAdvinhar.setVisible(false);
        flagAdv = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        buttonControle.setBounds(120, 200, 25, 25);
        buttonAdvinhar.setBounds(15, 202, 25, 25);

        int larguraCard = 140; 
        int alturaCard = 220;

        int larguraFoto = larguraCard - 10;
        int alturaFoto = (int) (0.85 * alturaCard);

        int larguraCampoDeTexto = larguraCard;
        int alturaCampoDeTexto = alturaCard - alturaFoto;
        int posYCampoDeTexto = posY + alturaFoto;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(foto, posX + 5, posY + 5, larguraFoto, alturaFoto, null);
        
        g.setColor(new Color(170, 170, 170));
        g.fillRect(posX, posYCampoDeTexto, larguraCampoDeTexto, alturaCampoDeTexto);

        g.setColor(Color.black);
        String text = nomePersonagem;
        int textX = 0;
        int textY = 0;
        if(nomePersonagem.length() == 9) {
        	 textX = posX + 35;
             textY = posYCampoDeTexto + 25;
        }else {
        	textX = posX + 45;
            textY = posYCampoDeTexto + 25;
        }
        Font fonte = new Font("Arial", Font.BOLD, 14);
        g.setFont(fonte);
        
        g.drawString(text, textX, textY);

    }
    
    public void setImage(BufferedImage newImage) {
        this.foto = newImage;
        repaint(); // Repaint the panel to reflect the change
    }
    
    public void setButtonIcon(ImageIcon icon) {
    	this.buttonControle.setIcon(icon);
    	repaint();
    }
    
    public JButton getButtonControle() {
    	return buttonControle;
    }
    
    public JButton getButtonAdvinhar() {
    	return buttonAdvinhar;
    }
    
    public Integer getFlag() {
    	return flag;
    }
    
    public void setFlag(Integer flag) {
    	this.flag = flag;
    }
    
    public void addButtonAdvinhar() {
    	if(flagAdv == 0) {
    		buttonAdvinhar.setVisible(true);
    		setFlagAdv(1);
    	}
    	else if (flagAdv == 1) {
    		buttonAdvinhar.setVisible(false);
    		setFlagAdv(0);
    	}
    }
    
    public Integer getFlagAdv() {
    	return flagAdv;
    }
    
    public void setFlagAdv(Integer flagAdv) {
    	this.flagAdv = flagAdv;
    }
    
    public ControllerCardPersonagem getController() {
    	return controller;
    }
}
