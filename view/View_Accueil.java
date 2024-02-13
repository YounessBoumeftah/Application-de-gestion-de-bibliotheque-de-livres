package view;
import java.awt.EventQueue;

import javax.swing.JFrame;

import model.model;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class View_Accueil {
	private static model m;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Accueil window = new View_Accueil();
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
	public View_Accueil() throws ClassNotFoundException, SQLException{
		model.getAll();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Accueil");
		frame.getContentPane().setBackground(new Color(128, 64, 64));
		frame.setBackground(new Color(128, 64, 64));
		frame.setForeground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnMenu3 = new JButton("Retours");
		btnMenu3.setBackground(new Color(255, 255, 255));
		btnMenu3.setBounds(309, 358, 200, 50);
		btnMenu3.setForeground(new Color(128, 64, 64));
		btnMenu3.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		btnMenu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View_Retour window = new View_Retour();
				frame.dispose();

			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnMenu3);
		
		JButton btnMenu2 = new JButton("Catalogue");
		btnMenu2.setBackground(new Color(255, 255, 255));
		btnMenu2.setBounds(309, 272, 200, 50);
		btnMenu2.setForeground(new Color(128, 64, 64));
		btnMenu2.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		btnMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View_Catalogue window = new View_Catalogue();
				frame.dispose();

			}
		});
		frame.getContentPane().add(btnMenu2);
		
		JButton btnMenu1 = new JButton("Emprunts / Profil");
		btnMenu1.setBackground(new Color(255, 255, 255));
		btnMenu1.setBounds(309, 185, 200, 50);
		btnMenu1.setForeground(new Color(128, 64, 64));
		btnMenu1.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		btnMenu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Emprunt1 window = new View_Emprunt1();
					frame.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnMenu1);
		
		JLabel lblNewLabel = new JLabel("Bibliothèque");
		lblNewLabel.setBounds(214, 110, 392, 50);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		frame.getContentPane().add(lblNewLabel);
	}
}
