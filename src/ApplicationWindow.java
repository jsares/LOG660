

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JTable;

public class ApplicationWindow {

	private FacadeFilm facade = null;
	private JFrame frame = null;
	private JTable table = null;
	private DefaultTableModel model = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
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
	public ApplicationWindow() {
		facade = new FacadeFilm();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(310, 414, 297, 25);
		
		frame.getContentPane().add(searchBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Jtable setup
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Titre (Année)");

		model.addRow(facade.getEmptyRow());
		table.setBounds(28, 67, 381, 148);
              
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(311, 65, 297, 338);
	    frame.getContentPane().add(scroll);
	    
	    //Search fields
	    Font font = new Font("SansSerif", Font.PLAIN, 16);
	    
	    JTextArea movieSearchField = new JTextArea();
	    movieSearchField.setBounds(131, 73, 157, 22);
	    frame.getContentPane().add(movieSearchField);
	    movieSearchField.setColumns(20);
	    movieSearchField.setFont(font);
	    
	    JLabel lblTitleSearch = new JLabel("Titre");
	    lblTitleSearch.setBounds(30, 76, 56, 16);
	    frame.getContentPane().add(lblTitleSearch);
	    
	    JLabel lblSearchBy = new JLabel("Rechercher par");
	    lblSearchBy.setBounds(88, 24, 200, 16);
	    frame.getContentPane().add(lblSearchBy);
	    
	    JLabel lblFrom = new JLabel("Ann\u00E9e");
	    lblFrom.setBounds(30, 123, 43, 16);
	    frame.getContentPane().add(lblFrom);
	    
	    JTextArea fromTxtField = new JTextArea();
	    fromTxtField.setFont(font);
	    fromTxtField.setColumns(20);
	    fromTxtField.setBounds(131, 117, 56, 22);
	    frame.getContentPane().add(fromTxtField);
	    
	    JTextArea toTxtField = new JTextArea();
	    toTxtField.setFont(font);
	    toTxtField.setColumns(20);
	    toTxtField.setBounds(232, 117, 56, 22);
	    frame.getContentPane().add(toTxtField);
	    
	    JLabel ProdCountryLabel = new JLabel("Pays production");
	    ProdCountryLabel.setBounds(30, 172, 100, 16);
	    frame.getContentPane().add(ProdCountryLabel);
	    
	    JTextArea countryProdtextField = new JTextArea();
	    countryProdtextField.setFont(font);
	    countryProdtextField.setColumns(20);
	    countryProdtextField.setBounds(131, 166, 157, 22);
	    frame.getContentPane().add(countryProdtextField);
	    
	    JLabel lblLangue = new JLabel("Langue");
	    lblLangue.setBounds(30, 222, 100, 16);
	    frame.getContentPane().add(lblLangue);
	    
	    JTextArea langTextField = new JTextArea();
	    langTextField.setFont(font);
	    langTextField.setColumns(20);
	    langTextField.setBounds(131, 216, 157, 22);
	    frame.getContentPane().add(langTextField);
	    
	    JTextArea genreTextField = new JTextArea();
	    genreTextField.setFont(font);
	    genreTextField.setColumns(20);
	    genreTextField.setBounds(131, 267, 157, 22);
	    frame.getContentPane().add(genreTextField);
	    
	    JLabel lblGenre = new JLabel("Genre");
	    lblGenre.setBounds(30, 273, 100, 16);
	    frame.getContentPane().add(lblGenre);
	    
	    JLabel lblRealisateur = new JLabel("Realisateur");
	    lblRealisateur.setBounds(30, 317, 100, 16);
	    frame.getContentPane().add(lblRealisateur);
	    
	    JTextArea realisateurTxtField = new JTextArea();
	    realisateurTxtField.setFont(font);
	    realisateurTxtField.setColumns(20);
	    realisateurTxtField.setBounds(131, 314, 157, 22);
	    frame.getContentPane().add(realisateurTxtField);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] result = facade.search(
						movieSearchField.getText(), 
						new String[] {fromTxtField.getText(), toTxtField.getText()},
						countryProdtextField.getText(),
						langTextField.getText(),
						genreTextField.getText(),
						realisateurTxtField.getText()
				);
			
				//Clear data in JTable
				int rowCount = model.getRowCount();
				for(int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				
				//Add data to JTable
				for(int i=0; i<result.length;i++) {
					model.addRow(new String[] {result[i]});
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				facade.closeSession();
			    System.exit(0);
		}});
	}
}
