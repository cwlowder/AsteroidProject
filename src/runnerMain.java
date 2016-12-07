import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import vectorsMatrices.vector;


public class runnerMain {//TODO update asteriods
	//public static int nDeg=500;
	public static double timeChange=.0001;//0.000019025875;//0.000019025875 or .000009506625;//years
	//public static body test=new body(100,.5,0,0);
	public static double tempRotate=0;//TODO remove
	public static ArrayList<body> bodies=new ArrayList<body>();
	public static ArrayList<satellite> sats=new ArrayList<satellite>();//List of satelites
	public static double time=0;//universal standard time
	public static gameWindow con=new gameWindow();
	
	public static filePrinter testPrint=null;
	
	public static int delay=0;//number of ms btw steps
	
	public static double totalMass=0;
	
	public static int carb=0;
	public static int stone=0;
	public static int metal=0;
	public static int error=0;
	
	public static double zoom=50;
	
	private static int genTypeSeed=152123;//152123:Mass=6.633167524635761E21
	private static int genSizeSeed=624125;
	private static int genMassSeed=624125;
	public static Random genType=new Random(genTypeSeed);//Random number generator for Type of asteriod
	public static Random genSize=new Random(genSizeSeed);
	public static Random genMass=new Random(genMassSeed);
	private static final int threadCountOrb=1;
	
	public static int timeTaken=0;
	public static double G=6.674*Math.pow(10,-11);
	public static void main(String[] args) {
		//con.repaint();		
		try {
			testPrint=new filePrinter();//TODO Completly remove all FileWritting for pos of asteriods
		} catch (FileNotFoundException | UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//Chunck is done
		//Test Sattelite-from earth's data
		satellite testSat=new satellite(1.0000,0,"1");
		//satellite testSat2=new satellite(1.0000,0,"2");
		//satellite testSat3=new satellite(1.0000,0,"3");
		testSat.Inclination=0;
		//testSat2.Inclination=0;testSat2.meanAnomaly0=.087;//about 5 degrees
		//testSat3.Inclination=0;testSat3.meanAnomaly0=.174;//about 10 degrees
		sats.add(testSat);
		//sats.add(testSat2);
		//sats.add(testSat3);

		try {
			fileReaderDesignated fRD=new fileReaderDesignated();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true){
		try {//TODO move this Refresh thingy some where else
			Thread.sleep(delay);
			//System.out.println(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timerStart=System.currentTimeMillis();
		ArrayList<Thread> threadsOrb=new ArrayList<Thread>();
		int incrementAmount=runnerMain.bodies.size()/threadCountOrb;
		int starting=0;
		for (int i=0;i<threadCountOrb;i++)
		{
			System.out.println("New Thread from:"+starting+" ID:"+bodies.get(starting).name+" to "+(starting+incrementAmount));
			threadsOrb.add(new Thread(new threadOrbitalCalc(starting, starting+incrementAmount)));
			starting+=incrementAmount;
		}
		for(int i=0;i<threadCountOrb;i++){
			threadsOrb.get(i).start();
		}
		for(int i=0;i<threadCountOrb;i++)
		{
			try {
				threadsOrb.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		for(body temp:bodies){
			temp.calc();//TODO seperate the drawing task from calculating the simulation
		}
		*/
		for(satellite temp:sats){
			temp.calc();//TODO seperate the drawing task from calculating the simulation
			temp.gravCalc();
		}
		timeTaken=(int) (System.currentTimeMillis()-timerStart);
		System.out.println("Ceres:"+bodies.get(1).pos.i+" "+bodies.get(1).pos.j);
		//System.out.println("Ceres:"+bodies.get(1).x+" "+bodies.get(0).y);
		System.out.println("Real Time passed:"+(timeTaken));
		//testPrint.print(time,System.currentTimeMillis()-timerStart);
		System.out.println("time is "+time+", moving time forward by "+timeChange);
		System.out.println("TotalMass:"+totalMass);//TODO remove mass printer
		time+=timeChange;
		con.repaint();
		}
		
	}
}