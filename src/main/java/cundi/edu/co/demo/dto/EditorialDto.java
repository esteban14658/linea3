package cundi.edu.co.demo.dto;

public class EditorialDto {

    private String nit;

    private String nombre;

    private String correo;

    public EditorialDto() { }

    public EditorialDto(String nit, String nombre, String correo){
        super();
        this.nit = nit;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
