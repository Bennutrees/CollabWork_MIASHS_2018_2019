package Arbre.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.InputEvent;

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
		
		JMenuBar toolBar = new JMenuBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_T);
		toolBar.add(menuFichier);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuFichier.add(mntmSauvegarder);
		
		JMenuItem mntmCharger = new JMenuItem("Charger");
		mntmCharger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.ALT_MASK));
		menuFichier.add(mntmCharger);
		
		JMenu menuAide = new JMenu("Aide");
		toolBar.add(menuAide);
		
		JMenuItem mntmApropos = new JMenuItem("A propos");
		menuAide.add(mntmApropos);
		
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
