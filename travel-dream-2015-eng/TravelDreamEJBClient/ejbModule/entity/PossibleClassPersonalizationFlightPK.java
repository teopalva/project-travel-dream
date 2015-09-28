package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the POSSIBLE_CLASS_PERSONALIZATION_FLIGHT database table.
 * 
 */
@Embeddable
public class PossibleClassPersonalizationFlightPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int flightId;

	@Column(insertable=false, updatable=false)
	private int classPersonalizationId;

	public PossibleClassPersonalizationFlightPK() {
	}
	public int getFlightId() {
		return this.flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getClassPersonalizationId() {
		return this.classPersonalizationId;
	}
	public void setClassPersonalizationId(int classPersonalizationId) {
		this.classPersonalizationId = classPersonalizationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PossibleClassPersonalizationFlightPK)) {
			return false;
		}
		PossibleClassPersonalizationFlightPK castOther = (PossibleClassPersonalizationFlightPK)other;
		return 
			(this.flightId == castOther.flightId)
			&& (this.classPersonalizationId == castOther.classPersonalizationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flightId;
		hash = hash * prime + this.classPersonalizationId;
		
		return hash;
	}
}