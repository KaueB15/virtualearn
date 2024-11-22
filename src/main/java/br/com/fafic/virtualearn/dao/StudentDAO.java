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
    public void registerStudent(Student student){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(student);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Student> getAllStudents(){
        List<Student> students = null;
        try{
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Student> query = getEmc().getEntityManager()
                    .createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();
        }finally {
            getEmc().getEntityManager().close();
        }
        return students;
    }
    public void updateStudent(Student student){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(student);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Student getByID(UUID id){
            return getEmc().getEntityManager().find(Student.class, id);
    }
    public void deleteById(UUID id){
        try{
            Student student = getByID(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(student);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
