package br.com.fafic.virtualearn.dao;

import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class ContractDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }
    public void registerContract(Contract contract){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(contract);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public List<Contract> getAllContract(){
        List<Contract> contracts = null;
        try {
            getEmc().getEntityManager().getTransaction().begin();
            TypedQuery<Contract> query = getEmc().getEntityManager()
                    .createQuery("SELECT c FROM Contract c", Contract.class);
            contracts = query.getResultList();
        }finally {
            getEmc().getEntityManager().close();
        }
        return contracts;
    }
    public void updateContract(Contract contract){
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(contract);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
    public Contract getContractById(UUID id) {
        return getEmc().getEntityManager().find(Contract.class, id);
    }
    public void deleteContractById(UUID id){
        try {
            Contract contract = getContractById(id);
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().merge(contract);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }
}
