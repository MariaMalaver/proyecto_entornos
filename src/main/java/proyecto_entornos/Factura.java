package proyecto_entornos;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents an invoice generated for a customer order.
 * Stores the invoice identifier, issue date, order totals, applied discount, customer information, and final amount.
 */
public class Factura
{
    /*ATRIBUTOS*/
    private String codigoFactura;
    private LocalDate fechaEmision;
    private double totalNeto;
    private double totalIva;
    private double totalEnvio;
    private double descuentoAplicado;
    private double totalFinal;
    private String nombreCliente;
    private String idPedido;

    /**
     * Invoice constructor
     * The code is automatically generated with a "FAC-" prefix followed by 8 random characters
     * @param totalNeto Sum of base prices excluding taxes and shipping
     * @param totalIva Total VAT amount applied
     * @param totalEnvio Total shipping cost amount
     * @param descuentoAplicado Loyalty discount amount
     * @param totalFinal Final amount to be paid by the customer
     * @param nombreCliente Name of the customer holding the invoice
     * @param idPedido Identifier of the corresponding order
     */
    public Factura(double totalNeto, double totalIva, double totalEnvio, double descuentoAplicado, double totalFinal, String nombreCliente, String idPedido)
    {
        this.codigoFactura = "FAC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fechaEmision = LocalDate.now();
        this.totalNeto = totalNeto;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.descuentoAplicado = descuentoAplicado;
        this.totalFinal = totalFinal;
        this.nombreCliente = nombreCliente;
        this.idPedido = idPedido;
    }

    /*GETTERS*/

    /**
     * Returns the automatically generated invoice code.
     *
     * @return the unique invoice code
     */
    public String getCodigoFactura()
    { 
        return codigoFactura; 
    }

    /**
     * Returns the invoice issue date.
     *
     * @return the date on which the invoice was issued
     */
    public LocalDate getFechaEmision() 
    { 
        return fechaEmision; 
    }

    /**
     * Returns the net total before taxes and shipping.
     *
     * @return the net total amount
     */
    public double getTotalNeto() 
    { 
        return totalNeto; 
    }

    /**
     * Returns the total VAT amount.
     *
     * @return the VAT amount
     */
    public double getTotalIva() 
    { 
        return totalIva; 
    }

    /**
     * Returns the total shipping cost.
     *
     * @return the total shipping cost
     */
    public double getTotalEnvio() 
    { 
        return totalEnvio; 
    }

    /**
     * Returns the loyalty discount applied to the invoice.
     *
     * @return the applied discount amount
     */
    public double getDescuentoAplicado() 
    { 
        return descuentoAplicado; 
    }

    /**
     * Returns the final amount to be paid by the customer.
     *
     * @return the final invoice amount
     */
    public double getTotalFinal() 
    { 
        return totalFinal; 
    }

    /**
     * Returns the name of the customer associated with the invoice.
     *
     * @return the customer's name
     */
    public String getNombreCliente() 
    { 
        return nombreCliente; 
    }

    /**
     * Returns the identifier of the associated order.
     *
     * @return the order identifier
     */
    public String getIdPedido() 
    { 
        return idPedido; 
    }

    /**
     * Print the invoice.
     * Shows the invoice code, date, customer, order, VAT, shipping cost, discount, and final total.
     */
    public void mostrarDesglose()
    {
        System.out.println("============================================");
        System.out.println("              FACTURA EMITIDA               ");
        System.out.println("============================================");
        System.out.println("Código Factura: " + codigoFactura);
        System.out.println("Fecha Emisión: " + fechaEmision);
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("--------------------------------------------");
        System.out.printf("Total Neto: %.2f€%n", totalNeto);
        System.out.printf("Total IVA: %.2f€%n", totalIva);
        System.out.printf("Total Envío: %.2f€%n", totalEnvio);
        System.out.printf("Descuento: -%.2f€%n", descuentoAplicado);
        System.out.println("--------------------------------------------");
        System.out.printf("TOTAL FINAL: %.2f€%n", totalFinal);
        System.out.println("============================================");
    }

    /**
     * Returns a string representation of the invoice.
     *
     * @return a string containing the main invoice information
     */
    @Override
    public String toString()
    {
        return "Factura{" +
               "codigo='" + codigoFactura + '\'' +
               ", fecha=" + fechaEmision +
               ", totalFinal=" + totalFinal +
               ", cliente='" + nombreCliente + '\'' +
               '}';
    }
}