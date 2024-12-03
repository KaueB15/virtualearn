package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.ContractDAO;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;

public class ContractController {

    ContractDAO contractDAO = new ContractDAO();

    public void createContract(Course course, Teacher teacher, String matter){

        Contract contract = new Contract();

        contract.setCourse(course);
        contract.setTeacher(teacher);
        contract.setMatter(matter);

        contractDAO.registerContract(contract);
    }

}
