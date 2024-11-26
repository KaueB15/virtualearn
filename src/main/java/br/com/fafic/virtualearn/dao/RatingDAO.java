package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class RatingDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }
    public void registerRating(Rating rating){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(rating);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Rating> getAllRating(){
        List<Rating> ratings = null;
        try {
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Rating> query = getEmc().getEntityManager()
                    .createQuery("SELECT r FROM Rating r", Rating.class);
            ratings = query.getResultList();
        }finally {
            getEmc().getEntityManager().close();
        }
        return ratings;
    }
    public void updateRating(Rating rating){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(rating);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Rating getRatingById(UUID id){
        return getEmc().getEntityManager().find(Rating.class, id);
    }
    public void deleteRatingById(UUID id){
        try {
            Rating rating = getRatingById(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(rating);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
