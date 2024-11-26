package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Registration;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class RegistrationDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }
    public void registerRegistration(Registration registration){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(registration);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Registration> registrations(){
        List<Registration> registrations = null;
        try {
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Registration> query = getEmc().getEntityManager()
                    .createQuery("SELECT r FROM Registration r", Registration.class);
            registrations = query.getResultList();
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
        return registrations;
    }
    public void updateRegistration(Registration registration){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(registration);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Registration getRegistrationById(UUID id){
        return getEmc().getEntityManager().find(Registration.class, id);
    }
    public void deleteRegistrationById(UUID id){
        try {
            Registration registration = getRegistrationById(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(registration);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
