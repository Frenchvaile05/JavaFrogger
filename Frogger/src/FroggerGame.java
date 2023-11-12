import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FroggerGame extends JFrame implements KeyListener, ActionListener
	{
		private FroggerSprite r;
		private FroggerChar1 frog;
		private FroggerChar2 log[] = new FroggerChar2[4];
		private FroggerChar3 car[] = new FroggerChar3[4];
		
		//private FroggerChar2 detectCollision();
	
		//variables
		private Container content;
		private JLabel frogLabel, logLabel0, logLabel1, logLabel2, logLabel3, carLabel0, carLabel1, carLabel2, carLabel3, scoreLabel;
		private ImageIcon frogImage, logImage, carImage;
		int score = 0;
	
		//button
		private JButton startButton, visibilityButton;
	
		public FroggerGame()
			{
				//GUI Code Setup
				frog = new FroggerChar1(400, 600, 250, 100, "Frog.png");

				log[0] = new FroggerChar2(400, 50, 200, 200, "log.png", true, false);
				log[1] = new FroggerChar2(50, 100, 200, 200, "log.png", true, false);
				log[2] = new FroggerChar2(700, 150, 200, 200, "log.png", true, false);
				log[3] = new FroggerChar2(300, 150, 200, 200, "log.png", true, false);
				
				car[0] = new FroggerChar3(100, 300, 200, 200, "car.png", true, false);
				car[1] = new FroggerChar3(1050, 400, 200, 200, "car.png", true, false);
				car[2] = new FroggerChar3(900, 500, 200, 200, "car.png", true, false);
				car[3] = new FroggerChar3(-100, 500, 200, 200, "car.png", true, false);
				
				//set up screen
				setSize(FroggerProps.SCREEN_WIDTH, FroggerProps.SCREEN_HEIGHT);
				content = getContentPane();
				content.setBackground(Color.green);
				setLayout(null);
				
				JPanel road = new JPanel();
				road.setSize(FroggerProps.SCREEN_WIDTH, 300);
				road.setLocation(0, 400);
				road.setBackground(Color.gray);
				
				JPanel water = new JPanel();
				water.setSize(FroggerProps.SCREEN_WIDTH, 150);
				water.setLocation(0, 100);
				water.setBackground(Color.blue);
				
				JPanel safe = new JPanel();
				safe.setSize(FroggerProps.SCREEN_WIDTH, 150);
				safe.setLocation(0, 0);
				safe.setBackground(Color.yellow);
				
				//Frog setup
				frog.setX(400);
				frog.setY(600);
				frog.setHeight(200);
				frog.setWidth(100);
				frog.setImage("Frog.png");
		
				frogLabel = new JLabel();
				frogImage = new ImageIcon(getClass().getResource(frog.getImage()));
				frogLabel.setIcon(frogImage);
				frogLabel.setSize(frog.getWidth(), frog.getHeight());
				frogLabel.setLocation(frog.getX(), frog.getY());
		
				//Log0 setup
				log[0].setX(400);
				log[0].setY(50);
				log[0].setHeight(200);
				log[0].setWidth(200);
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
				log[1].setX(50);
				log[1].setY(100);
				log[1].setHeight(200);
				log[1].setWidth(400);
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
				log[2].setX(700);
				log[2].setY(150);
				log[2].setHeight(200);
				log[2].setWidth(200);
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
				log[3].setY(150);
				log[3].setHeight(200);
				log[3].setWidth(200);
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
				
				//Car0 setup
				car[0].setX(100);
				car[0].setY(300);
				car[0].setHeight(200);
				car[0].setWidth(300);
				car[0].setImage("car.png");
				car[0].setMoving(false);

				carLabel0 = new JLabel();
				carImage = new ImageIcon(getClass().getResource(car[0].getImage()));
				carLabel0.setIcon(carImage);
				carLabel0.setSize(car[0].getWidth(), car[0].getHeight());
				carLabel0.setLocation(car[0].getX(), car[0].getY());
				car[0].setcarLabel(carLabel0);
				car[0].setfrog(frog);
				car[0].setfrogLabel(frogLabel);
				
				//Car1 setup
				car[1].setX(1050);
				car[1].setY(400);
				car[1].setHeight(200);
				car[1].setWidth(300);
				car[1].setImage("car.png");
				car[1].setMoving(false);

				carLabel1 = new JLabel();
				carImage = new ImageIcon(getClass().getResource(car[1].getImage()));
				carLabel1.setIcon(carImage);
				carLabel1.setSize(car[1].getWidth(), car[1].getHeight());
				carLabel1.setLocation(car[1].getX(), car[1].getY());
				car[1].setcarLabel(carLabel1);
				car[1].setfrog(frog);
				car[1].setfrogLabel(frogLabel);
				
				//Car2 setup
				car[2].setX(900);
				car[2].setY(500);
				car[2].setHeight(200);
				car[2].setWidth(300);
				car[2].setImage("car.png");
				car[2].setMoving(false);

				carLabel2 = new JLabel();
				carImage = new ImageIcon(getClass().getResource(car[2].getImage()));
				carLabel2.setIcon(carImage);
				carLabel2.setSize(car[2].getWidth(), car[2].getHeight());
				carLabel2.setLocation(car[2].getX(), car[2].getY());
				car[2].setcarLabel(carLabel2);
				car[2].setfrog(frog);
				car[2].setfrogLabel(frogLabel);
				
				//Car3 setup
				car[3].setX(-100);
				car[3].setY(500);
				car[3].setHeight(200);
				car[3].setWidth(300);
				car[3].setImage("car.png");
				car[3].setMoving(false);

				carLabel3 = new JLabel();
				carImage = new ImageIcon(getClass().getResource(car[3].getImage()));
				carLabel3.setIcon(carImage);
				carLabel3.setSize(car[3].getWidth(), car[3].getHeight());
				carLabel3.setLocation(car[3].getX(), car[3].getY());
				car[3].setcarLabel(carLabel3);
				car[3].setfrog(frog);
				car[3].setfrogLabel(frogLabel);
			
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
				
				//Score panel
				scoreLabel = new JLabel("Score: " + score);
				scoreLabel.setSize(100, 10);
				scoreLabel.setLocation(20,20);
				scoreLabel.setFocusable(false);
		
				//populate screen
				startButton.addActionListener(this);
				visibilityButton.addActionListener(this);
				add(scoreLabel);
				add(startButton);
				add(visibilityButton);
				add(frogLabel);
				add(logLabel0);
				add(logLabel1);
				add(logLabel2);
				add(logLabel3);
				add(water);
				add(carLabel0);
				add(carLabel1);
				add(carLabel2);
				add(carLabel3);
				add(road);
				add(safe);

		
				content.addKeyListener(this);
				content.setFocusable(true);
		
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	
		public static void main(String[] args)
			{
				FroggerGame myGame = new FroggerGame();
				myGame.setVisible(true);
				
				Connection conn = null;
				Statement stmt = null;
				
				try
					{
						Class.forName("org.sqlite.JDBC");
						System.out.println("Driver Loaded");
						String dbURL = "jdbc:sqlite:froggerScore.db";
						conn = DriverManager.getConnection(dbURL);
				
						//if successful,
						if (conn != null)
							{
								//get DB metadata
								DatabaseMetaData db = (DatabaseMetaData)conn.getMetaData();
								System.out.println("Driver Name: " + db.getDriverName());
								
								stmt = conn.createStatement();
								String sql = "";
								
								sql += "CREATE TABLE IF NOT EXISTS FROGGERSCORE ";
								sql += "(ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
								sql += " PLAYER TEXT NOT NULL, ";
								sql += " SCORE INT NOT NULL) ";
								
								stmt.executeUpdate(sql);
								
								// conn.commit();
								System.out.println("Table Successfully Created");
								
								//sql = "INSERT INTO SCORE (SCORE) VALUES (?);";
								
								//SELECTS
								sql = "SELECT * FROM FROGGERSCORE";
								ResultSet rs = stmt.executeQuery(sql);
								DisplayRecords(rs);
								rs.close();
								
								//CLOSE CONNECTION
								conn.close();
							}
					}
				
				catch (Exception e)
					{
						e.printStackTrace();
					}
			}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e)
			{
				//get current position
				int x = frog.getX();
				int y = frog.getY();
				
				if (/* reach safe space &&*/ e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					FroggerGame myGame = new FroggerGame();
					score += 50;
					scoreLabel.setText("Score: " + score);
				}
			//else if (frog collids with car || collids with water)
				//{
					//FroggerGame myGame = new FroggerGame();
					//score -= 50;
					//scoreLabel = new JLabel("Score: " + score);
				//}
		
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
				if (e.getSource() == visibilityButton)
					{
						//check visibility of log. if visible, hide, change text to show
						if (log[0].getVisible() & car[0].getVisible())
							{
								log[0].setVisible(false);
								log[1].setVisible(false);
								log[2].setVisible(false);
								log[3].setVisible(false);
								logLabel0.setVisible(log[0].getVisible());
								logLabel1.setVisible(log[1].getVisible());
								logLabel2.setVisible(log[2].getVisible());
								logLabel3.setVisible(log[3].getVisible());
								
								car[0].setVisible(false);
								car[0].setVisible(false);
								car[2].setVisible(false);
								car[3].setVisible(false);
								carLabel0.setVisible(car[0].getVisible());
								carLabel1.setVisible(car[1].getVisible());
								carLabel2.setVisible(car[2].getVisible());
								carLabel3.setVisible(car[3].getVisible());
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
								
								car[0].setVisible(true);
								car[1].setVisible(true);
								car[2].setVisible(true);
								car[3].setVisible(true);
								carLabel0.setVisible(car[0].getVisible());
								carLabel1.setVisible(car[1].getVisible());
								carLabel2.setVisible(car[2].getVisible());
								carLabel3.setVisible(car[3].getVisible());
								visibilityButton.setText("Hide");
							}
					}
		
				else if (e.getSource() == startButton)
					{
						//if log and car are moving
						if (log[0].getMoving() & car[0].getMoving())
							{
								//if moving, stop, change text to start
								log[0].stopThread();
								log[1].stopThread();
								log[2].stopThread();
								log[3].stopThread();
								car[0].stopThread();
								car[1].stopThread();
								car[2].stopThread();
								car[3].stopThread();
								startButton.setText("Start");
							}
						
						else
							{
								//else start thread, change text to stop
								log[0].startThread();
								log[1].startThread();
								log[2].startThread();
								log[3].startThread();
								car[0].startThread();
								car[1].startThread();
								car[2].startThread();
								car[3].startThread();
								startButton.setText("Stop");
							}
					}
			}
		
		public static void DisplayRecords(ResultSet rs) throws SQLException
			{
				while (rs.next())
					{
						String player = rs.getString("player");
						int score = rs.getInt("score");
					
						System.out.println("Player: " + player);
						System.out.println("Score: " + score);
					}
			}
	}