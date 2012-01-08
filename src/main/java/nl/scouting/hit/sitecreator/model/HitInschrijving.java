package nl.scouting.hit.sitecreator.model;

import org.joda.time.LocalDate;

public class HitInschrijving {

	private String locatie; // required
	private String formulierNaam; // required
	private Integer formulierNummer; // required
	private Integer aantalDeelnemers;
	private Integer minimumAantalDeelnemers;
	private Integer maximumAantalDeelnemers;
	private Integer maximumAantalLedenUitEenGroep;
	private Integer minimumLeeftijd;
	private Integer maximumLeeftijd;
	private Integer aantalDeelnemersAndereOrganisaties;
	private Integer gereserveerd; // required
	private Integer subgroepen;
	private Integer minimumAantalSubgroepen;
	private Integer maximumAantalSubgroepen;
	private Integer minimumAantalDeelnemersInSubgroep;
	private Integer deelnemersInSubgroepTellenMeeVoorTotaal;
	private Integer modulo;
	private Integer deelnemersMogenSubgroepMaken;
	private Integer dagenIncompleet;
	private LocalDate formulierStartdatum;
	private LocalDate formulierEinddatum;
	private Boolean formulierActief;

	public void cleanUp() {
		setFormulierNummer(zeroIfNull(getFormulierNummer()));
		setAantalDeelnemers(zeroIfNull(getAantalDeelnemers()));
		setMinimumAantalDeelnemers(zeroIfNull(getMinimumAantalDeelnemers()));
		setMaximumAantalDeelnemers(zeroIfNull(getMaximumAantalDeelnemers()));
		setMaximumAantalLedenUitEenGroep(zeroIfNull(getMaximumAantalLedenUitEenGroep()));
		setMinimumLeeftijd(zeroIfNull(getMinimumLeeftijd()));
		setMaximumLeeftijd(zeroIfNull(getMaximumLeeftijd()));
		setAantalDeelnemersAndereOrganisaties(zeroIfNull(getAantalDeelnemersAndereOrganisaties()));
		setGereserveerd(zeroIfNull(getGereserveerd()));
		setSubgroepen(zeroIfNull(getSubgroepen()));
		setMinimumAantalSubgroepen(zeroIfNull(getMinimumAantalSubgroepen()));
		setMaximumAantalSubgroepen(zeroIfNull(getMaximumAantalSubgroepen()));
		setMinimumAantalDeelnemersInSubgroep(zeroIfNull(getMinimumAantalDeelnemersInSubgroep()));
		setDeelnemersInSubgroepTellenMeeVoorTotaal(zeroIfNull(getDeelnemersInSubgroepTellenMeeVoorTotaal()));
		setModulo(zeroIfNull(getModulo()));
		setDeelnemersMogenSubgroepMaken(zeroIfNull(getDeelnemersMogenSubgroepMaken()));
		setDagenIncompleet(zeroIfNull(getDagenIncompleet()));
	}

	private static Integer zeroIfNull(final Integer i) {
		return i == null ? Integer.valueOf(0) : i;
	}

	public String getLocatie() {
		return locatie;
	}

	public void setLocatie(final String locatie) {
		this.locatie = locatie;
	}

	public String getFormulierNaam() {
		return formulierNaam;
	}

	public void setFormulierNaam(final String formulierNaam) {
		this.formulierNaam = formulierNaam;
	}

	public Integer getFormulierNummer() {
		return formulierNummer;
	}

	public void setFormulierNummer(final Integer formulierNummer) {
		this.formulierNummer = formulierNummer;
	}

	public Integer getAantalDeelnemers() {
		return aantalDeelnemers;
	}

	public void setAantalDeelnemers(final Integer aantalDeelnemers) {
		this.aantalDeelnemers = aantalDeelnemers;
	}

	public Integer getMinimumAantalDeelnemers() {
		return minimumAantalDeelnemers;
	}

	public void setMinimumAantalDeelnemers(final Integer minimumAantalDeelnemers) {
		this.minimumAantalDeelnemers = minimumAantalDeelnemers;
	}

	public Integer getMaximumAantalDeelnemers() {
		return maximumAantalDeelnemers;
	}

	public void setMaximumAantalDeelnemers(final Integer maximumAantalDeelnemers) {
		this.maximumAantalDeelnemers = maximumAantalDeelnemers;
	}

	public Integer getMaximumAantalLedenUitEenGroep() {
		return maximumAantalLedenUitEenGroep;
	}

	public void setMaximumAantalLedenUitEenGroep(
			final Integer maximumAantalLedenUitEenGroep) {
		this.maximumAantalLedenUitEenGroep = maximumAantalLedenUitEenGroep;
	}

	public Integer getMinimumLeeftijd() {
		return minimumLeeftijd;
	}

	public void setMinimumLeeftijd(final Integer minimumLeeftijd) {
		this.minimumLeeftijd = minimumLeeftijd;
	}

	public Integer getMaximumLeeftijd() {
		return maximumLeeftijd;
	}

	public void setMaximumLeeftijd(final Integer maximumLeeftijd) {
		this.maximumLeeftijd = maximumLeeftijd;
	}

	public Integer getAantalDeelnemersAndereOrganisaties() {
		return aantalDeelnemersAndereOrganisaties;
	}

	public void setAantalDeelnemersAndereOrganisaties(
			final Integer aantalDeelnemersAndereOrganisaties) {
		this.aantalDeelnemersAndereOrganisaties = aantalDeelnemersAndereOrganisaties;
	}

	public Integer getGereserveerd() {
		return gereserveerd;
	}

	public void setGereserveerd(final Integer gereserveerd) {
		this.gereserveerd = gereserveerd;
	}

	public Integer getSubgroepen() {
		return subgroepen;
	}

	public void setSubgroepen(final Integer subgroepen) {
		this.subgroepen = subgroepen;
	}

	public Integer getMinimumAantalSubgroepen() {
		return minimumAantalSubgroepen;
	}

	public void setMinimumAantalSubgroepen(final Integer minimumAantalSubgroepen) {
		this.minimumAantalSubgroepen = minimumAantalSubgroepen;
	}

	public Integer getMaximumAantalSubgroepen() {
		return maximumAantalSubgroepen;
	}

	public void setMaximumAantalSubgroepen(final Integer maximumAantalSubgroepen) {
		this.maximumAantalSubgroepen = maximumAantalSubgroepen;
	}

	public Integer getMinimumAantalDeelnemersInSubgroep() {
		return minimumAantalDeelnemersInSubgroep;
	}

	public void setMinimumAantalDeelnemersInSubgroep(
			final Integer minimumAantalDeelnemersInSubgroep) {
		this.minimumAantalDeelnemersInSubgroep = minimumAantalDeelnemersInSubgroep;
	}

	public Integer getDeelnemersInSubgroepTellenMeeVoorTotaal() {
		return deelnemersInSubgroepTellenMeeVoorTotaal;
	}

	public void setDeelnemersInSubgroepTellenMeeVoorTotaal(
			final Integer deelnemersInSubgroepTellenMeeVoorTotaal) {
		this.deelnemersInSubgroepTellenMeeVoorTotaal = deelnemersInSubgroepTellenMeeVoorTotaal;
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(final Integer modulo) {
		this.modulo = modulo;
	}

	public Integer getDeelnemersMogenSubgroepMaken() {
		return deelnemersMogenSubgroepMaken;
	}

	public void setDeelnemersMogenSubgroepMaken(
			final Integer deelnemersMogenSubgroepMaken) {
		this.deelnemersMogenSubgroepMaken = deelnemersMogenSubgroepMaken;
	}

	public Integer getDagenIncompleet() {
		return dagenIncompleet;
	}

	public void setDagenIncompleet(final Integer dagenIncompleet) {
		this.dagenIncompleet = dagenIncompleet;
	}

	public LocalDate getFormulierStartdatum() {
		return formulierStartdatum;
	}

	public void setFormulierStartdatum(final LocalDate formulierStartdatum) {
		this.formulierStartdatum = formulierStartdatum;
	}

	public LocalDate getFormulierEinddatum() {
		return formulierEinddatum;
	}

	public void setFormulierEinddatum(final LocalDate formulierEinddatum) {
		this.formulierEinddatum = formulierEinddatum;
	}

	public Boolean getFormulierActief() {
		return formulierActief;
	}

	public void setFormulierActief(final Boolean formulierActief) {
		this.formulierActief = formulierActief;
	}

}
