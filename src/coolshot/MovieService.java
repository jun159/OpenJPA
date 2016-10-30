package coolshot;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

public class MovieService {
	protected EntityManager em;

	public MovieService(EntityManager em) {
		this.em = em;
	}

	public Movie createMovie(int id, String title, String summary, String imgUrl) {
		Movie mov = new Movie(id);
		mov.setTitle(title);
		mov.setSummary(summary);
		mov.setImgUrl(imgUrl);
		mov.setUpdatedDate(new Date());

		em.persist(mov);

		return mov;
	}

	public Movie removeMovie(int id) {
		Movie mov = findMovie(id);
		if (mov != null) {
			em.remove(mov);
		}
		return mov;
	}

	public Movie findMovie(int id) {
		return em.find(Movie.class, id);
	}

	public Collection<Movie> findAllMovies() {
		Query query = em.createQuery("SELECT m FROM Movie m");
		return (Collection<Movie>)query.getResultList();
	}
}
