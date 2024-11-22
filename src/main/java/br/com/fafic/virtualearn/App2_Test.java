package br.com.fafic.virtualearn;

import br.com.fafic.virtualearn.persistence.EntityManagerConnection;

public class App2_Test {

    public static void main(String[] args) {

        EntityManagerConnection emc =  new EntityManagerConnection();

        emc.getEntityManager().clear();

    }

}
