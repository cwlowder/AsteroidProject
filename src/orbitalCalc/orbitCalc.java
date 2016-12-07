package orbitalCalc;
public class orbitCalc{//this class will take parameters and calc the change in angle for a body at a time
	/*Usefull link:https://en.wikipedia.org/wiki/Eccentric_anomaly
	 * this method works on this eqn:
	 * https://upload.wikimedia.org/math/f/9/e/f9e86507781d52cc564d7d759f6d44b2.png
	 */
	public orbitCalc(){
		//this is only a constructor
	}
	/*
	 * This method calculates the delta angle per time change for a Orbiting body
	 * inputs: period(yrs), eccentricity, time(years)
	 * outputs: change of angle(radians)
	 */
	public static double calc(double period,double eccen,double time,double meanAn0){
	double meanAn=meanAn0+((2*Math.PI*time)/period);//TODO-How stupid is this??
	while(meanAn>2*Math.PI){
		meanAn-=2*Math.PI;
	}
	//double meanAn=(2*Math.PI*time)/period;//TODO factor in the mean motion at epoch/make sure current works
	//System.out.println("mean An:"+meanAn);//TODO remove this
	double Y=bisectionOrbit.setup(meanAn,eccen,time);//the eccentric anomally
	//System.out.println("Y: "+Y);//TODO remove this
	//these next lines of code use the equation from the link above
	//TODO fix any quadrant/large time/theta>2PI issues
	double specE=Math.sqrt((1+eccen)/(1-eccen));//just a way to simplify the code a little more
	double theta=2*Math.atan(specE*Math.tan(Y/2));
	//System.out.println("theta: "+theta);//TODO remove this
	return theta;
	}
	/*
	 * Equivalent of above, just with inputed Mean anomally
	 */
}
