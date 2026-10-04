
package proyecto_entornos;

/**
 * Represents a physical product sold by the store.
 * Extends the generic Product class and includes weight
 * and destination-based shipping costs.
 */
public class ProductoFisico extends Producto //HERENCIA
{
    /*ATRIBUTOS*/
    private double peso;

    // Constantes de coste de envío por zona
    private static final double ENVIO_ESPANNA = 0.0;
    private static final double ENVIO_ZONA_CERCANA  = 5.0;
    private static final double ENVIO_RESTO = 10.0;
    
    /*CONSTRUCTOR ORIGINAL*/
    /**
     * Creates a physical product with a name, base price, and weight.
     *
     * @param nombre the product name
     * @param precio the base price of the product
     * @param peso the product weight in kilograms
     * @throws IllegalArgumentException if the weight is negative
     */
    public ProductoFisico(String nombre, double precio, double peso)
    {
        super(nombre, precio);
        if (peso < 0)
        {
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        // En el constructor original costeEnvio se usaba como peso aproximado
        this.peso = peso;
    }

    /**
     * Full constructor including weight
     * @param nombre Product name
     * @param precio Base product price
     * @param peso Product weight in kg for calculating shipping cost
     * @param zonaDestino Destination country for calculating shipping
     * @throws IllegalArgumentException if price or weight is negative
     */
    public ProductoFisico(String nombre, double precio, double peso, String zonaDestino)
    {
        super(nombre, precio);
        if (peso < 0)
        {
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        this.peso = peso;
    }
    
    /*METODOS GET Y SET*/
    //PESO
    /**
     * Returns the product weight.
     *
     * @return the product weight in kilograms
     */
    public double getPeso() 
    { 
        return this.peso; 
    }

    /**
     * Sets the product weight.
     *
     * @param peso the new product weight in kilograms
     * @throws IllegalArgumentException if the weight is negative
     */
    public void setPeso(double peso) 
    { 
        if (peso < 0)
        {
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        this.peso = peso; 
    }
    
    /**
     * Maintains compatibility with existing code that used getCosteEnvio()
     * Calculates the cost for Spain by default
     * @return Base shipping cost
     */
    public double getCosteEnvio()
    {
        return this.peso;
    }
 
    /**
     * Calculates the shipping cost based on the destination country
     * Spain: €0; France, Italy, Portugal: €5; Rest: €10
     * @param paisDestino Destination country for the shipment
     * @return Shipping cost in euros
     */
    public double calcularCosteEnvio(String paisDestino)
    {
        if (paisDestino == null)
        {
            return ENVIO_ESPANNA;
        }
        switch (paisDestino.trim().toUpperCase().replace("Ñ", "N").replace("ñ", "N")) 
        {
            case "ESPANA":
            case "ESPANNA":
                return ENVIO_ESPANNA;
            case "FRANCIA":
            case "ITALIA":
            case "PORTUGAL":
                return ENVIO_ZONA_CERCANA;
            default:
                return ENVIO_RESTO;
        }
    }
 
    /**
     * Calculates the final product price by adding the base price and the shipping cost to Spain.
     * For international shipments, use...
     * @return Final price including shipping to Spain
     */
    @Override
    public double calcularPrecioFinal()
    {
        return getPrecio() + ENVIO_ESPANNA;
    }
 
    /**
     * Calculates the final price including shipping costs based on the destination zone
     * @param paisDestino Destination country for the shipment
     * @return Final price including shipping
     */
    public double calcularPrecioFinalConZona(String paisDestino)
    {
        return getPrecio() + calcularCosteEnvio(paisDestino);
    }
    
    /**
     * Returns a string representation of the physical product.
     *
     * @return a string containing the product, weight, and shipping information
    */
    @Override
    public String toString()
    {
        return super.toString()
               + "Peso: " + this.peso + " kg\n"
               + "Envío España: " + ENVIO_ESPANNA + " €\n"
               + "Envío zona cercana (Francia/Italia/Portugal): " + ENVIO_ZONA_CERCANA + " €\n"
               + "Envío resto del mundo: " + ENVIO_RESTO + " €\n";
    }
}
 
