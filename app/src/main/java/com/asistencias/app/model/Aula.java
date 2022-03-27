package com.asistencias.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JsonIgnoreProperties({"aulas","hibernateLazyInitializer", "handler"})
    private Auxiliar auxiliar;

    @OneToMany(mappedBy = "aula")
    @JsonIgnoreProperties({"aula","hibernateLazyInitializer", "handler"})
    private List<Estudiante> estudiantes;

}
