package nl.scouting.hit.sitecreator.model;

public final class ModelUtil {

	public static final HitProject createEmptyStructure() {
		return new HitProject(null);
	}

	public static final HitProject createTestStructure() {
		return new HitProject(2012//
				, new HitPlaats("Mook" //
						, new HitKamp("100%") //
						, new HitKamp("Interhity") //
				) //
				, new HitPlaats("Zeeland" //
						, new HitKamp("Kroegentocht") //
				) //
				, new HitPlaats("Harderwijk" //
				) //
		);
	}

	private ModelUtil() {
		// private
	}

}
