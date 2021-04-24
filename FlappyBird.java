import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlappyBird {

	static JFrame frame = new JFrame("Flappy Bird");
	static JLabel startLabel = new JLabel("Press Up Key to Start.");
	static JLabel scoreLabel = new JLabel("Score: " + Move.score);


	public static void main(String[] args) {
		mainGUI();

	}

	public static void mainGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		startLabel.setBounds(85, 60, 200, 100);
		FlappyBird.frame.add(startLabel);
		scoreLabel.setBounds(10, 250, 100, 25);;
		frame.add(scoreLabel);
		frame.add(new Move());
		frame.setVisible(true);
	}

	public static void promptReset() {

		frame.setVisible(false);

		final JFrame resetMenu = new JFrame();
		resetMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resetMenu.setSize(400, 400);
		resetMenu.setLocationRelativeTo(frame);
		resetMenu.setTitle("(Score: " + Move.score + ") New Game?");
		Move.score = 0;
		scoreLabel.setText("Score: " + Move.score);
		Container pane = resetMenu.getContentPane();
		pane.setLayout(new GridLayout(2, 1));

		final JButton button1 = new JButton();
		final JButton button2 = new JButton();

		button1.setText("Play Again");
		button1.setBackground(Color.magenta);
		button1.setBorder(BorderFactory.createLineBorder(Color.black));
		button1.setOpaque(true);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				resetMenu.dispose();
				frame.setVisible(true);
			}
		});

		button2.setText("Quit");
		button2.setBackground(Color.MAGENTA);
		button2.setBorder(BorderFactory.createLineBorder(Color.black));
		button2.setOpaque(true);

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				resetMenu.dispose();
				frame.dispose();
				
			}
		});

		pane.add(button1);
		pane.add(button2);
		resetMenu.setVisible(true);

	}

}

@SuppressWarnings("serial")
class Move extends JComponent implements KeyListener {

	public Move() {
		addKeyListener(this);
		setFocusable(true);
		Thread animationThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(20);
					} catch (Exception ex) {
					}
				}
			}
		});

		animationThread.start();
	}

	static boolean down = true;
	static double speed;
	static int ballY;
	static int ballX;
	static boolean stop;
	static boolean gameOver;
	static int score;
	static int[] toploc1 = new int[2];
	static int[] bottomloc1 = new int[2];
	static int[] toploc2 = new int[2];
	static int[] bottomloc2 = new int[2];
	static int[] toploc3 = new int[2];
	static int[] bottomloc3 = new int[2];
	static int[] toploc4 = new int[2];
	static int[] bottomloc4 = new int[2];
	static int[] toploc5 = new int[2];
	static int[] bottomloc5 = new int[2];
	static boolean startOff = true;

	public void paintComponent(Graphics g) {
		Graphics2D ball = (Graphics2D) g;
		Graphics2D ground = (Graphics2D) g;
		Graphics2D sky = (Graphics2D) g;
		Graphics2D topPipe1 = (Graphics2D) g;
		Graphics2D bottomPipe1 = (Graphics2D) g;
		Graphics2D topPipe2 = (Graphics2D) g;
		Graphics2D bottomPipe2 = (Graphics2D) g;
		Graphics2D topPipe3 = (Graphics2D) g;
		Graphics2D bottomPipe3 = (Graphics2D) g;
		Graphics2D topPipe4 = (Graphics2D) g;
		Graphics2D bottomPipe4 = (Graphics2D) g;
		Graphics2D topPipe5 = (Graphics2D) g;
		Graphics2D bottomPipe5 = (Graphics2D) g;

		if (startOff) {
			speed = 0;
			ballY = 100;
			ballX = 10;
			stop = true;
			startOff = true;
			gameOver = false;
			down = true;
			FlappyBird.startLabel.setVisible(true);
		}
		
		

		if (ballY <= 0) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= 230) {
			startOff = true;
			FlappyBird.promptReset();
		} else if (speed < 1) {
			down = true;
		}

		if (speed < 0) {
			stop = true;
			speed = 0;
		}

		if (!stop) {
			if (down) {
				speed+=.5;
				ballY += speed;
			} else {
				speed--;
				ballY -= speed;
			}
		}

		ground.setColor(Color.green);
		ground.fillRect(0, 250, 400, 50);
		sky.setColor(Color.cyan);
		sky.fillRect(0, 0, 400, 250);
		ball.setColor(Color.BLACK);
		ball.fillOval(ballX, ballY, 20, 20);

		if (startOff) {
			Random gen = new Random();
			int temp = 0;

			temp = gen.nextInt(6) + 1;
			toploc1[0] = 450;
			bottomloc1[0] = toploc1[0];

			toploc1[1] = temp * 30;
			bottomloc1[1] = 180 - toploc1[1];

			temp = gen.nextInt(6) + 1;
			toploc2[0] = toploc1[0] + 150;
			bottomloc2[0] = toploc2[0];

			toploc2[1] = temp * 30;
			bottomloc2[1] = 180 - toploc2[1];

			temp = gen.nextInt(6) + 1;
			toploc3[0] = toploc2[0] + 150;
			bottomloc3[0] = toploc3[0];

			toploc3[1] = temp * 30;
			bottomloc3[1] = 180 - toploc3[1];

			temp = gen.nextInt(6) + 1;
			toploc4[0] = toploc3[0] + 150;
			bottomloc4[0] = toploc4[0];

			toploc4[1] = temp * 30;
			bottomloc4[1] = 180 - toploc4[1];

			temp = gen.nextInt(6) + 1;
			toploc5[0] = toploc4[0] + 150;
			bottomloc5[0] = toploc5[0];

			toploc5[1] = temp * 30;
			bottomloc5[1] = 180 - toploc5[1];

		} else {
			Random gen = new Random();
			int temp = 0;
			if (toploc1[0] < -25) {
				temp = gen.nextInt(6) + 1;
				toploc1[0] = toploc5[0] + 150;
				bottomloc1[0] = toploc1[0];
				toploc1[1] = temp * 30;
				bottomloc1[1] = 180 - toploc1[1];
			} else {
				toploc1[0] -= 3;
				bottomloc1[0] -= 3;
			}

			if (toploc2[0] < -25) {
				temp = gen.nextInt(6) + 1;
				toploc2[0] = toploc1[0] + 150;
				bottomloc2[0] = toploc2[0];
				toploc2[1] = temp * 30;
				bottomloc2[1] = 180 - toploc2[1];
			} else {
				toploc2[0] -= 3;
				bottomloc2[0] -= 3;
			}

			if (toploc3[0] < -25) {
				temp = gen.nextInt(6) + 1;
				toploc3[0] = toploc2[0] + 150;
				bottomloc3[0] = toploc3[0];
				toploc3[1] = temp * 30;
				bottomloc3[1] = 180 - toploc3[1];
			} else {
				toploc3[0] -= 3;
				bottomloc3[0] -= 3;
			}

			if (toploc4[0] < -25) {
				temp = gen.nextInt(6) + 1;
				toploc4[0] = toploc3[0] + 150;
				bottomloc4[0] = toploc4[0];
				toploc4[1] = temp * 30;
				bottomloc4[1] = 180 - toploc4[1];
			} else {
				toploc4[0] -= 3;
				bottomloc4[0] -= 3;
			}

			if (toploc5[0] < -25) {
				temp = gen.nextInt(6) + 1;
				toploc5[0] = toploc4[0] + 150;
				bottomloc5[0] = toploc5[0];
				toploc5[1] = temp * 30;
				bottomloc5[1] = 180 - toploc5[1];
			} else {
				toploc5[0] -= 3;
				bottomloc5[0] -= 3;
			}
		}

		topPipe1.setColor(Color.ORANGE);

		topPipe1.fillRect(toploc1[0], 0, 25, toploc1[1]);
		bottomPipe1.fillRect(bottomloc1[0], toploc1[1] + 75, 25, bottomloc1[1]);

		topPipe2.fillRect(toploc2[0], 0, 25, toploc2[1]);
		bottomPipe2.fillRect(bottomloc2[0], toploc2[1] + 75, 25, bottomloc2[1]);

		topPipe3.fillRect(toploc3[0], 0, 25, toploc3[1]);
		bottomPipe3.fillRect(bottomloc3[0], toploc3[1] + 75, 25, bottomloc3[1]);

		topPipe4.fillRect(toploc4[0], 0, 25, toploc4[1]);
		bottomPipe4.fillRect(bottomloc4[0], toploc4[1] + 75, 25, bottomloc4[1]);

		topPipe5.fillRect(toploc5[0], 0, 25, toploc5[1]);
		bottomPipe5.fillRect(bottomloc5[0], toploc5[1] + 75, 25, bottomloc5[1]);

		if(toploc1[0] <= 5 && toploc1[0] + 3 >= 5){
			score++;
			FlappyBird.scoreLabel.setText("Score: " + score);
		}
		
		if(toploc2[0] <= 5 && toploc2[0] + 3 >= 5){
			score++;
			FlappyBird.scoreLabel.setText("Score: " + score);
		}
		if(toploc3[0] <= 5 && toploc3[0] + 3 >= 5){
			score++;
			FlappyBird.scoreLabel.setText("Score: " + score);
		}
		if(toploc4[0] <= 5 && toploc4[0] + 3 >= 5){
			score++;
			FlappyBird.scoreLabel.setText("Score: " + score);
		}
		if(toploc5[0] <= 5 && toploc5[0] + 3 >= 5){
			score++;
			FlappyBird.scoreLabel.setText("Score: " + score);
		}
		
		if (ballY <= toploc1[1] && ballX + 20 >= toploc1[0]
				&& ballX <= toploc1[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= toploc1[1] + 75 && ballX + 20 >= bottomloc1[0]
				&& ballX <= bottomloc1[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}
		
		if (ballY <= toploc2[1] && ballX + 20 >= toploc2[0]
				&& ballX <= toploc2[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= toploc2[1] + 75 && ballX + 20 >= bottomloc2[0]
				&& ballX <= bottomloc2[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}
		
		if (ballY <= toploc3[1] && ballX + 20 >= toploc3[0]
				&& ballX <= toploc3[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= toploc3[1] + 75 && ballX + 20 >= bottomloc3[0]
				&& ballX <= bottomloc3[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}
		
		if (ballY <= toploc4[1] && ballX + 20 >= toploc4[0]
				&& ballX <= toploc4[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= toploc4[1] + 75 && ballX + 20 >= bottomloc4[0]
				&& ballX <= bottomloc4[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}
		
		if (ballY <= toploc5[1] && ballX + 20 >= toploc5[0]
				&& ballX <= toploc5[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

		if (ballY >= toploc5[1] + 75 && ballX + 20 >= bottomloc5[0]
				&& ballX <= bottomloc5[0] + 25) {
			startOff = true;
			FlappyBird.promptReset();
		}

	}

	public static void jump() {
		speed = 8;
		down = false;
		stop = false;
		startOff = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			jump();
			FlappyBird.startLabel.setVisible(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}