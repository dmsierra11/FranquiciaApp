/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

/**
 *
 * @author gracielalucena
 */
public class Producto {

    private String nombre;
    private String descripcion;
    private String costo;
    private String foto;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, String costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public Producto(String nombre, String descripcion, String foto, String costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
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

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
