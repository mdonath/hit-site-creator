package nl.scouting.hit.sitecreator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Activiteitengebied implements Comparable<Activiteitengebied> {

	private static final Map<String, Activiteitengebied> activiteitengebiedCache = new HashMap<String, Activiteitengebied>();

	private static void register(final Map<String, Activiteitengebied> cache,
			final Activiteitengebied activiteitengebied) {
		cache.put(activiteitengebied.getFormuliernaam(), activiteitengebied);
	}

	static {
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"uitdagend",
						"Uitdagende Scoutingtechnieken",
						"Uitdagende Scoutingtechnieken: activiteiten rondom een techniek, zoals hakken, stoken, kaart en kompas, routetechnieken, pionieren, zeilen en kamperen."));
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"expressie",
						"Expressie",
						"Expressie: activiteiten waarmee je je kunt uitdrukken, zoals dansen, filmen, handvaardigheid, toneelspelen, muziek maken, schrijven."));

		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"sportenspel",
						"Sport en Spel",
						"Sport & Spel: sporten, postenspelen, ren-spelen, gezelschapsspelen en teamspelen."));
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"buitenleven",
						"Buitenleven",
						"Buitenleven: activiteiten rondom het beleven van de natuur en overleven in de natuur, zoals survival, kennis van plant en dier, milieu, natuurbeheer en weer."));

		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"identiteit",
						"Identiteit",
						"Identiteit: activiteiten die te maken hebben met wie je zelf bent (zelfbeeld), welke levensovertuiging je hebt en de identiteit van je groep."));
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"internationaal",
						"Internationaal",
						"Internationaal: activiteiten rondom kennis over Scouting wereldwijd (bijvoorbeeld JOTA JOTI), internationale uitwisselingen en andere culturen."));
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"samenleving",
						"Samenleving",
						"Samenleving: activiteiten die te maken hebben met je eigen omgeving, het cultureel erfgoed en de maatschappij (maatschappelijke participatie)."));
		register(
				activiteitengebiedCache,
				new Activiteitengebied(
						"veiligengezond",
						"Veilig en Gezond",
						"Veilig & Gezond: activiteiten rondom voeding en veiligheid (zowel fysiek als emotioneel)."));

	}

	public static Activiteitengebied forIdentifier(final String identifier) {
		return activiteitengebiedCache.get(identifier);
	}

	public static Set<Activiteitengebied> getAll() {
		return new TreeSet<Activiteitengebied>(activiteitengebiedCache.values());
	}

	private final int volgorde;
	private final String bestandsnaam;
	private final String formuliernaam;
	private final String tekst;

	private static int volgordeCounter = 0;

	public Activiteitengebied(final String bestandsnaam,
			final String formuliernaam, final String tekst) {
		super();
		this.bestandsnaam = bestandsnaam;
		this.formuliernaam = formuliernaam;
		this.tekst = tekst;
		volgorde = volgordeCounter++;
	}

	@Override
	public int compareTo(final Activiteitengebied o) {
		return getVolgorde() - o.getVolgorde();
	}

	@Override
	public String toString() {
		return "[" + getVolgorde() + "]" + getTekst() + "(" + getBestandsnaam()
				+ ")";
	}

	public int getVolgorde() {
		return volgorde;
	}

	public String getBestandsnaam() {
		return bestandsnaam;
	}

	public String getTekst() {
		return tekst;
	}

	public String getFormuliernaam() {
		return formuliernaam;
	}
}
