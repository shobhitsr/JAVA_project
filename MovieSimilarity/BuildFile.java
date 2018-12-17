/********************************************************************
Author:    Shobhit Srivastava

Purpose:   The purose of this program is to build the matrix and the
		   		 names of the movies. Then serialize the data so that it
					 can be read by other programs. This program also performs
					 string manuplation to remove the non ascii characters.


Execution: Command to execute the program:

           java Hw4a
This is a standalone program and take the values from the directory


*********************************************************************/

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.*;

class BuildFile implements Serializable
{
	//Initializing the variables for the matrix
	static int rowFile = 0;
	static int rowMatrix = 0;

	public static void main(String[] args)  throws IOException
	{
		//Loading the movie names and the matrix into strings
		String fileName = "movie-names.txt";
		buildNameFile(fileName);	//Call the function to process the file
		String matrixFile = "movie-matrix.txt";
		buildMatrix(matrixFile);	//Call the function to process the matrix

		//Checking if there are the same number of records in both files
		if (rowFile == rowMatrix)
		{
			System.out.println("Name file and matrix both have " + rowFile + " records");
		}

	}

	private static void buildNameFile(String fName)
	{
		//Creating a map of characters to avoid
		Map<String,String> map = new HashMap();
		map.put("é","e");
		map.put("Á","A");
		map.put("è","e");
		map.put("ö","o");

		File fileName = new File(fName);
		String writeFile = "movie-names2.txt";	//Creating a new file to write the old file
		//Initializing the required variables
		String line = null;
		int totalHighCounter = 0;
		int totalLineCounter = 0;
		int totalCounter = 0;
		try
		{
			//creating varables for the read/write buffers
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"ISO-8859-1"));
			FileWriter filewriter = new FileWriter(writeFile);
			BufferedWriter bw = new BufferedWriter(filewriter);
			//Process the file
			while ((line = buffer.readLine()) != null)
			{
				totalCounter++;

				String[] lineSeg = line.split("\\|");	//Split everything by |

				String newString = null;

				int ctr = 0;
				for (int i = 0; i < line.length(); i++)
				{
					char movieChar = line.charAt(i);
					if (movieChar >= 128)
					{
						totalHighCounter++;
						if (ctr == 0)
						{
							System.out.println(line);
							ctr++;
							totalLineCounter += ctr;
						}


						System.out.print(movieChar + " (0x" + Integer.toHexString((int) movieChar) + ")" + " in line " + lineSeg[0] + " char " + ( i + 1 ) + " converted to ");
						Pattern p = Pattern.compile("[é]|[Á]|[è]|[ö]");		//Check if this pattern is in the line
						Matcher m = p.matcher(line);
						StringBuffer sb = new StringBuffer();
						while (m.find())
						{
							m.appendReplacement(sb,map.get(m.group()));	
						}
						m.appendTail(sb);
						newString = sb.toString();

						movieChar = sb.charAt(i);
						System.out.println(movieChar);


					}
					if ((i == line.length() - 1) && ctr > 0)
					{
						System.out.println(newString);
						line = newString;
						System.out.println();
					}


				}

				lineSeg = line.split("\\|");
				String movieNum = ("0000"+lineSeg[0]).substring(lineSeg[0].length());
				String movieTitle = lineSeg[1];
				try
				{

					bw.write(movieNum+movieTitle+"\n");

				}

				catch (IOException ex)
				{
					System.out.println("Error writing to file '" + writeFile + "'");
				}
			}
			bw.close();
			buffer.close();
			rowFile = totalCounter;
			System.out.println("No. of high order chars: " + totalHighCounter);
			System.out.println("Lines with high order chars: " + totalLineCounter );
			System.out.println("Total record count: " + totalCounter );
			System.out.println();

		}
		catch (FileNotFoundException ex)
		{
			System.out.println("File not found '" + fileName + "'");
		}
		catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
	private static void buildMatrix(String mFile) throws IOException
	{
		Scanner matrixFile = new Scanner(new File(mFile));
		String MatrixString;
		List<List<Integer>> reviewTable = new ArrayList<List<Integer>>();
		int row = 0;
		int column = 0;
		int columnCounter = 0;
		int otherColumns =0;
		while (matrixFile.hasNextLine())
		{
			row++;
			MatrixString = matrixFile.nextLine();
			List<Integer> temp =new ArrayList<Integer>();

			String[] line = MatrixString.split(";",-1);
			for (int i = 0; i < line.length; i++)
			{
				if(columnCounter == 0)
				{
					column++;
				}
				else
				{
					otherColumns++;
				}

				if (line[i].isEmpty())
				{
					temp.add(0);

				}
				else
				{
					temp.add(Integer.parseInt(line[i]));
				}

			}

			columnCounter++;
			if (columnCounter > 1 && column != otherColumns)
			{
				System.out.println("All rows don't have the same number of columns as the first row");
			}
			otherColumns = 0;
			reviewTable.add(temp);

		}
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("movie-matrix2.ser"));
			out.writeObject(reviewTable);
			out.close();

		}
		catch (IOException e) {}
		rowMatrix = row;
		System.out.println("*** First row has " + column + " columns");
		System.out.println("*** No. of rows (movies) in matrix = " + row);
		System.out.println("All rows have the same number of columns as the first row");
		System.out.println();

		matrixFile.close();

	}
}
