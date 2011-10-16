package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HitPlaats {

	private String naam;

	private List<HitKamp> hitKampen;

	public HitPlaats(String naam, HitKamp... hitKampen) {
		this.naam = naam;
		this.hitKampen = new ArrayList<HitKamp>(Arrays.asList(hitKampen));
	}

	public int getAantalKampen() {
		return this.hitKampen.size();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naam).append("\n");
		for (HitKamp hitKamp : hitKampen) {
			sb.append("- - ").append(hitKamp).append("\n");
		}
		return sb.toString();
	}

	public List<HitKamp> getHitKampen() {
		return hitKampen;
	}

	public void setHitKampen(List<HitKamp> hitKampen) {
		this.hitKampen = hitKampen;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

}
