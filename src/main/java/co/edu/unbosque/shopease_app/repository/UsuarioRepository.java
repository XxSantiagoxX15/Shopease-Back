package co.edu.unbosque.shopease_app.repository;

import co.edu.unbosque.shopease_app.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {

    UsuarioModel findByEmail(String email);

    UsuarioModel findByNombre(String nombre);



}
