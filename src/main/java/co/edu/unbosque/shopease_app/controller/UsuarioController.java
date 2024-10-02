package co.edu.unbosque.shopease_app.controller;


import co.edu.unbosque.shopease_app.model.UsuarioModel;
import co.edu.unbosque.shopease_app.service.EmailService;
import co.edu.unbosque.shopease_app.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
  private  UsuarioService usuarioService;

    @Autowired
  private  PasswordEncoder passwordEncoder;


    @Autowired
    private EmailService emailService;



    @PostMapping("/registrar")
    @Operation(summary = "Agregar Usuarios", description = "Agrega el objeto users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el usuarios")
    })
    public ResponseEntity<String> guardarUsuario(@RequestBody UsuarioModel usuario) {
        try {
            // Cifrar la contraseña
            String encryptedPassword = passwordEncoder.encode(usuario.getContraseña());
            usuario.setContraseña(encryptedPassword);

            usuarioService.saveUsuario(usuario);
            emailService.enviarCorreo(usuario.getEmail(),"Registro ShopEase","¡Hola "+usuario.getNombre()+"!"+"\n"
                    +"Gracias por registrarse en ShopEase. Ahora puedes acceder a nuestro catálogo y disfrutar de las mejores ofertas.\n"+
                    "Si tienes alguna duda o necesitas asistencia, no dudes en contactarnos.\n"+
                    "¡Gracias por confiar en nosotros! \n"+
                    "Atentamente,\n El equipo de ShopEase");
            return ResponseEntity.ok("Usuario guardado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se insertó el Usuario: " + usuario);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el usuario: " + e.getMessage());
        }
    }
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de usuarios ", description = "Obtener lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontradas"),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontradas")
    })
    public ResponseEntity<List<UsuarioModel>> listarTodosUsuarios() {
        List <UsuarioModel> usuarios = usuarioService.findAll();
        if (usuarios != null) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
