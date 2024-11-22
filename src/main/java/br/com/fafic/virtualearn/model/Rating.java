package br.com.fafic.virtualearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double r1, r2, r3;

    private double finalRating;

    @OneToOne
    @JoinColumn(name = "idteacher")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "idstudent")
    private Student student;
}
