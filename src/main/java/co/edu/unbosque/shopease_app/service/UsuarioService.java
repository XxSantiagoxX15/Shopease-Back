package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.ProductoModel;
import co.edu.unbosque.shopease_app.model.UsuarioModel;
import co.edu.unbosque.shopease_app.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
	private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioModel saveUsuario(UsuarioModel usuario) {

		UsuarioModel usuarioModel = usuarioRepository.save(usuario);
		return usuarioModel;
	}

	public List<UsuarioModel> findAll(){
		return usuarioRepository.findAll();
	}

	public UsuarioModel findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
