import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private final String codigoArticulo;
    private final String nombreArticulo;
    private final int cantidad;
    private final double precioUnitario;
    private final double descuentoItem;
    private final double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario, double descuentoItem, double subtotal) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoItem = descuentoItem;
        this.subtotal = subtotal;
    }

    public String getCodigoArticulo() { return codigoArticulo; }
    public String getNombreArticulo() { return nombreArticulo; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getDescuentoItem() { return descuentoItem; }
    public double getSubtotal() { return subtotal; }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private final ArrayList<DetalleFactura> detallesFactura;

    public Factura() {
        this.detallesFactura = new ArrayList<>();
    }

    public String getFechaFactura() { return fechaFactura; }
    public void setFechaFactura(String fechaFactura) { this.fechaFactura = fechaFactura; }

    public long getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(long numeroFactura) { this.numeroFactura = numeroFactura; }

    public double getTotalCalculadoFactura() { return totalCalculadoFactura; }
    public void setTotalCalculadoFactura(double totalCalculadoFactura) { this.totalCalculadoFactura = totalCalculadoFactura; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public ArrayList<DetalleFactura> getDetallesFactura() { return detallesFactura; }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }
}

public class Facturacion {
    private static final String[][] articulos = {
        {"101", "Leche", "25"},
        {"102", "Gaseosa", "30"},
        {"103", "Fideos", "15"},
        {"104", "Arroz", "28"},
        {"105", "Vino", "120"},
        {"106", "Manteca", "20"},
        {"107", "Lavandina", "18"},
        {"108", "Detergente", "46"},
        {"109", "Jabón en Polvo", "96"},
        {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());

        long numeroFactura;
        do {
            System.out.print("Ingrese el número de la factura (entero mayor a cero): ");
            numeroFactura = scanner.nextLong();
        } while (numeroFactura <= 0);
        factura.setNumeroFactura(numeroFactura);

        scanner.nextLine();
        String cliente;
        do {
            System.out.print("Ingrese el nombre del cliente: ");
            cliente = scanner.nextLine();
        } while (cliente.isEmpty());
        factura.setCliente(cliente);

        boolean continuar = true;
        while (continuar) {
            mostrarArticulos();

            System.out.print("Ingrese el código del artículo a facturar: ");
            String codigoArticulo = scanner.nextLine();
            String[] articuloEncontrado = buscarArticuloPorCodigo(codigoArticulo);

            if (articuloEncontrado == null) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            String nombreArticulo = articuloEncontrado[1];
            double precioUnitario = Double.parseDouble(articuloEncontrado[2]);

            System.out.print("Ingrese la cantidad a facturar: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            double descuentoItem = (cantidad > 5) ? precioUnitario * 0.1 : 0;
            double subtotal = (precioUnitario - descuentoItem) * cantidad;

            DetalleFactura detalle = new DetalleFactura(codigoArticulo, nombreArticulo, cantidad, precioUnitario, descuentoItem, subtotal);
            factura.getDetallesFactura().add(detalle);

            System.out.print("¿Desea agregar otro artículo? (s/n): ");
            continuar = scanner.nextLine().equalsIgnoreCase("s");
        }

        factura.calcularMontoTotal();

        imprimirFactura(factura);

        scanner.close();
    }

    private static void mostrarArticulos() {
        System.out.println("\nLista de artículos disponibles:");
        System.out.printf("%-10s %-20s %-10s%n", "Código", "Nombre", "Precio");
        for (String[] articulo : articulos) {
            System.out.printf("%-10s %-20s %-10s%n", articulo[0], articulo[1], articulo[2]);
        }
        System.out.println();
    }

    private static String[] buscarArticuloPorCodigo(String codigo) {
        for (String[] articulo : articulos) {
            if (articulo[0].equals(codigo)) {
                return articulo;
            }
        }
        return null;
    }

    private static void imprimirFactura(Factura factura) {
        System.out.println("\nFactura:");
        System.out.println("Fecha: " + factura.getFechaFactura());
        System.out.println("Número: " + factura.getNumeroFactura());
        System.out.println("Cliente: " + factura.getCliente());
        System.out.println("Código Articulo | Nombre Articulo | Cantidad | Precio Unitario | Descuento | Subtotal");

        for (DetalleFactura detalle : factura.getDetallesFactura()) {
            System.out.printf("%-15s %-15s %-10d %-15.2f %-10.2f %-10.2f%n",
                detalle.getCodigoArticulo(),
                detalle.getNombreArticulo(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getDescuentoItem(),
                detalle.getSubtotal()
            );
        }

        System.out.println("Total: " + factura.getTotalCalculadoFactura());
    }
}