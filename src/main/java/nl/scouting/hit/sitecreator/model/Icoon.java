package nl.scouting.hit.sitecreator.model;

import java.util.HashMap;
import java.util.Map;

public class Icoon implements Comparable<Icoon> {

	private static final Map<String, Icoon> icoonCache = new HashMap<String, Icoon>();
	static {
		icoonCache.put("Staand kamp", //
				new Icoon("staand.gif", "Staand kamp"));
		icoonCache.put("Trekken per fiets", //
				new Icoon("fiets.gif", "Trekken per fiets"));
		icoonCache.put("Trekken met rugzak", //
				new Icoon("hike.gif", "Trekken met rugzak"));
		icoonCache.put("Trekken per kano", //
				new Icoon("kano.gif", "Trekken per kano"));
		icoonCache.put("Trekkend per boot", //
				new Icoon("zeilboot.gif", "Trekkend per boot"));
		icoonCache.put("Lopen zonder rugzak", //
				new Icoon("geenrugz.gif", "Lopen zonder rugzak"));
		icoonCache.put("Lopen met een ander voorwerp", //
				new Icoon("hikevr.gif", "Lopen met een ander voorwerp"));
		icoonCache.put("Inschrijven per persoon", //
				new Icoon("0pers.gif", "Inschrijven per persoon"));
		icoonCache.put("Inschrijven per groep", //
				new Icoon("groepje.gif", "Inschrijven per groep"));
		icoonCache
				.put("Overnachten in een zelfmeegenomen tent", //
						new Icoon("tent.gif",
								"Overnachten in een zelfmeegenomen tent"));
		icoonCache.put("Overnachten in een frietbuil", //
				new Icoon("friet.gif", "Overnachten in een frietbuil"));
		icoonCache.put("Overnachten zonder tent", //
				new Icoon("todo.gif", "Overnachten zonder tent"));
		icoonCache.put("Overnachten in tenten verzorgd door staf", //
				new Icoon("tent_opgezet.gif",
						"Overnachten in tenten verzorgd door staf"));
		icoonCache.put("Overnachten in gebouw", //
				new Icoon("gebouw.gif", "Overnachten in gebouw"));
		icoonCache.put("Totale afstand is 0 km", //
				new Icoon("0km.gif", "Totale afstand is 0 km"));
		icoonCache.put("Totale afstand is 5 km", //
				new Icoon("5km.gif", "Totale afstand is 5 km"));
		icoonCache.put("Totale afstand is 15 km", //
				new Icoon("15km.gif", "Totale afstand is 15 km"));
		icoonCache.put("Totale afstand is 20 km", //
				new Icoon("20km.gif", "Totale afstand is 20 km"));
		icoonCache.put("Totale afstand is 25 km", //
				new Icoon("25km.gif", "Totale afstand is 25 km"));
		icoonCache.put("Totale afstand is 30 km", //
				new Icoon("30km.gif", "Totale afstand is 30 km"));
		icoonCache.put("Totale afstand is 35 km", //
				new Icoon("35km.gif", "Totale afstand is 35 km"));
		icoonCache.put("Totale afstand is 40 km", //
				new Icoon("40km.gif", "Totale afstand is 40 km"));
		icoonCache.put("Totale afstand is 45 km", //
				new Icoon("45km.gif", "Totale afstand is 45 km"));
		icoonCache.put("Totale afstand is 50 km", //
				new Icoon("50km.gif", "Totale afstand is 50 km"));
		icoonCache.put("Totale afstand is 55 km", //
				new Icoon("55km.gif", "Totale afstand is 55 km"));
		icoonCache.put("Totale afstand is 60 km", //
				new Icoon("60km.gif", "Totale afstand is 60 km"));
		icoonCache.put("Totale afstand is 65 km", //
				new Icoon("65km.gif", "Totale afstand is 65 km"));
		icoonCache.put("Totale afstand is 70 km", //
				new Icoon("70km.gif", "Totale afstand is 70 km"));
		icoonCache.put("Totale afstand is 75 km", //
				new Icoon("75km.gif", "Totale afstand is 75 km"));
		icoonCache.put("Totale afstand is 80 km", //
				new Icoon("80km.gif", "Totale afstand is 80 km"));
		icoonCache.put("Totale afstand is 85 km", //
				new Icoon("85km.gif", "Totale afstand is 85 km"));
		icoonCache.put("Totale afstand is 90 km", //
				new Icoon("90km.gif", "Totale afstand is 90 km"));
		icoonCache.put("Totale afstand is 100 km", //
				new Icoon("100km.gif", "Totale afstand is 100 km"));
		icoonCache.put("Totale afstand is 120 km", //
				new Icoon("120km.gif", "Totale afstand is 120 km"));
		icoonCache.put("Totale afstand is 150 km", //
				new Icoon("150km.gif", "Totale afstand is 150 km"));
		icoonCache.put("Koken op houtvuur zonder pannen", //
				new Icoon("vuur.gif", "Koken op houtvuur zonder pannen"));
		icoonCache.put("Koken op houtvuur met pannen", //
				new Icoon("opvuur.gif", "Koken op houtvuur met pannen"));
		icoonCache.put("Koken op gas met pannen", //
				new Icoon("gas.gif", "Koken op gas met pannen"));
		icoonCache.put("Gekookt door de staf", //
				new Icoon("stafkookt.gif", "Gekookt door de staf"));
		icoonCache.put("Kennis van kaart en kompas op eenvoudig niveau", //
				new Icoon("k_kv.gif",
						"Kennis van kaart en kompas op eenvoudig niveau"));
		icoonCache.put("Kennis van kaart en kompas op gevorderd niveau", //
				new Icoon("k_kgv.gif",
						"Kennis van kaart en kompas op gevorderd niveau"));
		icoonCache.put("Kennis van kaart en kompas op specialistisch nivea", //
				new Icoon("k_ks.gif",
						"Kennis van kaart en kompas op specialistisch niveau"));
		icoonCache.put("Activiteit waarmee een insigne kan worden behaald", //
				new Icoon("insigne.gif",
						"Activiteit waarmee een insigne kan worden behaald"));
		icoonCache.put("Zwemdiploma verplicht", //
				new Icoon("zwem.gif", "Zwemdiploma verplicht"));
		icoonCache.put("Mobieltje meenemen", //
				new Icoon("mobieltje.gif", "Mobieltje meenemen"));
		icoonCache.put("Mobieltjes zijn verboden", //
				new Icoon("geenmobieltje.gif", "Mobieltjes zijn verboden"));
		icoonCache
				.put("Geschikt voor minder validen (rolstoel)", //
						new Icoon("todo.gif",
								"Geschikt voor minder validen (rolstoel)"));
		icoonCache.put("Vraagteken Mysterie elemeneten", //
				new Icoon("todo.gif", "Vraagteken Mysterie elemeneten"));
		icoonCache.put("Buitenland - ID kaart of paspoort verplicht", //
				new Icoon("todo.gif",
						"Buitenland - ID kaart of paspoort verplicht"));
		icoonCache.put("Trekkend per auto", //
				new Icoon("todo.gif", "Trekkend per auto"));
	}

	public static Icoon forIdentifier(final String identifier) {
		return icoonCache.get(identifier);
	}

	private String bestandsnaam; // "fiets.gif"
	private String tekst; // "Dit kamp is per fiets"

	public Icoon(final String bestandsnaam, final String tekst) {
		super();
		this.bestandsnaam = bestandsnaam;
		this.tekst = tekst;
	}

	@Override
	public int compareTo(final Icoon o) {
		return tekst.compareTo(o.getTekst());
	}

	public String getBestandsnaam() {
		return bestandsnaam;
	}

	public void setBestandsnaam(final String bestandsnaam) {
		this.bestandsnaam = bestandsnaam;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(final String tekst) {
		this.tekst = tekst;
	}

}
