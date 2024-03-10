package de.hitohitonika.data.daos;

import de.hitohitonika.data.entities.Grade;
import org.hibernate.SessionFactory;

public class GradeDAO {

    private final SessionFactory sessionFactory;

    public GradeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void saveGrade(Grade grade) {
        sessionFactory.inTransaction(session -> {
            session.persist(grade);
        });
    }
}
