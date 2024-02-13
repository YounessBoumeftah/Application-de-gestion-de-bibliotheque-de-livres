package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextField;

import controller.main;
import model.model;

import javax.swing.JTextArea;
import java.awt.List;
import javax.swing.SwingConstants;
import java.awt.Color;

public class View_Catalogue {
	private static model model;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Catalogue window = new View_Catalogue();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Catalogue() {
		try {
			model.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
		frame.setVisible(true);
		

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() {
		frame = new JFrame("Catalogue");
		frame.getContentPane().setBackground(new Color(128, 64, 64));
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Retour");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(128, 64, 64));
		btnBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Accueil window = new View_Accueil();
					frame.dispose();

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(354, 423, 118, 21);
		frame.getContentPane().add(btnBack);
		
		
		
		List catalogueLivre = new List();
		catalogueLivre.setBounds(189, 141, 457, 235);
		frame.getContentPane().add(catalogueLivre);

		
		JLabel lblNewLabel = new JLabel("Catalogue");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		lblNewLabel.setBounds(229, 63, 381, 72);
		frame.getContentPane().add(lblNewLabel);
		
		
		for(int i=0;i!=main.getM().getListLivre().size();i++) {
			String dispo;
			if(main.getM().getListLivre().get(i).getEmprunteur()==null) {
				dispo = "Disponible   - ";
			}else {
				dispo = "Indisponible - ";
			}
			
			catalogueLivre.add(
					dispo+
					main.getM().getListLivre().get(i).getTitre()+" - "+
					main.getM().getListLivre().get(i).getAuteur().getPrenom()+" "+
					main.getM().getListLivre().get(i).getAuteur().getNom()
					);
			
			
		}
		
		
		
	}
}
