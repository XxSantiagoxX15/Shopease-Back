package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.CodigoModel;
import co.edu.unbosque.shopease_app.repository.CodigoRepository;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodigoService {
	private final Logger logger = LoggerFactory.getLogger(CodigoService.class);

	@Autowired
	private CodigoRepository codigoRepository;

	public CodigoModel saveCodigo(CodigoModel codigo) {

		CodigoModel codigoModel = codigoRepository.save(codigo);
		return codigoModel;
	}

	public CodigoModel findById(int id) {
		return codigoRepository.findById(id);
	}

	@Transactional
	public void deleteByIdUsuario(int id_usuario) {
		try {
			codigoRepository.deleteById(id_usuario);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No se encontró el código para el id_usuario: " + id_usuario);
		}
	}
}
