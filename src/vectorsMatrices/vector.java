package vectorsMatrices;
import java.util.ArrayList;
public class vector {
	//this class stores a 3D vector
	//set z(k)=0 for 2D
	public double i=0;//x
	public double j=0;//y
	public double k=0;//z
	/*
	 * These next few methods define the vector
	 * */
	public vector(){//BLANK VECTOR
		i=0;j=0;k=0;
	}
	public vector(coord s,coord e){//s=start;e=end--vector between start to end
		i=e.x-s.x;
		j=e.y-s.y;
		k=e.z-s.z;
	}
	public vector(coord in){//vector from origin to inputed coordinate class
		i=in.x;
		j=in.y;
		k=in.z;
	}
	public vector(double iN,double jN,double kN){//vector from origin to point inputed
		i=iN;
		j=jN;
		k=kN;
	}
	/*
	 * Next 3 methods return specific terms of the vector
	 */
	public double getX(){
		return i;
	}
	public double getY(){
		return j;
	}
	public double getZ(){
		return k;
	}
	/*
	 * Rest of methods are for function
	 */
	public double mag()//returns the magnitude of this vector
	{
		return Math.sqrt(i*i+j*j+k*k);
	}
	
	public void invert(){//inverts the vector's direction
		i=-i;
		j=-j;
		k=-k;
	}
	
	public vector add(vector In){//Adds this and inputed vector and returns the sum
		return new vector(In.i+i,In.j+j,In.k+k);
	}
	public vector sub(vector In){//Subtracts inputed from this vector and returns the remainder
		return new vector(i-In.i,j-In.j,k-In.k);
	}
	public vector scale(double num){		
		return new vector(i*num,j*num,k*num);
	}
	/*
	 * Turns this vector into a column matrix
	 */
	public matrix turnToColumnMatrix(){
		matrix fin=new matrix(3,1);
		fin.setValPos(0,0,i);
		fin.setValPos(1,0,j);
		fin.setValPos(2, 0, k);
		return fin;
	}
	public static vector add(ArrayList<vector> ins){//Adds ArrayList of vectors together(doesn't use local values)
		vector fin=new vector(0,0,0);//this is the vector that the method returns
		for(vector temp:ins){
			fin=fin.add(temp);
		}
		return fin;//returns sum of ins(type=ArrayList) in single vector
	}
	public void print(){//prints out components of this vector
		System.out.println("i: "+i+" j: "+j+" k: "+k);
	}
	//Turns this vector into a vector with magnitude 1
	//but the same direction
	public vector unitize(){
		return scale(1/mag());
	}
}
