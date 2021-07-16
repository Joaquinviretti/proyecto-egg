package libreria.web.libreria.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
    @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
    public List<Libro> buscarLibroPorIsbn(@Param("isbn") String isbn);
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public List<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);
    
    @Query("DELETE FROM Libro l WHERE l.isbn = :isbn")
    public void eliminarLibroPorIsbn(@Param("isbn") String isbn);
    
    @Query("SELECT l FROM Libro l")
    public List<Libro> buscarLibros();
    
  
}
