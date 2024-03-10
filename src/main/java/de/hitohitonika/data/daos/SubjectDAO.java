package de.hitohitonika.data.daos;

import de.hitohitonika.data.entities.Subject;
import org.hibernate.SessionFactory;

public class SubjectDAO {
    private final SessionFactory sessionFactory;

    public SubjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveSubject(Subject subject) {
        sessionFactory.inTransaction(session -> {
            session.persist(subject);
        });
    }

}
