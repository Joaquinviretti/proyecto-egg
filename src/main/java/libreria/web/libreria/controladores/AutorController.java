package libreria.web.libreria.controladores;

import java.util.List;
import libreria.web.libreria.entidades.Autor;
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
    public String listado() {
        /*  List<Autor>autores = autorServicio.listarTodos();
        modelo.put("autores", autores);*/
        return "autores/listado.html";
    }


    @PostMapping("/cargar")
    public String cargarAutor(@RequestParam String nombre, @RequestParam String apellido) {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        return "autores/carga.html";
    }
}
