package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.model;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Button;

public class View_Emprunt2 {

	private JFrame frame;
	private JTextField textFieldNumLivre;
	public static String numMembre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Emprunt2 window = new View_Emprunt2();
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
	public View_Emprunt2() throws ClassNotFoundException, SQLException {
		model.getAll();
		initialize();
		frame.setVisible(true);


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Emprunts");
		frame.getContentPane().setBackground(new Color(128, 64, 64));
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		numMembre = View_Emprunt1.getnumMembre();

		//Message qui va afficher une erreur
		JLabel message = new JLabel("New label");
		message.setBackground(new Color(255, 255, 255));
		message.setForeground(new Color(255, 0, 0));
		message.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(144, 309, 529, 46);
		frame.getContentPane().add(message);
		message.setVisible(false);
		
		JLabel lbl = new JLabel("Entrez le numéro du livre à emprunter :");
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lbl.setBounds(181, 127, 459, 67);
		frame.getContentPane().add(lbl);
		
		textFieldNumLivre = new JTextField();
		textFieldNumLivre.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		textFieldNumLivre.setColumns(10);
		textFieldNumLivre.setBounds(240, 210, 340, 32);
		frame.getContentPane().add(textFieldNumLivre);
		
		JButton btnSendNumCarte = new JButton("OK");
		btnSendNumCarte.setBackground(new Color(255, 255, 255));
		btnSendNumCarte.setForeground(new Color(128, 64, 64));
		btnSendNumCarte.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnSendNumCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numLivre = textFieldNumLivre.getText();
				System.out.println("ISBN : "+numLivre);

				if(model.findlivre(numLivre) != null){
					if(model.findlivre(numLivre).getEmprunteur() == null) {
						if(model.findadherent(numMembre).getNmbrEmprunts()<5) {
							try {
								model.emprunter(numLivre, numMembre);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								System.out.println("erreur");
							}
							message.setText("Le livre a été emprunté avec succès !");
							message.setVisible(true);
						}else {
							message.setText("Nombre d'emprunts maximum atteint !");
							message.setVisible(true);
						}

					}else {
						message.setText("Erreur");
						message.setVisible(true);
					}
				}else {
					message.setText("Numéro de livre incorrect !");
					message.setVisible(true);
				}
			}
		});
		btnSendNumCarte.setBounds(359, 276, 85, 21);
		frame.getContentPane().add(btnSendNumCarte);
		
		
		//***************************************************************
		// VERS View_Emprunt1
		//***************************************************************

		JButton btnBack = new JButton("Retour");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(128, 64, 64));
		btnBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					View_Emprunt1 window = new View_Emprunt1();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnBack.setBounds(359, 352, 85, 21);
		frame.getContentPane().add(btnBack);
		
		JButton btnInfos = new JButton("Profil");
		btnInfos.setBackground(new Color(255, 255, 255));
		btnInfos.setForeground(new Color(128, 64, 64));
		btnInfos.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Infos window = new View_Infos();
					frame.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnInfos.setBounds(313, 419, 179, 32);
		frame.getContentPane().add(btnInfos);
		
		
	}
}
