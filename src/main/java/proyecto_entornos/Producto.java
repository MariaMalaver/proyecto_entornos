package proyecto_entornos;
public class Producto 
{
    /*ATRIBUTOS*/
    private String nombre;
    private double precio; 
    
    /*CONTRUSTOR*/
    public Producto(String nombre, double precio)
    {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    /*METODOS GET Y SET*/
    //NOMBRE
    public String getNombre()
    {
        return this.nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    //PRECIO
    public double getPrecio()
    {
        return this.precio;
    }
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    /* METODOS */
    //CALCULAR PRECIO FINAL
    public double calcularPrecioFinal()
    {
        return this.precio;
    }

    //toString
    @Override
    public String toString()
    {
        String salida = "\nNombre: " + this.nombre + "\n";
        salida += "Precio: " + this.precio + "\n";
        return salida;
    }
}
