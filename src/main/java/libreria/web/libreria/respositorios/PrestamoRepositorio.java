package libreria.web.libreria.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String> {
    
    @Query("Select p FROM Prestamo p WHERE p.id = :id")
    public List<Prestamo> buscarPrestamoPorId (@Param("id") String id);
    
}
