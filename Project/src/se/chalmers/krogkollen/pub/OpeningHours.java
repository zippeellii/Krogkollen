package se.chalmers.krogkollen.pub;

/**
 * Class handling the opening and closing hours of a pub
 * 
 * @author Philip Carlen
 */
public class OpeningHours {

	private int	openingHour;
	private int	closingHour;

	/**
	 * Creates a new OpeningHours object
	 * 
	 * @param openingHour the opening hour
	 * @param closingHour the closing hour
	 */
	public OpeningHours(int openingHour, int closingHour) {
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}

	/**
	 * @return the opening hour
	 */
	public int getOpeningHour() {
		return this.openingHour;
	}

	/**
	 * @return the closing hour
	 */
	public int getClosingHour() {
		return this.closingHour;
	}

	@Override
	public String toString() {
		return (getOpeningHour() < 10 ? "0" + getOpeningHour() : getOpeningHour()) + ":00 - " +
				(getClosingHour() < 10 ? "0" + getClosingHour() : getClosingHour()) + ":00";
	}
}
