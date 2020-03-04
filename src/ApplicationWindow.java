import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ApplicationWindow extends JFrame{
    private final CardLayout cl = new CardLayout();
	private JPanel cards = new JPanel(cl);
	private FacadeFilm facade = new FacadeFilm();
	private JTable table = null;
	private DefaultTableModel model = null;

    /**
     * Create the application.
     */
    private ApplicationWindow() {
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
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            frame.facade.closeSession();
                            System.exit(0);
                        }});
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

	    JLabel lblTitleDetail = new JLabel("Titre: ");
	    lblTitleDetail.setBounds(662, 65, 43, 16);
        panel.add(lblTitleDetail);

	    JLabel lblYearDetail = new JLabel("Ann\u00E9e: ");
	    lblYearDetail.setBounds(662, 94, 62, 16);
        panel.add(lblYearDetail);

	    JLabel ProdCountryLabelDetail = new JLabel("Pays production: ");
	    ProdCountryLabelDetail.setBounds(660, 123, 100, 16);
        panel.add(ProdCountryLabelDetail);

	    JLabel lblLangueDetail = new JLabel("Langue: ");
	    lblLangueDetail.setBounds(662, 152, 50, 16);
        panel.add(lblLangueDetail);

	    JLabel lblTitleDetail_1 = new JLabel("");
	    lblTitleDetail_1.setBounds(703, 65, 379, 16);
        panel.add(lblTitleDetail_1);

	    JLabel lblYearDetail_1 = new JLabel("");
	    lblYearDetail_1.setBounds(713, 94, 62, 16);
        panel.add(lblYearDetail_1);

	    JLabel ProdCountryLabelDetail_1 = new JLabel("");
	    ProdCountryLabelDetail_1.setBounds(761, 123, 332, 16);
        panel.add(ProdCountryLabelDetail_1);

	    JLabel lblLangueDetail_1 = new JLabel("");
	    lblLangueDetail_1.setBounds(725, 152, 50, 16);
        panel.add(lblLangueDetail_1);

	    JLabel lblLengthDetail = new JLabel("Dur\u00E9e: ");
	    lblLengthDetail.setBounds(662, 181, 50, 16);
        panel.add(lblLengthDetail);

	    JLabel lblLengthDetail_1 = new JLabel("");
	    lblLengthDetail_1.setBounds(710, 181, 65, 16);
        panel.add(lblLengthDetail_1);

	    JLabel lblGenreDetail = new JLabel("Genre(s): ");
	    lblGenreDetail.setBounds(662, 210, 62, 16);
        panel.add(lblGenreDetail);

	    JLabel lblGenreDetail_1 = new JLabel("");
	    lblGenreDetail_1.setBounds(725, 210, 578, 16);
        panel.add(lblGenreDetail_1);

	    JLabel lblNomRealisateur = new JLabel("R\u00E9alisateur: ");
	    lblNomRealisateur.setBounds(660, 239, 73, 16);
        panel.add(lblNomRealisateur);

	    JLabel lblNomRealisateur_1 = new JLabel("");
	    lblNomRealisateur_1.setBounds(735, 239, 200, 16);
        panel.add(lblNomRealisateur_1);

	    JLabel lblDescription = new JLabel("Description: ");
	    lblDescription.setBounds(662, 267, 73, 16);
        panel.add(lblDescription);

	    JLabel lblDescription_1 = new JLabel("");
	    lblDescription_1.setBounds(745, 267, 585, 16);
        panel.add(lblDescription_1);

	    JLabel lblTrailer = new JLabel("Bande-annonces: ");
	    lblTrailer.setBounds(660, 296, 115, 16);
        panel.add(lblTrailer);

	    JLabel lblTrailer_1 = new JLabel("");
	    lblTrailer_1.setBounds(773, 296, 569, 16);
        panel.add(lblTrailer_1);

	    JLabel lblScenariste = new JLabel("Scenariste(s): ");
	    lblScenariste.setBounds(660, 320, 100, 16);
        panel.add(lblScenariste);

	    JLabel lblScenariste_1 = new JLabel("");
	    lblScenariste_1.setBounds(745, 320, 585, 16);
        panel.add(lblScenariste_1);

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

					lblTitleDetail_1.setText(items.getTitle());
					lblYearDetail_1.setText(items.getYear()[0]);
					ProdCountryLabelDetail_1.setText(items.getCountryProd());
					lblLangueDetail_1.setText(items.getLang());
					lblLengthDetail_1.setText(items.getLength());
					lblGenreDetail_1.setText(items.getGenre());
					lblNomRealisateur_1.setText(items.getRealisateur());
					lblDescription_1.setText(items.getScenarioDescription());
					lblTrailer_1.setText(items.getTrailers());
					lblScenariste_1.setText(items.getScenariste());
				}
			});
		 return panel;
	}
}
