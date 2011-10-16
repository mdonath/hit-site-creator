package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.HashMap;
import java.util.Map;

public class ColumnMapperHelper2011 implements ColumnMapperHelper {

	// "deelnemersnummer","lidnummer","lid initialen","lid voornaam","lid tussenvoegsel","lid achternaam","lid geslacht","lid straat","lid huisnummer","lid toevoegsel huisnr","lid postcode","lid plaats","lid landcode","lid geboortedatum","lid geboorteplaats","lid geboortelandcode","lid mailadres","lid telefoon","lid mobiel","lidstatus","organisatienummer","organisatie","plaats","speleenheid","deelnemer functie","deelnemer status",
	// "deelnemer inschrijfdatum","deelnemertype","formuliernummer","formulier","formulier startdatum","ingeschreven door",
	// "lid mailadres ouder/verzorger","totaalbedrag","openstaand bedrag","deelnemer toegevoegd door ander","bovenliggende org nr.","bovenliggende organisatie",

	// "Shantiformuliernummer","hitwrapperpagina","HIT-Kamp in HIT-Plaats","HIT-Kamp naam","HIT-Kamp Contactpersoon","HIT-Kamp Contactpersoon Emailadres","HIT-Kamp Contactpersoon Telefoonnummer",
	// "HIT-Kamp doelstelling","HIT-Kamp Scoutsgebieden: Uitdagende Scoutingtechnieken","HIT-Kamp Scoutsgebieden: Expressie","HIT-Kamp Scoutsgebieden: Sport en Spel","HIT-Kamp Scoutsgebieden: Buitenleven","HIT-Kamp Scoutsgebieden: Identiteit","HIT-Kamp Scoutsgebieden: Internationaal","HIT-Kamp Scoutsgebieden: Samenleving","HIT-Kamp Scoutsgebieden: Veilig en Gezond",
	// "HIT-Kamp titeltekst","HIT-Kamp Couranttekst","HIT-Kamp Startdatum","HIT-Kamp Starttijd","HIT-Kamp Einddatum","HIT-Kamp Eindtijd",
	// "Deelnamekosten","Leeftijd minimaal","Leeftijd maximaal","Subgroepsamenstelling","Subgroep extra",
	// "De HIT Icoontjes: Staand kamp","De HIT Icoontjes: Trekken per fiets","De HIT Icoontjes: Trekken met rugzak","De HIT Icoontjes: Trekken per kano","De HIT Icoontjes: Trekkend per boot","De HIT Icoontjes: Lopen zonder rugzak","De HIT Icoontjes: Lopen met een ander voorwerp","De HIT Icoontjes: Inschrijven per persoon","De HIT Icoontjes: Inschrijven per groep","De HIT Icoontjes: Overnachten in een zelfmeegenomen tent","De HIT Icoontjes: Overnachten in een frietbuil","De HIT Icoontjes: Overnachten zonder tent","De HIT Icoontjes: Overnachten in tenten verzorgd door staf","De HIT Icoontjes: Overnachten in gebouw","De HIT Icoontjes: Totale afstand is 0 km","De HIT Icoontjes: Totale afstand is 5 km","De HIT Icoontjes: Totale afstand is 15 km","De HIT Icoontjes: Totale afstand is 20 km","De HIT Icoontjes: Totale afstand is 25 km","De HIT Icoontjes: Totale afstand is 30 km","De HIT Icoontjes: Totale afstand is 35 km","De HIT Icoontjes: Totale afstand is 40 km","De HIT Icoontjes: Totale afstand is 45 km","De HIT Icoontjes: Totale afstand is 50 km","De HIT Icoontjes: Totale afstand is 55 km","De HIT Icoontjes: Totale afstand is 60 km","De HIT Icoontjes: Totale afstand is 65 km","De HIT Icoontjes: Totale afstand is 70 km","De HIT Icoontjes: Totale afstand is 75 km","De HIT Icoontjes: Totale afstand is 80 km","De HIT Icoontjes: Totale afstand is 85 km","De HIT Icoontjes: Totale afstand is 90 km","De HIT Icoontjes: Totale afstand is 100 km","De HIT Icoontjes: Totale afstand is 120 km","De HIT Icoontjes: Totale afstand is 150 km","De HIT Icoontjes: Koken op houtvuur zonder pannen","De HIT Icoontjes: Koken op houtvuur met pannen","De HIT Icoontjes: Koken op gas met pannen","De HIT Icoontjes: Gekookt door de staf","De HIT Icoontjes: Kennis van kaart en kompas op eenvoudig niveau","De HIT Icoontjes: Kennis van kaart en kompas op gevorderd niveau","De HIT Icoontjes: Kennis van kaart en kompas op specialistisch nivea","De HIT Icoontjes: Activiteit waarmee een insigne kan worden behaald","De HIT Icoontjes: Zwemdiploma verplicht","De HIT Icoontjes: Mobieltje meenemen","De HIT Icoontjes: Mobieltjes zijn verboden","De HIT Icoontjes: Geschikt voor rolstoel","De HIT Icoontjes: Vraagteken","De HIT Icoontjes: Paspoort","De HIT Icoontjes: Auto nodig",
	// "HIT-Kamp websitetekst","HIT-Kamp websiteadres","Webadres naar foto1","Webadres naar foto2","Webadres naar foto3","HIT-Kamp Websitecontacttelefoonnummer","HIT-Kamp Websitecontactemailadres","HIT-Kamp websitecontactpersoon",
	// "Minimaal aantal deelnemers","Maximum aantal deelnemers","Overschrijding aantal deelnemers","Maximum aantal subgroepjes","Maximum aantal uit 1 Scoutinggroep","bijlagen toestaan"

	/** {@inheritDoc} */
	@Override
	public Map<String, String> getColumnMapping() {
		final Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("HIT-Kamp naam", "naam");
		columnMapping.put("HIT-Kamp in HIT-Plaats", "plaatsNaam");
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
				"De HIT Icoontjes: Geschikt voor rolstoel",
				"De HIT Icoontjes: Vraagteken", "De HIT Icoontjes: Paspoort",
				"De HIT Icoontjes: Auto nodig" }) {
			columnMapping.put(ic, "icoontje");
		}
		return columnMapping;
	}
}
