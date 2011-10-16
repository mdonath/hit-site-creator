package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HitPlaats {

	private String naam;

	private List<HitKamp> hitKampen;

	public HitPlaats(final String naam, final HitKamp... hitKampen) {
		this.naam = naam;
		this.hitKampen = new ArrayList<HitKamp>(Arrays.asList(hitKampen));
	}

	public void addHitKamp(final HitKamp hitKamp) {
		this.hitKampen.add(hitKamp);
		hitKamp.setPlaats(this);
		Collections.sort(this.hitKampen);
	}

	public int getAantalKampen() {
		return this.hitKampen.size();
	}

	public List<HitKamp> getHitKampen() {
		return this.hitKampen;
	}

	public String getNaam() {
		return this.naam;
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
		sb.append(this.naam).append("\n");
		for (final HitKamp hitKamp : this.hitKampen) {
			sb.append("- - ").append(hitKamp).append("\n");
		}
		return sb.toString();
	}

}
