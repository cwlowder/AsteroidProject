import java.awt.Color;
import java.awt.Graphics;
import orbitalCalc.orbitCalc;
import vectorsMatrices.*;

public class body {//distances in AU
	public String name="N/A";
	public int ID=-9999;
	public double apoapsis;//highest point of orbit
	public double periapsis;//lowest point of orbit
	public double eccentricity;
	public double theta;//angle from orient(true anomaly) in radians
	//public double phi;//angle from orient at start of simulation
	
	public double meanAnomaly0;//mean anomaly at epoch
	public double eccentricAnomaly;//the eccentric anomaly
	public double period;//period of the orbit
	public double semimajor;//semi major axis
	public double semiminor;//semi major axis
	public double radius;
	
	public double LongAscenNode=0;//longitude of Ascending Node
	public double ArgPeriapsis=0;//Argument of Periapsis
	public double Inclination=0;//Inclination
	
	public vector pos=new vector(0,0,0);//position vector
	public double x=10;
	public double y;
	public double z;
	
	public double mass;//mass of this body
	
	public body(double semi,double e){
		semimajor=semi;
		eccentricity=e;
		semiminor=semimajor*Math.sqrt(1-e*e);
		period=Math.sqrt(Math.pow(semimajor,3));
		radius=semimajor*(1-eccentricity*eccentricity)/(1+eccentricity*Math.cos(theta));
		
		apoapsis=(1+e)*semimajor;
		periapsis=(1-e)*semimajor;
	}
	public void calc(){//https://en.wikipedia.org/wiki/Kepler's_laws_of_planetary_motion
		//double n=2*Math.PI/period;//mean motion
		theta=orbitCalc.calc(period,eccentricity,runnerMain.time,meanAnomaly0);//TODO factor in mean anomally since epoch
		radius=semimajor*(1-eccentricity*eccentricity)/(1+eccentricity*Math.cos(theta));
		//TODO this code is all shit

		matrix posPerhel=rotationalMatrix.rotateComplete(ArgPeriapsis, Inclination, LongAscenNode,radius);//positional vector for the periapsis TODO make sure to check if longAsceNde should be shifted like in Prof Larson's paper
		matrix posPerpPerhel=rotationalMatrix.rotatePerp(Inclination, LongAscenNode);
		posPerhel=rotationalMatrix.rotate(posPerhel, posPerpPerhel, theta);
		
		//x=posPerhel.getValPos(0, 0);
		//y=posPerhel.getValPos(0, 1);
		//z=posPerhel.getValPos(0, 2);
		pos.i=posPerhel.getValPos(0, 0);
		pos.j=posPerhel.getValPos(0, 1);
		pos.k=posPerhel.getValPos(0, 2);
		if(pos.i==0&&pos.j==0&&pos.k==0){
			System.out.println("zeroer- "+ID);
		}
		//runnerMain.testPrint.print(100*x,100*y,100*z,runnerMain.time,theta,radius,temp);//TODO remove
	}
	public void draw(Graphics g){
		int xFake=(int)(pos.i*runnerMain.zoom);//TODO what scale should I draw at
		int yFake=(int)(pos.j*runnerMain.zoom);
		
		g.setColor(Color.red);
		if(ID==0)
		{}
		else
			g.drawOval(runnerMain.con.xSun+xFake,runnerMain.con.ySun+yFake, 10, 10);
	
	}
	public void print(){
		System.out.println("name: "+name);
		System.out.println("radius: "+radius);
		System.out.println("angle: "+theta);
	}
}
