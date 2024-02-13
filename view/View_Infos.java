package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.ADHERENT;
import model.model;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import controller.main;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Infos {

	private JFrame frame;
	private static String numMembre;
	private static ADHERENT adherent;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Infos window = new View_Infos();
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
	public View_Infos() throws ClassNotFoundException, SQLException {
		model.getAll();
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Profil");
		frame.getContentPane().setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		frame.getContentPane().setBackground(new Color(128, 64, 64));
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		numMembre = View_Emprunt1.getnumMembre();
		adherent = model.findadherent(numMembre);
		
		List listEmprunts = new List();
		listEmprunts.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		listEmprunts.setBounds(453, 174, 256, 150);
		frame.getContentPane().add(listEmprunts);
		
		for(int i=0;i!=adherent.getListLivre().size();i++) {
			listEmprunts.add(
					main.getM().getListLivre().get(i).getTitre()+" - "+
					main.getM().getListLivre().get(i).getAuteur().getPrenom()+" "+
					main.getM().getListLivre().get(i).getAuteur().getNom()
					);
			
			
		}
		
		JLabel lblNewLabel = new JLabel("Profil");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		lblNewLabel.setBounds(106, 57, 631, 51);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(93, 219, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prénom");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(93, 258, 66, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse email");
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(93, 302, 119, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Numéro de membre");
		lblNewLabel_4.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(93, 177, 176, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel num = new JLabel("Affichage num");
		num.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		num.setForeground(new Color(255, 255, 255));
		num.setBounds(279, 170, 239, 26);
		frame.getContentPane().add(num);
		num.setText(adherent.getNum());
		
		JLabel nom = new JLabel("Affichage nom");
		nom.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		nom.setForeground(new Color(255, 255, 255));
		nom.setBounds(279, 215, 279, 21);
		frame.getContentPane().add(nom);
		nom.setText(adherent.getNom());

		JLabel prénom = new JLabel("Affichage prenom");
		prénom.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		prénom.setForeground(new Color(255, 255, 255));
		prénom.setBounds(279, 251, 251, 26);
		frame.getContentPane().add(prénom);
		prénom.setText(adherent.getPrenom());

		JLabel email = new JLabel("Affichage email");
		email.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		email.setForeground(new Color(255, 255, 255));
		email.setBounds(279, 298, 162, 26);
		frame.getContentPane().add(email);
		email.setText(adherent.getEmail());
		
		JButton btnBack = new JButton("Retour");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(128, 64, 64));
		btnBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Emprunt2 window = new View_Emprunt2();
					frame.dispose();

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(375, 432, 85, 21);
		frame.getContentPane().add(btnBack);
	}
}
