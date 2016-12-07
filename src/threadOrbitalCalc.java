
public class threadOrbitalCalc implements Runnable{
	int start;
	int stop;
	public threadOrbitalCalc(int s,int e){
		start=s;
		stop=e;
	}
	public void run(){//A thread for calculating position of each body
		runnerMain.bodies.get(start).calc();
		for(int i=start;i<stop;i++){
			runnerMain.bodies.get(i).calc();//TODO seperate the drawing task from calculating the simulation
		}
	}
}
