package libreria.web.libreria.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre and a.apellido = :apellido and a.baja = 0")
    public List <Autor> buscarAutorPorNombre (@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    @Query("SELECT a FROM Autor a WHERE a.apellido = :apellido and a.baja = 0")
    public List <Autor> buscarAutorPorApellido (@Param("apellido") String apellido);
    
    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    public Autor buscarAutorPorId (@Param("id") String id);
    
    @Query("SELECT a FROM Autor a where a.baja = 0")
    public List<Autor> buscarTodos();
    
    @Modifying
    @Query("UPDATE Autor a SET a.baja = 1 WHERE a.id=:id")
    public void darBaja(@Param("id") String id);
    
    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre, a.apellido = :apellido WHERE a.id=:id")
    public void modificar(@Param("id") String id, @Param("nombre") String nombre, @Param ("apellido")
    String apellido);
    
    
    
    
}
