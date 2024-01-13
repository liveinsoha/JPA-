package org.example;

import org.example.domain.*;
import org.example.domain.embeddable.Address;
import org.example.domain.item.Album;
import org.example.domain.item.Book;
import org.example.domain.item.Item;
import org.example.domain.item.Movie;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /**
         * 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
         * • 엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).
         * • JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Book book = new Book("QQQ", "WWW", 1000);
            Album album = new Album("AAA", 2000);
            Movie movie = new Movie("ZZZ", "XXX", 3000);
            em.persist(book);
            em.persist(album);
            em.persist(movie);

            em.flush();
            em.clear();

            String jpql = "delete from Item i where i.price <= :price";
            int lines = em.createQuery(jpql)
                    .setParameter("price", 1000)
                    .executeUpdate();

            System.out.println("lines = " + lines);



          /*  Item item1 =  em.find(Item.class, book.getId());
            Item item2 =  em.find(Item.class, album.getId());
            Item item3 =  em.find(Item.class, movie.getId());

            sout*/

           /* item1.setPrice(10000);
            item2.setPrice(20000);
            item3.setPrice(30000);*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}