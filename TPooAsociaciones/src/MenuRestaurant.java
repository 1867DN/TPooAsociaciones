import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private final String nombre;
    private final double cantidad;
    private final String unidadMedida;

    // Constructor
    public Ingrediente(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
}

class Plato {
    private final String nombreCompleto;
    private final double precio;
    private final boolean esBebida;
    private final ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEsBebida() {
        return esBebida;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.print("Ingrese la cantidad de platos a cargar en el menú: ");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.print("Ingrese el nombre del plato: ");
            String nombrePlato = scanner.nextLine();

            System.out.print("Ingrese el precio del plato: ");
            double precio = scanner.nextDouble();

            System.out.print("¿Es una bebida? (true/false): ");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();

            Plato plato = new Plato(nombrePlato, precio, esBebida);

            if (!esBebida) {
                int cantidadIngredientes;
                do {
                    System.out.print("Ingrese la cantidad de ingredientes del plato (al menos 1): ");
                    cantidadIngredientes = scanner.nextInt();
                } while (cantidadIngredientes < 1);

                scanner.nextLine();

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.print("Ingrese el nombre del ingrediente: ");
                    String nombreIngrediente = scanner.nextLine();

                    System.out.print("Ingrese la cantidad del ingrediente: ");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Ingrese la unidad de medida del ingrediente: ");
                    String unidadMedida = scanner.nextLine();

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidad, unidadMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }
            platosMenu.add(plato);
        }

        System.out.println("\nMENÚ:");
        for (Plato plato : platosMenu) {
            System.out.println(plato.getNombreCompleto());
            System.out.println("Precio: $ " + plato.getPrecio());

            if (!plato.isEsBebida()) {
                System.out.println("Ingredientes:");
                System.out.printf("%-20s %-10s %-15s%n", "Nombre", "Cantidad", "Unidad de Medida");
                for (Ingrediente ingrediente : plato.getIngredientes()) {
                    System.out.printf("%-20s %-10.2f %-15s%n", ingrediente.getNombre(), ingrediente.getCantidad(), ingrediente.getUnidadMedida());
                }
            }
            System.out.println("------------------------");
        }

        scanner.close();
    }
}