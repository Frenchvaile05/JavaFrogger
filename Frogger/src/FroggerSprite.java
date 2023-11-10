import java.awt.Rectangle;

/*
 * 1. Create the class skeleton
 * 2. Identify all class attributes (data members)
 * 3. Getters and setters (accessors, mutators)
 * 4. Default constructor
 * 5. Secondary constructor(s)
 * 6. Print/display function
 * 7. Any other code
 * . Test in an application
*/

public class FroggerSprite
	{
		protected int x, y; //upper left, top positions
		protected int height, width;
		protected String image;
		protected Rectangle r;
		
		public FroggerSprite()
			{
				super();
				r = new Rectangle(0,0,0,0);
			}
		 
		public FroggerSprite(int x, int y, int height, int width, String image)
			{
				super();
				this.x = x;
				this.y = y;
				this.height = height;
				this.width = width;
				this.image = image;
				r = new Rectangle(x,y,width,height);
			}

		public int getX()
			{
				return this.x;
			}
		
		public void setX(int x)
			{
				this.x = x;
				this.r.x = this.x;
			}
		
		public int getY()
			{
				return y;
			}
		
		public void setY(int y)
			{
				this.y = y;
				this.r.y = this.y;
			}
		
		public int getHeight()
			{
				return height;
			}
		
		public void setHeight(int height)
			{
				this.height = height;
			}
		
		public int getWidth()
			{
				return width;
			}
		
		public void setWidth(int width)
			{
				this.width = width;
			}
		
		public String getImage()
			{
				return image;
			}
		
		public void setImage(String image)
			{
				this.image = image;
			}
		
		public Rectangle getRectangle()
			{
				return this.r;
			}
	}