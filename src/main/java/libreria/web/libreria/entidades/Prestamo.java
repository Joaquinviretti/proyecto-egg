package libreria.web.libreria.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Prestamo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date devolucion;
    private Double multa;
    
    @OneToMany
    private List <Libro> libros;
    
    @OneToOne
    private Cliente cliente;
    
    private boolean baja;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the devolucion
     */
    public Date getDevolucion() {
        return devolucion;
    }

    /**
     * @param devolucion the devolucion to set
     */
    public void setDevolucion(Date devolucion) {
        this.devolucion = devolucion;
    }

    /**
     * @return the multa
     */
    public Double getMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the libros
     */
    public List <Libro> getLibros() {
        return libros;
    }

    /**
     * @param libros the libros to set
     */
    public void setLibros(List <Libro> libros) {
        this.libros = libros;
    }

    /**
     * @return the baja
     */
    public boolean isBaja() {
        return baja;
    }

    /**
     * @param baja the baja to set
     */
    public void setBaja(boolean baja) {
        this.baja = baja;
    }
    
    
}
