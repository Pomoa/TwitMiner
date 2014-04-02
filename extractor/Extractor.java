import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.*;

import twitter4j.conf.*; 
import twitter4j.json.*; 
import twitter4j.management.*; 
import twitter4j.auth.*; 
import twitter4j.api.*; 
import twitter4j.util.*; 
import twitter4j.*;


public class Extractor {

	static TwitterFactory twitterFactory; 
	static Twitter twitter; 
	RequestToken requestToken; 
	static Query requete; 
	static QueryResult resultat;  
	static ArrayList<Status> tweets = new ArrayList();  
	static String msg;
	static String user;
	static String pseudo;
	
	static void setup() {   
		ConfigurationBuilder cb = new ConfigurationBuilder();   
		cb.setOAuthConsumerKey("n43KH8GH3lmFkElEZu9Ezg");   
		cb.setOAuthConsumerSecret("msdUN8agMXem9fpS6rUm3aaWun7tFmpxNEKXobHJrE");   
		cb.setOAuthAccessToken("1952255185-hIMKlhTciiwxjys9jBvCtX0cH99GWqquPVyAvoA");   
		cb.setOAuthAccessTokenSecret("5RIn2tWgFSRk1w86MTHsnDxgGSkezt50iHbkBxJp10eNr");   
		twitterFactory = new TwitterFactory(cb.build());   
		twitter = twitterFactory.getInstance();  
	}
	
	public static void WritterCSV (ArrayList <Status> tweets) throws FileNotFoundException {
		File csvFile = new File("exemple.csv");
		try {
			csvFile.delete();
			csvFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!csvFile.exists()) 
			throw new FileNotFoundException("Le fichier n'existe pas"); 
		else {
			PrintStream l_out = new PrintStream(new FileOutputStream("exemple.csv", true));

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
	}

	static void draw() throws FileNotFoundException {   
		try {     
			requete = new Query("#GouvernementValls");
			
			
			for ( int p =0 ; p < 100 ; p++ )
			{
				requete.setCount(100);
				resultat = twitter.search(requete);
				tweets.addAll(resultat.getTweets());
				WritterCSV(tweets);
				requete.setMaxId(tweets.get(tweets.size()-1).getId()-1);
				
				System.out.println("" + p + '%');
			}
		} catch (TwitterException e) {     
				System.out.println("Couldn't connect: " + e);   
		}
		System.out.print("Nombre de tweets : " + tweets.size());
	}

	public static void main (String [] arg) throws FileNotFoundException{
		setup();
		draw();
	}

}
