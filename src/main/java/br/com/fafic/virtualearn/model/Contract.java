package br.com.fafic.virtualearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "contract")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idcourse")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "idteacher")
    private Teacher teacher;

    private String matter;

}
