import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvToTrans {

	static ArrayList<String> keywords = new ArrayList<String>();

	
	
	static public void serializeKeywords(ArrayList<String> keywords) {
		try {
			ObjectOutputStream keywordsSave = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File(
							"dictionnaire.txt"))));
			keywordsSave.writeObject(keywords);
			keywordsSave.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CsvToTrans() {
		char[] semicolon = new String(";").toCharArray();
		char[] newline = new String("\n").toCharArray();

		try {
			BufferedReader i = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("Fic.csv")), "UTF-8"));
			BufferedWriter o = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("fichierTraduit.trans"))));

			char[] buf = new char[1];
			int count = 0;
			// Dans le fichier
			for (;i.read(buf) != -1;) {
				// Dans une ligne
				for ( ; count < 2; ) {
					//
					i.read(buf);
					if (Arrays.equals(semicolon, buf))
						count++;
				}
				count = 0;
				i.skip(1);
				// Dans la liste de TT
				do {
					i.skip(1);
					i.read(buf);
					String ttString = new String();
					// Dans un TT
					for ( ;!Arrays.equals(buf, semicolon)
							&& !Arrays.equals(buf, newline) ; i.read(buf)) {
						ttString += (char) buf[0];
						i.read(buf);
					}
					// Sauvegarde (si besoin) dans l'Arraylist, et écrit l'index
					// de l'item dans le fichier transactions
					o.write(save(ttString) + " ");
				} while (!Arrays.equals(newline, buf));
				o.write('\n');
			}
			serializeKeywords(keywords);
			o.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int save(String TT) {
		int rank = 0;
		for (String s : keywords) {
			if (s.equals(TT)) {
				break;
			}
			++rank;
		}
		if (rank == keywords.size())
			keywords.add(TT);
		return rank;

	}
	
	public static void main(String[] args) {
		new CsvToTrans();
	}
}