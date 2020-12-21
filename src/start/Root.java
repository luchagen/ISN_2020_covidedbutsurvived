package start;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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

public class Root extends JFrame {
	public Root() throws IOException {
	    this.setTitle("CovidedButSurvived");
	    this.setSize(320,320);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    Image icon= ImageIO.read(new File("img/icon.png"));
		this.setIconImage(icon);
		
		JPanel panneau = new JPanel();
		
		BufferedImage logo = ImageIO.read(new File("img/logo.png"));
		BufferedImage resizedLogo=this.scale(logo, 0.2);
		JLabel picLabel = new JLabel(new ImageIcon(resizedLogo));
		panneau.add(picLabel);
		
		JButton StartButton = new JButton("Start");
		panneau.add(StartButton);
		this.getContentPane().add(panneau);
	    this.setVisible(true);
	    

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
	
}
