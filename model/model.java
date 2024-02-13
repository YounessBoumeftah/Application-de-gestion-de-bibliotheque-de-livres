package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class model {
	private static Connection con;
	private static ArrayList<LIVRE> listLivre;
	private static ArrayList<ADHERENT> listAdherent;
	private static ArrayList<AUTEUR> listAuteur;
	
	public ArrayList<LIVRE> getListLivre(){
		return listLivre;
	}
	public ArrayList<AUTEUR> getListLAuteur(){
		return listAuteur;
	}
	public ArrayList<ADHERENT> getListAdherent(){
		return listAdherent;
	}
	public static void getAll() throws SQLException, ClassNotFoundException {
		listAuteur.clear();
		listLivre.clear();
		listAdherent.clear();
		listLivre=new ArrayList<LIVRE>();
		listAuteur=new ArrayList<AUTEUR>();
		listAdherent=new ArrayList<ADHERENT>();

		

		
		//Ajout des auteurs dans la liste
		Statement stm = con.createStatement();
		ResultSet res=stm.executeQuery("SELECT * FROM auteur");
		while(res.next()) {
			AUTEUR auteur= new AUTEUR(String.valueOf(res.getInt("num")),res.getString("nom"),res.getString("prenom"),String.valueOf(res.getDate("date_naissance")),res.getString("description"));
			listAuteur.add(auteur);
		}
		System.out.println("Nombre d'auteurs : "+listAuteur.size());
		
		//Ajout des adherents dans la liste
		res=stm.executeQuery("SELECT * FROM adherent");
		while(res.next()) {
			ADHERENT adherent= new ADHERENT(String.valueOf(res.getInt("num")),res.getString("nom"),res.getString("prenom"),res.getString("email"));
			listAdherent.add(adherent);
		}
		System.out.println("Nombre d'adherents : "+listAdherent.size());

		//Ajout des livres dans la liste
		res=stm.executeQuery("SELECT * FROM livre");
		while(res.next()) {
			LIVRE livre = new LIVRE(String.valueOf(res.getInt("ISBN")),res.getString("titre"),res.getFloat("prix"),null,null);
			listLivre.add(livre);
		}
		System.out.println("Nombre de livres : "+listLivre.size());
		
		res=stm.executeQuery("select titre, ISBN , auteur as numAuteur from livre");
		while(res.next()) {
			String ISBN=res.getString("ISBN");
			String numAuteur = res.getString("numAuteur");
			LIVRE livre = findlivre(ISBN);
			AUTEUR auteur = findauteur(numAuteur);
			
			livre.setAuteur(auteur);
		}
		
		res=stm.executeQuery("select ISBN, adherent as numAdherent from livre");
		while(res.next()) {
			String ISBN=res.getString("ISBN");
			String numAdherent = res.getString("numAdherent");
			if (numAdherent != null) {
				LIVRE livre = findlivre(ISBN);
				ADHERENT adherent = findadherent(numAdherent);
				livre.setEmprunteur(adherent);
				adherent.emprunter(livre);
			}
		}
	}
	
	public static LIVRE findlivre(String ISBN) {		
		for(LIVRE lelivre : listLivre) {
			if(lelivre.getISBN().equalsIgnoreCase(ISBN)) {
				return lelivre;
			}
		}
		return null;
	}
	
	public static AUTEUR findauteur(String num) {		
		for(AUTEUR lauteur : listAuteur) {
			if(lauteur.getNum().equalsIgnoreCase(num)) {
				return lauteur;
			}
		}
		return null;
	}
	
	public static ADHERENT findadherent(String num) {		
		for(ADHERENT ladherent : listAdherent) {
			if(ladherent.getNum().equalsIgnoreCase(num)) {
				return ladherent;
			}
		}
		return null;
	}
	
	public static void emprunter(String numLivre, String numAdherent) throws SQLException{
		Statement stm = con.createStatement();
		String requete = "UPDATE livre SET adherent ='"+numAdherent+"'WHERE ISBN ='"+numLivre+"'";
		int maj = stm.executeUpdate(requete);
	}
	
	public static void rendre(String numLivre) throws SQLException {
		LIVRE livre = findlivre(numLivre);
		Statement stm = con.createStatement();
		
		String requete = "SELECT * FROM livre WHERE ISBN = '"+numLivre+"'";
		ResultSet res=stm.executeQuery(requete);
		String numAdherent = "";
		while(res.next()) {
			numAdherent = res.getString("adherent");
		}
		requete = "UPDATE livre SET adherent = null WHERE ISBN ='"+numLivre+"'";
		int maj = stm.executeUpdate(requete);
		
		ADHERENT adherent = findadherent(numAdherent);
		adherent.rendre(livre);
	}
	
	
	
	public model() throws ClassNotFoundException, SQLException{
		listLivre=new ArrayList<LIVRE>();
		listAuteur=new ArrayList<AUTEUR>();
		listAdherent=new ArrayList<ADHERENT>();
		String BDD ="ap2";
		String url ="jdbc:mysql://localhost:3306/"+BDD;
		String user="root";
		String pwd="";
		
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,pwd);
		this.con=con;
		System.out.println("Connection ok");
	}
}

