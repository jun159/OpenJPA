package coolshot;

import javax.persistence.*;
import java.util.Collection;
import java.util.Scanner;

public class ReviewTest {

    public static void main(String[] args) {
        // refer to the persistent unit name defined in persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoolShotPU");

        EntityManager em = emf.createEntityManager();

        MovieService movieService = new MovieService(em);
        ReviewService reviewService = new ReviewService(em);

        // find movie 14
        Movie mov = movieService.findMovie(14);

        // display all reviews for this movie		
        System.out.println("All reviews for " + mov.getTitle() + ":");
        System.out.println("---------------------------------------");
        Collection<Review> reviews = reviewService.findAllReviews(mov);
        for (Review r : reviews) {
            System.out.println(r.toString());
        }

        // add a review for movie with id 14		      
        em.getTransaction().begin();
        Review rev = reviewService.createReview(8, 4, "Theme song is wonderful", mov);
        em.getTransaction().commit();
        System.out.println("Persisted " + rev.toString());
        System.out.println();

        // display all reviews for this movie		
        System.out.println("All reviews for " + mov.getTitle() + ":");
        System.out.println("---------------------------------------");
        reviews = reviewService.findAllReviews(mov);
        for (Review r : reviews) {
            System.out.println(r.toString());
        }
        System.out.println();

        // delete this review
        em.getTransaction().begin();
        reviewService.removeReview(rev.getId());
        em.getTransaction().commit();
        System.out.println("Deleted " + rev.toString());
        System.out.println();

        // display all reviews in the database
        System.out.println("All reviews in our database:");
        System.out.println("---------------------------------------");
        reviews = reviewService.findAllReviews();
        for (Review r : reviews) {
            System.out.println(r.toString());
        }
        System.out.println();

        // test find reviews by title
        System.out.println("Enter a title to search for reviews: ");
        Scanner keyboard = new Scanner(System.in);
        String userTitle = keyboard.nextLine();
        System.out.println("---------------------------------------");
        reviews = reviewService.findReviewsByTitle(userTitle);
        for (Review r : reviews) {
            System.out.println(r.toString());
        }
        System.out.println();

        em.close();
        emf.close();
    }
}
