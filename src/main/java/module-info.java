module br.com.fafic.virtualearn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;

    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires org.apache.pdfbox;

    opens br.com.fafic.virtualearn to javafx.fxml;
    exports br.com.fafic.virtualearn;

    exports br.com.fafic.virtualearn.model;
    opens br.com.fafic.virtualearn.model;

    exports br.com.fafic.virtualearn.controllers;
    opens br.com.fafic.virtualearn.controllers;
    exports br.com.fafic.virtualearn.view;
    opens br.com.fafic.virtualearn.view;
    exports br.com.fafic.virtualearn.adapters;
    opens br.com.fafic.virtualearn.adapters;

}