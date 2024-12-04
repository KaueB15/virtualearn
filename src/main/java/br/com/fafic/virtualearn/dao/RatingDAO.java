package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class RatingDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void registerRating(Rating rating) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(rating);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Rating> getAllRating() {
        TypedQuery<Rating> query = getEmc().getEntityManager()
                .createQuery("SELECT r FROM Rating r", Rating.class);
        return query.getResultList();
    }

    public void updateRating(Rating rating) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(rating);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Rating getRatingById(UUID id) {
        return getEmc().getEntityManager().find(Rating.class, id);
    }

    public void deleteRatingById(UUID id) {
        Rating rating = getRatingById(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(rating);
        getEmc().getEntityManager().getTransaction().commit();
    }
}
