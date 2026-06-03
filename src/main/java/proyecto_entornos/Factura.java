package proyecto_entornos;

import java.time.LocalDate;
import java.util.UUID;

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
     * Constructor de Factura. El código se genera automáticamente.
     * @param totalNeto Suma de precios base sin impuestos ni envío
     * @param totalIva Importe total de IVA aplicado
     * @param totalEnvio Importe total de costes de envío
     * @param descuentoAplicado Importe descontado por fidelidad
     * @param totalFinal Importe final a pagar por el cliente
     * @param nombreCliente Nombre del cliente titular de la factura
     * @param idPedido Identificador del pedido al que corresponde
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
    /** @return Codigo unico de la factura generado automaticamente */
    public String getCodigoFactura()
    { 
        return codigoFactura; 
    }

    /** @return Fecha de emisión de la factura */
    public LocalDate getFechaEmision() 
    { 
        return fechaEmision; 
    }

    /** @return Total neto sin impuestos ni envío */
    public double getTotalNeto() 
    { 
        return totalNeto; 
    }

    /** @return Importe total de IVA */
    public double getTotalIva() 
    { 
        return totalIva; 
    }

    /** @return Importe total de costes de envío */
    public double getTotalEnvio() 
    { 
        return totalEnvio; 
    }

    /** @return Descuento aplicado por fidelidad */
    public double getDescuentoAplicado() 
    { 
        return descuentoAplicado; 
    }

    /** @return Importe final a pagar */
    public double getTotalFinal() 
    { 
        return totalFinal; 
    }

    /** @return Nombre del cliente */
    public String getNombreCliente() 
    { 
        return nombreCliente; 
    }

    /** @return ID del pedido asociado */
    public String getIdPedido() 
    { 
        return idPedido; 
    }

    /**
     * Imprime la factura
     * Muestra el importe de cada concepto: IVA, envio y descuentos.
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