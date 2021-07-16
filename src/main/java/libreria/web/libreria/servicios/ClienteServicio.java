package libreria.web.libreria.servicios;

import java.util.List;
import java.util.Optional;
import libreria.web.libreria.errores.ErrorServicio;
import org.springframework.stereotype.Service;
import libreria.web.libreria.entidades.Cliente;
import libreria.web.libreria.respositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    /* Creación */
    public void agregarCliente(Long documento, String nombre, String apellido,
            String domicilio, String telefono) throws ErrorServicio {

        validarDatos(documento, nombre, apellido, domicilio, telefono);

        Cliente cliente = new Cliente();
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDomicilio(domicilio);
        cliente.setTelefono(telefono);

        clienteRepositorio.save(cliente);

    }
    
    /* Consultar por DNI */
    public List<Cliente> consultarPorDni(Long dni){
        List<Cliente>clientes = clienteRepositorio.buscarPorDni(dni);
        return clientes;
    }
    
    /* Consultar todos */
    public List<Cliente> consultarTodos(Long dni){
        List<Cliente>clientes = clienteRepositorio.buscarTodos();
        return clientes;
    }

    /* Modificación */
    public void modificarCliente(Long documento, String nombre, String apellido,
            String domicilio, String telefono) throws ErrorServicio {

        validarDatos(documento, nombre, apellido, domicilio, telefono);

        Optional<Cliente> respuesta = clienteRepositorio.findById(documento);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();
            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDomicilio(domicilio);
            cliente.setTelefono(telefono);

            clienteRepositorio.save(cliente);
        } else {
            throw new ErrorServicio("El documento no corresponde a ningún cliente.");
        }

    }

    /* Validación */
    private void validarDatos(Long documento, String nombre, String apellido,
            String domicilio, String telefono) throws ErrorServicio {

        if (documento == null) {
            throw new ErrorServicio("El documento no puede estar vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede estar vacío.");
        }
        if (domicilio == null || domicilio.isEmpty()) {
            throw new ErrorServicio("El domicilio no puede estar vacío.");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El teléfono no puede estar vacío.");
        }

    }

}
