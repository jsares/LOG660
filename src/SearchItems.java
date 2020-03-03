
public class SearchItems {

	private String title;
	private String[] year;
	private String countryProd;
	private String lang;
	private String genre;
	private String realisateur;
	private String acteur;
	private String length;
	private String scenarioDescription;
	private String trailers;
	private String scenariste;
	
	public SearchItems() {
	}

	public SearchItems(String title, String[] year, String countryProd, String lang, String genre, String realisateur, String acteur) {
		this.title = title;
		this.year = year;
		this.countryProd = countryProd;
		this.lang = lang;
		this.genre = genre;
		this.realisateur = realisateur;
		this.acteur = acteur;
	}
	
	public boolean isAllEmpty() {
		return this.title.isEmpty() 
				&& (this.year[0].isEmpty() && this.year[1].isEmpty()) 
				&& this.countryProd.isEmpty()
				&& this.lang.isEmpty()
				&& this.genre.isEmpty()
				&& this.realisateur.isEmpty()
				&& this.acteur.isEmpty();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getYear() {
		return year;
	}

	public void setYear(String[] year) {
		this.year = year;
	}

	public String getCountryProd() {
		return countryProd;
	}

	public void setCountryProd(String countryProd) {
		this.countryProd = countryProd;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getActeur() {
		return acteur;
	}

	public void setActeur(String acteur) {
		this.acteur = acteur;
	}
	
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	public String getScenarioDescription() {
		return scenarioDescription;
	}

	public void setScenarioDescription(String scenarioDescription) {
		this.scenarioDescription = scenarioDescription;
	}
	
	public String getTrailers() {
		return trailers;
	}

	public void setTrailers(String trailers) {
		this.trailers = trailers;
	}
	
	
	public String getScenariste() {
		return scenariste;
	}

	public void setScenariste(String scenariste) {
		this.scenariste = scenariste;
	}

}
