package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.ContractDAO;
import br.com.fafic.virtualearn.exceptions.ContractNotRegisterException;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;

import java.util.List;

public class ContractController {

    ContractDAO contractDAO = new ContractDAO();

    public void createContract(Course course, Teacher teacher, String matter, String teacherFormation, String teacherName){

        Contract contract = new Contract();

        contract.setCourse(course);
        contract.setTeacher(teacher);
        contract.setMatter(matter);
        contract.setTeacherFormation(teacherFormation);
        contract.setTeacherName(teacherName);

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

}
