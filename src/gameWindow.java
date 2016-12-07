import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class gameWindow extends JPanel{
	public static int xSun=500;
	public static int ySun=500;
	
	public int xDegShift;
	public int yDegShift;
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 10000, 10000);
		g.setColor(Color.white);
		g.drawString("time: "+runnerMain.time, 10, 10);
		g.drawString("timeTaken(milisecs): "+runnerMain.timeTaken, 10, 30);
		g.setColor(Color.red);
		g.fillOval(xSun-5,ySun-5,10,10);
		//runnerMain.test.draw(g);
		g.setColor(Color.yellow);
		for(body temp:runnerMain.bodies){
			temp.draw(g);
		}
		for(body temp:runnerMain.sats){
			temp.draw(g);
		}
	}
	public gameWindow(){
		JFrame jf=new JFrame();
		jf.setTitle("GravWaveSim");
		jf.setSize(1000,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
	}
}
