package de.hitohitonika.data.daos;

import de.hitohitonika.data.entities.Student;
import org.hibernate.SessionFactory;

public class StudentDAO {
    private final SessionFactory sessionFactory;

    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveStudent(Student student) {
        sessionFactory.inTransaction(session -> {
            session.persist(student);
        });
    }


}

