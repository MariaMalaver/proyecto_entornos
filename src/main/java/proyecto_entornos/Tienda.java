package proyecto_entornos;

import java.util.ArrayList;
import java.util.List;

public class Tienda
{
    /*ATRIBUTOS*/
    private String nombre;
    private List<Factura> facturas;

    /**
     * Constructor de la Tienda
     * @param nombre Nombre comercial de la tienda
     */
    public Tienda(String nombre)
    {
        this.nombre = nombre;
        this.facturas = new ArrayList<>();
    }

    /*GETTERS*/
    /** @return Nombre de la tienda */
    public String getNombre() 
    { 
        return nombre; 
    }

    /** @return Lista de facturas emitidas por esta tienda */
    public List<Factura> getFacturas() 
    { 
        return facturas; 
    }

    /**
     * Punto de entrada principal que orquesta el flujo completo de una venta
     * Pasos:
     * 1. Valida que el pedido no esté vacío
     * 2. Calcula el subtotal bruto del pedido
     * 3. Desglosa IVA y envío
     * 4. Aplica descuento de fidelidad del cliente
     * 5. Genera y registra la Factura
     * @param cliente Cliente que realiza la compra
     * @param pedido Pedido con la lista de productos
     * @return Factura generada con el desglose completo
     * @throws IllegalArgumentException si el cliente o pedido son nulos
     * @throws IllegalStateException si el pedido no tiene productos
     */
    public Factura realizarVenta(Cliente cliente, Pedidos pedido)
    {
        // Validaciones de entrada
        if (cliente == null)
        {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (pedido == null)
        {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (pedido.getProducto().isEmpty())
        {
            throw new IllegalStateException("No se puede procesar un pedido sin productos.");
        }

        // 1. Calcular totales desglosados
        double totalNeto = calcularTotalNeto(pedido);
        double totalIva = calcularTotalIva(pedido);
        double totalEnvio = calcularTotalEnvio(pedido, cliente.getPais());
        double subtotal = totalNeto + totalIva + totalEnvio;

        // 2. Aplicar descuento de fidelidad del cliente
        double porcentajeDescuento = cliente.calcularDescuentoFidelidad();
        double descuentoAplicado = subtotal * porcentajeDescuento;
        double totalFinal = subtotal - descuentoAplicado;

        // 3. Generar factura
        Factura factura = new Factura(totalNeto, totalIva, totalEnvio, descuentoAplicado, totalFinal, cliente.getNombre(), pedido.getIdPedido());

        // 4. Registrar la factura en el historial de la tienda
        facturas.add(factura);
        return factura;
    }

    /**
     * Calcula la suma de los precios base de todos los productos
     * @param pedido Pedido con los productos
     * @return Total neto sin impuestos
     */
    private double calcularTotalNeto(Pedidos pedido)
    {
        double neto = 0;
        for (Producto p : pedido.getProducto())
        {
            neto += p.getPrecio();
        }
        return neto;
    }

    /**
     * Calcula el importe total de IVA de los productos digitales del pedido
     * Los productos físicos no aplican IVA en este método
     * @param pedido Pedido con los productos
     * @return Importe total de IVA
     */
    private double calcularTotalIva(Pedidos pedido)
    {
        double totalIva = 0;
        for (Producto p : pedido.getProducto())
        {
            if (p instanceof ProductoDigital)
            {
                ProductoDigital pd = (ProductoDigital) p;
                totalIva += pd.calcularPrecioFinal() - pd.getPrecio();
            }
        }
        return totalIva;
    }

    /**
     * Calcula el coste total de envío de los productos físicos del pedido
     * El coste depende del país del cliente
     * @param pedido Pedido con los productos
     * @param paisCliente País de destino del envío
     * @return Coste total de envío
     */
    private double calcularTotalEnvio(Pedidos pedido, String paisCliente)
    {
        double totalEnvio = 0;
        String pais = (paisCliente != null) ? paisCliente : "España";

        for (Producto p : pedido.getProducto())
        {
            if (p instanceof ProductoFisico)
            {
                ProductoFisico pf = (ProductoFisico) p;
                totalEnvio += pf.calcularCosteEnvio(pais);
            }
        }
        return totalEnvio;
    }
}