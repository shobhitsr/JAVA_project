/********************************************************************
Author:    Shobhit Srivastava

Purpose:   In this project we start with the number 1000 and start 
	   substracting by 1 repeatedly from it untill a number 
	   ending with zero appears. Then switch to substracting by 2
	   and so on. The execution stops when the next substraction 
	   causes a negative number.

Execution: Command to execute the program:

           java Henderson_Method.java nnn
           where nnn is some random integer
           If no parameters are provided, Default of 1000 is used.


*********************************************************************/

public class Henderson_Method
{
	public static void main(String[] args)
	{
		int StartNumber = 1000; // Setting the default value if no parameters are provided
		
		//initializing the required variables
		int SubstractBy = 1;
		int SubstractByCounter = 0;
		int TotalSpokenCounter = 0;
		int TotalPassedCounter = 0;
		float AverageNumbersSpoken;
		float AverageNumbersPassed;
		float Increment;
		
		if ( args.length > 0 ) {
			StartNumber = Integer.valueOf(args[0]); // taking the arguments from the command line
		}
		System.out.println("Decrement    Current      Count"); // printing the header
		System.out.printf("               %5d%n",StartNumber); // printing the number that we start with
		
		while ( StartNumber >= SubstractBy ) //till the starting number is greater than the the substract by do
		{
			StartNumber -= SubstractBy; //Reducing the start number by the value of SubstractBy
			SubstractByCounter++; //incrementing the SubstractByCounter
			TotalSpokenCounter++; //incrementing the TotalSpokenCounter
			TotalPassedCounter += SubstractBy; // calculating the total numbers passed
			
			if ( StartNumber % 10 == 0 ) //if the number ends with a zero then display contents
			{
				System.out.format("%9d      %5d       %5d ", SubstractBy, StartNumber, SubstractByCounter); //formatting the output
				for (int i = 0; i < SubstractByCounter; i++)
					System.out.print("*"); //printing out stars for the number of times substracted.
				System.out.println();
				SubstractBy++; //incrementing SubstractBy to the next value
				SubstractByCounter = 0; //Reset counter
			}
		}
		
		if ( StartNumber > 0 ) //to display the last number
		{
			System.out.format("%9d      %5d       %5d ", SubstractBy, StartNumber, SubstractByCounter); //formatting the output
		
			for (int i = 0; i < SubstractByCounter; i++)
				System.out.print("*"); //printing out stars for the number of times substracted.
			System.out.println();
			SubstractBy++; //incrementing SubstractBy to the next value
		}
		
		Increment = SubstractBy - 1;
		
		AverageNumbersSpoken = TotalSpokenCounter / Increment; //Calculating the average numbers spoken
		AverageNumbersPassed = TotalPassedCounter / Increment; //Calculating the average numbers passed
		
		//Printing out the total numbers spoken,its count and the average of it
		System.out.println("There were " + TotalSpokenCounter + " numbers spoken with " + Increment + " different increments.");
		System.out.printf("There were an average of %.2f cycles per increment.%n", AverageNumbersSpoken);

		//Printing out the total numbers passed,its count and the average of it		
		System.out.println("There were " + TotalPassedCounter + " numbers passed by with " + Increment + " different increments.");
		System.out.printf("There were an average of %.2f numbers per increment.%n", AverageNumbersPassed);
	}
}
