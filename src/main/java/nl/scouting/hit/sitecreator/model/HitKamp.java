package nl.scouting.hit.sitecreator.model;

import java.util.Set;
import java.util.TreeSet;

public class HitKamp {
	private HitPlaats plaats;

	private String naam;
	private String plaatsNaam;

	private String icoontje;
	private final Set<String> icoontjes;

	public HitKamp() {
		super();
		this.icoontjes = new TreeSet<String>();
	}

	public HitKamp(final String naam) {
		this();
		this.naam = naam;
	}

	public String getIcoontje() {
		return this.icoontje;
	}

	public Set<String> getIcoontjes() {
		return this.icoontjes;
	}

	public String getNaam() {
		return this.naam;
	}

	public HitPlaats getPlaats() {
		return this.plaats;
	}

	public String getPlaatsNaam() {
		return this.plaatsNaam;
	}

	public void setIcoontje(final String icoontje) {
		this.icoontje = icoontje;
		if (!"".equals(icoontje)) {
			this.icoontjes.add(icoontje);
		}
	}

	public void setNaam(final String naam) {
		this.naam = naam;
	}

	public void setPlaats(final HitPlaats hitPlaats) {
		this.plaats = hitPlaats;
	}

	public void setPlaatsNaam(final String plaatsNaam) {
		this.plaatsNaam = plaatsNaam;
	}

	@Override
	public String toString() {
		return this.naam;
	}

}
