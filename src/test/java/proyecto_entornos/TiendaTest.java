package proyecto_entornos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TiendaTest
{
    private Tienda tienda;
    private Cliente clienteEspanna;
    private Cliente clienteVip;
    private Cliente clienteFrancia;
    private ProductoDigital software;
    private ProductoFisico teclado;
    private Pedidos pedido;

    @BeforeEach
    void setUp()
    {
        tienda = new Tienda("TechShop");
        clienteEspanna = new Cliente("C001", "Ana", "ana@test.com", "Calle Mayor 1", 0, false, "España");
        clienteVip = new Cliente("C002", "Carlos", "carlos@test.com", "Av. Principal 5", 5, true, "España");
        clienteFrancia = new Cliente("C003", "Pierre", "pierre@test.com", "Rue de Paris 3", 0, false, "Francia");
        software = new ProductoDigital("Antivirus", 100.00, 440, "Licencia anual", "GENERAL");
        teclado  = new ProductoFisico("Teclado mecánico", 50.00, 1.2, "España");
        pedido   = new Pedidos();
    }

    // PRUEBAS DE INTEGRACIÓN POSITIVAS
    @Test
    @DisplayName("IT-01: Venta completa genera factura con datos correctos")
    void testVentaCompletaGeneraFactura()
    {
        // ARRANGE
        pedido.asignarCliente(clienteEspanna);
        pedido.agregarProducto(software);

        // ACT
        Factura factura = tienda.realizarVenta(clienteEspanna, pedido);

        // ASSERT
        assertNotNull(factura, "La factura no debe ser nula");
        assertEquals("Ana", factura.getNombreCliente());
        assertTrue(factura.getTotalFinal() > 0, "El total final debe ser mayor que 0");
        assertNotNull(factura.getCodigoFactura(), "El código de factura debe generarse automáticamente");
        assertNotNull(factura.getFechaEmision(), "La fecha de emisión debe generarse automáticamente");
    }

    @Test
    @DisplayName("IT-02: IVA general (21%) se refleja correctamente en la factura")
    void testIvaGeneralEnFactura()
    {
        // ARRANGE
        pedido.asignarCliente(clienteEspanna);
        pedido.agregarProducto(software); // 100€ + 21% IVA = 121€

        // ACT
        Factura factura = tienda.realizarVenta(clienteEspanna, pedido);

        // ASSERT
        assertEquals(100.0, factura.getTotalNeto(), 0.01, "El neto debe ser el precio base");
        assertEquals(21.0,  factura.getTotalIva(),  0.01, "El IVA debe ser 21€ sobre 100€");
        assertEquals(0.0,   factura.getTotalEnvio(), 0.01, "Producto digital no tiene envío");
        assertEquals(0.0,   factura.getDescuentoAplicado(), 0.01, "Sin descuento para cliente estándar");
        assertEquals(121.0, factura.getTotalFinal(), 0.01, "Total final debe ser 121€");
    }

    @Test
    @DisplayName("IT-03: Descuento VIP (15%) se aplica correctamente sobre el total")
    void testDescuentoVipAplicado()
    {
        // ARRANGE
        pedido.asignarCliente(clienteVip);
        pedido.agregarProducto(software);

        // ACT
        Factura factura = tienda.realizarVenta(clienteVip, pedido);

        // ASSERT
        double subtotal = 121.0;
        double descuentoEsperado = subtotal * 0.15;
        double totalEsperado = subtotal - descuentoEsperado;

        assertEquals(descuentoEsperado, factura.getDescuentoAplicado(), 0.01, "El descuento VIP debe ser el 15% del subtotal");
        assertEquals(totalEsperado, factura.getTotalFinal(), 0.01, "El total final debe tener el descuento VIP aplicado");
    }

    @Test
    @DisplayName("IT-04: Envío a Francia (5€) se refleja en la factura")
    void testEnvioFranciaEnFactura()
    {
        // ARRANGE
        pedido.asignarCliente(clienteFrancia);
        pedido.agregarProducto(teclado);
        // ACT
        Factura factura = tienda.realizarVenta(clienteFrancia, pedido);

        // ASSERT
        assertEquals(5.0, factura.getTotalEnvio(), 0.01, "Envío a Francia debe ser 5€");
        assertEquals(50.0, factura.getTotalNeto(), 0.01, "Neto debe ser el precio base del teclado");
    }

    @Test
    @DisplayName("IT-05: Pedido con producto digital y físico desglosa correctamente")
    void testPedidoMixtoDesglose()
    {
        // ARRANGE
        pedido.asignarCliente(clienteEspanna);
        pedido.agregarProducto(software);
        pedido.agregarProducto(teclado);

        // ACT
        Factura factura = tienda.realizarVenta(clienteEspanna, pedido);

        // ASSERT
        assertEquals(150.0, factura.getTotalNeto(),  0.01, "Neto = 100 + 50");
        assertEquals(21.0,  factura.getTotalIva(),   0.01, "IVA solo del digital");
        assertEquals(0.0,   factura.getTotalEnvio(), 0.01, "Envío España = 0€");
        assertEquals(171.0, factura.getTotalFinal(), 0.01, "Total = 150 + 21");
    }

    @Test
    @DisplayName("IT-06: La tienda registra la factura en su historial")
    void testFacturaRegistradaEnTienda()
    {
        // ARRANGE
        pedido.asignarCliente(clienteEspanna);
        pedido.agregarProducto(software);

        // ACT
        tienda.realizarVenta(clienteEspanna, pedido);

        // ASSERT
        assertEquals(1, tienda.getFacturas().size(), "La tienda debe tener 1 factura registrada");
    }

    @Test
    @DisplayName("IT-07: Múltiples ventas generan múltiples facturas en la tienda")
    void testMultiplesVentasGeneranMultiplesFacturas()
    {
        // ARRANGE
        Pedidos pedido2 = new Pedidos();
        pedido.asignarCliente(clienteEspanna);
        pedido.agregarProducto(software);
        pedido2.asignarCliente(clienteVip);
        pedido2.agregarProducto(teclado);

        // ACT
        tienda.realizarVenta(clienteEspanna, pedido);
        tienda.realizarVenta(clienteVip, pedido2);

        // ASSERT
        assertEquals(2, tienda.getFacturas().size(), "Deben haberse generado 2 facturas");
    }


    // PRUEBAS DE INTEGRACIÓN NEGATIVAS
    @Test
    @DisplayName("IT-08: Venta con pedido vacio lanza IllegalStateException")
    void testVentaPedidoVacioLanzaExcepcion()
    {
        // ARRANGE
        pedido.asignarCliente(clienteEspanna);

        // ACT & ASSERT
        assertThrows(IllegalStateException.class, () -> tienda.realizarVenta(clienteEspanna, pedido),"Un pedido vacío debe lanzar IllegalStateException");
    }

    @Test
    @DisplayName("IT-09: Venta con cliente nulo lanza IllegalArgumentException")
    void testVentaClienteNuloLanzaExcepcion()
    {
        // ARRANGE
        pedido.agregarProducto(software);

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> tienda.realizarVenta(null, pedido),"Un cliente nulo debe lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("IT-10: Venta con pedido nulo lanza IllegalArgumentException")
    void testVentaPedidoNuloLanzaExcepcion()
    {
        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> tienda.realizarVenta(clienteEspanna, null),"Un pedido nulo debe lanzar IllegalArgumentException");
    }
}