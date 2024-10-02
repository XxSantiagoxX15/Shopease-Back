package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel,Integer> {


    ProductoModel findByNombre(String nombre);

}
