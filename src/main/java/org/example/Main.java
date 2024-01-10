package org.example;

import org.example.domain.*;
import org.example.domain.item.Album;
import org.example.domain.item.Book;
import org.example.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            Parent parent = new Parent();
            parent.setName("JIM");

            Child child1 = new Child();
            child1.setName("aaa");

            Child child2 = new Child();
            child2.setName("bbb");

            parent.getChildren().add(child1);
            parent.getChildren().add(child2);
            child1.setParent(parent);
            child2.setParent(parent);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildren().remove(0);

            em.flush();
            em.clear();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}