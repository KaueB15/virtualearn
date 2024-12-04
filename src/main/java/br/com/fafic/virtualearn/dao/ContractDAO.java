package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ContractDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void registerContract(Contract contract) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(contract);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Contract> getAllContract() {
        TypedQuery<Contract> query = getEmc().getEntityManager()
                .createQuery("SELECT c FROM Contract c", Contract.class);
        return query.getResultList();
    }

    public void updateContract(Contract contract) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(contract);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public Contract getContractById(UUID id) {
        return getEmc().getEntityManager().find(Contract.class, id);
    }

    public Contract findByContract(String contract){
        getEmc().getEntityManager().getTransaction().begin();
        TypedQuery<Contract> query = getEmc().getEntityManager()
                .createQuery("SELECT c FROM Contract c.contract = :username", Contract.class);
        query.setParameter(":username", contract);
        List<Contract> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void deleteContractById(UUID id) {
        Contract contract = getContractById(id);
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().remove(contract);
        getEmc().getEntityManager().getTransaction().commit();
    }
}
