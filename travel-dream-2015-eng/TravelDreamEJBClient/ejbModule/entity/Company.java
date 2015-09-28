package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	//bi-directional many-to-one association to Excursion
	@OneToMany(mappedBy="company")
	private List<Excursion> excursions;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="company")
	private List<Flight> flights;

	//bi-directional many-to-one association to Hotel
	@OneToMany(mappedBy="company")
	private List<Hotel> hotels;

	public Company() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Excursion> getExcursions() {
		return this.excursions;
	}

	public void setExcursions(List<Excursion> excursions) {
		this.excursions = excursions;
	}

	public Excursion addExcursion(Excursion excursion) {
		getExcursions().add(excursion);
		excursion.setCompany(this);

		return excursion;
	}

	public Excursion removeExcursion(Excursion excursion) {
		getExcursions().remove(excursion);
		excursion.setCompany(null);

		return excursion;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight addFlight(Flight flight) {
		getFlights().add(flight);
		flight.setCompany(this);

		return flight;
	}

	public Flight removeFlight(Flight flight) {
		getFlights().remove(flight);
		flight.setCompany(null);

		return flight;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Hotel addHotel(Hotel hotel) {
		getHotels().add(hotel);
		hotel.setCompany(this);

		return hotel;
	}

	public Hotel removeHotel(Hotel hotel) {
		getHotels().remove(hotel);
		hotel.setCompany(null);

		return hotel;
	}

}