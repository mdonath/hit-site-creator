package nl.scouting.hit.sitecreator.model;

public enum HitEntiteit {

	/** Het HIT Project. */
	Project(HitProject.class),

	/** Een van de HIT plaatsen. */
	Plaats(HitPlaats.class),

	/** Een van de vele HIT Kampen. */
	Kamp(HitKamp.class),

	/** De inschrijvende deelnemers. */
	Deelnemer(HitDeelnemer.class);

	private Class<?> clazz;

	HitEntiteit(final Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClassForEntiteit() {
		return clazz;
	}
}
