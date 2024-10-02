package co.edu.unbosque.shopease_app.controller;

import co.edu.unbosque.shopease_app.model.CategoriaModel;
import co.edu.unbosque.shopease_app.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/crear")
    @Operation(summary = "Crear Categoria", description = "Crea una categoría de acuerdo a un cuerpo JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = CategoriaModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> crearCategoria(@RequestBody CategoriaModel categoria) {
        try {

            CategoriaModel nuevaCategoria = categoriaService.saveCategoria(categoria);
            return ResponseEntity.ok("Se insertó la categoría");
        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger en lugar de imprimir la traza
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la categoría: " + e.getMessage());
        }
    }
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de categorias ", description = "Obtener lista de categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas"),
            @ApiResponse(responseCode = "404", description = "Categorias no encontradas")
    })
    public ResponseEntity<List<CategoriaModel>> listarTodasCategorias() {
        List <CategoriaModel> categoria = categoriaService.findAll();
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar una categoria", description = "Actualiza un Categoria existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria actualizado exitosamente", content = @Content(schema = @Schema(implementation = CategoriaModel.class))),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<CategoriaModel> actualizarCategoria(@PathVariable int id, @RequestBody CategoriaModel categoriaModel) {

        CategoriaModel actualizarCategoria= categoriaService.updateCategoria(id,categoriaModel);
        if (actualizarCategoria != null) {
            return ResponseEntity.ok(actualizarCategoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar Categoría", description = "Elimina una categoría por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> eliminarCategoria(@PathVariable int id) {
        try {
            boolean isRemoved = categoriaService.deleteCategoria(id);
            if (isRemoved) {
                return ResponseEntity.ok("Categoría eliminada exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la categoría: " + e.getMessage());
        }
    }
}
