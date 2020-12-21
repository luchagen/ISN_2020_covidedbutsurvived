package start;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import engine.GameEngineGraphical;
import engine.GraphicalInterface;
import model.Labyrinthe;
import model.MainPainter;
import model.Monster;
import model.Pacman;
import model.PacmanController;
import model.PacmanGame;

public class Root extends JFrame implements ActionListener{
	private boolean gameStarted;
	public Root() throws IOException, InterruptedException {
		this.gameStarted=false;
	    this.setTitle("CovidedButSurvived");
	    this.setSize(320,320);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    Image icon= ImageIO.read(new File("img/icon.png"));
		this.setIconImage(icon);
		
		JPanel panneau = new JPanel();
		
		BufferedImage logo = ImageIO.read(new File("img/logo.png"));
		BufferedImage resizedLogo=Root.scale(logo, 0.2);
		JLabel picLabel = new JLabel(new ImageIcon(resizedLogo));
		panneau.add(picLabel);
		
		JButton StartButton = new JButton("Start");
		panneau.add(StartButton);
		StartButton.addActionListener(this);
		this.getContentPane().add(panneau);
	    this.setVisible(true);
	    this.run();
	    

	}
	 public static BufferedImage scale(BufferedImage bi, double scaleValue) { 
	        AffineTransform tx = new AffineTransform(); 
	        tx.scale(scaleValue, scaleValue); 
	        AffineTransformOp op = new AffineTransformOp(tx, 
	                AffineTransformOp.TYPE_BILINEAR); 
	        BufferedImage biNew = new BufferedImage( (int) (bi.getWidth() * scaleValue), 
	                (int) (bi.getHeight() * scaleValue), 
	                bi.getType()); 
	        return op.filter(bi, biNew); 
	  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gameStarted=true;
		this.dispose();
	}
	public void run() throws InterruptedException {
		while(gameStarted==false) {
			Thread.sleep(20);
		}
	}

	
}
