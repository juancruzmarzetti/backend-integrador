package com.me.odontologo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int matricula;
    private String nombre;
    private String apellido;
    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnoSet = new HashSet<>();
}