package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class DateRange {
	private LocalDate start, end;

	public DateRange(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}

	public LocalDate getStart() {
		return this.start;
	}

	public LocalDate getEnd() {
		return this.end;
	}

	public long toYears() {
		return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
	}

	public long toDays() {
		return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
	}

	public Boolean overlaps(DateRange other) {
		// TODO: implement date range intersection checking

		LocalDate start1 = other.getStart();
		LocalDate end1   = other.getEnd();
		if(start.compareTo(start1)>=0){
			if(start.compareTo(end1)>=0) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			if(end.compareTo(start1)<0){
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	// checks if this daterange lies completely on the "other" daterange
  public Boolean overlapsCompletely(DateRange other){
	LocalDate start1 = other.getStart();
	LocalDate end1   = other.getEnd();
	return start.compareTo(start1)>=0 && end.compareTo(end1)>=0;
  }	


	@Override
	public int hashCode() {
		// hashCode method allowing use in collections
		return Objects.hash(end, start);
	}

	@Override
	public boolean equals(Object obj) {
		// equals method for testing equality in tests
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateRange other = (DateRange) obj;
		return Objects.equals(end, other.end) && Objects.equals(start, other.start);
	}

	// You can add your own methods here

}
