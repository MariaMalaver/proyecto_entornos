
package proyecto_entornos;
public class ProductoFisico extends Producto //HERENCIA
{
    /*ATRIBUTOS*/
    private double peso;

    // Constantes de coste de envío por zona
    private static final double ENVIO_ESPANNA = 0.0;
    private static final double ENVIO_ZONA_CERCANA  = 5.0;
    private static final double ENVIO_RESTO = 10.0;
    
    /*CONSTRUCTOR ORIGINAL*/
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
     * Constructor completo con peso
     * @param nombre Nombre del producto
     * @param precio Precio base del producto
     * @param peso Peso del producto en kg para calcular el coste de envío
     * @param zonaDestino País de destino para calcular el envío
     * @throws IllegalArgumentException si el precio o el peso son negativos
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
    public double getPeso() 
    { 
        return this.peso; 
    }
    public void setPeso(double peso) 
    { 
        if (peso < 0)
        {
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        this.peso = peso; 
    }
    
    /**
     * Mantiene compatibilidad con código existente que usaba getCosteEnvio()
     * Calcula el coste para España por defecto
     * @return Coste de envío base
     */
    public double getCosteEnvio()
    {
        return this.peso;
    }
 
    /**
     * Calcula el coste de envío según el país de destino
     * España: 0€ Francia, Italia, Portugal: 5€ Resto: 10€
     * @param paisDestino País de destino del envío
     * @return Coste de envío en euros
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
     * Calcula el precio final del producto sumando el precio base y el coste
     * de envío a España
     * Para envíos internacionales utilizar
     * @return Precio final con envío a España incluido
     */
    @Override
    public double calcularPrecioFinal()
    {
        return getPrecio() + ENVIO_ESPANNA;
    }
 
    /**
     * Calcula el precio final con el coste de envío según la zona de destino
     * @param paisDestino País de destino del envío
     * @return Precio final con envío incluido
     */
    public double calcularPrecioFinalConZona(String paisDestino)
    {
        return getPrecio() + calcularCosteEnvio(paisDestino);
    }
 
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
 
