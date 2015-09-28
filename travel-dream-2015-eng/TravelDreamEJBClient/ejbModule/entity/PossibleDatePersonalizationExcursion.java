package entity;

import java.io.Serializable;

import javax.persistence.*;

import entity.DatePersonalization;
import entity.Excursion;

import java.math.BigDecimal;


/**
 * The persistent class for the POSSIBLE_DATE_PERSONALIZATION_EXCURSION database table.
 * 
 */
@Entity
@EntityListeners({PossibleDatePersonalizationExcursionListener.class})
@Table(name="POSSIBLE_DATE_PERSONALIZATION_EXCURSION")
@NamedQuery(name="PossibleDatePersonalizationExcursion.findAll", query="SELECT p FROM PossibleDatePersonalizationExcursion p")
public class PossibleDatePersonalizationExcursion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PossibleDatePersonalizationExcursionPK id;

	private BigDecimal price;

	//bi-directional many-to-one association to DatePersonalization
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="DatePersonalizationId", nullable=false)
	private DatePersonalization datePersonalization;

	//bi-directional many-to-one association to Excursion
	@ManyToOne
	@JoinColumn(name="ExcursionId", nullable=false)
	private Excursion excursion;

	public PossibleDatePersonalizationExcursion() {
	}

	public PossibleDatePersonalizationExcursionPK getId() {
		return this.id;
	}

	public void setId(PossibleDatePersonalizationExcursionPK id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public DatePersonalization getDatePersonalization() {
		return this.datePersonalization;
	}

	public void setDatePersonalization(DatePersonalization datePersonalization) {
		this.datePersonalization = datePersonalization;
	}

	public Excursion getExcursion() {
		return this.excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

}