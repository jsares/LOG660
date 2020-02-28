

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
		
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Titre (Année)");

		model.addRow(facade.getEmptyRow());
		table.setBounds(28, 67, 381, 148);
              
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(311, 65, 297, 338);
	    frame.getContentPane().add(scroll);
	    
	    JTextArea movieSearchField = new JTextArea();
	    movieSearchField.setBounds(131, 73, 157, 22);
	    frame.getContentPane().add(movieSearchField);
	    movieSearchField.setColumns(20);
	    
	    JLabel lblTitleSearch = new JLabel("Titre");
	    lblTitleSearch.setBounds(30, 76, 56, 16);
	    frame.getContentPane().add(lblTitleSearch);
	    
	    JLabel lblSearchBy = new JLabel("Rechercher par");
	    lblSearchBy.setBounds(88, 24, 200, 16);
	    frame.getContentPane().add(lblSearchBy);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] result = facade.search(movieSearchField.getText());
			
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
