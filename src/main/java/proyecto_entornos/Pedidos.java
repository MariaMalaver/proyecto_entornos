
package proyecto_entornos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a customer order.
 * Stores the order identifier, associated customer,
 * products, and quantities.
 */
public class Pedidos 
{
    /* LISTAS */
    private String idPedido;
    private ArrayList<Producto> productos;
    private Map<String, Integer> cantidades;
    
    /* ATRIBUTOS - ASOCIACION */
    private Cliente cliente;

    private static int contadorPedidos = 1;

    /* CONSTRUCTOR */
    /**
     * Creates a new empty order.
     * Generates a unique order identifier automatically.
     */
    public Pedidos()
    {
        this.idPedido = "PED-" + contadorPedidos++;
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
        this.cliente = null;
    }

    /*GETTERS*/
    /**
     * Returns the unique identifier of the order.
     *
     * @return the order identifier
     */
    public String getIdPedido() 
    { 
        return idPedido; 
    }

    /**
     * Returns the customer associated with the order.
     *
     * @return the customer associated with the order, or null if none is assigned
     */
    public Cliente getCliente() 
    { 
        return cliente; 
    }
    
    /**
     * Returns the list of products in the order
     * @return List of products
     */
    public ArrayList<Producto> getProducto() 
    {    
        return productos;
    }

    /**
     * Returns the map of quantities by product name
     * @return Name-quantity map
     */
    public Map<String, Integer> getCantidades()
    {
        return cantidades;
    }

    /* METODOS */
    /**
     * Assigns the customer placing this order
     * @param cliente The customer placing the order
     * @throws IllegalArgumentException if the customer
     */
    public void asignarCliente(Cliente cliente)
    {
        this.cliente = cliente;
        System.out.println("Este cliente " + cliente.getNombre() + " acaba de realizar un pedido.");
    }
 
    /**
     * Adds a product to the order with a quantity of 1
     * @param producto Product to add
     * @throws IllegalArgumentException if the product
     */
    public void agregarProducto(Producto producto)
    {
        productos.add(producto);
        cantidades.merge(producto.getNombre(), 1, Integer::sum);
        System.out.println("El producto " + producto.getNombre() + " ha sido añadido al pedido.");
    }
 
    /**
     * Removes a product from the order.
     * @param producto Product to remove
     */
    public void eliminarProducto(Producto producto)
    {
        productos.remove(producto);
        System.out.println("El producto " + producto.getNombre() + " ha sido eliminado del pedido.");
    }
 
    /**
     * Calculates the order total by summing the final price of all products.
     * @return Order total in euros
     * @throws IllegalStateException if the order contains no products
     */
    public double calcularTotal()
    {
        if (productos.isEmpty())
        {
            throw new IllegalStateException("No se puede calcular el total sin productos.");
        }
        double total = 0;
        for (Producto producto : productos)
        {
            total += producto.calcularPrecioFinal();
        }
        return total;
    }
 
    /**
     * Displays a complete summary of the order to the console:
     * ID, customer, list of products, and total.
     * @throws IllegalStateException if no customer has been assigned to the order
     */
    public void mostrarResumen()
    {
        if (cliente == null)
        {
            throw new IllegalStateException("No se puede mostrar el resumen sin un cliente asignado.");
        }
        System.out.println("********** RESUMEN DEL PEDIDO **********");
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Productos: ");
        for (Producto producto : productos)
        {
            System.out.println(producto);
        }
        System.out.println("Total: " + calcularTotal() + "€");
    }
}