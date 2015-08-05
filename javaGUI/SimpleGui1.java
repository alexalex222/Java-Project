import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui1 implements ActionListener
{
	JFrame frame;
	
	public static void main(String[] args)
	{
		SimpleGui1 gui = new SimpleGui1();
		gui.go();		
	}
	
	public void go()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Change Colors");		
		button.addActionListener(this);
		
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.setSize(300, 300);		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		frame.repaint();
	}
}

class MyDrawPanel extends JPanel
{
	
	public void paintCompent(Graphics g)
	{		
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		
	 int red = (int) (Math.random() * 255);
	 int green = (int) (Math.random() * 255);
	 int blue = (int) (Math.random() * 255);

   Color randomColor = new Color(red, green, blue);
   g.setColor(randomColor);
   g.fillOval(70,70,100,100);
		// Graphics2D g2d = (Graphics2D) g;
				
		// int firstRGB = (int) (Math.random()*225);
		// int secondRGB = (int) (Math.random()*225);
		// int thirdRGB = (int) (Math.random()*225);		
		// Color startColor = new Color(firstRGB, secondRGB, thirdRGB);
		
		// firstRGB = (int) (Math.random()*225);
		// secondRGB = (int) (Math.random()*225);
		// thirdRGB = (int) (Math.random()*225);		
		// Color endColor = new Color(firstRGB, secondRGB, thirdRGB);
		
		// GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
		// g2d.setPaint(gradient);
		// g2d.fillOval(70, 70, 100, 100);
	}
}