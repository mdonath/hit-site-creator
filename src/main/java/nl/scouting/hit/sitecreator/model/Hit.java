package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hit {

	private Integer jaar;
	private List<HitPlaats> hitPlaatsen;

	public Hit(Integer jaar, HitPlaats... hitPlaatsen) {
		this.jaar = jaar;
		this.hitPlaatsen = new ArrayList<HitPlaats>(Arrays.asList(hitPlaatsen));
	}

	public int getAantalKampen() {
		int totaal = 0;
		for (HitPlaats hitPlaats : hitPlaatsen) {
			totaal += hitPlaats.getAantalKampen();
		}
		return totaal;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HIT ").append(jaar).append("\n");
		for (HitPlaats hitPlaats : hitPlaatsen) {
			sb.append("- ").append(hitPlaats);
		}
		return sb.toString();
	}

	public Integer getJaar() {
		return jaar;
	}

	public void setJaar(Integer jaar) {
		this.jaar = jaar;
	}

	public List<HitPlaats> getHitPlaatsen() {
		return hitPlaatsen;
	}

	public void setHitPlaatsen(List<HitPlaats> hitPlaatsen) {
		this.hitPlaatsen = hitPlaatsen;
	}

}
