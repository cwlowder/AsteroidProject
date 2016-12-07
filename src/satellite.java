import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import vectorsMatrices.vector;


public class satellite extends body{
	public filePrinter printer=null;
	public satellite(double semi, double e,String name) {
		super(semi, e);
		mass=100;//kg
		try {
			printer=new filePrinter("Data_for_sat_"+name+".txt");//TODO Completly remove all FileWritting for pos of asteriods
		}
		catch (FileNotFoundException | UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		printer.print(name,"Semi:",semi,"Eccen:",e,"MeanAn0:",meanAnomaly0);
		printer.print("generatorType seed",runnerMain.genType);
		printer.divide();
		printer.print("Time","X-comp","Y-comp","Z-comp","Radius of orbit","Theta");
		printer.divide();
	}
	public void draw(Graphics g){
		int xFake=(int)(pos.getX()*runnerMain.zoom);//TODO what scale should I draw at
		int yFake=(int)(pos.getY()*runnerMain.zoom);
		
		g.setColor(Color.white);
		
		g.drawOval(runnerMain.con.xSun+xFake,runnerMain.con.ySun+yFake, 5, 5);
	
	}
	public void gravCalc(){//TODO finnish gravCalc for Sats
		vector temper=new vector();
		for(int i=0;i<runnerMain.bodies.size();i++){
			vector temp=runnerMain.bodies.get(i).pos.sub(pos);//distance vector
			double temp2=runnerMain.G*runnerMain.bodies.get(i).mass;
			double accel=temp2/Math.pow(1.496*Math.pow(10, 11)*temp.mag(),2);
			temp=temp.unitize();
			temper=temper.add(temp.scale(accel));
		}
		printer.print(runnerMain.time,temper.getX(),temper.getY(),temper.getZ(),radius,theta);
	}
	
}
