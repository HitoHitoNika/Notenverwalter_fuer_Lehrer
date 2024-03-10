package de.hitohitonika.data.daos;

import de.hitohitonika.data.entities.SchoolClass;
import org.hibernate.SessionFactory;

public class SchoolClassDAO {
    private final SessionFactory sessionFactory;

    public SchoolClassDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveSchoolClass(SchoolClass schoolClass) {
        sessionFactory.inTransaction(session -> {
            session.persist(schoolClass);
        });
    }



}
