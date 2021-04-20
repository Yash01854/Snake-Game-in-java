import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class FDemo extends JFrame
{
	JPDemo jp;
	FDemo()
	{
		jp=new JPDemo();
		add(jp);
	}
	
} 
class JPDemo extends JPanel implements ActionListener,KeyListener
{
	ImageIcon img1,img2,img3;
	Image dot,head,food;
	Timer t;
	int x[]=new int[100];
	int y[]=new int[100];
    int dots=4;
	boolean start_game=false;
	boolean end_game=false;
    boolean left=false,right=true,up=false,down=false;
	int r1=0,r2=0;
	int score=0;
	JPDemo()
   {
	   x[0]=120;
	   y[0]=100;
	   x[1]=100;
	   y[1]=100; 
	   x[2]=80;
	   y[2]=100; 
	   x[3]=60;
	   y[3]=100;
	   x[4]=40;
	   y[4]=100;
	   setLayout(null);
	   setBackground(Color.black);
	   setForeground(Color.white);
       Font f=new Font("",Font.BOLD,30);
	   setFont(f);
	   
	   img1=new ImageIcon("dot.png");
	   dot=img1.getImage();
       img2=new ImageIcon("head.png");
	   head=img2.getImage();
       img3=new ImageIcon("food.png");
	   food=img3.getImage();
	   t=new Timer(120,this);
       t.start();
       addKeyListener(this);
       setFocusable(true);	   
       randomDemo();

    }
    void randomDemo()
	{
		r1=(int)Math.round(Math.random()*44+1);
		r1=r1*20;
		r2=(int)Math.round(Math.random()*44+1);
	    r2=r2*20;
	}	
   public void paintComponent(Graphics g)
   {
	   super.paintComponent(g);
	     g.drawImage(food,r1,r2,this);
	   for(int i=0;i<dots;i++)
	   {	 
         if(i==0)
         {
	     g.drawImage(head,x[i],y[i],this);
		 }			 
		 else
		 {
	     g.drawImage(dot,x[i],y[i],this);
		 }
		 g.drawString("Score :"+score,700,30);
       }
	   if(end_game)
	   { 
          g.drawString("Game Over",350,500);
       }
   }
   public void actionPerformed(ActionEvent e)
   {
	  if(x[0]==r1 && y[0]==r2)
	  {
		   dots++;
		   randomDemo();
		   score++;
		 
	  }
	  if( (x[0]==0) || (x[0]==899 && right) || (x[0]==0 && up) || (x[0]==899 && down))
	  {
			start_game=false;
			t.stop();
			end_game=true;
	  }
	  
	  for(int i=dots; i>0; i--)
	  {
		if(x[i]==x[0] && y[i]==y[0])
		{
				start_game=false;
				t.stop();
				end_game=true;
		}
	  }
	 if(start_game)
	 {
	   for(int i=dots;i>0;i--)
	   {
	   x[i]=x[i-1];
	   y[i]=y[i-1];
		   
	   }
	   if(right)
	   {
		x[0]=x[0]+20;
	   }
	   if(left)
	   {
		x[0]=x[0]-20;
	   }
	   if(up)
	   {
		y[0]=y[0]-20;
	   }
	   if(down)
	   {
		y[0]=y[0]+20;
	   }
	   
	 }
	   repaint();
   }
   public void keyReleased(KeyEvent e){}
   public void keyPressed(KeyEvent e){
	   start_game=true;
	   if(e.getKeyCode()==KeyEvent.VK_LEFT)
	   {
		   left=true;
		   right=false;
		   up=false;
		   down=false;
	   }
	    if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	   {
		   left=false;
		   right=true;
		   up=false;
		   down=false;
	   }
	    if(e.getKeyCode()==KeyEvent.VK_UP)
	   {
		   left=false;
		   right=false;
		   up=true;
		   down=false;
	   }
	    if(e.getKeyCode()==KeyEvent.VK_DOWN)
	   {
		   left=false;
		   right=false;
		   up=false;
		   down=true;
	   }
   }
   public void keyTyped(KeyEvent e){}
}
class SnakeGame
{
  static public void main(String ar[])
  {
       FDemo f=new FDemo();
	   f.setVisible(true);
	   f.setBounds(200,20,900,900);
	   // f.setResizable(false);
	   f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
  }
}