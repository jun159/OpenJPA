package coolshot;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    @Id
    private int id;    
    private int score;
    private String comment;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reviewedDate;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
    
    public Review() {}
    public Review(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }   
    
    public String toString() {
        return movie.getTitle() + ": (" + score + ") - " + comment;
    }
}
