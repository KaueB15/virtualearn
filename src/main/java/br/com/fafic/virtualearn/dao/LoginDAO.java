package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void enterLogin(Login login) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(login);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Login> getAllLogins() {
        getEmc().getEntityManager().getTransaction().begin();
        TypedQuery<Login> query = getEmc().getEntityManager()
                .createQuery("SELECT l FROM Login l", Login.class);
        return query.getResultList();
    }

    public void updateLogin(Login login) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(login);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Login getloginById(UUID id) {
        return getEmc().getEntityManager().find(Login.class, id);
    }

    public void deleteLoginById(UUID id) {
        Login login = getloginById(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(id);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Login checkLogin(String username, String password) {
        getEmc().getEntityManager().getTransaction().begin();
        TypedQuery<Login> query = getEmc().getEntityManager()
                .createQuery("SELECT COUNT(l) FROM Login l WHERE l.login = :username AND l.password = :password", Login.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Login login = query.getSingleResult();
        getEmc().getEntityManager().getTransaction().commit();
        return login;
    }

    public boolean checkLoginn(String username, String password) {
        //Long é o tipo de dado retornado pela consulta, representando a contagem de registros
        //que atendem aos critérios fornecidos.
        TypedQuery<Long> query = getEmc().getEntityManager()
                .createQuery("SELECT COUNT(l) FROM Login l WHERE l.login = :username AND l.password = :password", Long.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Long count = query.getSingleResult();
        return count > 0;
    }

    public Login findByLogin(String login) {
        TypedQuery<Login> query = getEmc().getEntityManager()
                .createQuery("SELECT l FROM Login l WHERE l.login = :username", Login.class);
        query.setParameter("username", login);
        List<Login> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void deleteLogin(Login login) {
        Login foundLogin = findByLogin(login.getLogin());
        if (foundLogin != null) {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(foundLogin);
            getEmc().getEntityManager().getTransaction().commit();
        }
    }
}
