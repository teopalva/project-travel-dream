package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CLASS_PERSONALIZATION database table.
 * 
 */
@Entity
@Table(name="CLASS_PERSONALIZATION")
@NamedQuery(name="ClassPersonalization.findAll", query="SELECT c FROM ClassPersonalization c")
public class ClassPersonalization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Class")
	private String class_;

	//bi-directional many-to-many association to Flight
	@ManyToMany
	@JoinTable(
		name="POSSIBLE_CLASS_PERSONALIZATION_FLIGHT"
		, joinColumns={
			@JoinColumn(name="ClassPersonalizationId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FlightId")
			}
		)
	private List<Flight> flights;

	//bi-directional many-to-many association to Hotel
	@ManyToMany
	@JoinTable(
		name="POSSIBLE_CLASS_PERSONALIZATION_HOTEL"
		, joinColumns={
			@JoinColumn(name="ClassPersonalizationId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="HotelId")
			}
		)
	private List<Hotel> hotels;

	//bi-directional many-to-one association to PersonalizedProductFlight
	@OneToMany(mappedBy="classPersonalization")
	private List<PersonalizedProductFlight> personalizedProductFlights;

	//bi-directional many-to-one association to PersonalizedProductHotel
	@OneToMany(mappedBy="classPersonalization")
	private List<PersonalizedProductHotel> personalizedProductHotels;

	//bi-directional many-to-one association to PossibleClassPersonalizationFlight
	@OneToMany(mappedBy="classPersonalization")
	private List<PossibleClassPersonalizationFlight> possibleClassPersonalizationFlights;

	//bi-directional many-to-one association to PossibleClassPersonalizationHotel
	@OneToMany(mappedBy="classPersonalization")
	private List<PossibleClassPersonalizationHotel> possibleClassPersonalizationHotels;

	public ClassPersonalization() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<PersonalizedProductFlight> getPersonalizedProductFlights() {
		return this.personalizedProductFlights;
	}

	public void setPersonalizedProductFlights(List<PersonalizedProductFlight> personalizedProductFlights) {
		this.personalizedProductFlights = personalizedProductFlights;
	}

	public PersonalizedProductFlight addPersonalizedProductFlight(PersonalizedProductFlight personalizedProductFlight) {
		getPersonalizedProductFlights().add(personalizedProductFlight);
		personalizedProductFlight.setClassPersonalization(this);

		return personalizedProductFlight;
	}

	public PersonalizedProductFlight removePersonalizedProductFlight(PersonalizedProductFlight personalizedProductFlight) {
		getPersonalizedProductFlights().remove(personalizedProductFlight);
		personalizedProductFlight.setClassPersonalization(null);

		return personalizedProductFlight;
	}

	public List<PersonalizedProductHotel> getPersonalizedProductHotels() {
		return this.personalizedProductHotels;
	}

	public void setPersonalizedProductHotels(List<PersonalizedProductHotel> personalizedProductHotels) {
		this.personalizedProductHotels = personalizedProductHotels;
	}

	public PersonalizedProductHotel addPersonalizedProductHotel(PersonalizedProductHotel personalizedProductHotel) {
		getPersonalizedProductHotels().add(personalizedProductHotel);
		personalizedProductHotel.setClassPersonalization(this);

		return personalizedProductHotel;
	}

	public PersonalizedProductHotel removePersonalizedProductHotel(PersonalizedProductHotel personalizedProductHotel) {
		getPersonalizedProductHotels().remove(personalizedProductHotel);
		personalizedProductHotel.setClassPersonalization(null);

		return personalizedProductHotel;
	}

	public List<PossibleClassPersonalizationFlight> getPossibleClassPersonalizationFlights() {
		return this.possibleClassPersonalizationFlights;
	}

	public void setPossibleClassPersonalizationFlights(List<PossibleClassPersonalizationFlight> possibleClassPersonalizationFlights) {
		this.possibleClassPersonalizationFlights = possibleClassPersonalizationFlights;
	}

	public PossibleClassPersonalizationFlight addPossibleClassPersonalizationFlight(PossibleClassPersonalizationFlight possibleClassPersonalizationFlight) {
		getPossibleClassPersonalizationFlights().add(possibleClassPersonalizationFlight);
		possibleClassPersonalizationFlight.setClassPersonalization(this);

		return possibleClassPersonalizationFlight;
	}

	public PossibleClassPersonalizationFlight removePossibleClassPersonalizationFlight(PossibleClassPersonalizationFlight possibleClassPersonalizationFlight) {
		getPossibleClassPersonalizationFlights().remove(possibleClassPersonalizationFlight);
		possibleClassPersonalizationFlight.setClassPersonalization(null);

		return possibleClassPersonalizationFlight;
	}

	public List<PossibleClassPersonalizationHotel> getPossibleClassPersonalizationHotels() {
		return this.possibleClassPersonalizationHotels;
	}

	public void setPossibleClassPersonalizationHotels(List<PossibleClassPersonalizationHotel> possibleClassPersonalizationHotels) {
		this.possibleClassPersonalizationHotels = possibleClassPersonalizationHotels;
	}

	public PossibleClassPersonalizationHotel addPossibleClassPersonalizationHotel(PossibleClassPersonalizationHotel possibleClassPersonalizationHotel) {
		getPossibleClassPersonalizationHotels().add(possibleClassPersonalizationHotel);
		possibleClassPersonalizationHotel.setClassPersonalization(this);

		return possibleClassPersonalizationHotel;
	}

	public PossibleClassPersonalizationHotel removePossibleClassPersonalizationHotel(PossibleClassPersonalizationHotel possibleClassPersonalizationHotel) {
		getPossibleClassPersonalizationHotels().remove(possibleClassPersonalizationHotel);
		possibleClassPersonalizationHotel.setClassPersonalization(null);

		return possibleClassPersonalizationHotel;
	}

}