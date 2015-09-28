package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Airport;
import entity.City;
import entity.ClassPersonalization;
import entity.Company;
import entity.DatePersonalization;
import entity.Excursion;
import entity.Flight;
import entity.Hotel;
import entity.PersonalizedProductExcursion;
import entity.PersonalizedProductFlight;
import entity.PersonalizedProductHotel;
import entity.PossibleClassPersonalizationFlight;
import entity.PossibleClassPersonalizationHotel;
import entity.PossibleDatePersonalizationExcursion;
import entity.PossibleDatePersonalizationFlight;
import entity.User;

public class TestDB {
	
	@PersistenceUnit EntityManagerFactory emf;
	protected EntityManager em;
	
	private City city;
	private Airport airport1;
	private Airport airport2;
	private Company company;
	private DatePersonalization datePersonalization;
	private ClassPersonalization classPersonalization;
	private Flight flight1;
	private Flight flight2;
	private Hotel hotel;
	private Excursion excursion;
	
	private entity.Package _package;
	
	private PersonalizedProductFlight personalizedProductFlight;
	private PersonalizedProductHotel personalizedProductHotel;
	private PersonalizedProductExcursion personalizedProductExcursion;
	
    @Before
    public void setUp() {
    	Map<String, String> properties = new HashMap<String, String>();
    	properties.put("eclipselink.persistencexml", "META-INF/persistence-test.xml");

    	emf = Persistence.createEntityManagerFactory("TravelDreamDB", properties);
    	em = emf.createEntityManager();
        em.getTransaction().begin();
    }

	@Test
	public void testUserTDC() {
		
		//Insert
		User user = new User();
		user.setFirstName("Gianluca");
		user.setLastName("Venturini");
		user.setMail("gianluca.91@gmail.com");
		user.setPassword("12345");
		
		try {
			em.persist(user);
			//Retrieve
			User user2 = em.find(User.class, "gianluca.91@gmail.com");
			if(user2 == null)
				fail("user2 Null");
			assertEquals(user.getFirstName(),user2.getFirstName());
			assertEquals(user.getLastName(),user2.getLastName());
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
		
	}
	
	@Test
	public void testUserTDF() {
		
		//Insert
		User user = new User();
		user.setMail("tdf@gmail.com");
		user.setPassword("12345");
		
		try {
			em.persist(user);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
		
	}
	
	@Test
	public void testCity() {
		city = new City();
		city.setName("Silent Hill");
		try {
			em.persist(city);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testAirport() {
		
		testCity();
		
		airport1 = new Airport();
		airport1.setCity(city);
		airport1.setId("SOS");
		airport2 = new Airport();
		airport2.setCity(city);
		airport2.setId("TST");
		try {
			em.persist(airport1);
			em.persist(airport2);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testCompany() {
		Company retreivedCompany = em.find(Company.class, "Coralline Exploration");
		if(retreivedCompany == null) {
			company = new Company();
			company.setName("Coralline Exploration");
		}
		try {
			em.persist(company);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testDatePersonalization() {
		datePersonalization = new DatePersonalization();
		datePersonalization.setDate(new Date(2014,01,20));
		datePersonalization.setDuration(20);
		
		try {
			em.persist(datePersonalization);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testClassPersonalization() {
		classPersonalization = new ClassPersonalization();
		classPersonalization.setClass_("Economy");
		
		try {
			em.persist(classPersonalization);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testFlight() {
		
		testAirport();
		testCompany();
		
		flight1 = new Flight();
		flight2 = new Flight();
		
		flight1.setAirportDeparture(airport1);
		flight1.setAirportArrival(airport2);
		flight1.setCompany(company);
		flight1.setName("UniqueFlightNameForTestingPurpose");
		flight2.setAirportDeparture(airport2);
		flight2.setAirportArrival(airport1);
		flight2.setCompany(company);
		flight2.setName("flight2");
		try {
			em.persist(flight1);
			em.persist(flight2);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testHotel() {
		testCity();
		testCompany();
		
		hotel = new Hotel();
		hotel.setCompany(company);
		hotel.setName("Hotel di test");
		hotel.setCity(city);
		hotel.setStars(5);
		
		try {
			em.persist(hotel);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testExcursion() {
		testCity();
		testCompany();
		
		excursion = new Excursion();
		excursion.setCompany(company);
		excursion.setName("Gita alle barriere coralline di test");
		excursion.setCity(city);
		
		try {
			em.persist(excursion);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testAddPersonalizationToFlight() {
		testFlight();
		testDatePersonalization();
		testClassPersonalization();
		
		try {
			em.merge(flight1);
			em.merge(datePersonalization);
			em.merge(classPersonalization);
			em.flush();
			
			PossibleDatePersonalizationFlight possibleDataPersonalizationFlight = new PossibleDatePersonalizationFlight();
			possibleDataPersonalizationFlight.setFlight(flight1);
			flight1.addPossibleDatePersonalizationFlight(possibleDataPersonalizationFlight);
			possibleDataPersonalizationFlight.setPrice(new BigDecimal(10));
			possibleDataPersonalizationFlight.setDatePersonalization(datePersonalization);
			datePersonalization.addPossibleDatePersonalizationFlight(possibleDataPersonalizationFlight);
			
			PossibleClassPersonalizationFlight possibleClassPersonalizationFlight = new PossibleClassPersonalizationFlight();
			possibleClassPersonalizationFlight.setFlight(flight1);
			flight1.addPossibleClassPersonalizationFlight(possibleClassPersonalizationFlight);
			possibleClassPersonalizationFlight.setPrice(new BigDecimal(10));
			possibleClassPersonalizationFlight.setClassPersonalization(classPersonalization);
			classPersonalization.addPossibleClassPersonalizationFlight(possibleClassPersonalizationFlight);
			
			em.persist(possibleDataPersonalizationFlight);
			em.persist(possibleClassPersonalizationFlight);
			em.merge(flight1);
			em.merge(classPersonalization);
			em.merge(datePersonalization);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testRetreivePersonalizationFromFlight() {
		testAddPersonalizationToFlight();
		
		try {
			em.flush();
			Flight flight = (Flight)em.createQuery("select f from Flight f join fetch f.classPersonalizations where f.name='UniqueFlightNameForTestingPurpose' ", Flight.class).getResultList().get(0);
			em.flush();
			if(flight.getId() != flight1.getId()) {
				fail("The flight id doesn't match the original one");
			}
			//Se tentate di tirare su i parametri senza usare JPA esplode la query
			//ClassPersonalization cp = (ClassPersonalization)em.createNativeQuery("SELECT C.* FROM CLASS_PERSONALIZATION C, POSSIBLE_CLASS_PERSONALIZATION_FLIGHT P, FLIGHT F WHERE C.Id = P.ClassPersonalizationId AND P.FlightId = F.Id AND F.Name='UniqueFlightNameForTestingPurpose'",ClassPersonalization.class).getResultList().get(0);
			PossibleClassPersonalizationFlight pc = flight.getPossibleClassPersonalizationFlights().get(0);
			ClassPersonalization cp = pc.getClassPersonalization();
			
			em.flush();
			if(cp.getId() != classPersonalization.getId()) {
				fail("The classPersonalization id doesn't match the original one");
			}
			
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't retreive into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testAddPersonalizationToExcursion() {
		testExcursion();
		testDatePersonalization();
		testClassPersonalization();
		
		try {
			em.merge(excursion);
			em.merge(datePersonalization);
			em.merge(classPersonalization);
			em.flush();
			
			PossibleDatePersonalizationExcursion possibleDataPersonalizationExcursion = new PossibleDatePersonalizationExcursion();
			possibleDataPersonalizationExcursion.setExcursion(excursion);
			excursion.addPossibleDatePersonalizationExcursion(possibleDataPersonalizationExcursion);
			possibleDataPersonalizationExcursion.setPrice(new BigDecimal(10));
			possibleDataPersonalizationExcursion.setDatePersonalization(datePersonalization);
			datePersonalization.addPossibleDatePersonalizationExcursion(possibleDataPersonalizationExcursion);
			
			em.persist(possibleDataPersonalizationExcursion);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testAddPersonalizationToHotel() {
		testHotel();
		testDatePersonalization();
		testClassPersonalization();
		
		try {
			em.merge(hotel);
			em.merge(datePersonalization);
			em.merge(classPersonalization);
			em.flush();
			
			PossibleClassPersonalizationHotel possibleClassPersonalizationHotel = new PossibleClassPersonalizationHotel();
			possibleClassPersonalizationHotel.setHotel(hotel);
			hotel.addPossibleClassPersonalizationHotel(possibleClassPersonalizationHotel);
			possibleClassPersonalizationHotel.setPrice(new BigDecimal(10));
			possibleClassPersonalizationHotel.setClassPersonalization(classPersonalization);
			classPersonalization.addPossibleClassPersonalizationHotel(possibleClassPersonalizationHotel);
			
			em.persist(possibleClassPersonalizationHotel);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testCreatePackage() {
		
		try {
			_package = new entity.Package();
			
			_package.setName("Pacchetto di test");
			_package.setNumPeople(2);
			_package.setReduction(0.1);
			
			em.persist(_package);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testCreatePersonalizedProductFlight() {
		testCreatePackage();
		testAddPersonalizationToFlight();
		
		try {
			em.merge(flight1);
			
			PossibleClassPersonalizationFlight possibleClassPersonalizationFlight = flight1.getPossibleClassPersonalizationFlights().get(0);
			PossibleDatePersonalizationFlight possibleDatePersonalizationFlight = flight1.getPossibleDatePersonalizationFlights().get(0);
			
			personalizedProductFlight = new PersonalizedProductFlight();
			personalizedProductFlight.setFlight(flight1);
			personalizedProductFlight.setClassPersonalization(possibleClassPersonalizationFlight.getClassPersonalization());
			personalizedProductFlight.setDatePersonalization(possibleDatePersonalizationFlight.getDatePersonalization());
			personalizedProductFlight.setPackage(_package);
			
			em.persist(personalizedProductFlight);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testCreatePersonalizedProductHotel() {
		testCreatePackage();
		testAddPersonalizationToHotel();
		
		try {
			em.merge(hotel);
			
			PossibleClassPersonalizationHotel possibleClassPersonalizationHotel = hotel.getPossibleClassPersonalizationHotels().get(0);
			
			personalizedProductHotel = new PersonalizedProductHotel();
			personalizedProductHotel.setHotel(hotel);
			personalizedProductHotel.setClassPersonalization(possibleClassPersonalizationHotel.getClassPersonalization());
			personalizedProductHotel.set_package(_package);
			
			em.persist(personalizedProductHotel);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
	
	@Test
	public void testCreatePersonalizedProductExcursion() {
		testCreatePackage();
		testAddPersonalizationToExcursion();
		
		try {
			em.merge(excursion);
			
			PossibleDatePersonalizationExcursion possibleDatePersonalizationExcursion = excursion.getPossibleDatePersonalizationExcursions().get(0);
			
			personalizedProductExcursion = new PersonalizedProductExcursion();
			personalizedProductExcursion.setExcursion(excursion);
			personalizedProductExcursion.setDatePersonalization(possibleDatePersonalizationExcursion.getDatePersonalization());
			personalizedProductExcursion.setPackage(_package);
			
			em.persist(personalizedProductExcursion);
		} catch(Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
			fail("Can't insert into DB");
		}
		assertTrue(true);
	}
		
	@After
	 public void closeTransaction() {
				
		//Delete all  results
		em.getTransaction().rollback();
	    em.close();
	    emf.close();
	 }

}

