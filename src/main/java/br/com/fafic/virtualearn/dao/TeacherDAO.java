package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Teacher;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class TeacherDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void registerTeacher(Teacher teacher) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(teacher);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Teacher> getAllTeacher() {
        TypedQuery<Teacher> query = getEmc().getEntityManager()
                .createQuery("SELECT t FROM Teacher t", Teacher.class);
        return query.getResultList();
    }

    public void updateTeacher(Teacher teacher) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(teacher);
        getEmc().getEntityManager().getTransaction().commit();
    }


    public Teacher getTeacherByID(UUID id) {
        return getEmc().getEntityManager().find(Teacher.class, id);
    }

    public Teacher findByteacher(String teacher) {
        TypedQuery<Teacher> query = getEmc().getEntityManager()
                .createQuery("SELECT t FROM Teacher t WHERE t.name = :username", Teacher.class);
        query.setParameter("username", teacher);
        List<Teacher> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void deleteTeacherById(UUID id) {
        Teacher teacher = getTeacherByID(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(teacher);
        getEmc().getEntityManager().getTransaction().commit();
    }
}
