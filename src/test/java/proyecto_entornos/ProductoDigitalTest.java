package proyecto_entornos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

// Tests para comprobar que ProductoDigital aplica bien el IVA del 21%
class ProductoDigitalTest {

    private ProductoDigital software;

    @BeforeEach
    void setUp() {
        software = new ProductoDigital("Antivirus", 100.00, 440, "Licencia anual");
    }

    //precio 100 con IVA tiene que dar 121
    @Test
    @DisplayName("El precio final tiene el 21% de IVA")
    void testCalcularPrecioFinalConIVA() {
        // Arrange
        double esperado = 121.00;
        // Act
        double resultado = software.calcularPrecioFinal();
        // Assert
        assertEquals(esperado, resultado, 0.001,"Con precio base 100, el precio con IVA tiene que ser 121");
    }

    //precio 0 con IVA sigue siendo 0
    @Test
    @DisplayName("Si el precio base es 0 el precio final también es 0")
    void testPrecioBaseCero() {
        // Arrange
        ProductoDigital gratis = new ProductoDigital("Prueba", 0.00, 0, "Sin licencia");
        // Act & Assert
        assertEquals(0.0, gratis.calcularPrecioFinal(), 0.001,"El 21% de 0 sigue siendo 0");
    }

    //el precio con IVA nunca puede ser igual al precio base
    @Test
    @DisplayName("El precio final siempre es mayor que el precio base")
    void testPrecioFinalEsMayorQuePrecioBase() {
        assertNotEquals(software.getPrecio(), software.calcularPrecioFinal(),"El IVA siempre aumenta el precio, nunca pueden ser iguales");
    }

    //precio negativo tiene que lanzar excepción
    @Test
    @DisplayName("No se puede crear un producto con precio negativo")
    void testPrecioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,() -> new ProductoDigital("Roto", -10.00, 0, "Ninguna"),"Un precio negativo tiene que lanzar IllegalArgumentException");
    }

    // compruebo que el IVA se calcula bien con distintos precios
    // usando @ParameterizedTest y @CsvSource para probar varios casos en una sola prueba
    @ParameterizedTest(name = "Precio {0}€ debería dar {1}€ con IVA")
    @CsvSource({
            "10.00,  12.10",
            "50.00,  60.50",
            "200.00, 242.00",
            "95.90,  116.039",
            "24.99,  30.2379"
    })
    @DisplayName("El IVA se calcula bien con distintos precios")
    void testIVAConVariosPrecios(double precioBase, double precioEsperado) {
        // Arrange
        ProductoDigital producto = new ProductoDigital("Producto", precioBase, 0, "Licencia");
        // Act & Assert
        assertEquals(precioEsperado, producto.calcularPrecioFinal(), 0.01,"El resultado tiene que ser precio base multiplicado por 1.21");
    }

    // compruebo que cualquier precio negativo lanza excepción
    // uso @ParameterizedTest y @ValueSource para probar varios precios negativos en una sola prueba
    @ParameterizedTest(name = "El precio {0}€ es negativo y debe fallar")
    @ValueSource(doubles = {-0.01, -1.0, -100.0, -999.99})
    @DisplayName("Cualquier precio negativo lanza excepción")
    void testCualquierPrecioNegativoFalla(double precioMalo) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,() -> new ProductoDigital("Test", precioMalo, 0, "Lic"),"Da igual cuánto de negativo sea, siempre tiene que lanzar excepción");
    }
}