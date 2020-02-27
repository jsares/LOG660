

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.hibernate.Session;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	FacadeFilm facade = null;
	private JFrame frame;
	private JTable table;

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
		initialize();
		facade = new FacadeFilm();
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
		
		
		String columns[]= {"Titre","Annee"};   
		String data[][]= { 
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
				{"film1","2010"},    
                {"film2","2001"},   
                {"film3","1900"}
		}; 
		
		table = new JTable(data, columns);
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
				movieSearchField.setText(facade.searchFilm(movieSearchField.getText()));
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
