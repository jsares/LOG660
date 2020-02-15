

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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Rectangle;

public class ApplicationWindow {

	private Session session = null;
	private JFrame frame;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnShowTop10Movies = new JButton("Show top 10 movies");
		
		frame.getContentPane().add(btnShowTop10Movies, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JTextArea movieTextField = new JTextArea();
		movieTextField.setColumns(20);
		panel.add(movieTextField);
		
		
		
		btnShowTop10Movies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				
				List<Film> rows = session.createQuery("FROM Film WHERE ROWNUM <= 10").list(); 
				
				String text = "";
				for(Film film : rows) {
					text += film.getTitre() + "\n";
				}
				
				movieTextField.setText(text);
				session.getTransaction().commit();
				session.close();
				HibernateUtil.shutdown();
			}
		});
	}
}
