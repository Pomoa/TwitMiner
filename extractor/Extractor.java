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
	static ArrayList<Status> tweets;  
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
			csvFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!csvFile.exists()) 
			throw new FileNotFoundException("Le fichier n'existe pas"); 
		else{
			PrintStream l_out = new PrintStream(new FileOutputStream("exemple.csv", true)); 
		
		l_out.print(tweets);  
		l_out.flush(); 
		l_out.close(); 
		l_out=null;
		
		}
	}

	static void draw() throws FileNotFoundException {   
		try {     
			requete = new Query("#ThanksTokioHotelForTheNewSong");
			
			
			for ( int p =0; p < 100; p++ )
			{     
				requete.setCount(100);
				resultat = twitter.search(requete);
				tweets = (ArrayList<Status>) resultat.getTweets();
				for (int i = 0; i < tweets.size(); i++) {       
					Status t=(Status) tweets.get(i);       
					User u=(User) t.getUser();       
					user=u.getName();      
					pseudo = t.getUser().getScreenName();       
					Date date = t.getCreatedAt();
					System.out.println(i);
				}
				WritterCSV(tweets);
			}
			
			
			System.out.println(tweets.size());
		} catch (TwitterException e) {     
				System.out.println("Couldn't connect: " + e);   
		} 
	}

	public static void main (String [] arg) throws FileNotFoundException{
		setup();
		draw();
	}

}
