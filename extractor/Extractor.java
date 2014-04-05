import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import twitter4j.conf.*; 
import twitter4j.*;


public class Extractor {
	
	public static void WriterCSV (ArrayList <Status> tweets) throws FileNotFoundException {
		File csvFile = new File("Fic.csv");
		try {
			csvFile.delete();
			csvFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!csvFile.exists()) 
			throw new FileNotFoundException("Le fichier n'existe pas"); 
		else {
			PrintStream l_out = new PrintStream(new FileOutputStream(csvFile, true));

			for (int i = 0; i < tweets.size(); i++) { 
				Status t = tweets.get(i);
				l_out.print("\"" + t.getCreatedAt() + "\";\"" + '@' + t.getUser().getScreenName() + "\";\"");
				for (int j = 0; j < t.getText().length() ; ++j) {
					if(!Character.isWhitespace(t.getText().charAt(j))) {
						l_out.print(t.getText().charAt(j));
					} else {
						l_out.print("\";\"");
					}
				}
				l_out.println('"');
			}
			l_out.flush(); 
			l_out.close(); 
			l_out=null;
			
		}
	}//writterCSV()

	static void exec() throws FileNotFoundException {
		//setup
		Twitter twitter; 
		Query requete; 
		QueryResult resultat;  
		ArrayList<Status> tweets = new ArrayList<Status>();
		ConfigurationBuilder cb = new ConfigurationBuilder();   
		cb.setOAuthConsumerKey("n43KH8GH3lmFkElEZu9Ezg");   
		cb.setOAuthConsumerSecret("msdUN8agMXem9fpS6rUm3aaWun7tFmpxNEKXobHJrE");   
		cb.setOAuthAccessToken("1952255185-hIMKlhTciiwxjys9jBvCtX0cH99GWqquPVyAvoA");   
		cb.setOAuthAccessTokenSecret("5RIn2tWgFSRk1w86MTHsnDxgGSkezt50iHbkBxJp10eNr");   
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance();
		//
		try {     
			requete = new Query("Boutin");
			
			
			for ( int p =0 ; p < 100 ; )
			{
				requete.setCount(100);
				resultat = twitter.search(requete);
				tweets.addAll(resultat.getTweets());
				WriterCSV(tweets);
				requete.setMaxId(tweets.get(tweets.size()-1).getId()-1);
				
				System.out.println(++p + "%");
			}
		} catch (TwitterException e) {     
				System.out.println("Couldn't connect: " + e);   
		}
		System.out.println("Nombre de tweets : " + tweets.size());
	}//exec()

	public static void main (String [] arg) throws FileNotFoundException{
		long start = System.nanoTime();
		exec();
		System.out.println("Temps d'execution (en ns) : " + (System.nanoTime() - start));
	}//main()

}
