package com.biblioteca00;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prestecs {

    // Definir la clase Prestec
    static class Prestec {
        String idPrestec;
        List<String> idLlibre;  // lista para varios libros
        String idUser;
        LocalDate fechaPrestec;
        LocalDate fechaDevolucio;

        // nicializar la lista de libros
        public Prestec() {
            idLlibre = new ArrayList<>();
        }

        // Método para convertir el objeto a JSONObject
        public JSONObject toJson() throws JSONException {
            JSONObject json = new JSONObject();
            json.put("id_Prestec", idPrestec);
            json.put("id_Llibre", new JSONArray(idLlibre));  // Convertimos la lista a un JSONArray
            json.put("id_User", idUser);
            json.put("data_Prestec", fechaPrestec.toString());
            json.put("data_Devolucio", fechaDevolucio.toString());
            return json;
        }
    }


    // Metodo de existencia del ID del prestamo
    public static boolean idPrestecExist(String idPrestec) {
        for (int i = 0; i < prestecsArray.length(); i++) {
            JSONObject prestec = prestecsArray.getJSONObject(i);
            if (existingId.equals(idPrestec)) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sortir = false;

        // Leer el archivo JSON si existe
        JSONArray prestecsArray = new JSONArray();
        try {
            // Verifica si el archivo JSON existe
            File file = new File("mavenjson\\data\\prestecs.json");
            if (file.exists() && file.length() > 0) {
                // Lee el contenido del archivo y lo convierte a JSONArray
                String content = new String(Files.readAllBytes(Paths.get("mavenjson\\data\\prestecs.json")));
                prestecsArray = new JSONArray(content);
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        while (!sortir) {
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
                    if (idPrestecExist(id_prestec)) {
                        System.out.println("Error: El ID del préstec ja existeix.");
                        return;
                    }

                    // Inicializamos el préstamo
                    Prestec prestec = new Prestec();
                    prestec.idPrestec = id_prestec;

                    // Añadir múltiples libros
                    boolean afegirMesLlibres = true;
                    while (afegirMesLlibres) {
                        System.out.print("Introdueix el ID del llibre: ");
                        String id_llibre = sc.nextLine();
                        prestec.idLlibre.add(id_llibre);  // Añadir el ID del libro a la lista

                        System.out.print("Vols afegir un altre llibre? (s/n): ");
                        String resposta = sc.nextLine();
                        if (resposta.equalsIgnoreCase("n")) {
                            afegirMesLlibres = false;
                        }
                    }
                    // Verificar si los libros ya están prestados
                    for (String idLlibre : prestec.idLlibre) {
                        if (isLlibrePrestat(idLlibre)) {
                            System.out.println("Error: El llibre " + idLlibre + " ja està prestat.");
                            return;
                        }
                    }

                    System.out.print("Introdueix el ID de l'usuari: ");
                    String id_user = sc.nextLine();
                    prestec.idUser = id_user;
                    if (numPrestecsPerUsuari(id_user) >= 4) {
                        System.out.println("Error: Un usuari no pot tenir més de 4 préstecs.");
                        return;
                    }

                    try {
                        // Solicitar la fecha del préstamo
                        System.out.print("Introdueix la data del préstec (yyyy-mm-dd): ");
                        String fechaPrestecStr = sc.nextLine();
                        LocalDate dataPrestec = LocalDate.parse(fechaPrestecStr);
                        prestec.fechaPrestec = dataPrestec;
                    
                        // Solicitar la fecha de devolución
                        System.out.print("Introdueix la data de devolució (yyyy-mm-dd): ");
                        String fechaDevolucioStr = sc.nextLine();
                        LocalDate dataDevolucio = LocalDate.parse(fechaDevolucioStr);
                        prestec.fechaDevolucio = dataDevolucio;
                    } catch (Exception e) {
                        System.out.println("Error: Una o més dates no són vàlides. Torna a intentar-ho.");
                        return;  // Si hay un error en la fecha, se detiene el proceso.
                    }
                    

                    // Convertir el objeto a JSON y manejar la excepción JSONException
                    try {
                        JSONObject prestecJson = prestec.toJson();
                        prestecsArray.put(prestecJson); // Añadir el nuevo préstamo al array existente

                        // Guardar el JSON en un archivo
                        try (FileWriter file = new FileWriter("mavenjson\\data\\prestecs.json")) {
                            file.write(prestecsArray.toString(4));  // 4 es para que el JSON se escriba con los saltos de linea entre variables
                            System.out.println("El préstec s'ha guardat correctament en prestec.json");
                        } catch (IOException e) {
                            System.out.println("Error en guardar l'arxiu JSON: " + e.getMessage());
                        }

                    } catch (JSONException e) {
                        System.out.println("Error al convertir l'objecte a JSON: " + e.getMessage());
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
                    System.out.println("Tornant al menú principal.");
                    sortir = true; // Salir del bucle
                    break;
                default:
                    System.out.println("Opció no vàlida. Si us plau, introdueix un número entre 1 i 5.");
                    break;
            }
        }

        sc.close();
    }
}
