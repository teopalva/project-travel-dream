package entity;

import java.io.Serializable;

import javax.persistence.*;

import entity.ClassPersonalization;
import entity.Hotel;

import java.math.BigDecimal;


/**
 * The persistent class for the POSSIBLE_CLASS_PERSONALIZATION_HOTEL database table.
 * 
 */
@Entity
@EntityListeners({PossibleClassPersonalizationHotelListener.class})
@Table(name="POSSIBLE_CLASS_PERSONALIZATION_HOTEL")
@NamedQuery(name="PossibleClassPersonalizationHotel.findAll", query="SELECT p FROM PossibleClassPersonalizationHotel p")
public class PossibleClassPersonalizationHotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PossibleClassPersonalizationHotelPK id;

	private BigDecimal price;

	//bi-directional many-to-one association to ClassPersonalization
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ClassPersonalizationId", nullable=false)
	private ClassPersonalization classPersonalization;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="HotelId", nullable=false)
	private Hotel hotel;

	public PossibleClassPersonalizationHotel() {
	}

	public PossibleClassPersonalizationHotelPK getId() {
		return this.id;
	}

	public void setId(PossibleClassPersonalizationHotelPK id) {
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

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}