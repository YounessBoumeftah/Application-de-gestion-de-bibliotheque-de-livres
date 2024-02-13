package controller;
import java.sql.SQLException;
import model.model;
import view.View_Accueil;

public class main {
	private static model m;
	
	public static model getM() {
		return m;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		System.out.println("main");
		m=new model();
		View_Accueil window = new View_Accueil();
	}

}
