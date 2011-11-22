package nl.scouting.hit.sitecreator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Icoon implements Comparable<Icoon> {

	private static final Map<String, Icoon> icoonCache = new HashMap<String, Icoon>();

	private static void register(final Map<String, Icoon> cache,
			final Icoon icoon) {
		cache.put(icoon.getTekst(), icoon);
	}

	static {
		register(icoonCache, new BewegingsIcoon("staand", "Staand kamp"));
		register(icoonCache, new BewegingsIcoon("fiets", "Trekken per fiets"));
		register(icoonCache, new BewegingsIcoon("hike", "Trekken met rugzak"));
		register(icoonCache, new BewegingsIcoon("kano", "Trekken per kano"));
		register(icoonCache,
				new BewegingsIcoon("zeilboot", "Trekkend per boot"));
		register(icoonCache, new BewegingsIcoon("geenrugz",
				"Lopen zonder rugzak"));
		register(icoonCache, new BewegingsIcoon("hikevr",
				"Lopen met een ander voorwerp"));
		register(icoonCache, new BewegingsIcoon("auto", "Trekkend per auto"));

		register(icoonCache, new InschrijfIcoon("0pers",
				"Inschrijven per persoon"));
		register(icoonCache, new InschrijfIcoon("groepje",
				"Inschrijven per groep"));

		register(icoonCache, new OvernachtingIcoon("tent",
				"Overnachten in een zelfmeegenomen tent"));
		register(icoonCache, new OvernachtingIcoon("friet",
				"Overnachten in een frietbuil"));
		register(icoonCache, new OvernachtingIcoon("nacht",
				"Overnachten zonder tent"));
		register(icoonCache, new OvernachtingIcoon("tent_opgezet",
				"Overnachten in tenten verzorgd door staf"));
		register(icoonCache, new OvernachtingIcoon("gebouw",
				"Overnachten in gebouw"));
		register(icoonCache, new OvernachtingIcoon("bootslaap",
				"Overnachten op een boot"));

		for (final int afstand : new int[] { 5, 15, 20, 25, 30, 35, 40, 45, 50,
				55, 60, 80, 100, 120 }) {
			register(icoonCache, new AfstandsIcoon(afstand));
		}

		register(icoonCache, new KookIcoon("vuur",
				"Koken op houtvuur zonder pannen"));
		register(icoonCache, new KookIcoon("opvuur",
				"Koken op houtvuur met pannen"));
		register(icoonCache, new KookIcoon("gas", "Koken op gas met pannen"));
		register(icoonCache, new KookIcoon("stafkookt", "Gekookt door de staf"));

		register(icoonCache, new Icoon("k_ks",
				"Kennis van kaart en kompas op eenvoudig niveau"));
		register(icoonCache, new Icoon("k_kv",
				"Kennis van kaart en kompas op gevorderd niveau"));
		register(icoonCache, new Icoon("k_kgv",
				"Kennis van kaart en kompas op specialistisch niveau"));
		register(icoonCache, new Icoon("insigne",
				"Activiteit waarmee een insigne kan worden behaald"));

		register(icoonCache, new Icoon("zwem", "Zwemdiploma verplicht"));
		register(icoonCache, new Icoon("mobieltje", "Mobieltje meenemen"));
		register(icoonCache, new Icoon("geenmobieltje",
				"Mobieltjes zijn verboden"));
		register(icoonCache, new Icoon("rolstoel",
				"Geschikt voor minder validen (rolstoel)"));
		register(icoonCache, new Icoon("vraagt",
				"Vraagteken Mysterie elemeneten"));
		register(icoonCache, new Icoon("buitenland",
				"Buitenland - ID kaart of paspoort verplicht"));
	}

	public static Icoon forIdentifier(final String identifier) {
		return icoonCache.get(identifier);
	}

	public static Set<Icoon> getAll() {
		return new TreeSet<Icoon>(icoonCache.values());
	}

	private final int volgorde;
	private final String bestandsnaam; // "fiets"
	private final String tekst; // "Dit kamp is per fiets"

	private static int volgordeCounter = 0;

	public Icoon(final String bestandsnaam, final String tekst) {
		super();
		this.bestandsnaam = bestandsnaam;
		this.tekst = tekst;
		volgorde = volgordeCounter++;
	}

	@Override
	public int compareTo(final Icoon o) {
		return volgorde - o.volgorde;
	}

	@Override
	public String toString() {
		return "[" + volgorde + "]" + tekst + "(" + bestandsnaam + ")";
	}

	public String getBestandsnaam() {
		return bestandsnaam;
	}

	public String getTekst() {
		return tekst;
	}

	public int getVolgorde() {
		return volgorde;
	}

	public boolean isAfstandsIndicate() {
		return false;
	}

	public boolean isBeweging() {
		return false;
	}

	public boolean isInschrijving() {
		return false;
	}

	public boolean isOvernachting() {
		return false;
	}

	public boolean isKook() {
		return false;
	}

	public static class AfstandsIcoon extends Icoon {

		private final int afstand;

		public AfstandsIcoon(final int afstand) {
			super(afstand + "km", "Totale afstand is " + afstand + " km");
			this.afstand = afstand;
		}

		public int getAfstand() {
			return afstand;
		}

		@Override
		public boolean isAfstandsIndicate() {
			return true;
		}
	}

	public static class BewegingsIcoon extends Icoon {

		public BewegingsIcoon(final String bestandsnaam, final String tekst) {
			super(bestandsnaam, tekst);
		}

		@Override
		public boolean isBeweging() {
			return true;
		}
	}

	public static class InschrijfIcoon extends Icoon {

		public InschrijfIcoon(final String bestandsnaam, final String tekst) {
			super(bestandsnaam, tekst);
		}

		@Override
		public boolean isInschrijving() {
			return true;
		}
	}

	public static class OvernachtingIcoon extends Icoon {
		public OvernachtingIcoon(final String bestandsnaam, final String tekst) {
			super(bestandsnaam, tekst);
		}

		@Override
		public boolean isOvernachting() {
			return true;
		}
	}

	public static class KookIcoon extends Icoon {
		public KookIcoon(final String bestandsnaam, final String tekst) {
			super(bestandsnaam, tekst);
		}

		@Override
		public boolean isKook() {
			return true;
		}
	}
}
