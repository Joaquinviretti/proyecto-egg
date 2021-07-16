package libreria.web.libreria.servicios;

import java.util.Optional;
import libreria.web.libreria.entidades.Editorial;
import libreria.web.libreria.respositorios.EditorialRepositorio;
import libreria.web.libreria.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    /* Creación */
    public void agregarEditorial(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        } else {
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }
    
    /* Consulta por nombre */
    public void buscarPorNombre(String nombre) {
        editorialRepositorio.buscarEditorialPorNombre(nombre);
    }

    /* Modificación */
    public void modificarEditorial(String id, String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        } else {
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Editorial editorial = respuesta.get();
                editorial.setNombre(nombre);
                
                editorialRepositorio.save(editorial);
            }
        }
    }

}
