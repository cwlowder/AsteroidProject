package vectorsMatrices;

public class matrix {
	//Note: Colm is plural of Col (column)
	public int rows;//num of rows(vertical direction)
	public int colm;//now of colm(horizontal direction)
	private double[][] values;
	public matrix(int xi, int yi){
		rows=xi;colm=yi;
		values=new double[rows][colm];
	}
	//this class paints in the Matrix with a given int[] like a book
	public void fill(int[] inVals){
		int pos=0;//position of the 
		if(rows*colm==inVals.length){
			for(int i=0;i<rows;i++){
				for(int j=0;j<colm;j++){
					setValPos(i,j,(double)inVals[pos]);
					pos++;
				}
			}
		}
	}
	//Same as above but with doubles
	public void fill(double[] inVals){
		int pos=0;//position of the 
		if(rows*colm==inVals.length){
			for(int i=0;i<rows;i++){
				for(int j=0;j<colm;j++){
					setValPos(i,j,inVals[pos]);
					pos++;
				}
			}
		}
	}
	//returns the val at a position in the matrix
	public double getValPos(int rowNum,int colNum){
		return values[rowNum][colNum];
	}
	//this sets the value of a position in a matrix
	public void setValPos(int rowNum,int colNum,double d){
		if(rowNum<rows&colNum<colm)
		values[rowNum][colNum]=d;
		else
		System.out.println("(Array Class)Out of bounds setValPos");
	}
	//this method returns the number of rows
	public int getNumRows(){
		return rows;
	}
	//this method returns the number of columns
	public int getNumColm(){
		return colm;
	}
	//prints the array out as it should look
	public void print(){
		System.out.println("Matrix");
		System.out.println("------------------");
		for(int i=0;i<rows;i++){
			for(int j=0;j<colm;j++){
				System.out.print(values[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("------------------");
	}
	//----------------WARNING:MATH------------------\\
	/*
	 * This class adds to matrices together
	 * takes this and given matrix
	 * and returns the sum in a new matrix
	 * Will not work if the matrices aren't equal in size
	 */
	public matrix add(matrix in){
		matrix fin=new matrix(getNumRows(),getNumColm());
		if(in.getNumColm()==getNumColm()&in.getNumRows()==getNumRows()){
			for(int i=0;i<rows;i++){
				for(int j=0;j<colm;j++){
					fin.setValPos(i, j, getValPos(i,j)+in.getValPos(i, j));
				}
			}
			return fin;
		}
		System.out.println("(Matirx) ERROR in adding-the two matrices have different dimensions");
		return null;//incase dimensions do not match up
	}
	//multiplies two matrices together if possible
	//returns the product as a new matrix
	public matrix multiply(matrix in){
		matrix fin=new matrix(getNumRows(),in.getNumColm());
		if(getNumColm()==in.getNumRows()){
			for(int i=0;i<rows;i++){//cycles through rows of first matrix
				for(int j=0;j<in.colm;j++){//Cycles through colm of 2nd matrix
					//temp storage of the value to be computed
					double tempVal=0;
					for(int x=0;x<colm;x++){//Cycles through position
							//System.out.print(getValPos(i,x)+"x"+in.getValPos(x, j)+"+");//TODO remove this
							tempVal+=(getValPos(i,x)*in.getValPos(x, j));
						}
					//System.out.println();//TODO remove this
					fin.setValPos(i, j, tempVal);
					}
					
				}
			return fin;
			}
		System.out.println("(Matirx) ERROR in multiplying-the two matrices have different dimensions");
		return null;
	}
	/*
	 * this method takes in a double and multiplies each stored term by that value
	 * Inputs: double
	 * Outputs: matrix
	 * preforms a scale multiplication
	 */
	public matrix scale(double in){
		matrix fin=this;
		for(int i=0;i<rows;i++){
			for(int j=0;j<colm;j++){
				fin.setValPos(i, j,fin.getValPos(i, j)*in);
			}
		}
		return fin;
	}
}
