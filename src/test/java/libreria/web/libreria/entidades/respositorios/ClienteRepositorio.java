
package libreria.web.libreria.entidades.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    
    @Query("SELECT c FROM Cliente c WHERE c.docuemento = :documento")
    public List<Cliente> buscarClientePorDni (@Param("documento") Long documento);
    
    /*@Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    public List<Cliente> buscarClientePorNombre (@Param("nombre") String nombre);
    
    @Query("SELECT c FROM Cliente c WHERE c.apellido = :apellido")
    public List<Cliente> buscarClientePorApellido (@Param("apellido") String apellido);*/
  
}
