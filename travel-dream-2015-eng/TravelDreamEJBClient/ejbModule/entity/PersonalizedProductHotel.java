package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PERSONALIZED_PRODUCT_HOTEL database table.
 * 
 */
@Entity
@Table(name="PERSONALIZED_PRODUCT_HOTEL")
@NamedQuery(name="PersonalizedProductHotel.findAll", query="SELECT p FROM PersonalizedProductHotel p")
public class PersonalizedProductHotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to ClassPersonalization
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="ClassPersonalization")
	private ClassPersonalization classPersonalization;

	//bi-directional many-to-one association to Hotel
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="HotelId")
	private Hotel hotel;

	//bi-directional many-to-one association to Package
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PackageId")
	private Package _package;

	public PersonalizedProductHotel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Package get_package() {
		return this._package;
	}

	public void set_package(Package _package) {
		this._package = _package;
	}

}