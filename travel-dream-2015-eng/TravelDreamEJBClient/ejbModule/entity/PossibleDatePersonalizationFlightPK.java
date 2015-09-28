package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the POSSIBLE_DATE_PERSONALIZATION_FLIGHT database table.
 * 
 */
@Embeddable
public class PossibleDatePersonalizationFlightPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int flightId;

	@Column(insertable=false, updatable=false)
	private int datePersonalizationId;

	public PossibleDatePersonalizationFlightPK() {
	}
	public int getFlightId() {
		return this.flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getDatePersonalizationId() {
		return this.datePersonalizationId;
	}
	public void setDatePersonalizationId(int datePersonalizationId) {
		this.datePersonalizationId = datePersonalizationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PossibleDatePersonalizationFlightPK)) {
			return false;
		}
		PossibleDatePersonalizationFlightPK castOther = (PossibleDatePersonalizationFlightPK)other;
		return 
			(this.flightId == castOther.flightId)
			&& (this.datePersonalizationId == castOther.datePersonalizationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flightId;
		hash = hash * prime + this.datePersonalizationId;
		
		return hash;
	}
}