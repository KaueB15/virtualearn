package br.com.fafic.virtualearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name, email, phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "idlogin")
    private Login login;

}
