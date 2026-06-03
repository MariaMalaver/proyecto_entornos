package proyecto_entornos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// tests para comprobar que los pedidos calculan bien el total
class PedidosTest {

    private Pedidos pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedidos();
    }

    //monitor 159.99 + envío 16.95 tiene que dar 176.94
    @Test
    @DisplayName("El total de un pedido con un producto físico es correcto")
    void testTotalConUnProductoFisico() {
        // Arrange
        ProductoFisico monitor = new ProductoFisico("Monitor", 159.99, 16.95);
        pedido.agregarProducto(monitor);
        // Act
        double resultado = pedido.calcularTotal();
        // Assert
        assertEquals(176.94, resultado, 0.01,"159.99 + 16.95 de envío tiene que dar 176.94");
    }

    //ebook 24.99 con IVA tiene que dar aproximadamente 30.24
    @Test
    @DisplayName("El total de un pedido con un producto digital incluye IVA")
    void testTotalConUnProductoDigital() {
        // Arrange
        ProductoDigital ebook = new ProductoDigital("Curso Java", 24.99, 140, "Licencia");
        pedido.agregarProducto(ebook);
        // Act
        double resultado = pedido.calcularTotal();
        // Assert
        assertEquals(30.2379, resultado, 0.01,"24.99 con el 21% de IVA tiene que dar aproximadamente 30.24");
    }

    //si el pedido está vacío no se puede calcular el total
    @Test
    @DisplayName("No se puede calcular el total de un pedido sin productos")
    void testTotalSinProductosFalla() {
        assertThrows(IllegalStateException.class,() -> pedido.calcularTotal(),"Sin productos no tiene sentido calcular el total");
    }

    //un pedido recién creado siempre tiene que estar vacío
    @Test
    @DisplayName("Un pedido nuevo siempre empieza vacío")
    void testPedidoNuevoEstaVacio() {
        assertTrue(pedido.getProducto().isEmpty(),"Nada más crear el pedido no tiene que tener ningún producto");
    }

    //si hay productos el total nunca puede ser 0
    @Test
    @DisplayName("El total nunca es 0 si hay productos con precio positivo")
    void testTotalNoEsCeroConProductos() {
        // Arrange
        pedido.agregarProducto(new ProductoFisico("Monitor", 159.99, 16.95));
        // Act & Assert
        assertNotEquals(0.0, pedido.calcularTotal(),"Si hay productos con precio el total no puede ser 0");
    }
    //pruebo el total con varios precios distintos usando @ParameterizedClass
    @ParameterizedTest(name = "Físico({0}+{1}) + Digital({2}) = {3}€ total")
    @CsvSource({
            "100.00, 5.00,  50.00,  165.50",
            "200.00, 10.00, 100.00, 331.00",
            "50.00,  3.00,  20.00,  77.20",
            "0.00,   0.00,  10.00,  12.10",
            "999.99, 20.00, 0.00,   1019.99"
    })
    @DisplayName("calcularTotal funciona bien con distintos productos y precios")
    void testTotalParametrizado(double precioF, double envioF,
                                double precioD, double esperado) {
        pedido.agregarProducto(new ProductoFisico("Físico", precioF, envioF));
        pedido.agregarProducto(new ProductoDigital("Digital", precioD, 0, "Lic"));
        assertEquals(esperado, pedido.calcularTotal(), 0.01,"El total tiene que ser la suma de los precios finales");
    }
}

