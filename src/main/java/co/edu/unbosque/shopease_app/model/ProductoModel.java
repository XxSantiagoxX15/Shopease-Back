package co.edu.unbosque.shopease_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name="productos")
public class ProductoModel {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    @Column(nullable = false)
    private String nombre;


    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    private int id_categoria;


    private String imagen_url;
}
