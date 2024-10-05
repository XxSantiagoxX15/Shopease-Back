package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.CodigoModel;
import co.edu.unbosque.shopease_app.model.UsuarioModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoRepository  extends JpaRepository<CodigoModel,Integer> {
    CodigoModel findById(int id);

}
