package proyecto_entornos;
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
     * Constructor completo con todos los atributos
     * @param id Identificador unico del cliente
     * @param nombre Nombre completo del cliente
     * @param correo Correo electrónico del cliente
     * @param direccion Dirección postal del cliente
     * @param annosAntiguedad Años que lleva siendo cliente
     * @param esVip Si el cliente es vip
     * @param pais País de residencia del cliente
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

    //CORREO
    public String getCorreo()
    {
        return this.correo;
    }
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    //DIRECCION
    public String getDireccion()
    {
        return this.direccion;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    //ANTIGUEDAD
    public int getAnnosAntiguedad() 
    { 
        return this.annosAntiguedad; 
    }
    public void setAnnosAntiguedad(int annosAntiguedad) 
    { 
        this.annosAntiguedad = annosAntiguedad; 
    }
 
    //VIP
    public boolean isEsVip() 
    { 
        return this.esVip; 
    }
    public void setEsVip(boolean esVip) 
    { 
        this.esVip = esVip; 
    }
 
    //PAIS
    public String getPais() 
    { 
        return this.pais; 
    }
    public void setPais(String pais) 
    { 
        this.pais = pais; 
    }

    /**
     * Calcula el porcentaje de descuento de fidelidad del cliente
     * vip + más de 3 años: 15%
     * vip sin antigüedad suficiente: 10%
     * No vip con más de 5 años: 5%
     * Sin descuento: 0%
     * @return Porcentaje de descuento como valor entre 0.0 y 1.0
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