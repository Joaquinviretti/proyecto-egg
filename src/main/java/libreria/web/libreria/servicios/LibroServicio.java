package libreria.web.libreria.servicios;

import java.util.List;
import java.util.Optional;
import libreria.web.libreria.entidades.Autor;
import libreria.web.libreria.entidades.Editorial;
import libreria.web.libreria.entidades.Libro;
import libreria.web.libreria.respositorios.LibroRepositorio;
import libreria.web.libreria.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    /* Creación */
    public void agregar(String isbn, String titulo, Integer anio, Integer ejemplares,
            Integer prestados, Autor autor, Editorial editorial) throws ErrorServicio {

        validarDatos(isbn, titulo, anio, ejemplares, prestados, autor, editorial);

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setPrestados(prestados);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    /* Modificación */
    public void modificar(String isbn, String titulo, Integer anio, Integer ejemplares,
            Integer prestados, Autor autor, Editorial editorial) throws ErrorServicio {

        validarDatos(isbn, titulo, anio, ejemplares, prestados, autor, editorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setPrestados(prestados);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("El ISBN no corresponde a ningún libro registrado.");
        }
    }
    
    /* Consultar todos */
    public List<Libro> listarLibros(){
        List<Libro> libros = libroRepositorio.buscarLibros();
        return libros;
    }

    /* Consulta por titulo */
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = libroRepositorio.buscarLibroPorTitulo(titulo);
        return libros;
    }

    /* Consultar por isbn */
    public List<Libro> buscarPorIsbn(String isbn) {
        List<Libro> libros = libroRepositorio.buscarLibroPorIsbn(isbn);
        return libros;
    }

    /* Eliminación */
    public void eliminar(String isbn) {
        libroRepositorio.eliminarLibroPorIsbn(isbn);
    }

    /* Validación */
    private void validarDatos(String isbn, String titulo, Integer anio, Integer ejemplares,
            Integer prestados, Autor autor, Editorial editorial) throws ErrorServicio {

        if (isbn == null || isbn.isEmpty()) {
            throw new ErrorServicio("El ISBN no puede estar vacío");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El título no puede estar vacío");
        }
        if (anio == null) {
            throw new ErrorServicio("El año no puede estar vacío");
        }
        if (ejemplares == null) {
            throw new ErrorServicio("El numero de ejemplares"
                    + " no puede estar vacío");
        }
        if (prestados == null) {
            throw new ErrorServicio("El numero de ejemplares prestados no puede "
                    + "estar vacío");
        }
        if (autor == null) {
            throw new ErrorServicio("El autor no puede estar vacío");
        }
        if (editorial == null) {
            throw new ErrorServicio("La editorial no puede estar vacío");
        }

    }

}
