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

    /* Consulta por apellido */
    public List<Autor> buscarPorApellido(String apellido) {
        List<Autor> autores = autorRepositorio.buscarAutorPorApellido(apellido);
        return autores;
    }

    /* Consulta por id */
    public Autor buscarPorId(String id) {
        Autor autor = autorRepositorio.buscarAutorPorId(id);
        return autor;
    }


    /* Consultar todos */
    public List<Autor> listarTodos() {
        List<Autor> autores = autorRepositorio.buscarTodos();
        return autores;
    }

    /* Modificación */
    @Transactional
    public void modificar(String id, String nombre, String apellido) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede estar vacío");
        }

        autorRepositorio.modificar(id, nombre, apellido);

    }

    /* Dar de baja */
    @Transactional
    public void darBaja(String id) {
        autorRepositorio.darBaja(id);
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
