
package proyecto_entornos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Pedidos()
    {
        this.idPedido = "PED-" + contadorPedidos++;
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
        this.cliente = null;
    }

    /*GETTERS*/
    public String getIdPedido() 
    { 
        return idPedido; 
    }
    public Cliente getCliente() 
    { 
        return cliente; 
    }
    
    /**
     * Devolvemos la lista de productos del pedido
     * @return Lista de productos
     */
    public ArrayList<Producto> getProducto() 
    {    
        return productos;
    }

    /**
     * Devolvemos el mapa de cantidades por nombre de producto
     * @return Mapa nombre-cantidad
     */
    public Map<String, Integer> getCantidades()
    {
        return cantidades;
    }

    /* METODOS */
    /**
     * Asignamos el cliente que realiza este pedido
     * @param cliente Cliente que realiza el pedido
     * @throws IllegalArgumentException si el cliente
     */
    public void asignarCliente(Cliente cliente)
    {
        this.cliente = cliente;
        System.out.println("Este cliente " + cliente.getNombre() + " acaba de realizar un pedido.");
    }
 
    /**
     * Agregamos un producto al pedido con cantidad 1
     * @param producto Producto a añadir
     * @throws IllegalArgumentException si el producto
     */
    public void agregarProducto(Producto producto)
    {
        productos.add(producto);
        cantidades.merge(producto.getNombre(), 1, Integer::sum);
        System.out.println("El producto " + producto.getNombre() + " ha sido añadido al pedido.");
    }
 
    /**
     * Eliminamos un producto del pedido.
     * @param producto Producto a eliminar
     */
    public void eliminarProducto(Producto producto)
    {
        productos.remove(producto);
        System.out.println("El producto " + producto.getNombre() + " ha sido eliminado del pedido.");
    }
 
    /**
     * Calcula el total del pedido sumando el precio final de todos los productos.
     * @return Total del pedido en euros
     * @throws IllegalStateException si el pedido no tiene productos
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
     * Muestra por consola un resumen completo del pedido:
     * id, cliente, lista de productos y total.
     * @throws IllegalStateException si no se ha asignado un cliente al pedido
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