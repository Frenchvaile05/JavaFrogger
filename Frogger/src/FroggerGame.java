import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FroggerGame extends JFrame implements KeyListener, ActionListener
	{
		private FroggerChar1 frog;
		private FroggerChar2 log[] = new FroggerChar2[4];
		private FroggerChar3 car;
	
		//variables
		private Container content;
		private JLabel frogLabel, logLabel0, logLabel1, logLabel2, logLabel3, carLabel;
		private ImageIcon frogImage, logImage, carImage;
	
		//button
		private JButton startButton, visibilityButton;
	
		public FroggerGame()
			{
				//GUI Code Setup
				frog = new FroggerChar1(100, 250, 250, 100, "Frog.png");

				log[0] = new FroggerChar2(200, 200, 200, 120, "log.png", true, false);
				log[1] = new FroggerChar2(150, 150, 200, 120, "log.png", true, false);
				log[2] = new FroggerChar2(100, 100, 200, 120, "log.png", true, false);
				log[3] = new FroggerChar2(300, 100, 200, 120, "log.png", true, false);
				
				car = new FroggerChar3(600, 600, 200, 200, "car.png", true, false);
		
				//set up screen
				setSize(FroggerProps.SCREEN_WIDTH, FroggerProps.SCREEN_HEIGHT);
				content = getContentPane();
				content.setBackground(Color.green);
				setLayout(null);		

				//Frog setup
				frog.setX(100);
				frog.setY(250);
				frog.setHeight(200);
				frog.setWidth(100);
				frog.setImage("Frog.png");
		
				frogLabel = new JLabel();
				frogImage = new ImageIcon(getClass().getResource(frog.getImage()));
				frogLabel.setIcon(frogImage);
				frogLabel.setSize(frog.getWidth(), frog.getHeight());
				frogLabel.setLocation(frog.getX(), frog.getY());
		
				//Log0 setup
				log[0].setX(200);
				log[0].setY(200);
				log[0].setHeight(200);
				log[0].setWidth(120);
				log[0].setImage("log.png");
				log[0].setMoving(false);
		
				logLabel0 = new JLabel();
				logImage = new ImageIcon(getClass().getResource(log[0].getImage()));
				logLabel0.setIcon(logImage);
				logLabel0.setSize(log[0].getWidth(), log[0].getHeight());
				logLabel0.setLocation(log[0].getX(), log[0].getY());
				log[0].setlogLabel(logLabel0);
				log[0].setfrog(frog);
				log[0].setfrogLabel(frogLabel);
				
				//Log1 setup
				log[1].setX(150);
				log[1].setY(150);
				log[1].setHeight(200);
				log[1].setWidth(120);
				log[1].setImage("log.png");
				log[1].setMoving(false);
		
				logLabel1 = new JLabel();
				logImage = new ImageIcon(getClass().getResource(log[1].getImage()));
				logLabel1.setIcon(logImage);
				logLabel1.setSize(log[1].getWidth(), log[1].getHeight());
				logLabel1.setLocation(log[1].getX(), log[1].getY());
				log[1].setlogLabel(logLabel1);
				log[1].setfrog(frog);
				log[1].setfrogLabel(frogLabel);
				
				//Log2 setup
				log[2].setX(100);
				log[2].setY(100);
				log[2].setHeight(200);
				log[2].setWidth(120);
				log[2].setImage("log.png");
				log[2].setMoving(false);
		
				logLabel2 = new JLabel();
				logImage = new ImageIcon(getClass().getResource(log[2].getImage()));
				logLabel2.setIcon(logImage);
				logLabel2.setSize(log[1].getWidth(), log[2].getHeight());
				logLabel2.setLocation(log[2].getX(), log[2].getY());
				log[2].setlogLabel(logLabel2);
				log[2].setfrog(frog);
				log[2].setfrogLabel(frogLabel);
				
				//Log3 setup
				log[3].setX(300);
				log[3].setY(100);
				log[3].setHeight(200);
				log[3].setWidth(120);
				log[3].setImage("log.png");
				log[3].setMoving(false);
		
				logLabel3 = new JLabel();
				logImage = new ImageIcon(getClass().getResource(log[3].getImage()));
				logLabel3.setIcon(logImage);
				logLabel3.setSize(log[3].getWidth(), log[3].getHeight());
				logLabel3.setLocation(log[3].getX(), log[3].getY());
				log[3].setlogLabel(logLabel3);
				log[3].setfrog(frog);
				log[3].setfrogLabel(frogLabel);
				
				//Car setup
				car.setX(600);
				car.setY(600);
				car.setHeight(200);
				car.setWidth(300);
				car.setImage("car.png");
				car.setMoving(false);

				carLabel = new JLabel();
				carImage = new ImageIcon(getClass().getResource(car.getImage()));
				carLabel.setIcon(carImage);
				carLabel.setSize(car.getWidth(), car.getHeight());
				carLabel.setLocation(car.getX(), car.getY());
				car.setcarLabel(carLabel);
				car.setfrog(frog);
				car.setfrogLabel(frogLabel);
			
				//Disappear Button
				visibilityButton = new JButton("Hide");
				visibilityButton.setSize(100, 50);
				visibilityButton.setLocation(FroggerProps.SCREEN_WIDTH - 100, FroggerProps.SCREEN_HEIGHT - 100);
				visibilityButton.setFocusable(false);
		
				//move button
				startButton = new JButton("Start");
				startButton.setSize(100, 100);
				startButton.setLocation(FroggerProps.SCREEN_WIDTH - 100, FroggerProps.SCREEN_HEIGHT - 200);
				startButton.setFocusable(false);
				log[0].setStartButton(startButton);
		
				//populate screen
				startButton.addActionListener(this);
				visibilityButton.addActionListener(this);
				add(startButton);
				add(visibilityButton);
				add(frogLabel);
				add(logLabel0);
				add(logLabel1);
				add(logLabel2);
				add(logLabel3);
				add(carLabel);
		
				content.addKeyListener(this);
				content.setFocusable(true);
		
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	
		public static void main(String[] args)
			{
				FroggerGame myGame = new FroggerGame();
				myGame.setVisible(true);
			}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e)
			{
				//get current position
				int x = frog.getX();
				int y = frog.getY();
		
				//detect direction & modify coordinates
				if (e.getKeyCode() == KeyEvent.VK_UP)
					{
						y -= FroggerProps.CHARACTER_STEP;
			
						if (y + frog.getHeight() <= 0)
							{
								y = FroggerProps.SCREEN_HEIGHT;
							}
					} 
				
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)	
					{
						y += FroggerProps.CHARACTER_STEP;
						
						if (y >= FroggerProps.SCREEN_HEIGHT)
							{
								y = -1 * frog.getHeight();
							}
			
					}
				
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						x -= FroggerProps.CHARACTER_STEP;
						
						if (x + frog.getWidth() <= 0)
							{
								x = FroggerProps.SCREEN_HEIGHT;
							}
			
					}
				
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						x += FroggerProps.CHARACTER_STEP;
			
						if (x >= FroggerProps.SCREEN_WIDTH)
							{
								x = -1 * frog.getWidth();
							}
					}
	
				else
					{
						System.out.println("Invalid Operation");
						return;
					}
		
				frog.setX(x);
				frog.setY(y);
				frogLabel.setLocation(frog.getX(), frog.getY());
			}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void actionPerformed(ActionEvent e)
			{
				if ( e.getSource() == visibilityButton )
					{
						//check visibility of log. if visible, hide, change text to show
						if (log[0].getVisible())
							{
								log[0].setVisible(false);
								log[1].setVisible(false);
								log[2].setVisible(false);
								log[3].setVisible(false);
								logLabel1.setVisible(log[0].getVisible());
								logLabel1.setVisible(log[1].getVisible());
								logLabel2.setVisible(log[2].getVisible());
								logLabel3.setVisible(log[3].getVisible());
								visibilityButton.setText("show");
							}
			
						else
							{
								//else show, change text to hide
								log[0].setVisible(true);
								log[1].setVisible(true);
								log[2].setVisible(true);
								log[3].setVisible(true);
								logLabel0.setVisible(log[0].getVisible());
								logLabel1.setVisible(log[1].getVisible());
								logLabel2.setVisible(log[2].getVisible());
								logLabel3.setVisible(log[3].getVisible());
								visibilityButton.setText("Hide");
							}
					}
		
				else if (e.getSource() == startButton)
					{
						//if log and car are moving
						if (log[0].getMoving() & car.getMoving())
							{
								//if moving, stop, change text to start
								log[0].stopThread();
								log[1].stopThread();
								log[2].stopThread();
								log[3].stopThread();
								car.stopThread();
								startButton.setText("Start");
							}
						else
							{
								//else start thread, change text to stop
								log[0].startThread();
								log[1].startThread();
								log[2].startThread();
								log[3].startThread();
								car.startThread();
								startButton.setText("Stop");
							}
					}
			}
		
	}