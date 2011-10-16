package nl.scouting.hit.sitecreator.model;

public final class ModelUtil {

	public static final Hit createEmptyStructure() {
		return new Hit(null);
	}

	public static final Hit createTestStructure() {
		return new Hit(2012//
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
