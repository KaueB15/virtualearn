package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Registration;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void registerRegistration(Registration registration) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(registration);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Registration> getAllRegistrations() {
        getEmc().getEntityManager().getTransaction().begin();
        TypedQuery<Registration> query = getEmc().getEntityManager()
                .createQuery("SELECT r FROM Registration r", Registration.class);
        return query.getResultList();
    }

    public void updateRegistration(Registration registration) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(registration);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Registration getRegistrationById(UUID id) {
        return getEmc().getEntityManager().find(Registration.class, id);
    }

    public void deleteRegistrationById(UUID id) {
        Registration registration = getRegistrationById(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(registration);
        getEmc().getEntityManager().getTransaction().commit();
    }
}
