package orbitalCalc;
//this class will use bisection inorder to figure out the eccentric anomally given the meanAnnomally and eccentricity
public class bisectionOrbit{
	//Y represents the eccentric Anommaly in this class
	public bisectionOrbit(){
		
	}
	/*
	 * this class sets up the calc for the bisection process
	 * @param Input: Mean Annomally of body
	 * @returns Output: Eccentric Anomally 
	*/
	public static double setup(double meanAn,double eccen, double time){
		//TODO factor in when meanAn>2*pi
		if (meanAn<(2*Math.PI)){
				return run(meanAn,eccen,0,2*Math.PI);
		}
		else{
			return 0;
		}
	}
	/*
	 * solves for kepler's eqn M=Y-e*sinY
	 * Inputs mean Annommaly, the eccentricity, and an eccentric anomally
	 * outputs the k value or the solution to the equation above(rearranged to be k=M-Y+e*sin(Y)
	*/
	private static double k(double meanAn, double eccen,double Yin){
		return meanAn-Yin+eccen*Math.sin(Yin);
	}
	/*
	 * uses a self-itterating process to solve for eccentric anomaly
	*/
	private static double run(double meanAn,double eccen,double Ylow,double Yhi){
		double Yhalf=(Yhi+Ylow)/2;//TODO make sure Yhalf should be an additive proccess, not negative
		
		if (Math.abs(k(meanAn,eccen,Yhalf))>.0001){//TODO find exceptable error process
			
			
		if(k(meanAn,eccen,Yhalf)>0)//TODO check the direction of these greater/less than symbols
			Ylow=Yhalf;
		else if(k(meanAn,eccen,Yhalf)<0)
			Yhi=Yhalf;
		
		return run(meanAn,eccen,Ylow,Yhi);
		
		
		}
		else{
		return Yhalf;
		}
		//}
	}
}
