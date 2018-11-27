package Arbre.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ArbreUI {

	private JFrame frame;
	private JTextField searchBar;
	private JTextField logs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArbreUI window = new ArbreUI();
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
	public ArbreUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnFichier = new JButton("Fichier");
		toolBar.add(btnFichier);
		
		JButton btnAide = new JButton("Aide");
		toolBar.add(btnAide);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel, BorderLayout.NORTH);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnPanel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnPanel.add(btnSupprimer);
		
		JButton btnChercher = new JButton("Chercher");
		btnPanel.add(btnChercher);
		
		JButton btnPrefixe = new JButton("Prefixe");
		btnPanel.add(btnPrefixe);
		
		JLabel labelQuoi = new JLabel("Quoi?");
		btnPanel.add(labelQuoi);
		
		searchBar = new JTextField();
		btnPanel.add(searchBar);
		searchBar.setColumns(6);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JTree arbre = new JTree();
		tabbedPane.addTab("Arbre", null, arbre, null);
		
		JTextArea liste = new JTextArea();
		tabbedPane.addTab("Liste", null, liste, null);
		
		logs = new JTextField();
		logs.setText("hello");
		logs.setEditable(false);
		frame.getContentPane().add(logs, BorderLayout.SOUTH);
		logs.setColumns(10);
	}

}
