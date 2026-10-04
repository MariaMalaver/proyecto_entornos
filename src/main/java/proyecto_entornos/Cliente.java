package proyecto_entornos;

/**
 * Represents a customer of the store
 * Stores the customer's personal information, loyalty status, and years of customer relationship
 */
public class Cliente
{
    /*ATRIBUTOS*/
    private String id;
    private String nombre;
    private String correo;
    private String direccion;
    private int annosAntiguedad;
    private boolean esVip;
    private String pais;

    /*CONSTRUCTOR*/
    
    /**
     * Creates a new customer with the basic information
     * @param nombre Customer's full name
     * @param correo Customer's email address
     * @param direccion Customer's postal address
     */
    public Cliente(String nombre, String correo, String direccion)
    {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.annosAntiguedad = 0;
        this.esVip = false;
        this.pais = "España";
    }

    /**
     * Creates a new customer with the basic information
     * @param id Unique customer identifier
     * @param nombre Customer's full name
     * @param correo Customer's email address
     * @param direccion Customer's postal address
     * @param annosAntiguedad Number of years as a customer
     * @param esVip Whether the customer is VIP
     * @param pais Customer's country of residence
     */
    public Cliente(String id, String nombre, String correo, String direccion, int annosAntiguedad, boolean esVip, String pais)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.annosAntiguedad = annosAntiguedad;
        this.esVip = esVip;
        this.pais = pais;
    }
    
    /*METODOS GET Y SET*/

    //ID
    /**
     * Returns the unique identifier of the client
     *
     * @return the client identifier
     */
    public String getId() 
    { 
        return this.id; 
    }

    /**
     * Sets the unique identifier of the client
     *
     * @param id the new client identifier
     */
    public void setId(String id) 
    { 
        this.id = id; 
    }

    //NOMBRE
    /**
     * Returns the full name of the customer
     *
     * @return the customer's full name
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * Sets the full name of the customer
     *
     * @param nombre the new full name
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    //CORREO
    /**
     * Returns the customer's email address
     *
     * @return the customer's email address
     */
    public String getCorreo()
    {
        return this.correo;
    }

    /**
     * Sets the customer's email address
     *
     * @param correo the new email address
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    //DIRECCION
    /**
     * Returns the customer's postal address
     *
     * @return the customer's postal address
     */
    public String getDireccion()
    {
        return this.direccion;
    }

    /**
     * Sets the customer's postal address
     *
     * @param direccion the new postal address
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    //ANTIGUEDAD
    /**
     * Returns the number of years the customer has been registered
     *
     * @return the number of years as a customer
     */
    public int getAnnosAntiguedad() 
    { 
        return this.annosAntiguedad; 
    }

    /**
     * Sets the number of years the customer has been registered
     *
     * @param annosAntiguedad the new number of years as a customer
     */
    public void setAnnosAntiguedad(int annosAntiguedad) 
    { 
        this.annosAntiguedad = annosAntiguedad; 
    }
 
    //VIP
    /**
     * Checks whether the customer has VIP status.
     *
     * @return true if the customer is VIP, false otherwise
     */
    public boolean isEsVip() 
    { 
        return this.esVip; 
    }

    /**
     * Sets the VIP status of the customer.
     *
     * @param esVip true to assign VIP status, false otherwise
     */
    public void setEsVip(boolean esVip) 
    { 
        this.esVip = esVip; 
    }
 
    //PAIS
    /**
     * Returns the customer's country of residence.
     *
     * @return the customer's country
     */
    public String getPais() 
    { 
        return this.pais; 
    }

    /**
     * Sets the customer's country of residence.
     *
     * @param pais the new country of residence
     */
    public void setPais(String pais) 
    { 
        this.pais = pais; 
    }

    /**
     * Calculates the customer loyalty discount percentage
     * VIP + more than 3 years: 15%
     * VIP without sufficient tenure: 10%
     * Non-VIP with more than 5 years: 5%
     * No discount: 0%
     * @return Discount percentage as a value between 0.0 and 1.0
     */
    public double calcularDescuentoFidelidad()
    {
        if (esVip && annosAntiguedad > 3)
        {
            return 0.15;
        }
        else if (esVip)
        {
            return 0.10;
        }
        else if (annosAntiguedad > 5)
        {
            return 0.05;
        }
        return 0.0;
    }
}