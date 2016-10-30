/**
 * Name   1 : Luah Bao Jun
 * Matric 1 : A0126258A
 *
 * Name   2 : Varunica
 * Matric 2 : A0117057J
 */

package coolshot;

import javax.persistence.*;
import java.util.List;

public class QueryTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoolShotPU");
        EntityManager em = emf.createEntityManager();

        // count all reviews for each movie
        System.out.println("Review count: ");
        Query queryCount = em.createQuery("SELECT r.movie.title, COUNT(r) FROM Review r GROUP BY r.movie.title");
        List<Object[]> resultCount = queryCount.getResultList();
        for (int i = 0; i < resultCount.size(); i++) {
            String title = resultCount.get(i)[0].toString();
            String count = resultCount.get(i)[1].toString();
            System.out.println(title + ": " + count + " reviews");
        }
        System.out.println();

        // find all movies that has at least 3 reviews
        System.out.println("Movie with at least 3 reviews: ");  
        Query queryTop = em.createQuery("SELECT r.movie.title, COUNT(r) FROM Review r GROUP BY r.movie.title HAVING COUNT(r) >= 3 ");
        List<Object[]> resultTop = queryTop.getResultList();
        for (int i = 0; i < resultTop.size(); i++) {
            String title = resultTop.get(i)[0].toString();
            String count = resultTop.get(i)[1].toString();
            System.out.println(title + ": " + count + " reviews");
        }
        System.out.println();

        em.close();
        emf.close();
    }
}