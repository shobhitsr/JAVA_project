class DataTable
{
	private TableEntry[] innerTable;
	DataTable()
	{
		innerTable = new TableEntry[26];	//creating the table with 26 variables
		int charNumValue = 1;	//temp variable for tracking the letters
		int charPlace = 0;	//temp variable to find the location of the character
		String str = "abcdefghijklmnopqrstuvwxyz";	//for searching through the string
		for(char c : str.toCharArray())	//check for each character c in the str
		{
			if (charPlace < 9)	//if the location is less than 9
			{
				innerTable[charPlace] = new TableEntry(c,charNumValue);
				charNumValue++;
			}
			else if (charPlace > 9)	//if the loaction is greater than 9
			{
				innerTable[charPlace] = new TableEntry(c,charNumValue);
				charNumValue = charNumValue + 10;
			}
			else
			{
				charNumValue = 0;
				innerTable[charPlace] = new TableEntry(c,charNumValue);
				charNumValue = charNumValue + 10;
			}
			charPlace++;
		}
	}
	//A function to search the character in the input
	private int charSearch(char c)
	{
		for (int i = 0; i < 26; i++)
		{
			if (c == innerTable[i].getLet())
			{
				return i;
			}
			
		}
		return 26;
	}
	//Function to return the value of the character
	public int getTableValue(char x)	//- return the numerological value for character x
	{
		int charLocation = charSearch(x); //find the character
		int value;
		if (charLocation < 26)
		{
			value = innerTable[charLocation].getNum();
			return value;	//returns the value
		}
		return 0;
		//returns the value
	}
	//function to increment the frequency of the letter
	public void bumpCount(char x)	//- add 1 to the count for character x
	{
		int charLocation = charSearch(x);
		if (charLocation < 26)
		{
			innerTable[charLocation].incFreq();
		}
	}
	//funtion to retrive the frequency of the letter
	public int getFrequencyValue(char x)	//- return the count for character x (for printing)
	{
		int charLocation = charSearch(x);
		int value;
		if (charLocation < 26)
		{
			value = innerTable[charLocation].getFreq();
			return value;
		}
		return 0;
	}
	//funtion to print the characters of the table
	public void printCharTable()
	{
		for (int i = 0; i < 5; i++)	//prints out a, b, c, d, e, f one below the outer
		{
			System.out.println();
			System.out.printf("%s%4d   ", innerTable[i].getLet(),innerTable[i].getFreq());
			int j=i+6;	//now j points to g
			while(j<26)	//till the time j isn't 26
			{
				System.out.printf("%s%4d   ",innerTable[j].getLet(),innerTable[j].getFreq());
				j+=5;	//increase j by 5 to get the next letter and its frequency
			}
		}
		System.out.println();
		System.out.printf("%s%4d   ",innerTable[5].getLet(),innerTable[5].getFreq());	//print the last element
	}
}
