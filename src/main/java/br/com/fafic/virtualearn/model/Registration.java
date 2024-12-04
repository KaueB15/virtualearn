package br.com.fafic.virtualearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID registrationNumber;

    private double rating;

    @Column(nullable = false)
    private LocalDate registerDay, endRegisterDay;

    @OneToOne
    @JoinColumn(name = "idstudent")
    private Student student;

    @OneToOne
    @JoinColumn(name = "idcourse")
    private Course course;
}
