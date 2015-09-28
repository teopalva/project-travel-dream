package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AIRPORT database table.
 * 
 */
@Entity
@NamedQuery(name="Airport.findAll", query="SELECT a FROM Airport a")
public class Airport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CityId", nullable=false)
	private City city;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airportArrival")
	private List<Flight> flightsArrival;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airportDeparture")
	private List<Flight> flightsDeparture;

	public Airport() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Flight> getFlightsArrival() {
		return this.flightsArrival;
	}

	public void setFlightsArrival(List<Flight> flightsArrival) {
		this.flightsArrival = flightsArrival;
	}

	public Flight addFlightsArrival(Flight flightsArrival) {
		getFlightsArrival().add(flightsArrival);
		flightsArrival.setAirportArrival(this);

		return flightsArrival;
	}

	public Flight removeFlightsArrival(Flight flightsArrival) {
		getFlightsArrival().remove(flightsArrival);
		flightsArrival.setAirportArrival(null);

		return flightsArrival;
	}

	public List<Flight> getFlightsDeparture() {
		return this.flightsDeparture;
	}

	public void setFlightsDeparture(List<Flight> flightsDeparture) {
		this.flightsDeparture = flightsDeparture;
	}

	public Flight addFlightsDeparture(Flight flightsDeparture) {
		getFlightsDeparture().add(flightsDeparture);
		flightsDeparture.setAirportDeparture(this);

		return flightsDeparture;
	}

	public Flight removeFlightsDeparture(Flight flightsDeparture) {
		getFlightsDeparture().remove(flightsDeparture);
		flightsDeparture.setAirportDeparture(null);

		return flightsDeparture;
	}

}