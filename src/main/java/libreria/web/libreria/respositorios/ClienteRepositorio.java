/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.web.libreria.respositorios;

import java.util.List;
import libreria.web.libreria.entidades.Cliente;
import libreria.web.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query("SELECT e FROM Cliente e WHERE e.documento = :documento")
    public List<Cliente> buscarPorDni (@Param("documento") Long documento);
    
    @Query("SELECT e FROM Cliente e")
    public List<Cliente> buscarTodos ();
    
    
}

    

