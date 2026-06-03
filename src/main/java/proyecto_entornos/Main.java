package proyecto_entornos;
public class Main 
{
    public static void main(String[] args) 
    {
        //DISTINTOS PRODUCTOS
        //DIGITAL
        ProductoDigital software = new ProductoDigital("Antivirus", 204.99, 440, "Licencia anual");
        ProductoDigital licencia = new ProductoDigital("Microsoft Office 365", 95.9, 0, "Licencia perpeuta");
        ProductoDigital eBook = new ProductoDigital("Curso Java", 24.99, 140, "Licencia de por vida");

        //FISICO
        ProductoFisico teclado = new ProductoFisico("Teclado mecánico", 59.99, 6.80);
        ProductoFisico monitor = new ProductoFisico("Monitor", 159.99, 16.95);
        ProductoFisico telefono = new ProductoFisico("Iphone 16", 860.99, 10.95);

        //MOSTRAR
        System.out.println("******** PRODUCTOS DIGITALES *********");
        System.out.println(software);
        System.out.println("Precio final: " + software.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(licencia);
        System.out.println("Precio final: " + licencia.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(eBook);
        System.out.println("Precio final: " + eBook.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(" ");
        System.out.println("******** PRODUCTOS FISICOS *********");
        System.out.println(teclado);
        System.out.println("Precio final: " + teclado.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(monitor);
        System.out.println("Precio final: " + monitor.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(telefono);
        System.out.println("Precio final: " + telefono.calcularPrecioFinal());
        System.out.println("*************************************************");
        System.out.println(" ");

        /*********************************************************************************************************************************************************************************/
        //PEDIDOS
        Pedidos pedido1 = new Pedidos();
        Pedidos pedido2 = new Pedidos();
        Pedidos pedido3 = new Pedidos();
        Pedidos pedido4 = new Pedidos();
        
        /*********************************************************************************************************************************************************************************/
        //CLIENTES
        Cliente cliente1 = new Cliente("Maria", "mmalgon1106@g.educaand.es", "Pago tras de la huerta");
        Cliente cliente2 = new Cliente("Javier", "javiermillan03@gmail.com", "Calle cortijillo");
        Cliente cliente3 = new Cliente("Eli", "elisabethfalcon21@gmail.com", "Calle nieves");
        Cliente cliente4 = new Cliente("Ivan", "castroalvarezivan@gmail.com", "Calle nenufar");
        
        /*********************************************************************************************************************************************************************************/
        //AGREGAMOS LOS CLIENTES Y PEDIDOS
        pedido1.asignarCliente(cliente1);
        pedido1.agregarProducto(monitor);
        pedido1.agregarProducto(teclado);
        pedido1.agregarProducto(software);
        System.out.println(" ");
        pedido2.asignarCliente(cliente2);
        pedido2.agregarProducto(telefono);
        pedido2.agregarProducto(licencia);
        System.out.println(" ");
        pedido3.asignarCliente(cliente3);
        pedido3.agregarProducto(eBook);
        pedido3.agregarProducto(monitor);
        System.out.println(" ");
        pedido4.asignarCliente(cliente4);
        pedido4.agregarProducto(telefono);
        pedido4.agregarProducto(teclado);
        System.out.println(" ");

        /*********************************************************************************************************************************************************************************/
        //PEDIDOS REALIZADOS
        pedido1.mostrarResumen();
        System.out.println(" ");
        pedido2.mostrarResumen();
        System.out.println(" ");
        pedido3.mostrarResumen();
        System.out.println(" ");
        pedido4.mostrarResumen();

    }
}
   