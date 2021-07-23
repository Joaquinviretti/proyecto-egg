package libreria.web.libreria.controladores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.web.libreria.entidades.Autor;
import libreria.web.libreria.errores.ErrorServicio;
import libreria.web.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/")
    public String inicio() {
        return "autores/menu.html";
    }

    @GetMapping("/carga")
    public String nuevo() {
        return "autores/carga.html";
    }

    @GetMapping("/listado")
    public String listado(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarTodos();
        modelo.put("autores", autores);
        modelo.put("cantidad", autores.size());
        return "autores/listado.html";
    }

    @PostMapping(value = "/editar", params = "eliminar")
    public String eliminar(ModelMap modelo, @RequestParam String eliminar) {
        Autor autorEliminado = autorServicio.buscarPorId(eliminar);
        String mensaje = "El autor " + autorEliminado.getNombre() + " "
                + autorEliminado.getApellido() + " se ha eliminado correctamente.";

        autorServicio.darBaja(eliminar);
        List<Autor> autores = autorServicio.listarTodos();
        modelo.put("autores", autores);
        modelo.put("cantidad", autores.size());
        modelo.put("exito", mensaje);
        return "autores/listado.html";
    }

    @PostMapping(value = "/editar", params = "modificar")
    public String edición(ModelMap modelo, @RequestParam String modificar) {

        Autor autor = autorServicio.buscarPorId(modificar);
        modelo.put("nombre", autor.getNombre());
        modelo.put("apellido", autor.getApellido());
        modelo.put("id", autor.getId());
        return "autores/modificar.html";

    }

    @PostMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre,
            @RequestParam String apellido) {

        modelo.put("nombre", nombre);
        modelo.put("apellido", apellido);
        modelo.put("id", id);

        try {
            System.out.println(id);
            autorServicio.modificar(id, nombre, apellido);
            modelo.put("exito", "Se han modificado los datos correctamente");
            return "autores/modificar.html";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            return "autores/modificar.html";
        }

    }

    @PostMapping("/buscarAutor")
    public String buscarAutor(ModelMap modelo, @RequestParam String apellido) {
        List<Autor> autores = autorServicio.buscarPorApellido(apellido);
        modelo.put("autores", autores);
        modelo.put("apellido", apellido);
        modelo.put("cantidad", autores.size());
        return "autores/listado.html";
    }

    @PostMapping("/cargar")
    public String cargarAutor(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido) {

        try {
            autorServicio.agregarAutor(nombre, apellido);
            modelo.put("exito", "El autor " + nombre + " " + apellido
                    + " se ha añadido correctamente.");
            return "autores/carga.html";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            return "autores/carga.html";
        }
    }

    /*@GetMapping("/modificacion")
    public String modificacion(ModelMap modelo){
        List <Autor> autor = autorServicio.buscarPorId(id);
    }*/
}
