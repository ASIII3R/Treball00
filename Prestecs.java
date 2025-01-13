import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Prestecs {
        // Definir la clase Prestec
        static class Prestec {
            String idPrestec;
            String idLlibre;
            String idUser;
            LocalDate fechaPrestec;
            LocalDate fechaDevolucio;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nGestió de préstecs");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Llistar préstecs");
            System.out.println("4. Eliminar préstec");
            System.out.println("5. Tornar al menú principal");
            System.out.print("Escull una opció: ");

            String opc = sc.nextLine();

            // Validar que la entrada sea un número válido
            int opcs = 0;
            try {
                opcs = Integer.parseInt(opc);
            } catch (NumberFormatException e) {
                System.out.println("Error: Si us plau, introdueix un número entre 1 i 5.\n");
                continue;
            }

            // Manejar las opciones del menú
            switch (opcs) {
                case 1:
                    // Captura de datos
                    System.out.print("Introdueix el ID del préstec: ");
                    String id_prestec = sc.nextLine();

                    System.out.print("Introdueix el ID del llibre: ");
                    String id_llibre = sc.nextLine();

                    System.out.print("Introdueix el ID del usuari: ");
                    String id_user = sc.nextLine();

                    System.out.print("Introdueix la data del préstec(yyyy-mm-dd): ");
                    String fechaPrestecStr = sc.nextLine();
                    LocalDate data_prestec = LocalDate.parse(fechaPrestecStr);

                    System.out.print("Introdueix la data de devolució(yyyy-mm-dd): ");
                    String fechaDevolucioStr = sc.nextLine();
                    LocalDate data_devolucio = LocalDate.parse(fechaDevolucioStr);

                    // Crear el objeto Prestec
                    prestec prestec = new prestec();
                    prestec.idPrestec = id_prestec;
                    prestec.idLlibre = id_llibre;
                    prestec.idUser = id_user;
                    prestec.fechaPrestec = data_prestec;
                    prestec.fechaDevolucio = data_devolucio;

                    // Usar Jackson para guardar el objeto en un archivo JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        objectMapper.writeValue(new File("prestec.json"), prestec);
                        System.out.println("El préstamo se ha guardado correctamente en prestec.json");
                    } catch (IOException e) {
                        System.out.println("Error al guardar el archivo JSON: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("2. Modificar");
                    break;
                case 3:
                    System.out.println("3. Llistar préstecs");
                    break;
                case 4:
                    System.out.println("4. Eliminar préstec");
                    break;
                case 5:
                    System.out.println("Tornar al menú principal.");
                    salir = true; // Salir del bucle
                    break;
                default:
                    System.out.println("Opció no vàlida. Si us plau, introdueix un número entre 1 i 5.");
                    break;
            }
        }

        sc.close();
    }
}
