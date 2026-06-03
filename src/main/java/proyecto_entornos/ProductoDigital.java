package proyecto_entornos;
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
    public ProductoDigital(String nombre, double precio, double tamannioDescarga, String licencia)
    {
        super(nombre, precio);
        this.tamannioDescarga = tamannioDescarga;
        this.licencia = licencia;
        this.tipoIva = "GENERAL";
    }
    
    /**
     * Constructor completo con tipo de IVA
     * @param nombre Nombre del producto digital
     * @param precio Precio base del producto
     * @param tamannioDescarga Tamaño en MB del archivo de descarga
     * @param licencia Tipo de licencia del producto
     * @param tipoIva Tipo de IVA: "GENERAL" (21%), "REDUCIDO" (10%) o "SUPER" (4%)
     */
    public ProductoDigital(String nombre, double precio, double tamannioDescarga, String licencia, String tipoIva)
    {
        super(nombre, precio);
        this.tamannioDescarga = tamannioDescarga;
        this.licencia = licencia;
        this.tipoIva = tipoIva;
    }

    /*METODOS GET Y SET*/
    //COSTE ENVIO
    public double getTamannioDescarga()
    {
        return this.tamannioDescarga;
    }
    public void setTamannioDescarga(double tamannioDescarga)
    {
        this.tamannioDescarga = tamannioDescarga;
    }
    //LICENCIA
    public String getLicencia()
    {
        return this.licencia;
    }
    public void setLicencia(String licencia)
    {
        this.licencia = licencia;
    }

    //TIPO IVA
    public String getTipoIva() 
    { 
        return this.tipoIva; 
    }
    public void setTipoIva(String tipoIva) 
    { 
        this.tipoIva = tipoIva; 
    }

    /**
     * Aplica el IVA al precio base según el tipo indicado
     * @param tipoIva "GENERAL" (21%), "REDUCIDO" (10%) o "SUPER" (4%)
     * @return Precio con IVA aplicado
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
     * Calcula el precio final aplicando el IVA configurado en el atributo tipoIva
     * Los productos digitales no tienen coste de envío
     * @return Precio final con IVA incluido
     */
    @Override
    public double calcularPrecioFinal()
    {
        return aplicarIVA(this.tipoIva);
    }
 
    /**
     * Devuelve la tasa de IVA aplicada como valor decimal
     * @return Tasa de IVA
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
