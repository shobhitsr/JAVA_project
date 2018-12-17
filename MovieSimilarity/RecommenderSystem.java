/********************************************************************
Author:    Shobhit Srivastava

Purpose:   This program emulates a recommender system that suggests
					 movies on the basis of the movie that you enter in the
					 prompt. To this we use the serialised file and process
					 the matrix to find the file name and then use person
					 r corelation algorithm to find the similar movies.


Execution: Command to execute the program:

           java RecommenderSystem
This is a standalone program and take the values from the directory


*********************************************************************/
import java.io.*;
import java.util.*;
class RecommenderSystem
{

	static List<String> movieNamesArray = new ArrayList<String>();
	static List<List<Integer>> movieMatrixArray = new ArrayList<List<Integer>>();
	static int [][] movieTwoDArray;
	static List<Integer> similarMovies = new ArrayList<Integer>();
	public static void main(String[] args)
	{
		String movieNumber = "";
		String userPrompt = "Enter a movie number: ";
		int num = 0;

		buildNameFile2();

		buildMatrix2();

		//While not q or quit
		while( !( movieNumber.equals( "q" ) || movieNumber.equals( "quit" ) ) )
		{
			//Ask user for a movie number
			System.out.println( userPrompt );
			Scanner userPromptScanner = new Scanner( System.in );
			movieNumber = userPromptScanner.next();

			//Processes the number and looks for comparisons
			try
			{
				num = Integer.parseInt( movieNumber );
				if( ( num < 0 ) || ( num > 1682 ) )
				{
					userPrompt = "Movie number must be between 1 and 1682";
				}
				else
				{
					String[] part = movieNamesArray.get( num - 1 ).split("(?<=\\d)(?=\\D)");
					System.out.printf( "***" + "%d %s\n", Integer.parseInt(part[0]), part[1]  + " \n \n" );

					printComparisons(commonReviewer(Integer.parseInt(part[0]), part[1]) , similarMovies , (num-1));
					userPrompt = "Enter a movie number: ";
				}
			}

			//Error if number is not entered
			catch( NumberFormatException numberError )
			{
				userPrompt = "Enter only numbers";
				continue;
			}
		}

	}
	static List<String> buildNameFile2()
	{
		//Create a file stream for "movie-names2.txt"
		try
		{
			FileInputStream movieNamesFile = new FileInputStream( "movie-names2.txt" );
			Scanner movieNamesScanner = new Scanner( movieNamesFile );
			while( movieNamesScanner.hasNextLine() )
			{
				movieNamesArray.add( movieNamesScanner.nextLine() );
			}
		}
		catch (FileNotFoundException ex)
		{
			//System.out.println("File not found '" + movieNamesFile + "'");
		}
		catch (IOException ex)
		{
			//System.out.println("Error reading file '" + movieNamesFile + "'");
		}

		return movieNamesArray;
	}
	static List<List<Integer>> buildMatrix2()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream (new FileInputStream( "movie-matrix2.ser" ));
			movieMatrixArray = (List<List<Integer>>) in.readObject();
			in.close();
		}
		catch (FileNotFoundException ex)
		{
			//System.out.println("File not found '" + movieMatrixFile + "'");
		}
		catch (IOException ex)
		{
			//System.out.println("Error reading file '" + movieMatrixFile + "'");
		}
		catch (ClassNotFoundException ex)
		{

		}
		return movieMatrixArray;

	}
	public static List<Double> commonReviewer(int selectedMovieNumber, String selectedMovieName)
	{
		List<Integer> selectedMovieList = movieMatrixArray.get(selectedMovieNumber-1);
		ArrayList<Double> movieScore = new ArrayList<Double>();
		for (int i = 0; i < movieMatrixArray.size(); i++)
		{
			List<Integer> movie1 = new ArrayList<Integer>();
			List<Integer> movie2 = new ArrayList<Integer>();
			int count = 0;
			for(int j = 0; j < movieMatrixArray.get(0).size(); j++)
			{
				if (selectedMovieList.get(j) != 0 && movieMatrixArray.get(i).get(j) != 0)
				{
					count++;
					movie1.add(selectedMovieList.get(j));
					movie2.add(movieMatrixArray.get(i).get(j));
				}
			}
			if (count < 10)
			{
				continue;
			}
			else
			{

				double moviePoint = calcPearson( selectedMovieNumber , selectedMovieName , movie1 , movie2 );

				boolean flag = false;
				for(int k = 0; k < movieScore.size(); k++)
				{
					if (movieScore.get(k) < moviePoint)
					{
						movieScore.add(k,moviePoint);
						flag = true;
						similarMovies.add(k,i);
						break;
					}

				}
				if (!flag)
				{
					movieScore.add( moviePoint );
					similarMovies.add( i );
				}

			}

		}

		return movieScore;

	}
	//printComparisons function
	static void printComparisons( List<Double> comparisons, List<Integer> movieNumbers, int selectedMovieNumber )
	{
		//If number is less than 20
		if( comparisons.size() < 20 )
		{
			System.out.println( "Insufficient number of comparison movies" );
			return;
		}

		//Prints headers
		System.out.println( "         R"+ "       No."+ "     Reviews "+ "          Name"  );

		//Prints comparisons
		for( int i = 0; i < 20; i++ )
		{
			System.out.printf( "%3d", i + 1 );
			System.out.printf( "%10.6f", comparisons.get( i ) );
			System.out.printf( " %6d ", ( movieNumbers.get( i )+1 ) );
			System.out.printf( " (%3d %s", totalReviews( movieNumbers.get( i ), selectedMovieNumber ), " reviews ) " );
			System.out.printf( " %-30s", movieNamesArray.get( movieNumbers.get( i )).substring(4));
			System.out.printf( "\n" );
		}
	}

	// calc sum of ints
	static int calcSum(List<Integer> vec)
	{
		int movieSum = 0;
		int movieLength = vec.size();

		//Calulate the mean
		for( int t1 = 0; t1 < movieLength; t1++ )
		{
			movieSum = movieSum + vec.get( t1 );
		}
		return movieSum;
	}

	// calc avg
	static double calcAvg(List<Integer> vec)
	{
		double sum = calcSum(vec);
		int moviesLength = vec.size();
		double moviesMean = sum / ( double )moviesLength;

		return moviesMean;
	}

	// calc std dev
	static double calcStd(List<Integer> vec, double moviesMean)
	{
		//Variable declarations
		double tempVariable = 0;

		//Calculate the deviation
		for( int i = 0; i < vec.size(); i++ )
		{
			tempVariable += Math.pow( vec.get( i ) - moviesMean, 2 );
		}

		//Return the deviation
		return ( Double )Math.sqrt( tempVariable / ( ( double )vec.size() - 1 ) );
	}

	// calc Pearson
	static double calcPearson(int movieNum, String movieName, List<Integer> targetRatings, List<Integer> compareRatings)
	{
		//Variable declarations
		double pearsonMean1 = calcAvg( targetRatings );
		double pearsonMean2 = calcAvg( compareRatings );
		double pearsonDeviation1 = calcStd( targetRatings, pearsonMean1 );
		double pearsonDeviation2 = calcStd( compareRatings, pearsonMean2 );
		double movieScore = 0;

		//Calculate the Pearson coefficient
		for(int i = 0; i < targetRatings.size(); i++ )
		{
			double pearsonDifference1 = ( ( double )targetRatings.get( i ) ) - pearsonMean1;
			double pearsonDifference2 = ( double )compareRatings.get( i ) - pearsonMean2;
			movieScore += ( ( pearsonDifference1 / pearsonDeviation1 ) * ( pearsonDifference2 / pearsonDeviation2 ) ) / ( targetRatings.size() - 1 );
		}

		//Return movie score
		return movieScore;
	}
	//totalReviews function
	static int totalReviews( int movNumber, int selectedMovNumber )
	{
		//Variable declarations
		int reviewsNumber = 0;
		List<Integer> movieReviewsArray = movieMatrixArray.get(selectedMovNumber);

		//Calculate total number of reviews
		for(int i = 0; i < movieMatrixArray.get(0).size(); i++ )
		{
			if( movieMatrixArray.get(movNumber).get(i) != 0 && movieMatrixArray.get(selectedMovNumber).get(i) != 0 )
			{
				reviewsNumber++;
			}
		}

		//Return number of reviews
		return reviewsNumber;
	}

}
