package com.biblioteca00;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean funcionament = true;
        // print del menú
        try {
            while (funcionament) {
                System.out.println("gestió de biblioteca\n1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir");
                System.out.print("Escull una opció: ");

                // Escollir una opció del menú principal

                String opcio = scanner.nextLine().toLowerCase();

                switch (opcio) {
                    case "1":
                    case "llibres":
                        // Obrir menu Gestió Llibres si es selecciona la opció 1
                        menuGestióLlibres(scanner);
                        break;

                    case "2":
                    case "usuaris":
                        // Obrir menu Gestió Usuaris si es selecciona la opció 2
                        menuGestióUsuaris(scanner);
                        break;

                    case "3":
                    case "prestecs":
                        // Obrir menú gestió préstecs si es selecciona la opció 3
                        menuGestióPréstecs(scanner);
                        break;

                    // Si la opció es 0, sortir
                    case "0":
                    case "sortir":
                        funcionament = false;
                        break;
                    default:
                        System.out.println("Opció no vàlida");

                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void menuGestióLlibres(Scanner scanner) {
        while (true) {
            System.out.println(
                    "Gestió de Llibres\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase();

            // Afegir llibre
            switch (opc) {
                case "1":
                case "afegir":
                    afegirLlibre(scanner);
                    break;
                case "2":
                case "modificar":
                    modificarLlibre(scanner);
                    break;
                case "3":
                case "eliminar":
                    System.out.println("Aquí anirà la funció per eliminar un llibre");
                    break;
                case "4":
                case "llistar":
                    menuLlistarLlibres(scanner);
                    break;
                case "0":
                case "tornar":
                case "tornar al menú principal":
                    return;
            }
        }
    }

    public static void menuLlistarLlibres(Scanner scanner) {
        while (true) {
            System.out.println(
                    "Llistar llibres\n1. Tots\n2.En préstec\n3.Per autor\n4.Cercar títol\n0.Tornar al menú de llibres");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "1":
                case "tots":
                    System.out.println("Aquí anirà la funció per llistar tots els llibres");
                    break;
                case "2":
                case "en préstec":
                    System.out.println("Aquí anirà la funció per a llistar els llibres en préstec");
                    break;
                case "3":
                case "per autor":
                    System.out.println("Aquí anirà la funció per a llistar els llibres per autor");
                    break;
                case "4":
                case "cercar títol":
                    System.out.println("Aquí anirà la funció per cercar títol");
                    break;
                case "0":
                case "tornar al menú de llibres":
                case "tornar":
                    return;
            }
        }
    }

    public static void menuGestióUsuaris(Scanner scanner) {
        while (true) {
            System.out.println(
                    "Gestió de Usuaris\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase();

            // Afegir llibre
            switch (opc) {
                case "1":
                case "afegir":
                    System.out.println("Aquí anirà la funció per afegir usuaris");
                    break;
                case "2":
                case "modificar":
                    System.out.println("Aquí anirà la funció per modificar usuaris");
                    break;
                case "3":
                case "eliminar":
                    System.out.println("Aquí anirà la funció per eliminar un Usuari");
                    break;
                case "4":
                case "llistar":
                    menuLlistarUsuaris(scanner);
                    break;
                case "0":
                case "tornar":
                case "tornar al menú principal":
                    return;
            }
        }
    }

    public static void menuLlistarUsuaris(Scanner scanner) {
        while (true) {
            System.out.println("Llistar usuaris\n1. Tots\n2.Amb préstecs actius\n3.Amb préstecs fora de termini\n0.Tornar al menú de usuaris");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase(); 
            switch(opc){
                case "1":
                case "tots":
                    System.out.println("Aquí anirà la funció per llistar tots els usuaris");
                    break;
                case"2":
                case"en préstec":
                    System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs actius");
                    break;
                case"3":
                case"per autor":
                    System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs fora de termini");
                    break;
                case"0":
                case"tornar al menú de usuaris":
                case"tornar":
                    return;
            }
        }
    }

    // Métodos de Gestión de Préstecs
    public static void menuGestióPréstecs(Scanner scanner) {
        while (true) {
            System.out.println("Gestió de Préstecs\n1. Afegir\n2. Modificar\n3. Eliminar\n4. Llistar\n0. Tornar al menú principal");
            System.out.print("Escull una opció: ");
            String opc = scanner.nextLine().toLowerCase();

            switch (opc) {
                case "1":
                case "afegir":
                    afegirPrestec(scanner);
                    break;
                case "2":
                case "modificar":
                    System.out.println("Aquí anirà la funció per modificar préstecs");
                    break;
                case "3":
                case "eliminar":
                    System.out.println("Aquí anirà la funció per eliminar préstecs");
                    break;
                case "4":
                case "llistar":
                    llistarPrestecs();
                    break;
                case "0":
                case "tornar":
                    return;
                default:
                    System.out.println("Opció no vàlida");
            }
        }
    }

    // Función para añadir un préstamo
    public static void afegirPrestec(Scanner scanner) {
        try {
            // Leer el archivo JSON de los préstamos
            JSONArray prestecsArray = new JSONArray();
            File file = new File("mavenjson/data/prestecs.json");
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/prestecs.json")));
                prestecsArray = new JSONArray(content);
            }

            // Captura de datos
            System.out.print("Introdueix el ID del préstec: ");
            String idPrestec = scanner.nextLine();

            // Verificar que el ID del préstamo no exista
            if (idPrestecExist(prestecsArray, idPrestec)) {
                System.out.println("Error: El ID del préstec ja existeix.");
                return;
            }

            // Inicializar el préstamo
            Prestec prestec = new Prestec();
            prestec.idPrestec = idPrestec;

            // Añadir múltiples libros
            boolean afegirMesLlibres = true;
            while (afegirMesLlibres) {
                System.out.print("Introdueix el ID del llibre: ");
                String idLlibre = scanner.nextLine();
                prestec.idLlibre.add(idLlibre);

                System.out.print("Vols afegir un altre llibre? (s/n): ");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("n")) {
                    afegirMesLlibres = false;
                }
            }

            // Captura de datos del usuario
            System.out.print("Introdueix el ID de l'usuari: ");
            String idUser = scanner.nextLine();
            prestec.idUser = idUser;

            // Solicitar las fechas
            System.out.print("Introdueix la data del préstec (yyyy-mm-dd): ");
            String fechaPrestecStr = scanner.nextLine();
            LocalDate fechaPrestec = LocalDate.parse(fechaPrestecStr);
            prestec.fechaPrestec = fechaPrestec;

            System.out.print("Introdueix la data de devolució (yyyy-mm-dd): ");
            String fechaDevolucioStr = scanner.nextLine();
            LocalDate fechaDevolucio = LocalDate.parse(fechaDevolucioStr);
            prestec.fechaDevolucio = fechaDevolucio;

            // Convertir el préstamo a JSON
            JSONObject prestecJson = prestec.toJson();
            prestecsArray.put(prestecJson);

            // Guardar los datos en el archivo JSON
            try (FileWriter fileWriter = new FileWriter("mavenjson/data/prestecs.json")) {
                fileWriter.write(prestecsArray.toString(4)); // Escribe con formato legible
                System.out.println("Préstec afegit correctament!");
            }

        } catch (IOException | JSONException e) {
            System.out.println("Error al guardar el préstec: " + e.getMessage());
        }
    }

    // Comprobar si el ID de préstamo ya existe
    public static boolean idPrestecExist(JSONArray prestecsArray, String idPrestec) {
        for (int i = 0; i < prestecsArray.length(); i++) {
            JSONObject prestec = prestecsArray.getJSONObject(i);
            if (prestec.getString("id_Prestec").equals(idPrestec)) {
                return true;
            }
        }
        return false;
    }

    // Listar préstamos
    public static void llistarPrestecs() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/prestecs.json")));
            JSONArray prestecsArray = new JSONArray(content);
            System.out.println("Llistat de préstecs:");
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                System.out.println(prestec.toString(4));  // Imprime con formato legible
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir els préstecs: " + e.getMessage());
        }
    }

    // Clase Prestec
    static class Prestec {
        String idPrestec;
        List<String> idLlibre = new ArrayList<>();
        String idUser;
        LocalDate fechaPrestec;
        LocalDate fechaDevolucio;

        // Método para convertir el objeto Prestec a JSON
        public JSONObject toJson() throws JSONException {
            JSONObject json = new JSONObject();
            json.put("id_Prestec", idPrestec);
            json.put("id_Llibre", new JSONArray(idLlibre));
            json.put("id_User", idUser);
            json.put("data_Prestec", fechaPrestec.toString());
            json.put("data_Devolucio", fechaDevolucio.toString());
            return json;
        }
    }

    // Método para agregar un libro
    public static void afegirLlibre(Scanner scanner) {
        try {
            // Leer los datos existentes
            String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json")));
            JSONArray llibresArray = new JSONArray(content);

            // Recoger datos del nuevo libro
            System.out.print("Escribe el nombre del libro: ");
            String nomLlibre = scanner.nextLine();
            System.out.print("Escribe el nombre del autor: ");
            String autor = scanner.nextLine();

            // Validar el nombre del autor
            while (autor.trim().isEmpty() || autor.length() < 2 || autor.matches(".*\\d.*")) {
                System.out.println("Inserta un nombre válido");
                System.out.print("Escribe el nombre del autor: ");
                autor = scanner.nextLine();
            }

            // Generar un ID aleatorio para el libro
            Random random = new Random();
            String id = String.format("%06d", random.nextInt(1000000));

            // Crear el objeto JSON para el nuevo libro
            JSONObject llibreJson = new JSONObject();
            llibreJson.put("nom", nomLlibre);
            llibreJson.put("autor", autor);
            llibreJson.put("ID", id);

            // Agregar el nuevo libro al array
            llibresArray.put(llibreJson);

            // Escribir de nuevo al archivo
            try (FileWriter fileWriter = new FileWriter("mavenjson/data/llibres.json")) {
                fileWriter.write(llibresArray.toString(4));
                System.out.println("Llibre afegit correctament!");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }
}
