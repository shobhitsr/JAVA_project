/********************************************************************
Author:    Shobhit Srivastava
Date Due:  10/18/17 11:59 pm

Purpose:   In this project numerology is used to assign a numerical 
	   value to a string based on values assigned to the individual letters according to a variety of rules.
		   

Execution: Command to execute the program:

           java Text_Numerology.java cccc
           where cccc is some random string
           If no parameters are provided, the program throws 
           an exception.


*********************************************************************/
class Text_Numerology
{
		//Parm  Length  Total  String
		//----  ------  -----  ------
		public static void main(String[] args)
		{
			DataTable dTable = new DataTable();	//creating a new table
			int total = 0;	//temp variable to count the total count
			int totalLetter = 0;	//temp varable to store the total number of letters
			String convertToLowerString;	//string to store the converted to lower string
			char convertToLowerChar;	//char to store the converted to lower char
			System.out.println("Parm  Length  Total  String");
			System.out.println("----  ------  -----  ------");
			System.out.println();
			System.out.println();
			if (args.length <=0)	//if no arguements are entered
			{
				System.out.println("Invalid or no string entered");
				return;	//return an error
			}
			else
			{
				for (int i = 0; i < args.length; i++)	//for the entire string in the arguement
				{
					for (int j = 0; j < args[i].length(); j++)	//for each string in the arguement
					{
						convertToLowerString = args[i].toLowerCase();	//convert each string tolower case
						convertToLowerChar = convertToLowerString.charAt(j);	//put each character in the variable
						dTable.bumpCount(convertToLowerChar);	//increase the frequency of the letter
						total = total + dTable.getTableValue(convertToLowerChar);	//calculate the total
						if (dTable.getTableValue(convertToLowerChar) != 0)	//till the value isn't 0
						{
							totalLetter++;
						}
						
						
					}
					System.out.printf("%4d%7d  %6d  %s%n", i+1, args[i].length(), total, args[i]);
					total = 0;	//reset total
				}
			}
			System.out.println();
			dTable.printCharTable();	//print the table
			System.out.println();
			System.out.println("Total letters = " + totalLetter);	//print all the total number of letters
		}
}
