package proyecto_entornos;

/**
 * Represents a digital product sold by the store.
 * Extends the generic Product class and includes download size,
 * license information, and VAT configuration.
 */
public class ProductoDigital extends Producto //HERENCIA
{
   /*ATRIBUTOS*/
    private double tamannioDescarga;
    private String licencia;
    private String tipoIva;

    // Constantes IVA
    private static final double IVA_GENERAL = 0.21;
    private static final double IVA_REDUCIDO = 0.10;
    private static final double IVA_SUPER = 0.04;
    private static final double IVA_DEFAULT = IVA_GENERAL;
    
    /*CONTRUSTOR*/
    /**
     * Creates a digital product using the general VAT rate by default.
     *
     * @param nombre the product name
     * @param precio the base price of the product
     * @param tamannioDescarga the download size in megabytes
     * @param licencia the product license type
     */
    public ProductoDigital(String nombre, double precio, double tamannioDescarga, String licencia)
    {
        super(nombre, precio);
        this.tamannioDescarga = tamannioDescarga;
        this.licencia = licencia;
        this.tipoIva = "GENERAL";
    }
    
    /**
     * Full constructor including VAT rate
     * @param nombre Name of the digital product
     * @param precio Base price of the product
     * @param tamannioDescarga Download file size in MB
     * @param licencia Product license type
     * @param tipoIva VAT rate: "GENERAL" (21%), "REDUCIDO" (10%), or "SUPER" (4%)
     */
    public ProductoDigital(String nombre, double precio, double tamannioDescarga, String licencia, String tipoIva)
    {
        super(nombre, precio);
        this.tamannioDescarga = tamannioDescarga;
        this.licencia = licencia;
        this.tipoIva = tipoIva;
    }

    /*METODOS GET Y SET*/
    //TAMANO DESCARGA
    /**
     * Returns the download size of the digital product.
     *
     * @return the download size in megabytes
     */
    public double getTamannioDescarga()
    {
        return this.tamannioDescarga;
    }

    /**
     * Sets the download size of the digital product.
     *
     * @param tamannioDescarga the new download size in megabytes
     */
    public void setTamannioDescarga(double tamannioDescarga)
    {
        this.tamannioDescarga = tamannioDescarga;
    }
    
    //LICENCIA
    /**
     * Returns the license type of the digital product.
     *
     * @return the product license
     */
    public String getLicencia()
    {
        return this.licencia;
    }

    /**
     * Sets the license type of the digital product.
     *
     * @param licencia the new product license
     */
    public void setLicencia(String licencia)
    {
        this.licencia = licencia;
    }

    //TIPO IVA
    /**
     * Returns the VAT type configured for the product.
     *
     * @return the configured VAT type
     */
    public String getTipoIva() 
    { 
        return this.tipoIva; 
    }
    
    /**
     * Sets the VAT type for the product.
     *
     * @param tipoIva the new VAT type
     */
    public void setTipoIva(String tipoIva) 
    { 
        this.tipoIva = tipoIva; 
    }
    
    /**
     * Applies VAT to the base price according to the specified rate
     * @param tipoIva "GENERAL" (21%), "REDUCED" (10%), or "SUPER" (4%)
     * @return Price with VAT applied
     */
    public double aplicarIVA(String tipoIva)
    {
        double porcentajeIva;
        switch (tipoIva)
        {
            case "REDUCIDO":
                porcentajeIva = IVA_REDUCIDO;
                break;
            case "SUPER":
                porcentajeIva = IVA_SUPER;
                break;
            case "GENERAL":
            default:
                porcentajeIva = IVA_GENERAL;
                break;
        }
        return getPrecio() * (1 + porcentajeIva);
    }

    /**
     * Calculates the final price by applying the VAT configured in the tipoIva attribute.
     * Digital products have no shipping cost.
     * @return Final price including VAT.
     */
    @Override
    public double calcularPrecioFinal()
    {
        return aplicarIVA(this.tipoIva);
    }
 
    /**
     * Returns the applied VAT rate as a decimal value
     * @return VAT rate
     */
    public double getTasaIva()
    {
        switch (this.tipoIva)
        {
            case "REDUCIDO": return IVA_REDUCIDO;
            case "SUPER": return IVA_SUPER;
            default: return IVA_GENERAL;
        }
    }
    /**
     * Returns a string representation of the digital product.
     *
     * @return a string containing the product, download size,
     * license, and VAT information
     */
    @Override
    public String toString()
    {
        String salida = super.toString();
        salida += "Tamaño descarga: " + this.tamannioDescarga + " MB \n";
        salida += "Licencia: " + this.licencia + "\n";
        salida += "Tipo IVA: " + this.tipoIva;
        return salida;
    }
}
