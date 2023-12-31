import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FroggerChar2 extends FroggerSprite implements Runnable
	{
		private Boolean visible, moving;
		private Thread t;
		private JLabel logLabel;
		private FroggerChar1 frog;
	
		private JButton startButton;
		private JLabel frogLabel;

		public Boolean getVisible()
			{
				return visible;
			}

		public void setVisible(Boolean visible)
			{
				this.visible = visible;
			}

		public Boolean getMoving()
			{
				return moving;
			}

		public void setMoving(Boolean moving)
			{
				this.moving = moving;
			}

		public FroggerChar2()
			{
				super();
			}
	
		public FroggerChar2(Boolean visible, Boolean moving)
			{
				super();
				this.visible = visible;
				this.moving = moving;
			}
	
		public FroggerChar2(int x, int y, int height, int width, String image, Boolean visible, Boolean moving)
			{
				super(x, y, height, width, image);
				this.visible = visible;
				this.moving = moving;
			}

		@Override
		public void run()
			{
				System.out.println("Thread started");
		
				while (this.moving)
					{
						//moving code
						int x  = this.x;
						x += FroggerProps.CHARACTER_STEP;
					
						if (x >= FroggerProps.SCREEN_WIDTH)
							{
								x = -1 * this.width;
							}
					
						this.setX(x);
						logLabel.setLocation(this.x, this.y);
					
						//detect collision between frog and log
						if (this.visible) this.detectCollision();
						System.out.println("Thread stopped");
					
						System.out.println("x, y: " + this.x + ", " + this.y);
					
						try
						{
							Thread.sleep(200);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					
					}
	
					System.out.println("Thread stopped");
				}
			
		//start function to call thread
		public void startThread()
			{
				System.out.println("Start Thread. Moving: " + this.moving);		
				//if thread is already threaded, for not run again
				if (!this.moving)
					{	
						this.moving = true;
						System.out.println("Start Thread2. Moving: " + this.moving);
						t = new Thread(this, "Character 2 Thread");
						t.start();
					}
			}	
	
		public void stopThread()
			{
				this.moving = false;
			}
		
			//method to pass the original JLabel for log into the class so we can effect the original label
		public void setlogLabel(JLabel temp)
			{
				logLabel = temp;
			}
		
		public void setfrogLabel(JLabel temp)
			{
				frogLabel = temp;
			}
	
		public void setfrog(FroggerChar1 temp)
			{
				frog = temp;
			}
		
		public void setStartButton(JButton temp)
			{
				startButton = temp;
			}
		
		private void detectCollision()
			{
				if (r.intersects(frog.getRectangle()))
					{	
						System.out.println("Boom!");
						this.moving = false;
					}
			}
	}
		