package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HitPlaats {

	private Hit hit;

	private String naam;

	private String hitCourantTekst = "Nog geen tekst!";

	private List<HitKamp> hitKampen;

	public HitPlaats(final String naam, final HitKamp... hitKampen) {
		this.naam = naam;

		this.hitKampen = new ArrayList<HitKamp>();
		for (final HitKamp hitKamp : hitKampen) {
			addHitKamp(hitKamp);
		}
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

}
