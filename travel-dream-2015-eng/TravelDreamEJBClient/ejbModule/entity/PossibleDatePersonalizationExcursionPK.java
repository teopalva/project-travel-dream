package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the POSSIBLE_DATE_PERSONALIZATION_EXCURSION database table.
 * 
 */
@Embeddable
public class PossibleDatePersonalizationExcursionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int excursionId;

	@Column(insertable=false, updatable=false)
	private int datePersonalizationId;

	public PossibleDatePersonalizationExcursionPK() {
	}
	public int getExcursionId() {
		return this.excursionId;
	}
	public void setExcursionId(int excursionId) {
		this.excursionId = excursionId;
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
		if (!(other instanceof PossibleDatePersonalizationExcursionPK)) {
			return false;
		}
		PossibleDatePersonalizationExcursionPK castOther = (PossibleDatePersonalizationExcursionPK)other;
		return 
			(this.excursionId == castOther.excursionId)
			&& (this.datePersonalizationId == castOther.datePersonalizationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.excursionId;
		hash = hash * prime + this.datePersonalizationId;
		
		return hash;
	}
}