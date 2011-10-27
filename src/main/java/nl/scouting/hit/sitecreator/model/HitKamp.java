package nl.scouting.hit.sitecreator.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class HitKamp implements Comparable<HitKamp> {
	private HitPlaats plaats;

	private String naam;
	private String plaatsNaam;

	private String icoontje;
	private final Set<String> icoontjes;

	// TODO: vullen
	private Date startDatumtijd = new Date();
	private Date eindDatumtijd = new Date();
	private Integer minimumLeeftijd = 8, maximumLeeftijd = 88;
	private Integer maximumAantalDeelnemers = 40;
	private Integer maximumAantalUitEenGroep = 5;
	private Integer deelnamekosten = 43; // in euro's.
	private String subgroepSamenstelling = "1-4";

	private String titelTekst = "TitelTekst";
	private String courantTekst = "CourantTekst";
	private String websiteTekst = "WebsiteTekst";
	private URL webadresFoto1 = asURL("http://hit.scouting.nl/stories/mook-1.jpg");
	private URL webadresFoto2 = asURL("http://hit.scouting.nl/stories/mook-2.jpg");
	private URL webadresFoto3 = asURL("http://hit.scouting.nl/stories/mook-3.jpg");
	private URL websiteYoutube = asURL("http://www.youtube.com/webadresYoutube");
	private String websiteContactpersoon = "C. Ontactpersoon";
	private String websiteContactTelefoonnummer = "000-0000000";
	private String websiteContactEmailadres = "c.ontactpersoon@email.adres.nl";
	private URL websiteAdres = asURL("http://dit.is.ons.kamp.adres.nl");

	private Integer afgelegdeKilometers = 10;

	private HitKamp previous;
	private HitKamp next;
	private Integer deelnemersnummer;

	private static URL asURL(final String url) {
		try {
			return new URL(url);
		} catch (final MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public HitKamp() {
		super();
		icoontjes = new TreeSet<String>();
	}

	public HitKamp(final String naam) {
		this();
		this.naam = naam;
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
			icoontjes.add(icoontje);
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

	// ----

	public String getIcoontje() {
		return icoontje;
	}

	public Set<String> getIcoontjes() {
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

	public Date getStartDatumtijd() {
		return startDatumtijd;
	}

	public void setStartDatumtijd(final Date startDatumtijd) {
		this.startDatumtijd = startDatumtijd;
	}

	public Date getEindDatumtijd() {
		return eindDatumtijd;
	}

	public void setEindDatumtijd(final Date eindDatumtijd) {
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

	public String getSubgroepSamenstelling() {
		return subgroepSamenstelling;
	}

	public void setSubgroepSamenstelling(final String subgroepSamenstelling) {
		this.subgroepSamenstelling = subgroepSamenstelling;
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

	public URL getWebsiteYoutube() {
		return websiteYoutube;
	}

	public void setWebsiteYoutube(final URL websiteYoutube) {
		this.websiteYoutube = websiteYoutube;
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

}
