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

        // Inicializar la lista de libros
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

    // Método de existencia del ID del préstamo
    public static boolean idPrestecExist(String idPrestec, JSONArray prestecsArray) {
        for (int i = 0; i < prestecsArray.length(); i++) {
            JSONObject prestec = prestecsArray.optJSONObject(i);
            String existingId = prestec.optString("id_Prestec");
            if (existingId.equals(idPrestec)) {
                return true;
            }
        }
        return false;
    }
    
    // Verificar si un libro ya está prestado
    public static boolean isLlibrePrestat(String idLlibre, JSONArray prestecsArray) {
        for (int i = 0; i < prestecsArray.length(); i++) {
            JSONObject prestec = prestecsArray.optJSONObject(i);
            JSONArray llibres = prestec.optJSONArray("id_Llibre");
            if (llibres != null) {
                for (int j = 0; j < llibres.length(); j++) {
                    if (llibres.optString(j).equals(idLlibre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Contar el número de préstamos por usuario
    public static int numPrestecsPerUsuari(String idUser, JSONArray prestecsArray) {
        int count = 0;
        for (int i = 0; i < prestecsArray.length(); i++) {
            JSONObject prestec = prestecsArray.optJSONObject(i);
            if (idUser.equals(prestec.optString("id_User"))) {
                count++;
            }
        }
        return count;
    }

    // Validar todas las condiciones de préstamo
    public static boolean validarPrestec(String idPrestec, List<String> idLlibre, String idUser, LocalDate fechaPrestec, LocalDate fechaDevolucio, JSONArray prestecsArray) {
        // Verificar si el ID del préstamo ya existe
        if (idPrestecExist(idPrestec, prestecsArray)) {
            System.out.println("Error: El ID del préstec ja existeix.");
            return false;
        }

        // Verificar si los libros ya están prestados
        for (String idLlibreItem : idLlibre) {
            if (isLlibrePrestat(idLlibreItem, prestecsArray)) {
                System.out.println("Error: El llibre " + idLlibreItem + " ja està prestat.");
                return false;
            }
        }

        // Verificar el número de préstamos por usuario
        if (numPrestecsPerUsuari(idUser, prestecsArray) >= 4) {
            System.out.println("Error: Un usuari no pot tenir més de 4 préstecs.");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sortir = false;

        // Leer el archivo JSON si existe
        JSONArray prestecsArray = new JSONArray();
        try {
            // Verifica si el archivo JSON existe
            File file = new File("mavenjson" + File.separator + "data" + File.separator + "prestecs.json");
            if (file.exists() && file.length() > 0) {
                // Lee el contenido del archivo y lo convierte a JSONArray
                String content = new String(Files.readAllBytes(Paths.get("mavenjson" + File.separator + "data" + File.separator + "prestecs.json")));
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

                    // Captura de los libros
                    List<String> idLlibres = new ArrayList<>();
                    boolean afegirMesLlibres = true;
                    while (afegirMesLlibres) {
                        System.out.print("Introdueix el ID del llibre: ");
                        String id_llibre = sc.nextLine();
                        idLlibres.add(id_llibre);

                        System.out.print("Vols afegir un altre llibre? (s/n): ");
                        String resposta = sc.nextLine();
                        if (resposta.equalsIgnoreCase("n")) {
                            afegirMesLlibres = false;
                        }
                    }

                    System.out.print("Introdueix el ID de l'usuari: ");
                    String id_user = sc.nextLine();

                    // Captura de las fechas
                    LocalDate fechaPrestec = null;
                    LocalDate fechaDevolucio = null;
                    try {
                        System.out.print("Introdueix la data del préstec (yyyy-mm-dd): ");
                        fechaPrestec = LocalDate.parse(sc.nextLine());

                        System.out.print("Introdueix la data de devolució (yyyy-mm-dd): ");
                        fechaDevolucio = LocalDate.parse(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Error: La data no és vàlida. Torna a intentar-ho.");
                        continue;
                    }

                    // Validar condiciones
                    if (!validarPrestec(id_prestec, idLlibres, id_user, fechaPrestec, fechaDevolucio, prestecsArray)) {
                        break;
                    }

                    // Inicializar el préstamo
                    Prestec prestec = new Prestec();
                    prestec.idPrestec = id_prestec;
                    prestec.idLlibre = idLlibres;
                    prestec.idUser = id_user;
                    prestec.fechaPrestec = fechaPrestec;
                    prestec.fechaDevolucio = fechaDevolucio;

                    // Convertir el objeto a JSON y manejar la excepción JSONException
                    try {
                        JSONObject prestecJson = prestec.toJson();
                        prestecsArray.put(prestecJson); // Añadir el nuevo préstamo al array existente

                        // Guardar el JSON en un archivo
                        try (FileWriter file = new FileWriter("mavenjson" + File.separator + "data" + File.separator + "prestecs.json")) {
                            file.write(prestecsArray.toString(4)); // Indentación de 4 espacios
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
