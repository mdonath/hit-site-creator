package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HitPlaats implements Comparable<HitPlaats> {

	private Hit hit;
	private List<HitKamp> hitKampen;

	private Integer deelnemersnummer;
	private String naam;

	private String hitCourantTekst;
	private String contactPersoonNaam;
	private String contactPersoonEmail;
	private String contactPersoonTelefoon;

	/**
	 * Nodig voor de CSV Mapper.
	 */
	public HitPlaats() {
		this("");
	}

	public HitPlaats(final String naam, final HitKamp... hitKampen) {
		this.naam = naam;

		this.hitKampen = new ArrayList<HitKamp>();
		if (hitKampen != null) {
			for (final HitKamp hitKamp : hitKampen) {
				addHitKamp(hitKamp);
			}
		}
	}

	public void merge(final HitPlaats plaats) {
		if (naam.equals(plaats.naam)) {
			deelnemersnummer = plaats.deelnemersnummer;
			hitCourantTekst = plaats.hitCourantTekst;
			contactPersoonNaam = plaats.contactPersoonNaam;
			contactPersoonEmail = plaats.contactPersoonEmail;
			contactPersoonTelefoon = plaats.contactPersoonTelefoon;

			merge(plaats.hitKampen);
		} else {
			throw new MergeException("Naam niet gelijk: '" + naam + "' / '"
					+ plaats.naam + "'");
		}
	}

	public void merge(final Collection<HitKamp> kampen) {
		for (final HitKamp kamp : kampen) {
			if (hitKampen.contains(kamp)) {
				final int index = hitKampen.indexOf(kamp);
				hitKampen.get(index).merge(kamp);
			} else {
				addHitKamp(kamp);
			}
		}
	}

	@Override
	public boolean equals(final Object obj) {
		return naam.equals(((HitPlaats) obj).getNaam());
	}

	@Override
	public int hashCode() {
		return naam.hashCode();
	}

	@Override
	public int compareTo(final HitPlaats o) {
		return naam.compareTo(o.naam);
	}

	public void addHitKamp(final HitKamp hitKamp) {
		hitKampen.add(hitKamp);
		hitKamp.setPlaats(this);
		Collections.sort(hitKampen);
	}

	public int getAantalKampen() {
		return hitKampen.size();
	}

	public List<HitKamp> getHitKampen() {
		return hitKampen;
	}

	public String getNaam() {
		return naam;
	}

	public void setHitKampen(final List<HitKamp> hitKampen) {
		this.hitKampen = hitKampen;
	}

	public void setNaam(final String naam) {
		this.naam = naam;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(naam).append("\n");
		for (final HitKamp hitKamp : hitKampen) {
			sb.append("- - ").append(hitKamp).append("\n");
		}
		return sb.toString();
	}

	public Hit getHit() {
		return hit;
	}

	public void setHit(final Hit hit) {
		this.hit = hit;
	}

	public String getHitCourantTekst() {
		return hitCourantTekst;
	}

	public void setHitCourantTekst(final String hitCourantTekst) {
		this.hitCourantTekst = hitCourantTekst;
	}

	public Integer getDeelnemersnummer() {
		return deelnemersnummer;
	}

	public void setDeelnemersnummer(final Integer deelnemersnummer) {
		this.deelnemersnummer = deelnemersnummer;
	}

	public String getContactPersoonNaam() {
		return contactPersoonNaam;
	}

	public void setContactPersoonNaam(final String contactPersoonNaam) {
		this.contactPersoonNaam = contactPersoonNaam;
	}

	public String getContactPersoonEmail() {
		return contactPersoonEmail;
	}

	public void setContactPersoonEmail(final String contactPersoonEmail) {
		this.contactPersoonEmail = contactPersoonEmail;
	}

	public String getContactPersoonTelefoon() {
		return contactPersoonTelefoon;
	}

	public void setContactPersoonTelefoon(final String contactPersoonTelefoon) {
		this.contactPersoonTelefoon = contactPersoonTelefoon;
	}

}
