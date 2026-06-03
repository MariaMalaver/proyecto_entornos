package proyecto_entornos;
public class Producto 
{
    /*ATRIBUTOS*/
    private String id;
    private String nombre;
    private double precio; 
    
    /*CONTRUSTOR*/
    public Producto(String id, String nombre, double precio)
    {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Constructor de compatibilidad sin id (para código existente).
     * @param nombre Nombre del producto
     * @param precio Precio del producto
     * @throws IllegalArgumentException si el precio es negativo
     */
    public Producto(String nombre, double precio)
    {
        if (precio < 0)
        {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.id = "SIN-ID";
        this.nombre = nombre;
        this.precio = precio;
    }

    /*METODOS GET Y SET*/
    //ID
    public String getId()
    {
        return this.id;
    }
    public void setId(String id)    
    {
        this.id = id;
    }
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
        if (precio < 0)
        {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    /* METODOS */
    //CALCULAR PRECIO FINAL
    /**
     * Calcula el precio final del producto.
     * Las subclases sobreescriben este método para aplicar IVA o coste de envío.
     * @return Precio final del producto
     */
    public double calcularPrecioFinal()
    {
        return this.precio;
    }
 
    @Override
    public String toString()
    {
        String salida = "\nNombre: " + this.nombre + "\n";
        salida += "Precio: " + this.precio + "\n";
        return salida;
    }
}
