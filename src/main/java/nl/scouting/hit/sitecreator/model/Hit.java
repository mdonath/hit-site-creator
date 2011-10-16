package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hit {

	private Integer jaar;
	private List<HitPlaats> hitPlaatsen;

	public Hit(final Integer jaar) {
		this.jaar = jaar;
		this.hitPlaatsen = new ArrayList<HitPlaats>(6);
	}

	public Hit(final Integer jaar, final HitPlaats... hitPlaatsen) {
		this.jaar = jaar;
		if (hitPlaatsen != null) {
			this.hitPlaatsen = new ArrayList<HitPlaats>(
					Arrays.asList(hitPlaatsen));
		} else {
			this.hitPlaatsen = Collections.emptyList();
		}
	}

	public void addHitPlaats(final HitPlaats hitPlaats) {
		this.hitPlaatsen.add(hitPlaats);
	}

	public int getAantalKampen() {
		int totaal = 0;
		for (final HitPlaats hitPlaats : this.hitPlaatsen) {
			totaal += hitPlaats.getAantalKampen();
		}
		return totaal;
	}

	public List<HitPlaats> getHitPlaatsen() {
		return this.hitPlaatsen;
	}

	public Integer getJaar() {
		return this.jaar;
	}

	public void setHitPlaatsen(final List<HitPlaats> hitPlaatsen) {
		this.hitPlaatsen = hitPlaatsen;
	}

	public void setJaar(final Integer jaar) {
		this.jaar = jaar;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("HIT ").append(this.jaar).append("\n");
		for (final HitPlaats hitPlaats : this.hitPlaatsen) {
			sb.append("- ").append(hitPlaats);
		}
		return sb.toString();
	}

}
