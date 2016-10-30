package coolshot;

import javax.persistence.*;

public class ReviewTest2 {
	public static void main(String[] args) {
		// refer to the persistent unit name defined in persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoolShotPU");
		
		EntityManager em = emf.createEntityManager();        
        MovieService movieService = new MovieService(em);
		
        // find movie 14
        Movie mov = movieService.findMovie(14);
        
        // display all reviews for this movie		
        System.out.println("All reviews for " + mov.getTitle() + ":");
        System.out.println("---------------------------------------");		
		for (Review r : mov.getReviews()) {
			System.out.println(r.toString());
		}
        System.out.println();
        
        // add a review for movie with id 14        
		em.getTransaction().begin();		
        Review rev = new Review(8);
        rev.setScore(4);
        rev.setComment("Theme song is wonderful");
        rev.setMovie(mov);
        mov.getReviews().add(rev);
        em.getTransaction().commit();        
		System.out.println("Persisted " + rev.toString());
        System.out.println();
        
        // since movie 14 has already managed by the persist context, 
        // no retrieval from the database occurs.
        // here we call refresh() to explicitly retrieve 
        // the movie (and the review collection) from database
        em.refresh(mov);
        
        System.out.println("All reviews for " + mov.getTitle() + ":");
        System.out.println("---------------------------------------");		
		for (Review r : mov.getReviews()) {
			System.out.println(r.toString());
		}
        System.out.println();
        
        // delete this review by removing rev from the collection
        em.getTransaction().begin();
        mov.getReviews().remove(rev);
        em.getTransaction().commit();
        System.out.println("Removed " + rev.toString());
                
        em.refresh(mov);
        
        System.out.println("All reviews for " + mov.getTitle() + ":");
        System.out.println("---------------------------------------");		
		for (Review r : mov.getReviews()) {
			System.out.println(r.toString());
		}
        System.out.println();
        
		em.close();
		emf.close();
	}
}
