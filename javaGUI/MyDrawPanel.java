import javax.swing.*;
import java.awt.*;

class MyDrawPanel extends JPanel
{
	
	public void paintCompent(Graphics g)
	{		
		Graphics2D g2d = (Graphics2D) g;
				
		int firstRGB = (int) (Math.random()*225);
		int secondRGB = (int) (Math.random()*225);
		int thirdRGB = (int) (Math.random()*225);		
		Color startColor = new Color(firstRGB, secondRGB, thirdRGB);
		
		firstRGB = (int) (Math.random()*225);
		secondRGB = (int) (Math.random()*225);
		thirdRGB = (int) (Math.random()*225);		
		Color endColor = new Color(firstRGB, secondRGB, thirdRGB);
		
		GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}