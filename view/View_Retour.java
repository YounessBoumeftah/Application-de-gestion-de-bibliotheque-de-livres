package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import model.ADHERENT;
import model.LIVRE;
import model.model;

import javax.swing.JTextField;

public class View_Retour {

	private JFrame frame;
	private JTextField textFieldISBN;
	public static String numMembre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Retour window = new View_Retour();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View_Retour() {
		initialize();
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Retours");
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
		message.setBounds(132, 334, 529, 46);
		frame.getContentPane().add(message);
		message.setVisible(false);
		
		JButton btnBack = new JButton("Retour");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(128, 64, 64));
		btnBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
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
		btnBack.setBounds(353, 391, 85, 21);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Entrez le numéro du livre à rendre :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(185, 153, 459, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		textFieldISBN.setColumns(10);
		textFieldISBN.setBounds(244, 236, 340, 32);
		frame.getContentPane().add(textFieldISBN);
		
		JButton btnSendNumLivre = new JButton("OK");
		btnSendNumLivre.setBackground(new Color(255, 255, 255));
		btnSendNumLivre.setForeground(new Color(128, 64, 64));
		btnSendNumLivre.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnSendNumLivre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String numLivre = textFieldISBN.getText();

		        System.out.println("ISBN : " + numLivre);

		        LIVRE livre = model.findlivre(numLivre);

		        if (livre != null) {
		            ADHERENT emprunteur = livre.getEmprunteur();

		            if (emprunteur != null) {
		                try {
		                    model.rendre(numLivre); // Utilisation de la méthode rendre à la place de emprunter

		                    message.setText("Le livre a été retourné avec succès !");
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    message.setText("Erreur lors du retour du livre !");
		                }
		            } else {
		                message.setText("Le livre n'est pas actuellement emprunté.");
		            }
		        } else {
		            message.setText("Numéro de livre incorrect !");
		        }

		        message.setVisible(true);
		    }
		});
		btnSendNumLivre.setBounds(353, 302, 85, 21);
		frame.getContentPane().add(btnSendNumLivre);
		
		
	}
}
