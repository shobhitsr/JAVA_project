//a class for the table entry which creates and inputs variables into the table.
class TableEntry
{
	private char letter;
	private int num;
	private int freq;
	public TableEntry()		//default constructor
	{
		letter = '\n';
		num = 0;
		freq = 0;
	}
	
	public TableEntry(char a, int b)	//constructor with variables
	{
		letter = a;
		num = b;
		freq = 0;
	}
	public char getLet()	//fetches the letter
	{
		return letter;
	}
	//getnum
	public int getNum()		//fetches the value of the letter
	{
		return num;
	}
	public int getFreq()	//gets the frequency of the letter
	{
		return freq;
	}
	//incfreq
	public void incFreq()	//increments the frequency
	{
		freq++;
	}
	//setlet
	public void setLeter(char let, int number)	//sets the letter variable and its value
	{
		this.letter = let;
		this.num =number;
	}
}
