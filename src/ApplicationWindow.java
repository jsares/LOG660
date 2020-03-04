import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.table.TableModel;

public class ApplicationWindow extends JFrame{
    private final CardLayout cl = new CardLayout();
	private JPanel cards = new JPanel(cl);
	private FacadeFilm facade = new FacadeFilm();
	private JTable table = null;
	private JTable tablePerson = null;
	private DefaultTableModel model = null;
	private DefaultTableModel modelPerson = null;
	private ArrayList<Personne> listPersonne = new ArrayList<Personne>();

    /**
     * Create the application.
     */
    private ApplicationWindow() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setPreferredSize(new Dimension(1550, 550));

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
		searchBtn.setBounds(55, 414, 190, 25);

		panel.add(searchBtn);
		panel.setLayout(null);

		//Jtable setup
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Titre (Annee)");

		table.setBounds(28, 67, 381, 148);

	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(311, 65, 297, 338);
	    panel.add(scroll);
	    
	    modelPerson = new DefaultTableModel();
	    tablePerson = new JTable(modelPerson);
	    modelPerson.addColumn("Poste: Nom");
	    JScrollPane scrollPerson = new JScrollPane(tablePerson);
        scrollPerson.setBounds(662, 346, 307, 93);
        panel.add(scrollPerson);

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

	    JLabel ProdCountryLabel = new JLabel("Pays production*");
	    ProdCountryLabel.setBounds(30, 172, 100, 16);
        panel.add(ProdCountryLabel);

	    JTextArea countryProdtextField = new JTextArea();
	    countryProdtextField.setFont(font);
	    countryProdtextField.setColumns(20);
	    countryProdtextField.setBounds(131, 166, 157, 22);
        panel.add(countryProdtextField);

	    JLabel lblLangue = new JLabel("Langue*");
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

	    JLabel lblGenre = new JLabel("Genre*");
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

	    JLabel lblActors = new JLabel("Acteur*");
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
	    lblTitleDetail_1.setBounds(703, 65, 719, 16);
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

	    JLabel lblLengthDetail = new JLabel("Dure: ");
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

	    JLabel lblDescription = new JLabel("Description: ");
	    lblDescription.setBounds(662, 267, 73, 16);
        panel.add(lblDescription);

	    JLabel lblDescription_1 = new JLabel("");
	    lblDescription_1.setBounds(745, 267, 703, 16);
        panel.add(lblDescription_1);    
        JLabel lblTrailer = new JLabel("Bande-annonces: ");
	    lblTrailer.setBounds(660, 296, 115, 16);
        panel.add(lblTrailer);
	    JLabel lblTrailer_1 = new JLabel("");
	    lblTrailer_1.setBounds(773, 296, 675, 16);
        panel.add(lblTrailer_1);
	    JLabel lblScenariste_1 = new JLabel("");
	    lblScenariste_1.setBounds(735, 237, 687, 16);
	    panel.add(lblScenariste_1);
	    JLabel lblPersonD = new JLabel("Date :");
        lblPersonD.setBounds(979,348, 43, 14);
        panel.add(lblPersonD);        
        JLabel lblPersonL = new JLabel("Lieu :");
        lblPersonL.setBounds(979, 368, 30, 14);
        panel.add(lblPersonL);
        
        JLabel lblPersonP = new JLabel("Photo :");
        lblPersonP.setBounds(979, 389, 43, 14);
        panel.add(lblPersonP);
        
        JLabel lblPersonB = new JLabel("Biographie :");
        lblPersonB.setBounds(979, 414, 78, 14);
        panel.add(lblPersonB);
        
        JLabel lblPersonDate = new JLabel("");
        lblPersonDate.setBounds(1019, 348, 167, 14);
        panel.add(lblPersonDate);
        
        JLabel lblPersonLieu = new JLabel("");
        lblPersonLieu.setBounds(1019, 368, 272, 14);
        panel.add(lblPersonLieu);
        
        JLabel lblPersonPhoto = new JLabel("");
        lblPersonPhoto.setBounds(1020, 389, 428, 14);
        panel.add(lblPersonPhoto);
        
        JLabel lblPersonBio = new JLabel("");
        lblPersonBio.setBounds(1050, 414, 398, 14);
        panel.add(lblPersonBio);
        

        panel.add(lblScenariste_1);
        
        JButton btnLouer = new JButton("Louer");
        btnLouer.setBounds(374, 415, 190, 23);
        panel.add(btnLouer);
            JLabel lblScenariste = new JLabel("Sc\u00E9naristes:");
        lblScenariste.setBounds(660, 237, 75, 14);
        panel.add(lblScenariste);
        
        JLabel lblLouerMessage = new JLabel("");
        lblLouerMessage.setBounds(311, 449, 297, 14);
        panel.add(lblLouerMessage);
        
        JLabel lblAffiche = new JLabel("Affiche:");
        lblAffiche.setBounds(662, 321, 46, 14);
        panel.add(lblAffiche);
        
        JLabel lblAffiches = new JLabel("");
        lblAffiches.setBounds(713, 321, 735, 14);
        panel.add(lblAffiches);
        
        JLabel lblNewLabel = new JLabel("De");
        lblNewLabel.setBounds(103, 124, 18, 14);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("\u00C0");
        lblNewLabel_1.setBounds(212, 124, 18, 15);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("* Possibilit\u00E9 de faire une recherche multi gr\u00E2ce \u00E0 des virgules");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_2.setBounds(10, 389, 278, 14);
        panel.add(lblNewLabel_2);
        
        btnLouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow < 0)
					lblLouerMessage.setText("Veuillez selectionner un film");
				else
					lblLouerMessage.setText(facade.locationFilm(selectedRow));
				
				table.clearSelection();
			}
		});
        
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLouerMessage.setText("");
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
		
		tablePerson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = tablePerson.getSelectedRow();
				if (selectedRow != -1)
				{
					Personne dude = listPersonne.get(selectedRow);
					if (dude.getDatenaissance() != null)
						lblPersonDate.setText(dude.getDatenaissance().toString());
					else
						lblPersonDate.setText("N/A");
					
					if (dude.getLieunaissance() != null)
						lblPersonLieu.setText(dude.getLieunaissance());
					else
						lblPersonLieu.setText("N/A");
					
					if (dude.getPhoto() != null)
						lblPersonPhoto.setText(dude.getPhoto());
					else
						lblPersonPhoto.setText("N/A");
					
					if (dude.getBiographie() != null)
						lblPersonBio.setText(dude.getBiographie());
					else
						lblPersonBio.setText("N/A");
				}
			}
		});
		
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
				    int selectedRow = table.getSelectedRow();
				    if (selectedRow != -1) {
				    	Object dequoitest = facade.films;
				    	Film leFilm = facade.films.get(selectedRow);
					    //int test = leFilm.getAnneesortie().intValue();
				    	//Clear data in JTable
						int rowCount = modelPerson.getRowCount();
						for(int i = rowCount - 1; i >= 0; i--) {
							modelPerson.removeRow(i);
						}
						modelPerson.addRow(new String[] {"Réalisateur: " + leFilm.getRealisateur().getPersonne().getNom()});
						listPersonne.add(leFilm.getRealisateur().getPersonne());
						//Add data to JTable
						
						Set personnages = leFilm.getPersonnages();
						ArrayList<Personnage> listTest = new ArrayList<Personnage>(personnages);
						for ( Personnage dude : listTest) {
							modelPerson.addRow(new String[] {dude.getPersonnage() + " : " + dude.getPersonne().getNom()});
							listPersonne.add(dude.getPersonne());
						}
						
						
						
						
                        SearchItems items = facade.handleRowClick(table.getValueAt(selectedRow, 0).toString());
                        if (items != null) {
                            lblTitleDetail_1.setText(items.getTitle());
                            lblYearDetail_1.setText(items.getYear()[0]);
                            ProdCountryLabelDetail_1.setText(items.getCountryProd());
                            lblLangueDetail_1.setText(items.getLang());
                            lblLengthDetail_1.setText(items.getLength());
                            lblGenreDetail_1.setText(items.getGenre());
                            lblDescription_1.setText(items.getScenarioDescription());
                            lblTrailer_1.setText(items.getTrailers());
                            lblScenariste_1.setText(items.getScenariste());
                            lblAffiches.setText(facade.films.get(selectedRow).getAffiche());
                        }
                        else {
                            lblTitleDetail_1.setText("");
                            lblYearDetail_1.setText("");
                            ProdCountryLabelDetail_1.setText("");
                            lblLangueDetail_1.setText("");
                            lblLengthDetail_1.setText("");
                            lblGenreDetail_1.setText("");
                            lblDescription_1.setText("");
                            lblTrailer_1.setText("");
                            lblScenariste_1.setText("");
                            lblAffiches.setText("");
                        }
                    }
				}
			});
		 return panel;
	}
}
