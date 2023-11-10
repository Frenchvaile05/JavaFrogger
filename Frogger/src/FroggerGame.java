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
		private FroggerChar2 log;
	
		//variables
		private Container content;
		private JLabel frogLabel, logLabel;
		private ImageIcon frogImage, logImage;
	
		//button
		private JButton startButton, visibilityButton;
	
		public FroggerGame()
			{
				//GUI Code Setup
				frog = new FroggerChar1(100, 250, 250, 100, "Frog.png");
				log = new FroggerChar2(0, 0, 200, 120, "log.png", true, false);
		
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
		
				//Log setup
				log.setX(0);
				log.setY(0);
				log.setHeight(200);
				log.setWidth(120);
				log.setImage("log.png");
				log.setMoving(false);
		
				logLabel = new JLabel();
				logImage = new ImageIcon(getClass().getResource(log.getImage()));
				logLabel.setIcon(logImage);
				logLabel.setSize(log.getWidth(), log.getHeight());
				logLabel.setLocation(log.getX(), log.getY());
				log.setlogLabel(logLabel);
				log.setfrog(frog);
				log.setfrogLabel(frogLabel);
			
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
				log.setStartButton(startButton);
		
				//populate screen
				startButton.addActionListener(this);
				visibilityButton.addActionListener(this);
				add(startButton);
				add(visibilityButton);
				add(frogLabel);
				add(logLabel);
		
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
						if (log.getVisible())
							{
								log.setVisible(false);
								logLabel.setVisible(log.getVisible());
								visibilityButton.setText("show");
							}
			
						else
							{
								//else show, change text to hide
								log.setVisible(true);
								logLabel.setVisible(log.getVisible());
								visibilityButton.setText("Hide");
							}
					}
		
				else if (e.getSource() == startButton)
					{
						//if character2 is moving
						if (log.getMoving())
							{
								//if moving, stop, change text to start
								log.stopThread();
								startButton.setText("Start");
							}
						else
							{
								//else start thread, change text to stop
								log.startThread();
								startButton.setText("Stop");
							}
					}
			}
		
	}