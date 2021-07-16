package libreria.web.libreria.entidades;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Joaco
 */

@Entity
public class Libro {
    
    @Id
    private String isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer prestados;
    private boolean baja;
    
    @OneToOne
    private Autor autor;
    
    @OneToOne
    private Editorial editorial;

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the ejemplares
     */
    public Integer getEjemplares() {
        return ejemplares;
    }

    /**
     * @param ejemplares the ejemplares to set
     */
    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    /**
     * @return the prestados
     */
    public Integer getPrestados() {
        return prestados;
    }

    /**
     * @param prestados the prestados to set
     */
    public void setPrestados(Integer prestados) {
        this.prestados = prestados;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
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
