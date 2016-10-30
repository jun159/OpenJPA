/**
 * Name   1 : Luah Bao Jun
 * Matric 1 : A0126258A
 *
 * Name   2 : Varunica
 * Matric 2 : A0117057J
 */

package coolshot;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

public class ReviewService {
    private static final String SELECT_ALL_REVIEWS = "SELECT r FROM Review r";
    private static final String SELECT_REVIEWS_MOVIE = "SELECT r FROM Review r WHERE r.movie.id = :movieId";
    private static final String KEY_MOVIEID = "movieId";
    
    protected EntityManager em;
    private Review review;
    private Query query;

    public ReviewService(EntityManager em) {
        this.em = em;
    }

    public Review createReview(int id, int score, String comment, Movie mov) {
        review = new Review(id);
        review.setScore(score);
        review.setComment(comment);
        review.setMovie(mov);
        review.setReviewedDate(new Date());
        em.persist(review);
        
        return review;  
    }

    public Review removeReview(int id) {
        review = findReview(id);
        if (review != null) {
            em.remove(review);
        }
        
        return review;
    }

    public Review findReview(int id) {
        review = em.find(Review.class, id);
        
        return review;
    }

    public Collection<Review> findAllReviews() {
        query = em.createQuery(SELECT_ALL_REVIEWS);
        
        return (Collection<Review>)query.getResultList(); 
    }
    
    public Collection<Review> findAllReviews(Movie mov) {
        query = em.createQuery(SELECT_REVIEWS_MOVIE).setParameter(KEY_MOVIEID, mov.getId());
        
        return (Collection<Review>)query.getResultList(); 
    }
    
    public Collection<Review> findReviewsByTitle(String title) {
        query = em.createQuery("SELECT r FROM Review r WHERE r.movie.title = '" + title + "'");
        
        return (Collection<Review>)query.getResultList();       
    }
}
