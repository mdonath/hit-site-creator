package nl.scouting.hit.sitecreator.input.module.csv;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ColumnMapperHelper2012 implements ColumnMapperHelper {

	public static class IntegerPropertyEditor extends PropertyEditorSupport {
		public static void register() {
			PropertyEditorManager.registerEditor(Integer.class,
					IntegerPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(Integer.class, null);
		}

		@Override
		public String getAsText() {
			final Integer integer = (Integer) getValue();
			return integer.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			if ((text == null) || "".equals(text)) {
				setValue(null);
			} else {
				setValue(Integer.valueOf(text));
			}
		}

	}

	public static class UrlPropertyEditor extends PropertyEditorSupport {
		public static void register() {
			PropertyEditorManager.registerEditor(URL.class,
					UrlPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(URL.class, null);
		}

		@Override
		public String getAsText() {
			final URL url = (URL) getValue();
			return url.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			try {

				String checked = text;
				if (!checked.startsWith("http")) {
					checked = "http://" + checked;
				}
				final URL url = new URL(checked);
				setValue(url);
			} catch (final MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, String> getColumnMapping() {
		IntegerPropertyEditor.register();
		UrlPropertyEditor.register();
		final Map<String, String> columnMapping = new HashMap<String, String>();

		// deelnemersnummer
		columnMapping.put("deelnemersnummer", "deelnemersnummer");
		// lidnummer
		// lid initialen
		// lid voornaam
		// lid tussenvoegsel
		// lid achternaam
		// lid geslacht
		// lid straat
		// lid huisnummer
		// lid toevoegsel huisnr
		// lid postcode
		// lid plaats
		// lid landcode
		// lid geboortedatum
		// lid geboorteplaats
		// lid geboortelandcode
		// lid mailadres
		// lid telefoon
		// lid mobiel
		// lidstatus
		// organisatienummer
		// organisatie
		// plaats
		// speleenheid
		// deelnemer functie
		// deelnemer status
		// deelnemer inschrijfdatum
		// deelnemertype
		// formuliernummer
		// formulier
		// formulier startdatum
		// ingeschreven door
		// lid mailadres ouder/verzorger
		// totaalbedrag
		// openstaand bedrag
		// deelnemer toegevoegd door ander
		// bovenliggende org nr.
		// bovenliggende organisatie
		// Shantiformuliernummer
		// hitwrapperpagina
		// HIT-Kamp in HIT-Plaats
		columnMapping.put("HIT-Kamp in HIT-Plaats", "plaatsNaam");
		// HIT-Kamp naam
		columnMapping.put("HIT-Kamp naam", "naam");
		// HIT-Kamp Contactpersoon
		columnMapping.put("HIT-Kamp Contactpersoon", "websiteContactpersoon");
		// HIT-Kamp Contactpersoon Emailadres
		columnMapping.put("HIT-Kamp Contactpersoon Emailadres",
				"websiteContactEmailadres");
		// HIT-Kamp Contactpersoon Telefoonnummer
		columnMapping.put("HIT-Kamp Contactpersoon Telefoonnummer",
				"websiteContactTelefoonnummer");
		// HIT-Kamp doelstelling
		// HIT-Kamp Activiteitengebieden: Uitdagende Scoutingtechnieken
		// HIT-Kamp Activiteitengebieden: Expressie
		// HIT-Kamp Activiteitengebieden: Sport en Spel
		// HIT-Kamp Activiteitengebieden: Buitenleven
		// HIT-Kamp Activiteitengebieden: Identiteit
		// HIT-Kamp Activiteitengebieden: Internationaal
		// HIT-Kamp Activiteitengebieden: Samenleving
		// HIT-Kamp Activiteitengebieden: Veilig en Gezond
		// HIT-Kamp titeltekst
		columnMapping.put("HIT-Kamp titeltekst", "titeltekst");
		// HIT-Kamp Couranttekst
		columnMapping.put("HIT-Kamp Couranttekst", "courantTekst");
		// HIT-Kamp Startdatum
		// HIT-Kamp Starttijd
		// HIT-Kamp Einddatum
		// HIT-Kamp Eindtijd
		// Deelnamekosten
		columnMapping.put("Deelnamekosten", "deelnamekosten");
		// Leeftijd minimaal
		columnMapping.put("Leeftijd minimaal", "minimumLeeftijd");
		// Leeftijd maximaal
		columnMapping.put("Leeftijd maximaal", "maximumLeeftijd");
		// Subgroepsamenstelling
		// Subgroepsamenstelling van
		// Subgroepsamenstelling tot en met
		// Subgroep extra

		for (final String ic : new String[] {
				"De HIT Icoontjes: Staand kamp",
				"De HIT Icoontjes: Trekken per fiets",
				"De HIT Icoontjes: Trekken met rugzak",
				"De HIT Icoontjes: Trekken per kano",
				"De HIT Icoontjes: Trekkend per boot",
				"De HIT Icoontjes: Lopen zonder rugzak",
				"De HIT Icoontjes: Lopen met een ander voorwerp",
				"De HIT Icoontjes: Inschrijven per persoon",
				"De HIT Icoontjes: Inschrijven per groep",
				"De HIT Icoontjes: Overnachten in een zelfmeegenomen tent",
				"De HIT Icoontjes: Overnachten in een frietbuil",
				"De HIT Icoontjes: Overnachten zonder tent",
				"De HIT Icoontjes: Overnachten in tenten verzorgd door staf",
				"De HIT Icoontjes: Overnachten in gebouw",
				"De HIT Icoontjes: Totale afstand is 0 km",
				"De HIT Icoontjes: Totale afstand is 5 km",
				"De HIT Icoontjes: Totale afstand is 15 km",
				"De HIT Icoontjes: Totale afstand is 20 km",
				"De HIT Icoontjes: Totale afstand is 25 km",
				"De HIT Icoontjes: Totale afstand is 30 km",
				"De HIT Icoontjes: Totale afstand is 35 km",
				"De HIT Icoontjes: Totale afstand is 40 km",
				"De HIT Icoontjes: Totale afstand is 45 km",
				"De HIT Icoontjes: Totale afstand is 50 km",
				"De HIT Icoontjes: Totale afstand is 55 km",
				"De HIT Icoontjes: Totale afstand is 60 km",
				"De HIT Icoontjes: Totale afstand is 65 km",
				"De HIT Icoontjes: Totale afstand is 70 km",
				"De HIT Icoontjes: Totale afstand is 75 km",
				"De HIT Icoontjes: Totale afstand is 80 km",
				"De HIT Icoontjes: Totale afstand is 85 km",
				"De HIT Icoontjes: Totale afstand is 90 km",
				"De HIT Icoontjes: Totale afstand is 100 km",
				"De HIT Icoontjes: Totale afstand is 120 km",
				"De HIT Icoontjes: Totale afstand is 150 km",
				"De HIT Icoontjes: Koken op houtvuur zonder pannen",
				"De HIT Icoontjes: Koken op houtvuur met pannen",
				"De HIT Icoontjes: Koken op gas met pannen",
				"De HIT Icoontjes: Gekookt door de staf",
				"De HIT Icoontjes: Kennis van kaart en kompas op eenvoudig niveau",
				"De HIT Icoontjes: Kennis van kaart en kompas op gevorderd niveau",
				"De HIT Icoontjes: Kennis van kaart en kompas op specialistisch nivea",
				"De HIT Icoontjes: Activiteit waarmee een insigne kan worden behaald",
				"De HIT Icoontjes: Zwemdiploma verplicht",
				"De HIT Icoontjes: Mobieltje meenemen",
				"De HIT Icoontjes: Mobieltjes zijn verboden",
				"De HIT Icoontjes: Geschikt voor minder validen (rolstoel)",
				"De HIT Icoontjes: Vraagteken Mysterie elemeneten",
				"De HIT Icoontjes: Buitenland - ID kaart of paspoort verplicht",
				"De HIT Icoontjes: Trekkend per auto"
		//
		}) {
			columnMapping.put(ic, "icoontje");
		}

		// HIT-Kamp websiteadres
		columnMapping.put("HIT-Kamp websiteadres", "websiteAdres");
		// HIT-Kamp websitetekst
		columnMapping.put("HIT-Kamp websitetekst", "websiteTekst");

		// Webadres naar foto1
		columnMapping.put("Webadres naar foto1", "webadresFoto1");
		// Webadres naar foto2
		columnMapping.put("Webadres naar foto2", "webadresFoto2");
		// Webadres naar foto3 of naar 1 Youtube filmpje
		columnMapping.put("Webadres naar foto3 of naar 1 Youtube filmpje",
				"webadresFoto3");
		// HIT-Kamp Websitecontacttelefoonnummer
		columnMapping.put("HIT-Kamp Websitecontacttelefoonnummer",
				"websiteTekst");

		// HIT-Kamp Websitecontactemailadres
		// HIT-Kamp websitecontactpersoon
		// Minimaal aantal deelnemers
		// Maximum aantal deelnemers
		// Overschrijding aantal deelnemers
		// Maximum aantal subgroepjes
		// Maximum aantal uit 1 Scoutinggroep
		// Akkoord HIT-kamp
		// Akkoord HIT-plaats
		return columnMapping;
	}
}