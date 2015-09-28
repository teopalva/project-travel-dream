package entity;

import java.io.Serializable;

import javax.persistence.*;

import entity.ClassPersonalization;
import entity.Flight;

import java.math.BigDecimal;

// MODIFIED 0.1

/**
 * The persistent class for the POSSIBLE_CLASS_PERSONALIZATION_FLIGHT database table.
 * 
 */
@Entity
@EntityListeners({PossibleClassPersonalizationFlightListener.class})
@Table(name="POSSIBLE_CLASS_PERSONALIZATION_FLIGHT")
@NamedQuery(name="PossibleClassPersonalizationFlight.findAll", query="SELECT p FROM PossibleClassPersonalizationFlight p")
public class PossibleClassPersonalizationFlight implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PossibleClassPersonalizationFlightPK id;

	private BigDecimal price;

	//bi-directional many-to-one association to ClassPersonalization
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ClassPersonalizationId", nullable=false)
	private ClassPersonalization classPersonalization;

	//bi-directional many-to-one association to Flight
	@ManyToOne
	@JoinColumn(name="FlightId", nullable=false)
	private Flight flight;

	public PossibleClassPersonalizationFlight() {
	}

	public PossibleClassPersonalizationFlightPK getId() {
		return this.id;
	}

	public void setId(PossibleClassPersonalizationFlightPK id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ClassPersonalization getClassPersonalization() {
		return this.classPersonalization;
	}

	public void setClassPersonalization(ClassPersonalization classPersonalization) {
		this.classPersonalization = classPersonalization;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}