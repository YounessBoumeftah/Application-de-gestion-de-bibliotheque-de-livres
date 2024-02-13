package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.ADHERENT;
import model.model;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class View_Emprunt1 {
	private JFrame frame;
	private static model m;
	private JTextField textFieldNumCarte;
	public static String numMembre;
	
	public static String getnumMembre(){
		return numMembre;
	}

	 /*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Emprunt1 window = new View_Emprunt1();
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
	public View_Emprunt1() throws ClassNotFoundException, SQLException {
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
		
		//Message qui va afficher une erreur
		JLabel message = new JLabel("New label");
		message.setBackground(new Color(255, 255, 255));
		message.setForeground(new Color(255, 0, 0));
		message.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(144, 389, 529, 46);
		frame.getContentPane().add(message);
		message.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Entrez votre numéro de carte de membre :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblNewLabel.setBounds(181, 127, 459, 67);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldNumCarte = new JTextField();
		textFieldNumCarte.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		textFieldNumCarte.setBounds(240, 210, 340, 32);
		frame.getContentPane().add(textFieldNumCarte);
		textFieldNumCarte.setColumns(10);
		
		
		//***************************************************************
		// VERS View_Emprunt2
		//***************************************************************

		JButton btnSendNumCarte = new JButton("OK");
		btnSendNumCarte.setBackground(new Color(255, 255, 255));
		btnSendNumCarte.setForeground(new Color(128, 64, 64));
		btnSendNumCarte.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnSendNumCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numMembre = textFieldNumCarte.getText();

				if(model.findadherent(numMembre) != null) {
					try {
						View_Emprunt2 window = new View_Emprunt2();
						frame.dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
				}
				else {
					message.setText("Numéro de carte incorrect !");
					message.setVisible(true);
				}

			}
		});
		btnSendNumCarte.setBounds(364, 275, 85, 21);
		frame.getContentPane().add(btnSendNumCarte);
		
		
		//***************************************************************
		// VERS View_ACCUEIL
		//***************************************************************

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
		btnBack.setBounds(364, 356, 85, 21);
		frame.getContentPane().add(btnBack);
	}
}
