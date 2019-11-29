package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * The <code>DateRange</code> class represents a contigious collection of dates, and allows
 * a useful set of functionality on a range.
 */
public class DateRange {
    /**
     * the two edges of the date range
     */
    private LocalDate start, end;

    /**
     * the constructor for a <code>DateRange</code> class
     *
     * @param start the beginning of the range
     * @param end the end of the range
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the date starting the range
     *
     * @return the start date for this <code>DateRange</code>
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Gets the date ending the range
     *
     * @return the end date for this <code>DateRange</code>
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Gets the number of full years that are contained within the range
     *
     * @return the number of full years that has passed between the start and end date
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
     * Gets the number of days that are contained within the range
     *
     * @return the number of days that have passed between the start and end date
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * Determines whether two ranges overlap
     *
     * @return <code>true</code> if this <code>DateRange</code> has days in common
     * with the other <code>DateRange</code>
     */
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
                return false;
            }
            else {
                return true;
            }
        }

    }

    /**
     * Checks if this <code>DateRange</code> lies completely within the
     * <code>DateRange other</code>
     *
     * @return <code>true</code> if this <code>DateRange</code> lies completely inside the other <code>DateRange</code>
     */
    public Boolean overlapsCompletely(DateRange other){
        LocalDate start1 = other.getStart();
        LocalDate end1   = other.getEnd();
        return start.compareTo(start1)>=0 && end.compareTo(end1)>=0;
    }


    /**
     * Hashes the given <code>DateRange</code>
     *
     * @return the hash value for this <code>DateRange</code>
     */
    @Override
        public int hashCode() {
            // hashCode method allowing use in collections
            return Objects.hash(end, start);
        }

    /**
     * Checks equality between <code>DateRange</code> objects
     *
     * @param obj the <code>DateRange</code> to compare to this
     *
     * @return <code>true</code> if the two <code>DateRange</code> objects have the same
     * start and end dates
     */
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
