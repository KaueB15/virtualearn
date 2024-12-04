package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.ContractDAO;
import br.com.fafic.virtualearn.exceptions.ContractNotFoundException;
import br.com.fafic.virtualearn.exceptions.ContractNotRegisterException;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;

import java.util.UUID;

import java.util.List;

public class ContractController {

    ContractDAO contractDAO = new ContractDAO();

    public void createContract(Course course, Teacher teacher, String matter){

        Contract contract = new Contract();

        contract.setCourse(course);
        contract.setTeacher(teacher);
        contract.setMatter(matter);

        contractDAO.registerContract(contract);
    }

    public List<Contract> getAllContracts(){

        List<Contract> contracts = null;

        try {

            contracts = contractDAO.getAllContract();

            if (contracts.isEmpty()){
                throw new ContractNotRegisterException();
            }

            return contracts;

        }catch (ContractNotRegisterException e){
            System.err.println(e.getMessage());
            return contracts;
        }

    }

    public Contract getContractById(UUID id){
        try {
            Contract contract = contractDAO.getContractById(id);

            if(contract == null){
                throw new ContractNotFoundException();
            }
            return contract;
        }catch (ContractNotFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Contract findByContract(String contract){
        try{
            Contract contracts = contractDAO.findByContract(contract);

            if (contracts == null){
                throw new ContractNotFoundException();
            }
            return contracts;
        }catch (ContractNotFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}
