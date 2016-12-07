package vectorsMatrices;

public class rotationalMatrix{
	//this class returns a matrix that allows for rotations based off an inputed theta
	//https://en.wikipedia.org/wiki/Rotation_matrix
	public rotationalMatrix(){
		
	}
	public static matrix identity(){
		matrix fin=new matrix(3,3);
		fin.fill(new double[]{1,0,0,0,1,0,0,0,1});
		return fin;
	}
	public static matrix rotateX(double theta){
		matrix fin=new matrix(3,3);
		fin.fill(new int[]{1,0,0,0,0,0,0,0,0});
		fin.setValPos(1, 1, Math.cos(theta));
		fin.setValPos(2, 2, Math.cos(theta));
		fin.setValPos(2, 1, Math.sin(theta));
		fin.setValPos(1, 2, -Math.sin(theta));
		return fin;
	}
	public static matrix rotateY(double theta){
		matrix fin=new matrix(3,3);
		fin.fill(new int[]{0,0,0,0,1,0,0,0,0});
		fin.setValPos(0, 0, Math.cos(theta));
		fin.setValPos(2, 2, Math.cos(theta));
		fin.setValPos(0, 2, Math.sin(theta));
		fin.setValPos(2, 0, -Math.sin(theta));
		return fin;
	}
	public static matrix rotateZ(double theta){
		matrix fin=new matrix(3,3);
		fin.fill(new int[]{0,0,0,0,0,0,0,0,1});
		fin.setValPos(0, 0, Math.cos(theta));
		fin.setValPos(1, 1, Math.cos(theta));
		fin.setValPos(1, 2, Math.sin(theta));
		fin.setValPos(0, 1, -Math.sin(theta));
		return fin;
	}
	/*
	 * This method takes in orbital discriptors and
	 * calculates the finished rotationed position vector
	 * Only returns the positional vector of the perihelion
	 * inputs: Wp=argPeri	i=incl	Ω=longAscNde
	 * Outputs: Matrix that stands in as a positional vector pointing towards the perihelion
	 * Look at Prof Larson's paper for the source of this method
	 */
	public static matrix rotateComplete(double argPeri,double incl,double longAscNde,double radius){
		matrix fin=new matrix(1,3);//TODO this entire section screwed up my program before...so it may need a double checking later
		fin.fill(new double[]{Math.cos(argPeri)*Math.cos(longAscNde)-Math.sin(argPeri)*Math.cos(incl)*Math.sin(longAscNde),
							  Math.cos(argPeri)*Math.sin(longAscNde)+Math.sin(argPeri)*Math.cos(incl)*Math.cos(longAscNde),
							  Math.sin(argPeri)*Math.sin(incl)});
		fin=fin.scale(radius);//TODO should I multiply this by the periaps
		return fin;
	}
	/*
	 * This Method takes in orbital descriptors and returns a vector centered at the origin that is perpindicular to the orbital plane of a body
	 * Inputs: i=inclination	Ω=longAscNde
	 */
	public static matrix rotatePerp(double incl,double longAscNde){
		matrix fin=new matrix(1,3);
		fin.fill(new double[]{
				Math.sin(incl)*Math.sin(longAscNde),
				-Math.sin(incl)*Math.cos(longAscNde),
				Math.cos(incl)
				});
		return fin;
	}
	/*
	 * rotates swinger around axis by theta
	 * Both matrices must be horizonal 1x3 matrices
	 * inputs: swinger(matrix), axis(matrix), theta
	 * outputs: a new column matrix that makes a vector towards the final pos
	 * Link:http://steve.hollasch.net/cgindex/math/rotvec.html
	 * that first section is all I looked at
	 */
	public static matrix rotate(matrix swinger,matrix axis,double theta){//TODO remove all prints
		matrix fin=new matrix(3,1);
		matrix i=rotationalMatrix.identity();
		matrix L=new matrix(3,3);
		theta=-theta;//CORRECTING FOR RIGHT HAND RULE THAT PHYSICS LOVES
		L.fill(new double[]{0,axis.getValPos(0, 2),-axis.getValPos(0, 1),
							-axis.getValPos(0, 2),0,axis.getValPos(0, 0),
							axis.getValPos(0,1),-axis.getValPos(0, 0),0
							});
		double d=Math.sqrt(axis.getValPos(0,0)*axis.getValPos(0,0)+axis.getValPos(0,1)*axis.getValPos(0,1)+axis.getValPos(0,2)*axis.getValPos(0,2));
		//temp value to find the final vector
		
		matrix temp=L.multiply(L);//([L]x[L])
		temp=temp.scale((1 - Math.cos(theta))/(d*d));//(1 - cos(a))/(d*d)*([L]x[L])
		matrix temp2=(L.scale(Math.sin(theta)/d));//sin(a)/d*[L]
		temp=temp2.add(temp);//sin(a)/d*[L] + ((1 - cos(a))/(d*d)*([L]x[L]))
		temp=temp.add(i);//[i] + sin(a)/d*[L] + ((1 - cos(a))/(d*d)*([L]x[L]))
		fin=swinger.multiply(temp);//[v]x{[i] + sin(a)/d*[L] + ((1 - cos(a))/(d*d)*([L]x[L]))}
		return fin;	//TODO finish this method
	//Equation used:
	/*
	 * [v] = [v]x{
	 * 		[i] + sin(a)/d*[L] + (
	 * 			(1 - cos(a))/(d*d)*([L]x[L])
	 *		 )
	 * } 
	 * aka this:
	 * [v] = [v]x{[i] + sin(a)/d*[L] + ((1 - cos(a))/(d*d)*([L]x[L]))} 
	 */
	}
	
}
