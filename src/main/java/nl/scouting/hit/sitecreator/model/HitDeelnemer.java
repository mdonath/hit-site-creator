package nl.scouting.hit.sitecreator.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class HitDeelnemer {

	private Integer deelnemersnummer;

	private LocalDate geboorteDatum;
	private String postcode;

	private LocalDateTime inschrijfDatum;

	private Integer formulierNummer;

	public Integer getDeelnemersnummer() {
		return deelnemersnummer;
	}

	public void setDeelnemersnummer(final Integer deelnemersnummer) {
		this.deelnemersnummer = deelnemersnummer;
	}

	public LocalDate getGeboorteDatum() {
		return geboorteDatum;
	}

	public void setGeboorteDatum(final LocalDate geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(final String postcode) {
		this.postcode = postcode;
	}

	public LocalDateTime getInschrijfDatum() {
		return inschrijfDatum;
	}

	public void setInschrijfDatum(final LocalDateTime inschrijfDatum) {
		this.inschrijfDatum = inschrijfDatum;
	}

	public Integer getFormulierNummer() {
		return formulierNummer;
	}

	public void setFormulierNummer(final Integer formulierNummer) {
		this.formulierNummer = formulierNummer;
	}

}
