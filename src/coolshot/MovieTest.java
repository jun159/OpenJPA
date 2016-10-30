package coolshot;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

public class MovieTest {

	public static void main(String[] args) {
		// refer to the persistent unit name defined in persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoolShotPU");
		
		EntityManager em = emf.createEntityManager();
		MovieService service = new MovieService(em);

		// create a movie
		em.getTransaction().begin();
		Movie mov = service.createMovie(1, "Castle in the Sky", "", "");
		em.getTransaction().commit();
		System.out.println("Persisted " + mov.toString());

		// find a movie
		mov = service.findMovie(1);
		if (mov != null) {
			System.out.println("Found " + mov.toString());
		} else {
			System.out.println("Movie not found");
		}

		// get all movies
		Collection<Movie> movies = service.findAllMovies();
		for (Movie m : movies) {
			System.out.println(m.toString());
		}

		// update a movie title        
		em.getTransaction().begin();
		mov = service.findMovie(1);
		if (mov != null) {
			mov.setTitle("The Wind Rises");		
            mov.setUpdatedDate(new Date());
		}
		em.getTransaction().commit();

		// remove 
		em.getTransaction().begin();
		mov = service.removeMovie(1);
		em.getTransaction().commit();
		System.out.println("Removed " + mov.toString());

		em.close();
		emf.close();
	}
}
