import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class asteriod extends body{
	public int type;//0=carb;1=stone;2=metalic
	public double density;//kg/m^3
	public double H;
	public double pv;//albedo
	public asteriod(double semi, double e) {
		super(semi, e);
	}
	/*
	 * This method sets the type, density and volume thus mass of the asteriods
	 */
	public void setType(){//TODO determine pv for each
		Random gen=runnerMain.genType;
		double x=gen.nextDouble();
		if(0<x&&x<.75){
			type=0;
			runnerMain.carb++;
			density=1380;
			pv=.06;
		}
		else if(.75<=x&&x<.92){
			type=1;
			runnerMain.stone++;
			density=2710;//kg/m^3
			pv=.18;
		}
		else if(x<1){
			type=2;
			runnerMain.metal++;
			density=5320;//kg/m^3
			pv=.12;
		}
		else{
			//Failure
			type=-1;
			runnerMain.error++;
			density=-1;//kg/m^3
			pv=-1;
		}
		mass=findMass();
		runnerMain.totalMass+=mass;
		}
		//Next to methods out of Quantifying Earth Impact Risks
		public double findMass(){
			if(ID==1){//TODO deactivate if you want to do another trail
				return 939.3*Math.pow(10, 18);//TODO add back in
			}
			else if (ID==4)
				return 259.076*Math.pow(10, 18);//kg
			else if (ID==2)
				return 201*Math.pow(10, 18);//kg
			else if (ID==10)
				return 86.7*Math.pow(10, 18);
			else if (ID==31)
				return 58.1*Math.pow(10, 18);
			else if (ID==704)
				return 38.8*Math.pow(10, 18);
			else if (ID==511)
				return 37.7*Math.pow(10, 18);
			else if (ID==532)
				return 33*Math.pow(10, 18);
			else if (ID==15)
				return 31.8*Math.pow(10, 18);
			else if (ID==15)
				return 33*Math.pow(10, 18);
			else if (ID==3)
				return 28.6*Math.pow(10, 18);
			else if (ID==16)
				return 22.7*Math.pow(10, 18);
			else if (ID==52)
				return 22.7*Math.pow(10, 18);
			else if (ID==88)
				return 18.3*Math.pow(10, 18);
			else if (ID==7)
				return 16.2*Math.pow(10, 18);
			else if (ID==13)
				return 16*Math.pow(10, 18);
			else if (ID==423)
				return 16*Math.pow(10, 18);
			else if (ID==29)
				return 15.2*Math.pow(10, 18);
			else if (ID==87)
				return 14.78*Math.pow(10, 18);
			else if (ID==48)
				return 12*Math.pow(10, 18);
			else
				return Math.PI*density*Math.pow(findDiameter(),3)/6;
			//return runnerMain.genMass.nextInt(1000000);//TODO remove
		
			
			
			//int probCurve=(int) (Math.pow(Math.E, -Math.pow(per, 2))/Math.pow(Math.PI, .5));
		}
		public double findDiameter(){
			return 1329*1000*Math.pow(10, -H/5)*Math.pow(pv,-1/2);
		}
	public void draw(Graphics g){
		int xFake=(int)(pos.getX()*runnerMain.zoom);//TODO what scale should I draw at
		int yFake=(int)(pos.getY()*runnerMain.zoom);
		g.setColor(Color.yellow);
		g.drawOval(runnerMain.con.xSun+xFake,runnerMain.con.ySun+yFake, 1, 1);
	
	}

}
