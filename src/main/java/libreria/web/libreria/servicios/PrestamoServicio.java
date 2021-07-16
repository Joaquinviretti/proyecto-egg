package libreria.web.libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import libreria.web.libreria.entidades.Cliente;
import libreria.web.libreria.entidades.Libro;
import libreria.web.libreria.entidades.Prestamo;
import libreria.web.libreria.respositorios.PrestamoRepositorio;
import libreria.web.libreria.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    /* Creación */
    public void crearPrestamo(Date devolucion, Double multa, Cliente cliente,
            List<Libro> libros) throws ErrorServicio {

        validarDatos(devolucion, cliente, libros);

        Prestamo prestamo = new Prestamo();
        prestamo.setFecha(new Date()); // fecha del momento creado
        prestamo.setDevolucion(devolucion);
        prestamo.setMulta(0.0);
        prestamo.setCliente(cliente);
        prestamo.setLibros(libros);

        prestamoRepositorio.save(prestamo);

    }

    /*Como modifico? con el id?*/
 /* Modificación */
    public void modificarPrestamo(String id, Date fecha, Date devolucion, Double multa, Cliente cliente,
            List<Libro> libros) throws ErrorServicio {

        validarDatos(devolucion, cliente, libros);
        
        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Prestamo prestamo = respuesta.get();
            prestamo.setFecha(fecha);
            prestamo.setDevolucion(devolucion);
            prestamo.setMulta(multa);
            prestamo.setCliente(cliente);
            prestamo.setLibros(libros);

            prestamoRepositorio.save(prestamo);
        } else {
            throw new ErrorServicio("No hay ningún prestamo registrado con ese id.");
        }

    }

    /* Validación */
    private void validarDatos(Date devolucion, Cliente cliente, List<Libro> libros) throws ErrorServicio {

        if (devolucion == null) {
            throw new ErrorServicio("La fecha de devolución no puede estar vacía");
        }
        if (cliente == null) {
            throw new ErrorServicio("El cliente no puede estar vacío.");
        }
        if (libros == null) {
            throw new ErrorServicio("Los libros no pueden estar vacíos.");
        }

    }

}
