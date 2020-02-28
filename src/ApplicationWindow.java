

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

	private Session session = null;
	private FacadeFilm facade = null;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;

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
		
		JButton searchBtn = new JButton("Show top 10 movies");
		searchBtn.setBounds(0, 416, 764, 25);
		
		frame.getContentPane().add(searchBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Titre");
		model.addColumn("Annee");
		model.addColumn("Duree");
		model.addColumn("Langue");
		model.addRow(facade.getEmptyRow());
		table.setBounds(28, 67, 381, 148);
              
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(12, 65, 514, 338);
	    frame.getContentPane().add(scroll);
	    
	    JTextArea movieSearchField = new JTextArea();
	    movieSearchField.setBounds(122, 27, 265, 25);
	    frame.getContentPane().add(movieSearchField);
	    movieSearchField.setColumns(20);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ArrayList<String>> result = facade.searchFilm(movieSearchField.getText());
			
				int rowCount = model.getRowCount();
				for(int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				
				for(int i=0; i<result.size();i++) {
					ArrayList<String> row = result.get(i);
					model.addRow(new String[] {row.get(0), row.get(1), row.get(2), row.get(3)});
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
