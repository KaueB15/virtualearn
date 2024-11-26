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

    public void enterLogin(Login login){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(login);
            getEmc().getEntityManager().getTransaction().commit();
        } finally {
            getEmc().getEntityManager().close();
        }
    }

    public List<Login> getAllLogins(){
        List<Login> logins = null;
        try {
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Login> query = getEmc().getEntityManager()
                    .createQuery("SELECT l FROM Login l", Login.class);
            logins = query.getResultList();
        } finally {
            getEmc().getEntityManager().close();
        }
        return logins;
    }

    public void updateLogin(Login login){
        try{
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(login);
            getEmc().getEntityManager().getTransaction().commit();
        } finally {
            getEmc().getEntityManager().close();
        }
    }

    public Login getloginById(UUID id){
        return getEmc().getEntityManager().find(Login.class, id);
    }

    public void deleteLoginById(UUID id){
        try {
            Login login = getloginById(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().remove(id);
            getEmc().getEntityManager().getTransaction().commit();
        } finally {
            getEmc().getEntityManager().close();
        }
    }
    public Login checkLogin(String username, String password){
        try{
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Login> query = getEmc().getEntityManager()
                    .createQuery("SELECT COUNT(l) FROM Login l WHERE l.login = :username AND l.password = :password", Login.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Login login = query.getSingleResult();
            getEmc().getEntityManager().getTransaction().commit();
            return login;
        } finally {
            getEmc().getEntityManager().close();
        }
    }
    public boolean checkLoginn(String username, String password){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            //Long é o tipo de dado retornado pela consulta, representando a contagem de registros
            //que atendem aos critérios fornecidos.
            TypedQuery<Long> query = getEmc().getEntityManager()
                    .createQuery("SELECT COUNT(l) FROM Login l WHERE l.login = :username AND l.password = :password", Long.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Long count = query.getSingleResult();
            return  count > 0;
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
