package libreria.web.libreria.servicios;

import java.util.List;
import java.util.Optional;
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
    public void agregarAutor(String nombre, String apellido) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El nombre debe estar completo");
        } else {
            Autor autor = new Autor();
            autor.setNombre(nombre + " " + apellido);

            autorRepositorio.save(autor);
        }
    }
    
    /* Consulta por nombre */
    public List<Autor> buscarPorNombre(String nombre) {
         List<Autor> autores = autorRepositorio.buscarAutorPorNombre(nombre);
        return autores;
    }
    
    /* Consultar todos */
    public List<Autor> listarTodos(){
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
}
