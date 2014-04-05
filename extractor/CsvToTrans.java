import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class CsvToTrans {
	
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
		InputStream ips=new FileInputStream("Fic.csv");
		InputStreamReader ipsr=new InputStreamReader(ips);
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
					for ( ; ligne.charAt(i) !='"'; ) {
						mot += ligne.charAt(i++);
						if(i >= ligne.length()) break;
					}
					cpt++;
					l_out.println("" + cpt + "=" + mot);
					
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