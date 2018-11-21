package esercizio_jdbc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Garage {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		Scanner in = new Scanner(System.in);
		while (true) {
			List<Automobile> automobili = elencoAutomobili();
			System.out.print(
					"Scegli l'operazione da eseguire:\n1:stampa elenco\n2:ricerca automobile per marca\n3:inserisci automobile\n4:esporta elenco\n-1:uscita\n>>> ");
			int n = in.nextInt();
			switch (n) {
			case 1:
				stampaElenco(automobili);
				break;
			case 2:
				System.out.print("scegli la marca: ");
				String s = new Scanner(System.in).nextLine().toLowerCase();
				for (Automobile a : automobili) {
					if ((a.getMarca().toLowerCase()).equals(s)) {
						System.out.println(a.getMarca() + " | " + a.getModello() + " | " + a.getNumero_posti());
					}
				}
				break;
			case 3:
				Automobile nuova = new Automobile();
				System.out.println("inserisci id: ");
				nuova.setId(new Scanner(System.in).nextInt());
				System.out.println("inserisci marca: ");
				nuova.setMarca(new Scanner(System.in).nextLine());
				System.out.println("inserisci mdoello: ");
				nuova.setModello(new Scanner(System.in).nextLine());
				System.out.println("inserisci cilindrata: ");
				nuova.setCilindrata(Float.valueOf(new Scanner(System.in).nextLine()));
				System.out.println("inserisci data immatricolazione nel formato AAAA-MM-GG: ");
				nuova.setData_immatricolazione(Date.valueOf(new Scanner(System.in).nextLine()));
				System.out.println("inserisci il numero di posti: ");
				nuova.setNumero_posti(new Scanner(System.in).nextInt());
				creaAutomobile(nuova);
				break;
			case 4:
				BufferedWriter bw = new BufferedWriter(new FileWriter("esportazione_auto.txt"));
				for (Automobile a : automobili) {
					bw.append((a.getMarca() + " | " + a.getModello() + " | " + a.getNumero_posti() + "\n"));
				}
				System.out.println("elenco esportato nel file \"esportazione_auto.txt\"");
				bw.close();
				break;

			case -1:
				System.out.println("PROGRAMMA TERMINATO");
				break;
			default:
				System.out.println("inserire valore valido");
			}
			if (n == -1)
				break;
		}
		in.close();
	}

	public static List<Automobile> elencoAutomobili() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/garage", "root", "");
		Statement comandoSql = conn.createStatement();
		ResultSet dati = comandoSql.executeQuery("select * from automobili");

		List<Automobile> automobili = new ArrayList<>();
		while (dati.next()) {
			Automobile a = new Automobile(dati.getInt("id"), dati.getString("marca"), dati.getString("modello"),
					dati.getFloat("cilindrata"), dati.getDate("data_immatricolazione"), dati.getInt("numero_posti"));
			automobili.add(a);
		}
		return automobili;
	}

	public static void creaAutomobile(Automobile a) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO `garage`.`automobili` "
				+ "(`id`, `marca`, `modello`, `cilindrata`, `data_immatricolazione`, `numero_posti`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/garage", "root", "");
		PreparedStatement comandoSql = conn.prepareStatement(query);

		java.sql.Date sqlDate = new Date(a.getData_immatricolazione().getTime());
		comandoSql.setString(1, String.valueOf(a.getId()));
		comandoSql.setString(2, a.getMarca());
		comandoSql.setString(3, a.getModello());
		comandoSql.setString(4, String.valueOf(a.getCilindrata()));
		comandoSql.setDate(5, sqlDate);
		comandoSql.setString(6, String.valueOf(a.getNumero_posti()));
		comandoSql.executeUpdate();

	}

	public static void stampaElenco(List<Automobile> automobili) {
		for (Automobile a : automobili) {
			System.out.println(a.getMarca() + " | " + a.getModello() + " | " + a.getNumero_posti());
		}
	}

}
