/********************************************************************
Author:    Shobhit Srivastava

Purpose:   This is a class file to record the data of the similar
					 movies. This file contains the getters and setters of the
					 main class.


Execution: Command to execute the program:

           java GoodMovie



*********************************************************************/
package goodmovie;
import java.util.*;
public class GoodMovie
{
	int num;
	String name;
	double r_val;
	int rev_count;
	public GoodMovie()
	{
		this.num = 0;
		this.name = null;
		this.r_val = 0;
		this.rev_count = 0;
	}

	public GoodMovie(int Number, String Name, double R, int RevCount)
	{
		this.num = Number;
		this.name = Name;
		this.r_val = R;
		this.rev_count = RevCount;
	}
	//Getter for the number
	public int getNum()
	{
		return num;
	}
	//Setter for the number
	public void setNum(int Number)
	{
		this.num = Number;
	}
	//getter for the Name
	public String getName()
	{
		return name;
	}
	//Setter for the name
	public void setName(String Name)
	{
		this.name = Name;
	}
	//Getter for the r_val
	public double getRVal()
	{
		return r_val;
	}
	//Setter for the r_val
	public void setNum(double R)
	{
		this.r_val = R;
	}
	//Getter for the rev_count
	public int getRevNum()
	{
		return rev_count;
	}
	//Setter for the rev_count
	public void setRevNum(int RevCount)
	{
		this.rev_count = RevCount;
	}

	// nested class for comparator function
	public static Comparator<GoodMovie> goodMovieComparator = new Comparator<GoodMovie>()
	{
		// compare function inside the comparator class
		public int compare(GoodMovie g1, GoodMovie g2)
		{

			return Double.valueOf(g2.getRVal()).compareTo(Double.valueOf(g1.getRVal()));
		}
	};

}
