package proyecto_entornos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegresionTest
{
    // REGRESIÓN - PRODUCTO BASE
    @Test
    @DisplayName("REG-01: Constructor Producto funciona correctamente (Parte 3 - Caso 5)")
    void testConstructorProducto()
    {
        // ARRANGE & ACT
        Producto p = new ProductoDigital("TestDigital", 50.0, 100, "Lic");

        // ASSERT
        assertEquals("TestDigital", p.getNombre());
        assertEquals(50.0, p.getPrecio(), 0.001);
    }

    @Test
    @DisplayName("REG-02: Precio negativo lanza excepción (Parte 3 - Caso 6)")
    void testPrecioNegativoLanzaExcepcion()
    {
        assertThrows(IllegalArgumentException.class, () -> new ProductoDigital("Test", -10.00, 0, "Lic"), "Un precio negativo debe lanzar IllegalArgumentException");
    }

    // REGRESIÓN - PRODUCTO DIGITAL
    @Test
    @DisplayName("REG-03: IVA general 21% sigue calculándose correctamente (Parte 3 - Caso 1)")
    void testIvaGeneralProductoDigital()
    {
        // ARRANGE
        ProductoDigital producto = new ProductoDigital("Test", 100.00, 0, "Lic", "GENERAL");

        // ACT & ASSERT
        assertEquals(121.00, producto.calcularPrecioFinal(), 0.001,"El precio final debe incluir el 21% de IVA");
    }

    @Test
    @DisplayName("REG-04: Precio con IVA es distinto al precio base (Parte 3 - Caso 8)")
    void testPrecioFinalDistintoAlBase()
    {
        ProductoDigital producto = new ProductoDigital("Test", 100.00, 0, "Lic", "GENERAL");
        assertNotEquals(producto.getPrecio(), producto.calcularPrecioFinal(),"El precio con IVA no puede ser igual al precio base");
    }

    @Test
    @DisplayName("REG-05: IVA reducido (10%) calcula correctamente - nuevo tipo")
    void testIvaReducido()
    {
        ProductoDigital producto = new ProductoDigital("eBook", 50.00, 10, "Lic", "REDUCIDO");
        assertEquals(55.00, producto.calcularPrecioFinal(), 0.001, "IVA reducido 10% sobre 50€ = 55€");
    }

    @Test
    @DisplayName("REG-06: IVA super reducido (4%) calcula correctamente - nuevo tipo")
    void testIvaSuperReducido()
    {
        ProductoDigital producto = new ProductoDigital("LibroElectronico", 25.00, 5, "Lic", "SUPER");
        assertEquals(26.00, producto.calcularPrecioFinal(), 0.001, "IVA super 4% sobre 25€ = 26€");
    }

    // REGRESIÓN - PRODUCTO FISICO
    @Test
    @DisplayName("REG-07: Coste de envío suma al precio base (Parte 3 - Caso 2)")
    void testCosteEnvioSumaAlPrecio()
    {
        // ARRANGE
        ProductoFisico producto = new ProductoFisico("Teclado", 59.99, 6.80);

        // ACT & ASSERT
        assertEquals(66.79, producto.calcularPrecioFinal(), 0.001,"El precio final debe sumar el coste de envío");
    }

    @Test
    @DisplayName("REG-08: Con envío, precio final mayor que precio base (Parte 3 - Caso 9)")
    void testPrecioFinalMayorQuePrecioBase()
    {
        ProductoFisico producto = new ProductoFisico("Monitor", 100.00, 10.00);
        assertFalse(producto.calcularPrecioFinal() <= producto.getPrecio(), "Con envío, el precio final debe ser mayor que el precio base");
    }

    @Test
    @DisplayName("REG-09: Coste de envío negativo lanza excepción (Parte 3 - Caso 10)")
    void testCosteEnvioNegativoLanzaExcepcion()
    {
        assertThrows(IllegalArgumentException.class, () -> new ProductoFisico("Test", 10.00, -2.00), "Un coste de envío negativo debe lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("REG-10: Envío a Francia es 5€ con el nuevo sistema de zonas")
    void testEnvioFranciaEsCincoPesos()
    {
        ProductoFisico producto = new ProductoFisico("Monitor", 100.00, 1.0, "España");
        assertEquals(5.0, producto.calcularCosteEnvio("Francia"), 0.001, "El envío a Francia debe ser 5€");
    }

    @Test
    @DisplayName("REG-11: Envío a España es 0€")
    void testEnvioEspannaEsCero()
    {
        ProductoFisico producto = new ProductoFisico("Monitor", 100.00, 1.0, "España");
        assertEquals(0.0, producto.calcularCosteEnvio("España"), 0.001, "El envío a España debe ser 0€");
    }

    @Test
    @DisplayName("REG-12: Envío al resto del mundo es 10€")
    void testEnvioRestoMundoEsDiezPesos()
    {
        ProductoFisico producto = new ProductoFisico("Monitor", 100.00, 1.0, "España");
        assertEquals(10.0, producto.calcularCosteEnvio("Alemania"), 0.001, "El envío al resto del mundo debe ser 10€");
    }

    // REGRESIÓN - PEDIDOS
    @Test
    @DisplayName("REG-13: Total del pedido correcto con un producto (Parte 3 - Caso 3)")
    void testTotalPedidoConUnProducto()
    {
        // ARRANGE
        Pedidos pedido = new Pedidos();
        ProductoFisico monitor = new ProductoFisico("Monitor", 159.99, 16.95);
        Cliente cliente = new Cliente("Maria", "m@test.com", "Calle 1");
        pedido.asignarCliente(cliente);
        pedido.agregarProducto(monitor);

        // ACT & ASSERT
        assertEquals(176.94, pedido.calcularTotal(), 0.01, "El total debe coincidir con el precio final del producto");
    }

    @Test
    @DisplayName("REG-14: Productos añadidos correctamente al pedido (Parte 3 - Caso 4)")
    void testProductosAnadidosAlPedido()
    {
        // ARRANGE
        Pedidos pedido = new Pedidos();
        Cliente cliente = new Cliente("Javier", "j@test.com", "Calle 2");
        pedido.asignarCliente(cliente);
        pedido.agregarProducto(new ProductoDigital("Software A", 50.0, 0, "Lic"));
        pedido.agregarProducto(new ProductoFisico("Ratón", 25.0, 3.0));

        // ASSERT
        assertEquals(2, pedido.getProducto().size(), "El pedido debe contener exactamente 2 productos");
    }

    @Test
    @DisplayName("REG-15: Pedido vacío lanza excepción al calcular total (Parte 3 - Caso 7)")
    void testPedidoVacioLanzaExcepcion()
    {
        Pedidos pedido = new Pedidos();
        assertThrows(IllegalStateException.class, () -> pedido.calcularTotal(),"Un pedido sin productos no puede calcular el total");
    }

    @Test
    @DisplayName("REG-16: Eliminar producto reduce el tamaño de la lista")
    void testEliminarProductoReduceLista()
    {
        // ARRANGE
        Pedidos pedido = new Pedidos();
        Cliente cliente = new Cliente("Test", "t@test.com", "Dir");
        ProductoDigital prod = new ProductoDigital("SW", 10.0, 0, "Lic");
        pedido.asignarCliente(cliente);
        pedido.agregarProducto(prod);

        // ACT
        pedido.eliminarProducto(prod);

        // ASSERT
        assertEquals(0, pedido.getProducto().size(), "La lista debe estar vacía tras eliminar el producto");
    }

    // REGRESIÓN - CLIENTE
    @Test
    @DisplayName("REG-17: Constructor cliente correcto (Parte 3 - Caso 5)")
    void testConstructorClienteCorrecto()
    {
        Cliente cliente = new Cliente("Maria", "m@test.com", "Dir");
        assertEquals("Maria", cliente.getNombre(), "El nombre debe coincidir con el introducido");
    }

    @Test
    @DisplayName("REG-18: Cliente sin descuento devuelve 0%")
    void testClienteSinDescuento()
    {
        Cliente cliente = new Cliente("C001", "Pedro", "p@test.com", "Dir", 0, false, "España");
        assertEquals(0.0, cliente.calcularDescuentoFidelidad(), 0.001, "Cliente sin VIP y sin antigüedad no debe tener descuento");
    }

    @Test
    @DisplayName("REG-19: Cliente VIP sin antigüedad suficiente tiene 10% descuento")
    void testClienteVipSinAntiguedadSuficiente()
    {
        Cliente cliente = new Cliente("C002", "Ana", "a@test.com", "Dir", 2, true, "España");
        assertEquals(0.10, cliente.calcularDescuentoFidelidad(), 0.001, "Cliente VIP con menos de 3 años debe tener 10% de descuento");
    }

    // PRUEBAS PARAMETRIZADAS
    @ParameterizedTest
    @CsvSource({
        "100.0, 0.0, 121.0",
        "200.0, 0.0, 242.0",
        "50.0,  5.0, 55.0",
        "75.0,  3.0, 78.0"
    })
    @DisplayName("REG-20: calcularTotal() con distintos productos y cantidades")
    void testCalcularTotalParametrizado(double precioDigital, double envioFisico, double totalEsperado)
    {
        // ARRANGE
        Pedidos pedido = new Pedidos();
        Cliente cliente = new Cliente("Test", "t@test.com", "Dir");
        pedido.asignarCliente(cliente);

        if (envioFisico == 0.0)
        {
            pedido.agregarProducto(new ProductoDigital("Prod", precioDigital, 0, "Lic", "GENERAL"));
        }
        else
        {
            pedido.agregarProducto(new ProductoFisico("Prod", precioDigital, envioFisico));
        }

        // ACT & ASSERT
        assertEquals(totalEsperado, pedido.calcularTotal(), 0.01, "El total calculado debe coincidir con el esperado");
    }
}