package libreria.web.libreria.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre and a.apellido = :apellido")
    public List <Autor> buscarAutorPorNombre (@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    @Query("SELECT a FROM Autor a")
    public List<Autor> buscarTodos();
    
    
    
}
