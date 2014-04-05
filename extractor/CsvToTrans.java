import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class CsvToTrans {
	
	public static boolean existeDeja(String mot) throws IOException {
		InputStream ips2=new FileInputStream("fichierTraduit.txt");
		InputStreamReader ipsr2=new InputStreamReader(ips2);
		BufferedReader br2=new BufferedReader(ipsr2);
		String test = br2.readLine();
		for (int i = 0 ; i < test.length(); i++) {
			if ( test.charAt(i) == '=')
			{
				for (int p = 0 ; mot.length() > p ; p++, i++) {
					if ( i == test.length()) break;
					if ( test.charAt(i) != mot.charAt(p) )
						return false;
				}
				return true;
				
			}
		}
		return true;
	}
	
	public static void csvToTrans() throws IOException {
		int cpt = 0;
		File file = new File ("fichierTraduit.txt");
		try {
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintStream l_out = new PrintStream(new FileOutputStream(file, true));
		InputStream ips=new FileInputStream("test.csv");
		InputStream ips2=new FileInputStream("fichierTraduit.txt");
		InputStreamReader ipsr=new InputStreamReader(ips);
		InputStreamReader ipsr2=new InputStreamReader(ips2);
		BufferedReader br=new BufferedReader(ipsr);

		
		String ligne = br.readLine();

		for (; ligne != null ;) {
			int i = 0;
			
			for (int p = 0 ; p < 3 ; ) {
				if(i>=ligne.length()) break;
				if (ligne.charAt(i) == '"') {
					p++;
					i++;
					if(i>=ligne.length()) break;
					if (ligne.charAt(i) == ';') {
						p=0;
						i++;
					}
				} else {
					String mot = new String();
//					BufferedReader br2=new BufferedReader(ipsr2);
//					String test = br2.readLine();
					for ( ; ligne.charAt(i) !='"'; ) {
						mot += ligne.charAt(i++);
						//System.out.println(mot);
						if(i >= ligne.length()) break;
					}
					//System.out.println(mot);
					
					//System.out.println(mot);
					if ( cpt == 0) {
						cpt++;
						l_out.println("" + cpt + "=" + mot);
					} else {
						
						if (!existeDeja(mot)) {
							System.out.println(mot);
							l_out.println("" + cpt + "=" + mot);
						}
//						System.out.println(test);
//						for ( ; test !=null ; ) {
//							System.out.println(test);
//							if (!test.contains(mot)) {
//								System.out.println(test);
//								cpt++;
//								l_out.println("" + cpt + "=" + mot);
//							}
//						test = br2.readLine();
//						}
//						System.out.println(test);
					}
				}
				
			}
			ligne = br.readLine();
		}
		l_out.close();
		br.close();
	}
	
	public static void main (String [] arg) throws IOException{
		csvToTrans();
	}//main()

}