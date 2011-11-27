package nl.scouting.hit.sitecreator.model;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import nl.scouting.hit.sitecreator.model.Icoon.AfstandsIcoon;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class HitKamp implements Comparable<HitKamp> {

	//
	// Kamp onderdeel gegevens
	//
	private Integer deelnemersnummer;
	private String shantiformuliernummer;
	private String hitwrapperpagina;

	private String naam;
	private String plaatsNaam;
	private HitPlaats plaats;

	private LocalDate startDatum; // write-only ivm csv-mapping
	private LocalTime startTijd; // write-only ivm csv-mapping
	private LocalDateTime startDatumtijd;

	private LocalDate eindDatum; // write-only ivm csv-mapping
	private LocalTime eindTijd; // write-only ivm csv-mapping
	private LocalDateTime eindDatumtijd;

	private Integer deelnamekosten;

	private Boolean akkoordHitKamp;
	private Boolean akkoordHitPlaats;

	//
	// Deelnemer eigenschappen
	//
	private Integer minimumLeeftijd;
	private Integer maximumLeeftijd;

	private Integer minimumAantalDeelnemers;
	private Integer maximumAantalDeelnemers;
	private Integer maximumAantalUitEenGroep;
	private Integer overschrijdingAantalDeelnemers;

	private String subgroepsamenstellingOud;
	private String subgroepsamenstellingMinimum;
	private String subgroepsamenstellingMaximum;
	private String maximumAantalSubgroepjes;

	//
	// Alleen voor de Website
	//
	private String icoontje;
	private final Set<Icoon> icoontjes;

	private String titelTekst;
	private String courantTekst;
	private String websiteTekst;
	private URL webadresFoto1;
	private URL webadresFoto2;
	private URL webadresFoto3;

	private String websiteContactpersoon;
	private String websiteContactTelefoonnummer;
	private String websiteContactEmailadres;

	private URL websiteAdres;
	private Integer afgelegdeKilometers; // afleiden uit icoontjes

	/** Linkt alle kampen aan elkaar voor navigatiedoeleinden. */
	private HitKamp previous;
	/** Linkt alle kampen aan elkaar voor navigatiedoeleinden. */
	private HitKamp next;

	/** Ingeschreven deelnemers. */
	private List<HitDeelnemer> deelnemers;

	/**
	 * Constructor.
	 */
	public HitKamp() {
		this(null);
	}

	public HitKamp(final String naam) {
		this.naam = naam;
		icoontjes = new TreeSet<Icoon>();
	}

	public HitKamp(final String naam, final List<HitDeelnemer> deelnemers) {
		this(naam);
		this.deelnemers = deelnemers;
	}

	public void merge(final HitKamp kamp) {
		deelnemersnummer = kamp.deelnemersnummer;
		shantiformuliernummer = kamp.shantiformuliernummer;
		hitwrapperpagina = kamp.hitwrapperpagina;

		naam = kamp.naam;

		startDatumtijd = kamp.startDatumtijd;

		eindDatumtijd = kamp.eindDatumtijd;

		deelnamekosten = kamp.deelnamekosten;

		akkoordHitKamp = kamp.akkoordHitKamp;
		akkoordHitPlaats = kamp.akkoordHitPlaats;

		//
		// Deelnemer eigenschappen
		//
		minimumLeeftijd = kamp.minimumLeeftijd;
		maximumLeeftijd = kamp.maximumLeeftijd;

		minimumAantalDeelnemers = kamp.minimumAantalDeelnemers;
		maximumAantalDeelnemers = kamp.maximumAantalDeelnemers;
		maximumAantalUitEenGroep = kamp.maximumAantalUitEenGroep;
		overschrijdingAantalDeelnemers = kamp.overschrijdingAantalDeelnemers;

		subgroepsamenstellingMinimum = kamp.subgroepsamenstellingMinimum;
		subgroepsamenstellingMaximum = kamp.subgroepsamenstellingMaximum;
		maximumAantalSubgroepjes = kamp.maximumAantalSubgroepjes;

		//
		// Alleen voor de Website
		//
		icoontjes.clear();
		icoontjes.addAll(kamp.icoontjes);

		titelTekst = kamp.titelTekst;
		courantTekst = kamp.courantTekst;
		websiteTekst = kamp.websiteTekst;
		webadresFoto1 = kamp.webadresFoto1;
		webadresFoto2 = kamp.webadresFoto2;
		webadresFoto3 = kamp.webadresFoto3;

		websiteContactpersoon = kamp.websiteContactpersoon;
		websiteContactTelefoonnummer = kamp.websiteContactTelefoonnummer;
		websiteContactEmailadres = kamp.websiteContactEmailadres;

		websiteAdres = kamp.websiteAdres;
		afgelegdeKilometers = kamp.afgelegdeKilometers;
	}

	public boolean isHeeftBeginEnEindInVerschillendeMaanden() {
		return getStartDatumtijd().getMonthOfYear() != getEindDatumtijd()
				.getMonthOfYear();

	}

	public boolean isHeeftMaximumUitEenGroep() {
		return (maximumAantalUitEenGroep != null)
				&& (maximumAantalUitEenGroep > 0);
	}

	public boolean isHeeftAfgelegdeKilometers() {
		return (afgelegdeKilometers != null) && (afgelegdeKilometers > 0);
	}

	public boolean isHeeftEenYoutubeFilmpje() {
		return webadresFoto3.getHost().toLowerCase().contains("youtube.com");
	}

	public boolean isBegintOpGoedeVrijdag() {
		return DateTimeConstants.FRIDAY == startDatumtijd.getDayOfWeek();
	}

	public String getHtmlFileNaam() {
		return String.format("hit%d_%d.html", getPlaats().getHit().getJaar(),
				getDeelnemersnummer());
	}

	public HitKamp getPrevious() {
		return previous;
	}

	public void setPrevious(final HitKamp previous) {
		this.previous = previous;
	}

	public HitKamp getNext() {
		return next;
	}

	public void setNext(final HitKamp next) {
		this.next = next;
		next.setPrevious(this);
	}

	public void setIcoontje(final String icoontje) {
		this.icoontje = icoontje;
		if (!"".equals(icoontje)) {
			final Icoon forIdentifier = Icoon.forIdentifier(icoontje);
			if (forIdentifier != null) {
				icoontjes.add(forIdentifier);
				if (forIdentifier.isAfstandsIndicate()) {
					afgelegdeKilometers = ((AfstandsIcoon) forIdentifier)
							.getAfstand();
				}
			}
		}
	}

	@Override
	public String toString() {
		return naam;
	}

	@Override
	public int compareTo(final HitKamp o) {
		int result = getPlaats().getNaam().compareTo(o.getPlaats().getNaam());
		if (result == 0) {
			result = naam.compareTo(o.getNaam());
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		return naam.equals(((HitKamp) obj).naam);
	}

	// ----

	public String getIcoontje() {
		return icoontje;
	}

	public Set<Icoon> getIcoontjes() {
		return icoontjes;
	}

	public String getNaam() {
		return naam;
	}

	public HitPlaats getPlaats() {
		return plaats;
	}

	public String getPlaatsNaam() {
		return plaatsNaam;
	}

	public void setNaam(final String naam) {
		this.naam = naam;
	}

	public void setPlaats(final HitPlaats hitPlaats) {
		plaats = hitPlaats;
	}

	public void setPlaatsNaam(final String plaatsNaam) {
		this.plaatsNaam = plaatsNaam;
	}

	public LocalDateTime getStartDatumtijd() {
		return startDatumtijd;
	}

	public void setStartDatumtijd(final LocalDateTime startDatumtijd) {
		this.startDatumtijd = startDatumtijd;
	}

	public LocalDateTime getEindDatumtijd() {
		return eindDatumtijd;
	}

	public void setEindDatumtijd(final LocalDateTime eindDatumtijd) {
		this.eindDatumtijd = eindDatumtijd;
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

	public Integer getMaximumAantalDeelnemers() {
		return maximumAantalDeelnemers;
	}

	public void setMaximumAantalDeelnemers(final Integer maximumAantalDeelnemers) {
		this.maximumAantalDeelnemers = maximumAantalDeelnemers;
	}

	public Integer getMaximumAantalUitEenGroep() {
		return maximumAantalUitEenGroep;
	}

	public void setMaximumAantalUitEenGroep(
			final Integer maximumAantalUitEenGroep) {
		this.maximumAantalUitEenGroep = maximumAantalUitEenGroep;
	}

	public Integer getDeelnamekosten() {
		return deelnamekosten;
	}

	public void setDeelnamekosten(final Integer deelnamekosten) {
		this.deelnamekosten = deelnamekosten;
	}

	public String getTitelTekst() {
		return titelTekst;
	}

	public void setTitelTekst(final String titelTekst) {
		this.titelTekst = titelTekst;
	}

	public String getWebsiteTekst() {
		return websiteTekst;
	}

	public void setWebsiteTekst(final String websiteTekst) {
		this.websiteTekst = websiteTekst;
	}

	public URL getWebadresFoto1() {
		return webadresFoto1;
	}

	public void setWebadresFoto1(final URL webadresFoto1) {
		this.webadresFoto1 = webadresFoto1;
	}

	public URL getWebadresFoto2() {
		return webadresFoto2;
	}

	public void setWebadresFoto2(final URL webadresFoto2) {
		this.webadresFoto2 = webadresFoto2;
	}

	public URL getWebadresFoto3() {
		return webadresFoto3;
	}

	public void setWebadresFoto3(final URL webadresFoto3) {
		this.webadresFoto3 = webadresFoto3;
	}

	public String getWebsiteContactpersoon() {
		return websiteContactpersoon;
	}

	public void setWebsiteContactpersoon(final String websiteContactpersoon) {
		this.websiteContactpersoon = websiteContactpersoon;
	}

	public String getWebsiteContactEmailadres() {
		return websiteContactEmailadres;
	}

	public void setWebsiteContactEmailadres(
			final String websiteContactEmailadres) {
		this.websiteContactEmailadres = websiteContactEmailadres;
	}

	public URL getWebsiteAdres() {
		return websiteAdres;
	}

	public void setWebsiteAdres(final URL websiteAdres) {
		this.websiteAdres = websiteAdres;
	}

	public Integer getAfgelegdeKilometers() {
		return afgelegdeKilometers;
	}

	public void setAfgelegdeKilometers(final Integer afgelegdeKilometers) {
		this.afgelegdeKilometers = afgelegdeKilometers;
	}

	public Integer getDeelnemersnummer() {
		return deelnemersnummer;
	}

	public void setDeelnemersnummer(final Integer deelnemersnummer) {
		this.deelnemersnummer = deelnemersnummer;
	}

	public String getWebsiteContactTelefoonnummer() {
		return websiteContactTelefoonnummer;
	}

	public void setWebsiteContactTelefoonnummer(
			final String websiteContactTelefoonnummer) {
		this.websiteContactTelefoonnummer = websiteContactTelefoonnummer;
	}

	public String getCourantTekst() {
		return courantTekst;
	}

	public void setCourantTekst(final String courantTekst) {
		this.courantTekst = courantTekst;
	}

	public Integer getMinimumAantalDeelnemers() {
		return minimumAantalDeelnemers;
	}

	public void setMinimumAantalDeelnemers(final Integer minimumAantalDeelnemers) {
		this.minimumAantalDeelnemers = minimumAantalDeelnemers;
	}

	public Integer getOverschrijdingAantalDeelnemers() {
		return overschrijdingAantalDeelnemers;
	}

	public void setOverschrijdingAantalDeelnemers(
			final Integer overschrijdingAantalDeelnemers) {
		this.overschrijdingAantalDeelnemers = overschrijdingAantalDeelnemers;
	}

	public Boolean getAkkoordHitKamp() {
		return akkoordHitKamp;
	}

	public void setAkkoordHitKamp(final Boolean akkoordHitKamp) {
		this.akkoordHitKamp = akkoordHitKamp;
	}

	public Boolean getAkkoordHitPlaats() {
		return akkoordHitPlaats;
	}

	public void setAkkoordHitPlaats(final Boolean akkoordHitPlaats) {
		this.akkoordHitPlaats = akkoordHitPlaats;
	}

	public String getMaximumAantalSubgroepjes() {
		return maximumAantalSubgroepjes;
	}

	public void setMaximumAantalSubgroepjes(
			final String maximumAantalSubgroepjes) {
		this.maximumAantalSubgroepjes = maximumAantalSubgroepjes;
	}

	public void setStartDatum(final LocalDate startDatum) {
		this.startDatum = startDatum;
		startDatumtijd = updateDate(this.startDatum, startTijd);
	}

	public void setStartTijd(final LocalTime startTijd) {
		this.startTijd = startTijd;
		startDatumtijd = updateTime(startDatum, this.startTijd);
	}

	public void setEindDatum(final LocalDate eindDatum) {
		this.eindDatum = eindDatum;
		eindDatumtijd = updateDate(this.eindDatum, eindTijd);
	}

	public void setEindTijd(final LocalTime eindTijd) {
		this.eindTijd = eindTijd;
		eindDatumtijd = updateTime(eindDatum, this.eindTijd);
	}

	private LocalDateTime updateDate(final LocalDate date, final LocalTime time) {
		LocalDateTime result;
		if (time == null) {
			result = date.toLocalDateTime(LocalTime.MIDNIGHT);
		} else {
			result = date.toLocalDateTime(time);
		}
		return result;
	}

	private LocalDateTime updateTime(final LocalDate date, final LocalTime time) {
		LocalDateTime result;
		if (date == null) {
			result = new LocalDate().toLocalDateTime(time);
		} else {
			result = date.toLocalDateTime(time);
		}
		return result;
	}

	public String getSubgroepsamenstellingMinimum() {
		return subgroepsamenstellingMinimum;
	}

	public void setSubgroepsamenstellingMinimum(
			final String subgroepsamenstellingMinimum) {
		this.subgroepsamenstellingMinimum = subgroepsamenstellingMinimum;
	}

	public String getSubgroepsamenstellingMaximum() {
		return subgroepsamenstellingMaximum;
	}

	public void setSubgroepsamenstellingMaximum(
			final String subgroepsamenstellingMaximum) {
		this.subgroepsamenstellingMaximum = subgroepsamenstellingMaximum;
	}

	public String getShantiformuliernummer() {
		return shantiformuliernummer;
	}

	public void setShantiformuliernummer(final String shantiformuliernummer) {
		this.shantiformuliernummer = shantiformuliernummer;
	}

	public String getHitwrapperpagina() {
		return hitwrapperpagina;
	}

	public void setHitwrapperpagina(final String hitwrapperpagina) {
		this.hitwrapperpagina = hitwrapperpagina;
	}

	public List<HitDeelnemer> getDeelnemers() {
		return deelnemers;
	}

	public void setDeelnemers(final List<HitDeelnemer> deelnemers) {
		this.deelnemers = deelnemers;
	}

	public String getSubgroepsamenstellingOud() {
		return subgroepsamenstellingOud;
	}

	public void setSubgroepsamenstellingOud(String subgroepsamenstellingOud) {
		this.subgroepsamenstellingOud = subgroepsamenstellingOud;
	}

}
