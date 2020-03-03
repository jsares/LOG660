import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApplicationWindow extends JFrame{
    private final CardLayout cl = new CardLayout();
	JPanel cards = new JPanel(cl);
	private FacadeFilm facade = new FacadeFilm();
	private JTable table = null;
	private DefaultTableModel model = null;

    /**
     * Create the application.
     */
    public ApplicationWindow() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setPreferredSize(new Dimension(900, 550));

        JComponent connectionCard = new ConnectionView(facade, cl, cards).$$$getRootComponent$$$();
        cards.add(connectionCard, "Page de connexion");
        cards.add(searchPage(), "Page de recherche");
        CardLayout cl = (CardLayout)(cards.getLayout());

        contentPane.add(cards);
        cl.show(cards, "Page connexion");
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    ApplicationWindow frame = new ApplicationWindow();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JPanel searchPage() {
        JPanel panel = new JPanel();

		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(310, 414, 297, 25);

		panel.add(searchBtn);
		panel.setLayout(null);
		
		//Jtable setup
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Titre (Annee)");

		model.addRow(facade.getEmptyRow());
		table.setBounds(28, 67, 381, 148);
              
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(311, 65, 297, 338);
	    panel.add(scroll);
	    
	    //Search fields
	    Font font = new Font("SansSerif", Font.PLAIN, 16);
	    
	    JTextArea movieSearchField = new JTextArea();
	    movieSearchField.setBounds(131, 73, 157, 22);
        panel.add(movieSearchField);
	    movieSearchField.setColumns(20);
	    movieSearchField.setFont(font);
	    
	    JLabel lblTitleSearch = new JLabel("Titre");
	    lblTitleSearch.setBounds(30, 76, 56, 16);
        panel.add(lblTitleSearch);
	    
	    JLabel lblSearchBy = new JLabel("Rechercher par");
	    lblSearchBy.setBounds(88, 24, 200, 16);
        panel.add(lblSearchBy);
	    
	    JLabel lblFrom = new JLabel("Ann\u00E9e");
	    lblFrom.setBounds(30, 123, 43, 16);
        panel.add(lblFrom);
	    
	    JTextArea fromTxtField = new JTextArea();
	    fromTxtField.setFont(font);
	    fromTxtField.setColumns(20);
	    fromTxtField.setBounds(131, 117, 56, 22);
        panel.add(fromTxtField);
	    
	    JTextArea toTxtField = new JTextArea();
	    toTxtField.setFont(font);
	    toTxtField.setColumns(20);
	    toTxtField.setBounds(232, 117, 56, 22);
        panel.add(toTxtField);
	    
	    JLabel ProdCountryLabel = new JLabel("Pays production");
	    ProdCountryLabel.setBounds(30, 172, 100, 16);
        panel.add(ProdCountryLabel);
	    
	    JTextArea countryProdtextField = new JTextArea();
	    countryProdtextField.setFont(font);
	    countryProdtextField.setColumns(20);
	    countryProdtextField.setBounds(131, 166, 157, 22);
        panel.add(countryProdtextField);
	    
	    JLabel lblLangue = new JLabel("Langue");
	    lblLangue.setBounds(30, 222, 100, 16);
        panel.add(lblLangue);
	    
	    JTextArea langTextField = new JTextArea();
	    langTextField.setFont(font);
	    langTextField.setColumns(20);
	    langTextField.setBounds(131, 216, 157, 22);
        panel.add(langTextField);
	    
	    JTextArea genreTextField = new JTextArea();
	    genreTextField.setFont(font);
	    genreTextField.setColumns(20);
	    genreTextField.setBounds(131, 267, 157, 22);
        panel.add(genreTextField);
	    
	    JLabel lblGenre = new JLabel("Genre");
	    lblGenre.setBounds(30, 273, 100, 16);
        panel.add(lblGenre);
	    
	    JLabel lblRealisateur = new JLabel("Realisateur");
	    lblRealisateur.setBounds(30, 317, 100, 16);
        panel.add(lblRealisateur);
	    
	    JTextArea realisateurTxtField = new JTextArea();
	    realisateurTxtField.setFont(font);
	    realisateurTxtField.setColumns(20);
	    realisateurTxtField.setBounds(131, 314, 157, 22);
        panel.add(realisateurTxtField);
	    
	    JLabel lblActors = new JLabel("Acteur");
	    lblActors.setBounds(30, 367, 100, 16);
        panel.add(lblActors);
	    
	    JTextArea actorsTextField = new JTextArea();
	    actorsTextField.setFont(font);
	    actorsTextField.setColumns(20);
	    actorsTextField.setBounds(131, 364, 157, 22);
        panel.add(actorsTextField);
	    
	    JLabel lblTitleDetail = new JLabel("Titre");
	    lblTitleDetail.setBounds(662, 65, 247, 16);
        panel.add(lblTitleDetail);
	    
	    JLabel lblYearDetail = new JLabel("Ann\u00E9e");
	    lblYearDetail.setBounds(662, 94, 209, 16);
        panel.add(lblYearDetail);
	    
	    JLabel ProdCountryLabelDetail = new JLabel("Pays production");
	    ProdCountryLabelDetail.setBounds(660, 123, 332, 16);
        panel.add(ProdCountryLabelDetail);
	    
	    JLabel lblLangueDetail = new JLabel("Langue");
	    lblLangueDetail.setBounds(662, 152, 191, 16);
        panel.add(lblLangueDetail);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] result = facade.search(
						movieSearchField.getText(), 
						new String[] {fromTxtField.getText(), toTxtField.getText()},
						countryProdtextField.getText(),
						langTextField.getText(),
						genreTextField.getText(),
						realisateurTxtField.getText(),
						actorsTextField.getText()
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
		
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					SearchItems items = facade.handleRowClick(table.getValueAt(table.getSelectedRow(), 0).toString());
					
					lblTitleDetail.setText(lblTitleDetail.getText() + items.getTitle());
				}
			});
		 return panel;
	}
}
