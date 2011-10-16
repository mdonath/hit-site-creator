package nl.scouting.hit.sitecreator.model;

public class HitKamp {
	private HitPlaats plaats;
	private String naam;

	public HitKamp(String naam) {
		this.naam = naam;
	}

	public String toString() {
		return naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setPlaats(HitPlaats plaats) {
		this.plaats = plaats;
	}

	public HitPlaats getPlaats() {
		return plaats;
	}

}
