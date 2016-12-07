import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

//TODO debug code for errors in the file reading
public class fileReaderDesignated{
	final static Charset ENCODING = StandardCharsets.UTF_8;
	public static String fileName="CeresStandard.txt";//"MostOfAsteriods.txt";or TestOrbitalInfo.txt or CeresStandard.txt or "ELEMENTS.txt"//TODO select correct file
	public fileReaderDesignated() throws FileNotFoundException{
		BufferedReader in=new BufferedReader(new FileReader(fileName));
		try {
			readLargerTextFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 void readLargerTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
		    scanner.nextLine();
		    scanner.nextLine();
		      while (scanner.hasNextLine()){
		    	  if(scanner.hasNext()){
		    		  processLine(scanner.nextLine());
		    	  }
		      }      
		    }
	 }
	 protected void processLine(String aLine){//http://stackoverflow.com/questions/4023146/how-to-split-a-string-by-position-in-java
		    //use a second Scanner to parse the content of each line 
		   		  String temp=aLine.substring(0, 7);
		   		  temp=temp.replaceAll("[^\\d.]", "");
		 		  int ID=Integer.parseInt(temp);
	    		  String name=aLine.substring(7, 12);
	    		  //int forget=;//epoch
	    		  temp=aLine.substring(31, 42);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  double a=Double.parseDouble(temp);//TODO determine what the scale of simulation should be
	    		  temp=aLine.substring(42, 53);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  double e=Double.parseDouble(temp);
	    		  
	    		  asteriod newBody=new asteriod(a,e);//TODO find asteriodal angular shit
	    		  
	    		  double temp2;
	    		  //54 to 63 for inclination
	    		  temp=aLine.substring(54,63);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  temp2=Double.parseDouble(temp);
	    		  newBody.Inclination=Math.toRadians(temp2);
	    		  //64 for argument of periapsis
	    		  temp=aLine.substring(63, 74);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  temp2=Double.parseDouble(temp);
	    		  newBody.ArgPeriapsis=Math.toRadians(temp2);
	    		  //Longitude of ascending node
	    		  temp=aLine.substring(73, 84);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  temp2=Double.parseDouble(temp);
	    		  newBody.LongAscenNode=Math.toRadians(temp2);
	    		  //Mean Anommally/PHI
	    		  temp=aLine.substring(83,96);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  temp2=Double.parseDouble(temp);
	    		  temp2=Math.toRadians(temp2);
	    		  newBody.meanAnomaly0=temp2;
	    		  
	    		  temp=aLine.substring(95,100);
	    		  temp=temp.replaceAll("[^\\d.]", "");
	    		  temp2=Double.parseDouble(temp);
	    		  newBody.H=temp2;
	    		  
	    		  newBody.ID=ID;
	    		  newBody.name=name;
	    		  newBody.setType();
	    		  runnerMain.bodies.add(newBody);
	    		  System.out.println("e:"+newBody.eccentricity+" ID:"+ID+" name: "+name+" LongAsnNd: "+newBody.LongAscenNode+" Peri: "+newBody.ArgPeriapsis+" MeanAn0: "+newBody.meanAnomaly0);
		  }
	 public int checkmass(String name){
		 
		 
		 
		 return 0;
		 
	 }
}
