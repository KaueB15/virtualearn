package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CourseDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }
    public void registerCourse(Course course){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(course);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Course> getAllCourses(){
        List<Course> courses = null;
        try{
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Course> query = getEmc().getEntityManager()
                    .createQuery("SELECT c FROM Course c", Course.class);
            courses = query.getResultList();
        }finally {
            getEmc().getEntityManager().close();
        }
        return courses;
    }
    public void updateCourse(Course course){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(course);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Course getCourseByID(UUID id){
        return getEmc().getEntityManager().find(Course.class, id);
    }
    public void deleteCourseById(UUID id){
        try{
            Course course = getCourseByID(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(course);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
