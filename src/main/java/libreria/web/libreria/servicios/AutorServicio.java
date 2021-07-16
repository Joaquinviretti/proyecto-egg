package libreria.web.libreria.servicios;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import libreria.web.libreria.entidades.Autor;
import libreria.web.libreria.respositorios.AutorRepositorio;
import libreria.web.libreria.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    /* Creación */
    @Transactional
    public void agregarAutor(String nombre, String apellido) throws ErrorServicio {

        validar(nombre, apellido);

        List<Autor> autorRepetido = autorRepositorio.buscarAutorPorNombre(nombre, apellido);

        if (autorRepetido.isEmpty()) {
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setApellido(apellido);

            autorRepositorio.save(autor);

        } else {
            throw new ErrorServicio("El autor " + nombre + " " + apellido
                    + " ya se encuentra en la base de datos.");
        }
    }

    /* Consulta por nombre */
    public List<Autor> buscarPorNombre(String nombre, String apellido) {
        List<Autor> autores = autorRepositorio.buscarAutorPorNombre(nombre, apellido);
        return autores;
    }

    /* Consultar todos */
    public List<Autor> listarTodos() {
        List<Autor> autores = autorRepositorio.buscarTodos();
        return autores;
    }

    /* Modificación */
    public void modificarAutor(String id, String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        } else {
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Autor autor = respuesta.get();
                autor.setNombre(nombre);
                autorRepositorio.save(autor);
            }
        }
    }

    /* Validación */
    private void validar(String nombre, String apellido) throws ErrorServicio {

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El campo de apellido no puede estar vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El campo de nombre no puede estar vacío.");
        }

    }
}
