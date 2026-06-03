package proyecto_entornos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// tests para comprobar que ProductoFisico suma bien el coste de envío
class ProductoFisicoTest {

    private ProductoFisico teclado;

    @BeforeEach
    void setUp() {
        teclado = new ProductoFisico("Teclado mecánico", 59.99, 6.80);
    }

    //59.99 + 6.80 de envío tiene que dar 66.79
    @Test
    @DisplayName("El precio final suma el coste de envío")
    void testPrecioFinalSumaEnvio() {
        // Arrange
        double esperado = 66.79;
        // Act
        double resultado = teclado.calcularPrecioFinal();
        // Assert
        assertEquals(esperado, resultado, 0.001,"59.99 + 6.80 tiene que ser 66.79");
    }

    //si el envío es 0 el precio no varía
    @Test
    @DisplayName("Sin coste de envío el precio final es igual al precio base")
    void testSinEnvioElPrecioNoVaria() {
        // Arrange
        ProductoFisico producto = new ProductoFisico("Monitor barato", 100.00, 0.00);
        // Act & Assert
        assertEquals(100.00, producto.calcularPrecioFinal(), 0.001,"Si el envío es 0 el precio final tiene que ser el mismo que el base");
    }

    //precio negativo tiene que lanzar excepción
    @Test
    @DisplayName("No se puede crear un producto físico con precio negativo")
    void testPrecioNegativoFalla() {
        assertThrows(IllegalArgumentException.class,() -> new ProductoFisico("Roto", -5.00, 2.00),"Un precio negativo tiene que lanzar IllegalArgumentException");
    }

    //el envío tampoco puede ser negativo
    @Test
    @DisplayName("No se puede crear un producto con coste de envío negativo")
    void testEnvioNegativoFalla() {
        assertThrows(IllegalArgumentException.class,() -> new ProductoFisico("Roto", 10.00, -2.00),"Un coste de envío negativo tiene que lanzar IllegalArgumentException");
    }

    //con envío positivo el precio final tiene que ser mayor que el precio base
    @Test
    @DisplayName("Con envío positivo el precio final supera al precio base")
    void testConEnvioElPrecioAumenta() {
        assertFalse(teclado.calcularPrecioFinal() <= teclado.getPrecio(),"Si hay coste de envío el precio final tiene que ser mayor que el base");
    }

    // compruebo que el precio final se calcula bien con distintos precios
    // y costes de envío usando @ParameterizedTest y @CsvSource
    @ParameterizedTest(name = "{0}€ + {1}€ envío = {2}€ total")
    @CsvSource({
            "59.99,  6.80,  66.79",
            "159.99, 16.95, 176.94",
            "860.99, 10.95, 871.94",
            "100.00, 0.00,  100.00",
            "9.99,   2.50,  12.49"
    })
    @DisplayName("El precio final es correcto para distintos productos")
    void testPrecioFinalConVariosProductos(double precio, double envio, double esperado) {
        // Arrange
        ProductoFisico producto = new ProductoFisico("Producto", precio, envio);
        // Act & Assert
        assertEquals(esperado, producto.calcularPrecioFinal(), 0.01,"El precio final tiene que ser exactamente precio + envío");
    }
}