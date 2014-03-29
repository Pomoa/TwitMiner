import java.util.*;

import twitter4j.conf.*; 
import twitter4j.json.*; 
import twitter4j.management.*; 
import twitter4j.auth.*; 
import twitter4j.api.*; 
import twitter4j.util.*; 
import twitter4j.*;

	
public class Extractor {

	//Déclaration des objets 
	static TwitterFactory twitterFactory; 
	static Twitter twitter; 
	RequestToken requestToken; 
	//Déclaration de l'objet requête 
	static Query requete; 
	//Déclaration de l'objet de résultat de requête 
	static QueryResult resultat;  
	//Déclaration d'un tableau pour stocker les requêtes récupérées de Twitter 
	static ArrayList tweets;  
	//déclaration des variables textes qui nous permettront d'utiliser 
	//les résultats de requêtes 
	static String msg;
	static String user;
	static String pseudo;  
	static void setup() {   
		//Authentification   
		ConfigurationBuilder cb = new ConfigurationBuilder();   
		cb.setOAuthConsumerKey("n43KH8GH3lmFkElEZu9Ezg");   
		cb.setOAuthConsumerSecret("msdUN8agMXem9fpS6rUm3aaWun7tFmpxNEKXobHJrE");   
		cb.setOAuthAccessToken("1952255185-hIMKlhTciiwxjys9jBvCtX0cH99GWqquPVyAvoA");   
		cb.setOAuthAccessTokenSecret("5RIn2tWgFSRk1w86MTHsnDxgGSkezt50iHbkBxJp10eNr");   
		//Utilisation des informations d'authentification pour se connecter à l'API twitter   
		twitterFactory = new TwitterFactory(cb.build());   
		twitter = twitterFactory.getInstance();  
	}  
	static void draw() {   
		// on règle le rafraichissement de l'affichage de la page à   
		// toutes les 5 secondes pour éviter des demandes trop fréquentes   
		//on renouvelle l'arrière plan noir pour éviter la superposition des messages   

		//Essai   
		try {     
			// Requête de recherche, ici le hashtag prestige     
			requete = new Query("#prestige");     
			//Récupération des résultats de requête     
			resultat = twitter.search(requete);

			//Stockage des résultats de requête result dans le tableau Tweet     
			tweets = (ArrayList) resultat.getTweets();  
			System.out.println(tweets);
			// isolement de chaque tweet 
			// pour chaque élément du tableau tweets...     
			for (int i = 0; i < tweets.size(); i++) {       
				// ...obtenir le statut du tweet       
				Status t=(Status) tweets.get(i);       
				// ...obtenir l'utilisateur du tweet       
				User u=(User) t.getUser();       
				// ...obtenir le nom de l'utilisateur du tweet       
				user=u.getName();      
				// ...obtenir le texte du message du tweet       msg = t.getText();       
				//obtenir le pseudo de l'utilisateur du tweet       
				pseudo = t.getUser().getScreenName();       
				// ...obtenir la date du message du tweet       
				Date date = t.getCreatedAt();        
				//Couleur du texte affiché dans l'applet Processing       
				// Taille du texte affiché dans l'applet Processing       
				// Affichage du texte (en récupérant la variable msg) dans l'applet Processing       
				// chaque nouveau tweet sera affiché à une nouvelle ligne       
				// avec un interligne de 15 px       
				}   
			}   
		// si l'essai ne fonctionne pas, on imprime dans la console "Couldn't connect:"   
		catch (TwitterException e) {     
			System.out.println("Couldn't connect: " + e);   
		} 
		}
			
		
	
	
	public static void main (String [] arg){
		setup();
		draw();
	}

}
