import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class filePrinter{
	private BufferedWriter writer=null;
	public filePrinter() throws FileNotFoundException, UnsupportedEncodingException{
		writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("dump.txt"), "utf-8"));
		try {
			writer.write("Time stamp: "+System.currentTimeMillis());
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public filePrinter(String name) throws FileNotFoundException, UnsupportedEncodingException{
		writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(name), "utf-8"));
		//nextLine();
	}
	public void print(Object x, Object y, Object z,Object t,Object o,Object r,Object rc)
	{
		try {
			writer.write(x+" "+y+" "+z+" "+t+" "+o+" "+r+" "+rc);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object x, Object y, Object z,Object t,Object o,Object r)
	{
		try {
			writer.write(x+" "+y+" "+z+" "+t+" "+o+" "+r);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object x, Object y, Object z,Object t,Object o)
	{
		try {
			writer.write(x+" "+y+" "+z+" "+t+" "+o);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object x, Object y, Object z,Object t)
	{
		try {
			writer.write(x+" "+y+" "+z+" "+t);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object x, Object y, Object z)
	{
		try {
			writer.write(x+" "+y+" "+z);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object x, Object y)
	{
		try {
			writer.write(x+" "+y);
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void print(Object in){
		try {
			writer.write(in+" ");
			nextLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void divide(){
		try {
			writer.write("---------------------------------");
			nextLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void nextLine(){
		try {
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void end(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
