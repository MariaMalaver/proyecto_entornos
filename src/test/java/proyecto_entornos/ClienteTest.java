package proyecto_entornos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// tests sencillos para comprobar que Cliente guarda bien sus datos
class ClienteTest {

    //el constructor tiene que guardar bien los tres datos
    @Test
    @DisplayName("El constructor guarda bien el nombre, correo y dirección")
    void testConstructorGuardaLosDatos() {
        // Arrange & Act
        Cliente cliente = new Cliente("Maria", "mmalgon1106@g.educaand.es", "Pago tras de la huerta");
        // Assert
        assertEquals("Maria", cliente.getNombre(),"El nombre tiene que ser el que pusimos al crear el cliente");
        assertEquals("mmalgon1106@g.educaand.es", cliente.getCorreo(),"El correo tiene que ser el que pusimos al crear el cliente");
        assertEquals("Pago tras de la huerta", cliente.getDireccion(),"la dirección tiene que ser la que pusimos al crear el cliente");
    }

    //el constructor tiene que guardar bien los tres datos
    @Test
    @DisplayName("setNombre actualiza el nombre del cliente")
    void testSetNombreCambiaElNombre() {
        // Arrange
        Cliente cliente = new Cliente("Maria", "mmalgon1106@g.educaand.es", "Pago tras de la huerta");
        // Act
        cliente.setNombre("Javier");
        // Assert
        assertEquals("Javier", cliente.getNombre(),"después de setNombre tiene que aparecer el nombre nuevo");
        assertNotEquals("Maria", cliente.getNombre(),"el nombre antiguo no tiene que seguir ahí");
    }
}