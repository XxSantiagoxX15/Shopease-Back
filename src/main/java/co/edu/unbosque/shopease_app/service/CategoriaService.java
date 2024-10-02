package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.CategoriaModel;
import co.edu.unbosque.shopease_app.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaService {

    private final Logger logger = LoggerFactory.getLogger(CategoriaModel.class);
    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaModel saveCategoria(CategoriaModel categorias) {

        CategoriaModel categoriaModel = categoriaRepository.save(categorias);
        return categoriaModel;
    }

    public CategoriaModel updateCategoria(int id, CategoriaModel categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId_categoria(id);
            return categoriaRepository.save(categoria);
        } else {
            return null;
        }

    }

    public Boolean deleteCategoria(int id) {
        if (categoriaRepository.existsById(id)) { // Verifica si la categoría existe
            categoriaRepository.deleteById(id); // Elimina directamente por id
            return true;
        } else {
            throw new EntityNotFoundException("La categoría con ID " + id + " no existe.");
        }
    }

    public List<CategoriaModel> findAll(){
        return categoriaRepository.findAll();
    }

}
