package br.com.fafic.virtualearn.dao;

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
    public void registerTeacher(Teacher teacher){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(teacher);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Teacher> getAllTeacher(){
        List<Teacher> teachers = null;
        try{
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Teacher> query = getEmc().getEntityManager()
                    .createQuery("SELECT t FROM Teacher t", Teacher.class);
            teachers = query.getResultList();
        }finally {
            getEmc().getEntityManager().close();
        }
        return teachers;
    }
    public void updateTeacher(Teacher teacher){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(teacher);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Teacher getByID(UUID id){
        return getEmc().getEntityManager().find(Teacher.class, id);
    }
    public void deleteById(UUID id){
        try{
            Teacher teacher = getByID(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(teacher);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
