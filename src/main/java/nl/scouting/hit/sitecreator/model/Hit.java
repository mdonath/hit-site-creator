package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Hit {

	private Integer jaar;
	private List<HitPlaats> hitPlaatsen;
	private Date inschrijvingStartdatum = DateUtil.asDate("02-01-2012");
	private Date inschrijvingEinddatum = DateUtil.asDate("09-03-2012");
	private Date inschrijvingWijzigenTotDatum = DateUtil.asDate("09-03-2012");
	private Date inschrijvingKosteloosAnnulerenDatum = DateUtil
			.asDate("09-03-2012");
	private Date inschrijvingGeenRestitutieDatum = DateUtil
			.asDate("10-03-2012");
	private Date inningsdatum = DateUtil.asDate("19-03-2012");
	private Date datumNu = new Date();

	public Hit(final Integer jaar) {
		this.jaar = jaar;
		hitPlaatsen = new ArrayList<HitPlaats>(6);
	}

	public Hit(final Integer jaar, final HitPlaats... hitPlaatsen) {
		this.jaar = jaar;
		if (hitPlaatsen != null) {
			this.hitPlaatsen = new ArrayList<HitPlaats>(hitPlaatsen.length);
			for (final HitPlaats plaats : hitPlaatsen) {
				addHitPlaats(plaats);
			}
		} else {
			this.hitPlaatsen = Collections.emptyList();
		}
	}

	public void addHitPlaats(final HitPlaats hitPlaats) {
		hitPlaatsen.add(hitPlaats);
		hitPlaats.setHit(this);
		linkKampenAanElkaar();
	}

	public Iterator<HitKamp> kampenIterator() {
		return getKampenGesorteerd().iterator();
	}

	public List<HitKamp> getKampenGesorteerd() {
		final List<HitKamp> kampen = new ArrayList<HitKamp>();
		for (final HitPlaats hitPlaats : hitPlaatsen) {
			kampen.addAll(hitPlaats.getHitKampen());
		}
		Collections.sort(kampen);
		return kampen;
	}

	public void linkKampenAanElkaar() {
		final List<HitKamp> kampen = getKampenGesorteerd();
		final int totEnMetEenNaLaatsteKamp = kampen.size() - 1;
		for (int i = 0; i < totEnMetEenNaLaatsteKamp; i++) {
			kampen.get(i).setNext(kampen.get(i + 1));
		}
		if (totEnMetEenNaLaatsteKamp >= 0) {
			kampen.get(totEnMetEenNaLaatsteKamp).setNext(kampen.get(0));
		}
	}

	public Set<Icoon> getBeschikbareIconen() {
		return Icoon.getAll();
	}

	public Set<Icoon> getGebruikteIconen() {
		final Set<Icoon> result = new TreeSet<Icoon>();
		for (final HitPlaats hitPlaats : hitPlaatsen) {
			for (final HitKamp kamp : hitPlaats.getHitKampen()) {
				result.addAll(kamp.getIcoontjes());
			}
		}
		return result;
	}

	public int getAantalKampen() {
		int totaal = 0;
		for (final HitPlaats hitPlaats : hitPlaatsen) {
			totaal += hitPlaats.getAantalKampen();
		}
		return totaal;
	}

	public List<HitPlaats> getHitPlaatsen() {
		return hitPlaatsen;
	}

	public Integer getJaar() {
		return jaar;
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
		sb.append("HIT ").append(jaar).append("\n");
		for (final HitPlaats hitPlaats : hitPlaatsen) {
			sb.append("- ").append(hitPlaats);
		}
		return sb.toString();
	}

	public Date getInschrijvingStartdatum() {
		return inschrijvingStartdatum;
	}

	public void setInschrijvingStartdatum(final Date inschrijvingStartdatum) {
		this.inschrijvingStartdatum = inschrijvingStartdatum;
	}

	public Date getInschrijvingEinddatum() {
		return inschrijvingEinddatum;
	}

	public void setInschrijvingEinddatum(final Date inschrijvingEinddatum) {
		this.inschrijvingEinddatum = inschrijvingEinddatum;
	}

	public Date getInschrijvingWijzigenTotDatum() {
		return inschrijvingWijzigenTotDatum;
	}

	public void setInschrijvingWijzigenTotDatum(
			final Date inschrijvingWijzigenTotDatum) {
		this.inschrijvingWijzigenTotDatum = inschrijvingWijzigenTotDatum;
	}

	public Date getInschrijvingKosteloosAnnulerenDatum() {
		return inschrijvingKosteloosAnnulerenDatum;
	}

	public void setInschrijvingKosteloosAnnulerenDatum(
			final Date inschrijvingKosteloosAnnulerenDatum) {
		this.inschrijvingKosteloosAnnulerenDatum = inschrijvingKosteloosAnnulerenDatum;
	}

	public Date getInschrijvingGeenRestitutieDatum() {
		return inschrijvingGeenRestitutieDatum;
	}

	public void setInschrijvingGeenRestitutieDatum(
			final Date inschrijvingGeenRestitutieDatum) {
		this.inschrijvingGeenRestitutieDatum = inschrijvingGeenRestitutieDatum;
	}

	public Date getInningsdatum() {
		return inningsdatum;
	}

	public void setInningsdatum(final Date inningsdatum) {
		this.inningsdatum = inningsdatum;
	}

	public Date getDatumNu() {
		return datumNu;
	}

	public void setDatumNu(final Date datumNu) {
		this.datumNu = datumNu;
	}

}
