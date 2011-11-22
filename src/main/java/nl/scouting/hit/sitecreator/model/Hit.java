package nl.scouting.hit.sitecreator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import nl.scouting.hit.sitecreator.model.Icoon.AfstandsIcoon;
import nl.scouting.hit.sitecreator.util.DateUtil;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class Hit {

	private Integer jaar;
	private List<HitPlaats> hitPlaatsen;
	private LocalDate inschrijvingStartdatum;
	private LocalDate inschrijvingEinddatum;
	private LocalDate inschrijvingWijzigenTotDatum;
	private LocalDate inschrijvingKosteloosAnnulerenDatum;
	private LocalDate inschrijvingGeenRestitutieDatum;
	private LocalDate inningsdatum;

	public Hit() {
		this(0);
	}

	public Hit(final Integer jaar) {
		this(jaar, Collections.<HitPlaats> emptyList());
	}

	public Hit(final Integer jaar, final HitPlaats... hitPlaatsen) {
		this(jaar, Arrays.asList(hitPlaatsen));
	}

	public Hit(final Integer jaar, final Collection<HitPlaats> hitPlaatsen) {
		this.jaar = jaar;
		this.hitPlaatsen = new ArrayList<HitPlaats>(hitPlaatsen.size());
		if (!((hitPlaatsen == null) || hitPlaatsen.isEmpty())) {
			for (final HitPlaats plaats : hitPlaatsen) {
				addHitPlaats(plaats);
			}
		}
	}

	public void merge(final Hit otherHit) {
		if (jaar.equals(otherHit.jaar)) {
			inschrijvingStartdatum = otherHit.inschrijvingStartdatum;
			inschrijvingEinddatum = otherHit.inschrijvingEinddatum;
			inschrijvingGeenRestitutieDatum = otherHit.inschrijvingGeenRestitutieDatum;
			inschrijvingKosteloosAnnulerenDatum = otherHit.inschrijvingKosteloosAnnulerenDatum;
			inschrijvingWijzigenTotDatum = otherHit.inschrijvingWijzigenTotDatum;
			inningsdatum = otherHit.inningsdatum;
			merge(otherHit.getHitPlaatsen());
		} else {
			throw new MergeException("Verkeerd jaar om te mergen: " + jaar
					+ " / " + otherHit.jaar);
		}
	}

	public void merge(final Collection<HitPlaats> hitPlaatsen) {
		for (final HitPlaats plaats : hitPlaatsen) {
			if (this.hitPlaatsen.contains(plaats)) {
				final int index = this.hitPlaatsen.indexOf(plaats);
				this.hitPlaatsen.get(index).merge(plaats);
			} else {
				addHitPlaats(plaats);
			}
		}
	}

	public void mergeKampen(final Collection<HitPlaats> hitPlaatsen) {
		for (final HitPlaats plaats : hitPlaatsen) {
			if (this.hitPlaatsen.contains(plaats)) {
				final int index = this.hitPlaatsen.indexOf(plaats);
				this.hitPlaatsen.get(index).merge(plaats.getHitKampen());
			} else {
				addHitPlaats(plaats);
			}
		}
	}

	public void mergeDeelnemers(final Collection<HitPlaats> hitPlaatsen) {
		// pseudo kampen met hitplaats.shantiformuliernummer

	}

	public LocalDate getVrijdag() {
		return DateUtil.getEaster(jaar).minusDays(2);
	}

	public LocalDate getMaandag() {
		return DateUtil.getEaster(jaar).plusDays(1);
	}

	public boolean isHeeftBeginEnEindInVerschillendeMaanden() {
		return getVrijdag().getMonthOfYear() != getMaandag().getMonthOfYear();
	}

	public void addHitPlaats(final HitPlaats hitPlaats) {
		hitPlaatsen.add(hitPlaats);
		hitPlaats.setHit(this);
		Collections.sort(hitPlaatsen);
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

	public Set<Icoon> getGebruikteIconenVoorCourant() {
		final Set<Icoon> result = getGebruikteIconen();
		for (final Iterator<Icoon> i = result.iterator(); i.hasNext();) {
			final Icoon icoon = i.next();
			if (icoon.isAfstandsIndicate()
					&& (((AfstandsIcoon) icoon).getAfstand() != 60)) {
				i.remove();
			}
		}
		return result;
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

	public LocalDate getInschrijvingStartdatum() {
		return inschrijvingStartdatum;
	}

	public void setInschrijvingStartdatum(final LocalDate inschrijvingStartdatum) {
		this.inschrijvingStartdatum = inschrijvingStartdatum;
	}

	public LocalDate getInschrijvingEinddatum() {
		return inschrijvingEinddatum;
	}

	public void setInschrijvingEinddatum(final LocalDate inschrijvingEinddatum) {
		this.inschrijvingEinddatum = inschrijvingEinddatum;
	}

	public LocalDate getInschrijvingWijzigenTotDatum() {
		return inschrijvingWijzigenTotDatum;
	}

	public void setInschrijvingWijzigenTotDatum(
			final LocalDate inschrijvingWijzigenTotDatum) {
		this.inschrijvingWijzigenTotDatum = inschrijvingWijzigenTotDatum;
	}

	public LocalDate getInschrijvingKosteloosAnnulerenDatum() {
		return inschrijvingKosteloosAnnulerenDatum;
	}

	public void setInschrijvingKosteloosAnnulerenDatum(
			final LocalDate inschrijvingKosteloosAnnulerenDatum) {
		this.inschrijvingKosteloosAnnulerenDatum = inschrijvingKosteloosAnnulerenDatum;
	}

	public LocalDate getInschrijvingGeenRestitutieDatum() {
		return inschrijvingGeenRestitutieDatum;
	}

	public void setInschrijvingGeenRestitutieDatum(
			final LocalDate inschrijvingGeenRestitutieDatum) {
		this.inschrijvingGeenRestitutieDatum = inschrijvingGeenRestitutieDatum;
	}

	public LocalDate getInningsdatum() {
		return inningsdatum;
	}

	public void setInningsdatum(final LocalDate inningsdatum) {
		this.inningsdatum = inningsdatum;
	}

	public LocalDateTime getDatumNu() {
		return new LocalDateTime();
	}

}
