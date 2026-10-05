package proyecto_entornos;

/**
 * Represents a generic product sold by the store.
 * Stores the product identifier, name, and base price.
 */
public class Producto 
{
    /*ATRIBUTOS*/
    private String id;
    private String nombre;
    private double precio; 
    
    /*CONTRUSTOR*/
    /**
     * Creates a product with an identifier, name, and base price.
     *
     * @param id the unique product identifier
     * @param nombre the product name
     * @param precio the base price of the product
     * @throws IllegalArgumentException if the price is negative
     */
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
     * Creates a product without a specific identifier.
     * The identifier is automatically set to "SIN-ID".
     *
     * @param nombre the product name
     * @param precio the base price of the product
     * @throws IllegalArgumentException if the price is negative
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
    /**
     * Returns the product identifier.
     *
     * @return the product identifier
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Sets the product identifier.
     *
     * @param id the new product identifier
     */
    public void setId(String id)    
    {
        this.id = id;
    }

    //NOMBRE
    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getNombre()
    {
        return this.nombre;
    }
    
    /**
     * Sets the product name.
     *
     * @param nombre the new product name
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    //PRECIO
    /**
     * Returns the base price of the product.
     *
     * @return the product base price
     */
    public double getPrecio()
    {
        return this.precio;
    }

    /**
     * Sets the base price of the product.
     *
     * @param precio the new product price
     * @throws IllegalArgumentException if the price is negative
     */
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
     * Calculates the final price of the product.
     * Subclasses override this method to apply VAT or shipping costs.
     * @return The final price of the product.
     */
    public double calcularPrecioFinal()
    {
        return this.precio;
    }
 
    /**
     * Returns a string representation of the product.
     *
     * @return a string containing the product name and price
     */
    @Override
    public String toString()
    {
        String salida = "\nNombre: " + this.nombre + "\n";
        salida += "Precio: " + this.precio + "\n";
        return salida;
    }
}
