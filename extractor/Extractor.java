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

	static void draw() {   
		try {     
			requete = new Query("#ThanksTokioHotelForTheNewSong");     
			requete.setCount(100);
			resultat = twitter.search(requete);

			tweets = (ArrayList<Status>) resultat.getTweets();
			for (int i = 0; i < tweets.size(); i++) {       
				Status t=(Status) tweets.get(i);       
				User u=(User) t.getUser();       
				user=u.getName();      
				pseudo = t.getUser().getScreenName();       
				Date date = t.getCreatedAt();
			}
			System.out.println(tweets.size());
		} catch (TwitterException e) {     
				System.out.println("Couldn't connect: " + e);   
		} 
	}
			
		
	
	
	public static void main (String [] arg){
		setup();
		draw();
	}

}
