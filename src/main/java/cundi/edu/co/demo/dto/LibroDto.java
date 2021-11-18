package cundi.edu.co.demo.dto;

public class LibroDto {
    private String nombre;
    private String descripcion;
    private Integer numeroPaginas;
    private Integer autor;

    public LibroDto(){ }

    public LibroDto(String nombre, String descripcion, Integer numeroPaginas, Integer autor){
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroPaginas = numeroPaginas;
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }
}
