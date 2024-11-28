package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Student;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class StudentDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void registerStudent(Student student) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(student);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Student> getAllStudents() {
        getEmc().getEntityManager().getTransaction().begin();
        TypedQuery<Student> query = getEmc().getEntityManager()
                .createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    public void updateStudent(Student student) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(student);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Student getStudentByID(UUID id) {
        return getEmc().getEntityManager().find(Student.class, id);
    }

    public void deleteStudentById(UUID id) {
        Student student = getStudentByID(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(student);
        getEmc().getEntityManager().getTransaction().commit();
    }
}
