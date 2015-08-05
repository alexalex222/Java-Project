import javax.swing.*;
import java.awt.*;

public class Button1 {
	
	public static void main(String[] args) {
		Button1 gui = new Button1();
		gui.go();
	}
	
	public void go() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		JButton button2 = new JButton("shock me");
		JButton button3 = new JButton("bliss");
		JButton button4 = new JButton("hah?");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		JButton button = new JButton("Click This!");
		Font bigFont = new Font("serif", Font.BOLD, 28);
		button.setFont(bigFont);
		frame.getContentPane().add(BorderLayout.NORTH, button);
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}