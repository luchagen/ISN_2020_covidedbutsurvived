package start;

import java.awt.BorderLayout;
import java.awt.Button;
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

import Labyrinthe.Labyrinthe;
import engine.Game;
import engine.GameEngineGraphical;
import engine.GraphicalInterface;
import model.Monster;
import model.Pacman;
import model.PacmanController;
import model.PacmanGame;
import painters.MainPainter;

public class Root extends JFrame implements ActionListener {
	private boolean gameStarted;
	private boolean helpDisplayed;
	private JButton StartButton;
	private JButton HelpButton;

	public Root() throws IOException, InterruptedException {
		this.gameStarted = false;
		this.setTitle("CovidedButSurvived");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = ImageIO.read(new File("img/icon.png"));
		this.setIconImage(icon);
		this.setResizable(false);

		JPanel panneau = new RootPanel();

		panneau.setLayout(new BoxLayout(panneau, BoxLayout.PAGE_AXIS));
		BufferedImage logo = ImageIO.read(new File("img/logo.png"));
		BufferedImage resizedLogo = Root.scale(logo, 0.2);
		JLabel picLabel = new JLabel(new ImageIcon(resizedLogo));
		JPanel a1 = new JPanel();
		a1.setLayout(new BoxLayout(a1, BoxLayout.LINE_AXIS));
		a1.add(picLabel);

		this.StartButton = new JButton("Start");
		JPanel a2 = new JPanel();
		a2.setLayout(new BoxLayout(a2, BoxLayout.LINE_AXIS));
		a2.add(StartButton);
		this.StartButton.addActionListener(this);

		this.HelpButton = new JButton("Guide");
		this.HelpButton.addActionListener(this);
		JPanel a3 = new JPanel();
		a3.setLayout(new BoxLayout(a3, BoxLayout.LINE_AXIS));
		a3.add(HelpButton);

		panneau.add(a1);
		panneau.add(a2);
		panneau.add(a3);
		this.getContentPane().add(panneau);
		this.setVisible(true);
		this.run();

	}

	public static BufferedImage scale(BufferedImage bi, double scaleValue) {
		AffineTransform tx = new AffineTransform();
		tx.scale(scaleValue, scaleValue);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage biNew = new BufferedImage((int) (bi.getWidth() * scaleValue), (int) (bi.getHeight() * scaleValue),
				bi.getType());
		return op.filter(bi, biNew);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HelpFrame helpFrame;
		if (e.getSource() == StartButton) {
			this.gameStarted = true;
			this.dispose();
		}
		if (e.getSource() == HelpButton) {
			try {
				helpFrame = new HelpFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void run() throws InterruptedException {
		while (gameStarted == false) {
			Thread.sleep(20);
		}
	}

}
