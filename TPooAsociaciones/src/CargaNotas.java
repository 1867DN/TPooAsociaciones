import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private final String catedra;
    private final double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public String getCatedra() {
        return catedra;
    }

    public double getNotaExamen() {
        return notaExamen;
    }
}

class Alumno {
    private final String nombreCompleto;
    private final long legajo;
    private final ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return suma / notas.size();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public long getLegajo() {
        return legajo;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de alumnos a cargar: ");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre completo del alumno: ");
            String nombreCompleto = scanner.nextLine();
            
            System.out.print("Ingrese el legajo del alumno: ");
            long legajo = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombreCompleto, legajo);

            int cantidadNotas;
            do {
                System.out.print("Ingrese la cantidad de notas del alumno (al menos 1): ");
                cantidadNotas = scanner.nextInt();
            } while (cantidadNotas < 1);

            scanner.nextLine();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra de la nota: ");
                String catedra = scanner.nextLine();

                System.out.print("Ingrese la nota del examen: ");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine();

                Nota nota = new Nota(catedra, notaExamen);
                alumno.agregarNota(nota);
            }
            
            alumnos.add(alumno);
        }

        System.out.println("\nInformación de Alumnos:");
        for (Alumno alumno : alumnos) {
            System.out.println("Alumno: " + alumno.getNombreCompleto() + " - Legajo: " + alumno.getLegajo());
            System.out.println("Notas:");
            for (Nota nota : alumno.getNotas()) {
                System.out.println("  Cátedra: " + nota.getCatedra() + " - Nota: " + nota.getNotaExamen());
            }
            System.out.println("Promedio de notas: " + alumno.calcularPromedio());
            System.out.println("------------------------");
        }

        scanner.close();
    }
}