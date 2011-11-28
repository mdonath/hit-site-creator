package nl.scouting.hit.sitecreator.output.module.images;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.ImageUrl;
import nl.scouting.hit.sitecreator.util.IOUtil;

import org.junit.Test;

public class ImagesOutputModuleTest {

	@Test
	public void test_schalen_jpg() throws Exception {
		final File inputImage = File.createTempFile(getClass().getName(),
				".jpg");
		final FileOutputStream fos = new FileOutputStream(inputImage);
		IOUtil.copy(
				getClass()
						.getResourceAsStream(
								"/nl/scouting/hit/sitecreator/output/module/images/img-1.jpg"),
				fos);
		fos.close();
		final ImagesOutputModule om = new ImagesOutputModule();
		om.setOutDir(inputImage.getParentFile());
		final HitKamp kamp = new HitKamp("kamp");
		kamp.setDeelnemersnummer(123);
		kamp.setWebadresFoto1(new ImageUrl(inputImage.toURI().toURL()));
		final HitProject hit = new HitProject(Integer.valueOf(2012),
				new HitPlaats("plaats", kamp));
		om.save(hit);
		assertTrue(inputImage.delete());
		assertTrue(new File(inputImage.getParentFile(), "img-123-1.jpg")
				.delete());
		assertTrue(new File(inputImage.getParentFile(), "img-123-1-b.jpg")
				.delete());
	}

	@Test
	public void test_schalen_png() throws Exception {
		final File inputImage = File.createTempFile(getClass().getName(),
				".png");
		final FileOutputStream fos = new FileOutputStream(inputImage);
		IOUtil.copy(
				getClass()
						.getResourceAsStream(
								"/nl/scouting/hit/sitecreator/output/module/images/img-1.png"),
				fos);
		fos.close();
		final ImagesOutputModule om = new ImagesOutputModule();
		om.setOutDir(inputImage.getParentFile());
		final HitKamp kamp = new HitKamp("kamp");
		kamp.setDeelnemersnummer(123);
		kamp.setWebadresFoto1(new ImageUrl(inputImage.toURI().toURL()));
		final HitProject hit = new HitProject(Integer.valueOf(2012),
				new HitPlaats("plaats", kamp));
		om.save(hit);
		assertTrue(inputImage.delete());
		assertTrue(new File(inputImage.getParentFile(), "img-123-1.png")
				.delete());
		assertTrue(new File(inputImage.getParentFile(), "img-123-1-b.png")
				.delete());
	}

}
