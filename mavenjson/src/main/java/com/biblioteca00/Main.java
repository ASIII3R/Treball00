package com.biblioteca00;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// ./run.sh com.biblioteca00.Main

public class Main {

    public static String filePathPrestecs = "./data/prestecs.json";
    public static String filePathLlibres = "./data/llibres.json";
    public static String filePathUsuaris = "./data/usuaris.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean funcionament = true;
        // menú principal
        try {
            while (funcionament) {
                System.out.println("\n--- Gestió de biblioteca ---\n1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir");
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
        }
    }

    // menús biblioteca
    public static void menuGestióLlibres(Scanner scanner) {
        while (true) {
            try {
                System.out.println(
                        "\n--- Gestió de Llibres ---\n1. Afegir\n2. Modificar\n3. Eliminar\n4. Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                switch (opc) {
                    case "1":
                    case "afegir":
                        afegirLlibre(scanner); // Afegir llibre
                        break;
                    case "2":
                    case "modificar":
                        modificarLlibre(scanner); // Modificar llibre
                        break;
                    case "3":
                    case "eliminar":
                        eliminarLlibre(); // Eliminar Llibre
                        break;
                    case "4":
                    case "llistar":
                        menuLlistarLlibres(scanner); // Menu Llistar Llibres
                        break;
                    case "0":
                    case "tornar":
                    case "tornar al menú principal": // Tornar al menú principal
                        return;
                    default:
                        System.out.println("Opció no válida");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    public static void menuGestióUsuaris(Scanner scanner) {
        while (true) {
            try {
                System.out.println(
                        "\n--- Gestió de Usuaris---\n1. Afegir\n2. Modificar\n3. Eliminar\n4. Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                // Users
                switch (opc) {
                    case "1":
                    case "afegir":
                        afegirUsuari(scanner); // Afegir usuari
                        break;
                    case "2":
                    case "modificar":
                        modificarUsuaris(scanner); // Modificar usuari
                        break;
                    case "3":
                    case "eliminar":
                        eliminarUsuari(scanner); // Eliminar usuari
                        break;
                    case "4":
                    case "llistar":
                        menuLlistarUsuaris(scanner); // Menu Llistar Usuaris
                        break;
                    case "0":
                    case "tornar":
                    case "tornar al menú principal":
                        return;
                    default:
                        System.out.println("Opció no vàlida");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    public static void modificarUsuaris(Scanner scanner) {
        try {
            String filePath = filePathUsuaris;
            
            System.out.println("Inserta l'id de l'usuari que vulguis modificar");
            // Es crea una variable per poder comparar i es llegeix usuaris.json
            String idBuscar = scanner.nextLine();
            
            // Leer el archivo JSON
            String contenido = new String(Files.readAllBytes(Paths.get(filePath))); 
            JSONArray usuarisArray = new JSONArray(contenido);
            
            boolean trobat = false;
    
            // Buscar l'usuari per la seva ID
            for (int i = 0; i < usuarisArray.length(); i++) {
                JSONObject usuari = usuarisArray.getJSONObject(i);
                if (usuari.getString("id").equals(idBuscar)) {
                    trobat = true;
                    System.out.println("\n ----- MODIFICAR USER ----- ");
                    System.out.println("\nQue vols cambiar?\n1)Telèfon\n2)Nom\n3)Cognom\n4)ID\n0)Tornar");
                    String opcio = scanner.nextLine().toLowerCase();
    
                    // Selecció de que es vol modificar
                    switch (opcio) {
                        case "telèfon":
                        case "1":
                        case "telefon":
                            System.out.print("Inserta el nou telèfon: ");
                            String nouTelefon = scanner.nextLine();
                            usuari.put("telefon", nouTelefon);
                            break;
                        case "nom":
                        case "2":
                            System.out.print("Inserta el nou nom: ");
                            String nouNom = scanner.nextLine();
                            usuari.put("nom", nouNom);
                            break;
                        case "cognom":
                        case "3":
                            System.out.print("Inserta el nou cognom: ");
                            String nouCognom = scanner.nextLine();
                            usuari.put("cognom", nouCognom);
                            break;
                        case "id":
                        case "4":
                            System.out.print("Inserta el nou id: ");
                            String nouId = scanner.nextLine();
                            usuari.put("id", nouId);
                            break;
                        case "0":
                            System.out.println("Tornant al menú anterior.");
                            return;  // Si se elige la opción 0, sale del método.
                        default:
                            System.out.println("Opció no vàlida.");
                            return;
                    }
    
                    // Una vez se haya realizado el cambio, escribir el archivo con la actualización
                    Files.write(Paths.get(filePath), usuarisArray.toString(4).getBytes());
                    System.out.println("Usuari modificat correctament!");
                    break;
                }
            }
            // Si no es troba l'usuari mostra un missatge de que no s'ha trobat
            if (!trobat) {
                System.out.println("No s'ha trobat l'usuari");
            }
    
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    

    public static void menuLlistarUsuaris(Scanner scanner) {
        while (true) {
            System.out.println(
                    "\n--- Llistar usuaris ---\n1.Tots\n2.Amb préstecs actius\n3.Amb préstecs fora de termini\n0.Tornar al menú de usuaris");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "1":
                case "tots":
                    llistarTotsUsuaris(); // Llistar tots els usuaris
                    break;
                case "2":
                case "en préstec":
                    llistarUsuarisPrestecsActius(); // Llistar usuaris amb préstecs actius
                    break;
                case "3":
                case "per autor":
                    llistarUsuarisForaTermini(); // Llistar usuaris amb préstecs fora de termin
                    break;
                case "0":
                case "tornar al menú de usuaris":
                case "tornar":
                    return; // Tornar al menú anterior
            }
        }
    }

    public static void menuGestióPréstecs(Scanner scanner) {
        while (true) {
            System.out.println(
                    "\n--- Gestió de Préstecs ---\n1. Afegir\n2. Modificar\n3. Eliminar\n4. Llistar\n0. Tornar al menú principal");
            System.out.print("Escull una opció: ");
            String opc = scanner.nextLine().toLowerCase();

            switch (opc) {
                case "1":
                case "afegir":
                    afegirPrestec(scanner); // Afegir préstec
                    break;
                case "2":
                case "modificar":
                    modificarPrestec(scanner); // Modificar préstec
                    break;
                case "3":
                case "eliminar":
                    try {
                        eliminarPrestec(); // Eliminar préstec
                    } catch (IOException e) {
                        System.out.println("Error al eliminar el préstec: " + e.getMessage());
                    }
                    break;
                case "4":
                case "llistar":
                    llistarPrestecs(scanner); // Llistar préstecs
                    break;
                case "0":
                case "tornar":
                    return; // Tornar al menú anterior
                default:
                    System.out.println("Opció no vàlida"); // En cas d'opció no válida
            }
        }
    }

    public static void menuLlistarLlibres(Scanner scanner) {
        while (true) {
            System.out.println(
                    "\n--- Llistar llibres---\n1. Tots\n2. En préstec\n3. Per autor\n4. Cercar títol\n0. Tornar al menú de llibres");
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase();
            switch (opc) {
                case "1":
                case "tots":
                    llistarTotsLlibres(); // Llistar tots els llibres
                    break;
                case "2":
                case "en préstec":
                    llistarLlibresEnPrestec(); // Llistar tots els llibres en préstec
                    break;
                case "3":
                case "per autor":
                    llistarLlibresPerAutor(); // Llistar llibres per l'autor demanat
                    break;
                case "4":
                case "cercar títol":
                    buscarLlibrePorTitol(scanner); // Llistar llibres per el seu títol
                    break;
                case "0":
                case "tornar al menú de llibres":
                case "tornar":
                    return;
            }
        }
    }

    public static void eliminarLlibre() {
        // Usar la variable estática filePathLlibres para la ruta del archivo
        String llibresFilePath = filePathLlibres;
        Scanner scanner = new Scanner(System.in);

        try (FileReader leer = new FileReader(llibresFilePath)) {
            // Leer el contenido del archivo JSON
            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }

            // Parsear el contenido JSON a un JSONObject
            JSONObject llibres = new JSONObject(jsoncontenido.toString());

            // Solicitar ID del libro a eliminar
            System.out.println("Introdueix el ID del llibre a eliminar: ");
            String idEliminar = scanner.nextLine();

            Iterator<String> keys = llibres.keys();
            boolean llibreEliminat = false;

            // Buscar el libro y eliminarlo si se encuentra
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject llibre = llibres.getJSONObject(key);

                if (llibre.getString("ID").equals(idEliminar)) {
                    llibres.remove(key);
                    llibreEliminat = true;
                    break;
                }
            }

            // Escribir el JSON actualizado de vuelta al archivo
            if (llibreEliminat) {
                FileWriter writer = new FileWriter(llibresFilePath);
                writer.write(llibres.toString(4));
                writer.close();
                System.out.println("Llibre eliminat correctament");
            } else { // Mensaje si no se ha encontrado el libro
                System.out.println("No s'ha trobat l'ID del llibre demanat");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void llistarLlibresEnPrestec() {
        // Usar las variables estáticas para las rutas
        String llibresFilePath = filePathLlibres;
        String prestecsFilePath = filePathPrestecs;

        try (FileReader leer = new FileReader(llibresFilePath);
                FileReader prestecsLeer = new FileReader(prestecsFilePath)) {

            // Leer el contenido de llibres.json
            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }

            // Leer el contenido de prestecs.json
            StringBuilder prestecsJsonContenido = new StringBuilder();
            while ((i = prestecsLeer.read()) != -1) {
                prestecsJsonContenido.append((char) i);
            }

            // Convertir el contenido a JSONObject y JSONArray
            JSONObject llibres = new JSONObject(jsoncontenido.toString());
            JSONArray prestecs = new JSONArray(prestecsJsonContenido.toString());

            // Imprimir la cabecera
            System.out.println("\n--------- LLISTAT DE LLIBRES AMB PRÉSTECS ACTIUS ------------");
            System.out.printf("%-20s %-20s %-20s\n", "id", "nom", "autor");
            System.out.println("-------------------------------------------------------------");

            // Recorrer todos los libros
            Iterator<String> keys = llibres.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject llibre = llibres.getJSONObject(key);

                String id = llibre.getString("ID");
                String nom = llibre.getString("nom");
                String autor = llibre.getString("autor");

                // Verificar si el libro está en préstamo activo
                for (int k = 0; k < prestecs.length(); k++) {
                    JSONObject prestec = prestecs.getJSONObject(k);

                    // Si el préstamo está activo
                    if (prestec.has("actiu") && prestec.getBoolean("actiu")) {
                        if (prestec.has("id_llibre")) {
                            // Buscar si el libro está en la lista de libros prestados
                            JSONArray idLlibres = prestec.getJSONArray("id_llibre");
                            for (int l = 0; l < idLlibres.length(); l++) {
                                if (idLlibres.getString(l).equals(id)) {
                                    // Si el libro está en préstamo, imprimirlo
                                    System.out.printf("%-20s %-20s %-20s\n", id, nom, autor);
                                    break; // Ya encontramos el libro, no necesitamos seguir buscando
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void llistarTotsUsuaris() {
        // Usar la variable estática filePathUsuaris para la ruta del archivo
        String filePath = filePathUsuaris;

        try (FileReader leer = new FileReader(filePath)) {
            // Leer el contenido del archivo JSON
            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }

            // Parsear el contenido JSON
            JSONArray usuaris = new JSONArray(jsoncontenido.toString());

            // Imprimir la cabecera
            System.out.println("\n------------- LLISTAT DE TOTS ELS USUARIS -------------------");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "telefon", "id", "nom", "cognom");
            System.out.println("-------------------------------------------------------------");

            // Recorrer y listar todos los usuarios
            for (int j = 0; j < usuaris.length(); j++) {
                JSONObject usuari = usuaris.getJSONObject(j);

                String telefon = usuari.getString("telefon");
                String id = usuari.getString("id");
                String nom = usuari.getString("nom");
                String cognom = usuari.getString("cognom");

                // Imprimir los datos del usuario
                System.out.printf("%-15s %-15s %-15s %-15s\n", telefon, id, nom, cognom);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void llistarUsuarisPrestecsActius() {
        // Usar las variables estáticas para las rutas de los archivos
        String usuarisFilePath = filePathUsuaris; // Ruta a usuaris.json
        String prestecsFilePath = filePathPrestecs; // Ruta a prestecs.json

        // Leer el contenido de usuaris.json y prestecs.json
        try (FileReader leer = new FileReader(usuarisFilePath);
                FileReader prestecsLeer = new FileReader(prestecsFilePath)) {

            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }
            StringBuilder prestecsJsonContenido = new StringBuilder();
            while ((i = prestecsLeer.read()) != -1) {
                prestecsJsonContenido.append((char) i);
            }

            // Parsear los contenidos JSON
            JSONArray usuaris = new JSONArray(jsoncontenido.toString());
            JSONArray prestecs = new JSONArray(prestecsJsonContenido.toString());

            // Imprimir la cabecera
            System.out.println("\n--------- LLISTAT DE USUARIS AMB PRÉSTECS ACTIUS ------------");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "telefon", "id", "nom", "cognom");
            System.out.println("-------------------------------------------------------------");

            // Buscar y listar usuarios con préstamos activos
            for (int j = 0; j < usuaris.length(); j++) {
                JSONObject usuari = usuaris.getJSONObject(j);

                String telefon = usuari.getString("telefon");
                String id = usuari.getString("id");
                String nom = usuari.getString("nom");
                String cognom = usuari.getString("cognom");

                for (int k = 0; k < prestecs.length(); k++) {
                    JSONObject prestec = prestecs.getJSONObject(k);
                    // Verificar si el préstamo es activo y si el ID de usuario coincide
                    if (prestec.getBoolean("actiu") && prestec.getString("id_User").equals(id)) {
                        System.out.printf("%-15s %-15s %-15s %-15s\n", telefon, id, nom, cognom);
                        break; // Salir del bucle de préstamos una vez que se ha encontrado un préstamo activo
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void llistarUsuarisForaTermini() {
        // Usar las variables estáticas para las rutas de los archivos
        String usuarisFilePath = filePathUsuaris;
        String prestecsFilePath = filePathPrestecs;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date avui = new Date();

        // Leer el contenido de los archivos JSON
        try (FileReader leer = new FileReader(usuarisFilePath);
                FileReader prestecsLeer = new FileReader(prestecsFilePath)) {

            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }
            StringBuilder prestecsJsonContenido = new StringBuilder();
            while ((i = prestecsLeer.read()) != -1) {
                prestecsJsonContenido.append((char) i);
            }
            JSONArray usuaris = new JSONArray(jsoncontenido.toString());
            JSONArray prestecs = new JSONArray(prestecsJsonContenido.toString());

            // Imprimir la cabecera
            System.out.println("\n------ LLISTAT DE USUARIS AMB PRÉSTECS FORA DE TERMINI ------");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "telefon", "id", "nom", "cognom");
            System.out.println("-------------------------------------------------------------");

            // Buscar y listar usuarios con préstamos fuera de término
            for (int j = 0; j < usuaris.length(); j++) {
                JSONObject usuari = usuaris.getJSONObject(j);

                String telefon = usuari.getString("telefon");
                String id = usuari.getString("id");
                String nom = usuari.getString("nom");
                String cognom = usuari.getString("cognom");

                for (int k = 0; k < prestecs.length(); k++) {
                    JSONObject prestec = prestecs.getJSONObject(k);
                    // Verificar si el préstamo está activo y si la fecha de devolución ya pasó
                    if (prestec.getBoolean("actiu") && prestec.getString("id_User").equals(id)
                            && sdf.parse(prestec.getString("data_Devolucio")).before(avui)) {
                        System.out.printf("%-15s %-15s %-15s %-15s\n", telefon, id, nom, cognom);
                        break; // Salir del bucle de préstamos una vez que se encuentra uno fuera de término
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void afegirPrestec(Scanner scanner) {
        try {
            // Leer el archivo JSON de los préstamos
            JSONArray prestecsArray = new JSONArray();
            File file = new File(filePathPrestecs); // Usar la ruta desde la variable estática

            // Verificar si el archivo existe y tiene contenido
            if (file.exists() && file.length() > 0) {
                // Leer el contenido del archivo usando la ruta estática
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
                prestecsArray = new JSONArray(content);
            } else {
                System.out.println("El archivo de préstamos está vacío o no existe en la ruta: " + filePathPrestecs);
            }

            System.out.println("\n-------- AFEGIR PRÉSTEC --------");

            String idPrestec = generarIdUnicPrestec(prestecsArray);
            System.out.println("ID del préstec generat: " + idPrestec);

            // Lista para almacenar los IDs de los libros
            JSONArray llibresArray = new JSONArray();

            // Variable para almacenar el ID del usuario
            String idUser = ""; // Declarar idUser fuera del bucle para que sea accesible en todo el método.

            // Añadir libros
            boolean seguirAñadiendoLibros = true;
            while (seguirAñadiendoLibros) {
                // Verificar si el llibre ya está prestat
                System.out.print("Introdueix el ID del llibre: ");
                String idLlibre = scanner.nextLine();

                if (llibreJaPrestat(idLlibre, prestecsArray)) {
                    System.out.println("Error: El llibre ja està prestat.\n");
                    continue;
                }

                // Verificar si el usuario ya tiene 4 libros prestados
                if (idUser.isEmpty()) { // Solo preguntar por el ID del usuario si aún no se ha solicitado
                    System.out.print("Introdueix el ID de l'usuari: ");
                    idUser = scanner.nextLine(); // Asignar el ID del usuario
                }

                int librosPrestados = comprovarPrestecsActius(idUser);
                if (librosPrestados >= 4) {
                    System.out.println("Error: L'usuari ja té " + librosPrestados
                            + " llibres prestats. No es poden afegir més llibres.\n");
                    return;
                }

                // Agregar el libro al array de libros del préstamo
                llibresArray.put(idLlibre);

                // Preguntar si se quiere añadir otro libro
                if (llibresArray.length() >= 4) {
                    System.out.println("L'usuari ja ha afegit 4 llibres al préstec. No es poden afegir més llibres.");
                    seguirAñadiendoLibros = false; // Limitar a 4 libros
                } else {
                    System.out.print("Vols afegir més llibres? (sí/no): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();
                    if (respuesta.equals("sí") || respuesta.equals("s")) {
                        seguirAñadiendoLibros = true; // Continuar añadiendo libros
                    } else {
                        seguirAñadiendoLibros = false; // Finalizar el proceso
                    }
                }
            }

            // Sol·licitar fechas
            System.out.print("Introdueix la data del préstec (yyyy-mm-dd): ");
            String fechaPrestecStr = scanner.nextLine();
            LocalDate fechaPrestec = LocalDate.parse(fechaPrestecStr);

            System.out.print("Introdueix la data de devolució (yyyy-mm-dd): ");
            String fechaDevolucioStr = scanner.nextLine();
            LocalDate fechaDevolucio = LocalDate.parse(fechaDevolucioStr);

            // Validar si el préstamo está activo
            boolean actiu = false;
            while (true) {
                System.out.print("El préstec està actiu? (s/n): ");
                String llegiractiu = scanner.nextLine().toLowerCase();
                if (llegiractiu.equals("s") || llegiractiu.equals("si")) {
                    actiu = true;
                    break;
                } else if (llegiractiu.equals("n") || llegiractiu.equals("no")) {
                    actiu = false;
                    break;
                } else {
                    System.out.println("Opció no vàlida, inserta una opció vàlida.");
                }
            }

            // Crear el objeto JSON del préstamo
            JSONObject prestecJson = new JSONObject();
            prestecJson.put("id_Prestec", idPrestec);
            prestecJson.put("id_Llibre", llibresArray);
            prestecJson.put("id_User", idUser);
            prestecJson.put("data_Prestec", fechaPrestec.toString());
            prestecJson.put("data_Devolucio", fechaDevolucio.toString());
            prestecJson.put("actiu", actiu);

            // Agregar el nuevo préstamo a la lista de préstamos
            prestecsArray.put(prestecJson);

            // Guardar los préstamos al archivo JSON
            try (FileWriter fileWriter = new FileWriter(filePathPrestecs)) { // Usar la ruta desde la variable estática
                fileWriter.write(prestecsArray.toString(4));
                System.out.println("Préstec afegit correctament!\n");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    // Método auxiliar para generar un ID único
    public static String generarIdUnicPrestec(JSONArray prestecsArray) {
        int maxId = 0;

        // Buscar el número más alto de los IDs existentes
        for (int i = 0; i < prestecsArray.length(); i++) {
            try {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                String idActual = prestec.optString("id_Prestec", "PRESTEC0");

                // Extraer el número del ID (después de "PRESTEC")
                if (idActual.startsWith("PRESTEC")) {
                    try {
                        int numeroId = Integer.parseInt(idActual.substring(7));
                        if (numeroId > maxId) {
                            maxId = numeroId;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Advertència: ID invàlid detectat: " + idActual);
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error al processar el préstec a la posició " + i + ": " + e.getMessage());
            }
        }

        // Generar un nuevo ID basado en el número más alto
        return "PRESTEC" + (maxId + 1);
    }

    public static void modificarPrestec(Scanner scanner) {
        // Usar la ruta definida en la variable estática filePathPrestecs
        try {
            System.out.println("\n ------- MODIFICAR PRÉSTEC ------- ");
            System.out.println("\nInserta l'id del préstec que vulguis modificar");
            String idBuscar = scanner.nextLine().toLowerCase();
            String contenido = new String(Files.readAllBytes(Paths.get(filePathPrestecs))); // Usar la ruta de la
                                                                                            // variable estática
            JSONArray prestecsArray = new JSONArray(contenido);

            boolean trobat = false;

            // Recorrer prestecsArray y comprobar si id_Prestec es igual a idBuscar
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                if (prestec.getString("id_Prestec").toLowerCase().equals(idBuscar)) {
                    trobat = true;
                    System.out.println(
                            "Què vols canviar?\n1)Data devolució\n2)ID Llibre\n3)Data préstec\n4)ID Usuari\n5)ID Préstec\n6)Actiu\n0Tornar");
                    String opcio = scanner.nextLine().toLowerCase();
                    switch (opcio) {

                        // Opció 1: Cambiar la data de devolució
                        case "data devolució":
                        case "1":
                        case "data devolucio":
                            System.out.print("Inserta la nova data de devolució (yyyy-mm-dd): ");
                            String novaDataDevolucio = scanner.nextLine();
                            prestec.put("data_devolucio", novaDataDevolucio);
                            Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar la
                                                                                                            // ruta de
                                                                                                            // la
                                                                                                            // variable
                                                                                                            // estática
                            break;

                        // Opció 2: Cambiar el ID del llibre
                        case "id llibre":
                        case "2":
                            JSONArray idLlibreArray = prestec.getJSONArray("id_llibre");
                            System.out.print("IDs d'aquest llibre: ");
                            for (int j = 0; j < idLlibreArray.length(); j++) {
                                System.out.println((j + 1) + ")" + idLlibreArray.getString(j));
                            }
                            System.out.print("Inserta el número de l'ID que vulguis modificar: ");
                            int llista = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (llista >= 0 && llista < idLlibreArray.length()) {
                                System.out.print("Inserta el nou ID del llibre: ");
                                String nouIDLlibre = scanner.nextLine();
                                idLlibreArray.put(llista, nouIDLlibre);
                                prestec.put("id_Llibre", idLlibreArray);
                                Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar
                                                                                                                // la
                                                                                                                // ruta
                                                                                                                // de la
                                                                                                                // variable
                                                                                                                // estática
                                break;
                            } else {
                                System.out.println("ID del llibre no vàlid");
                                break;
                            }

                            // Opció 3: Cambiar la data de préstec
                        case "data préstec":
                        case "data prestec":
                        case "3":
                            System.out.print("Inserta la nova data de préstec (yyyy-mm-dd): ");
                            String novaDataPrestec = scanner.nextLine();
                            prestec.put("data_Prestec", novaDataPrestec);
                            Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar la
                                                                                                            // ruta de
                                                                                                            // la
                                                                                                            // variable
                                                                                                            // estática
                            break;

                        // Opció 4: Cambiar el ID de usuari
                        case "id usuari":
                        case "4":
                            System.out.print("Inserta el nou id de usuari: ");
                            String nouIdUser = scanner.nextLine();
                            prestec.put("id_User", nouIdUser);
                            Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar la
                                                                                                            // ruta de
                                                                                                            // la
                                                                                                            // variable
                                                                                                            // estática
                            break;

                        // Opció 5: Cambiar el ID de préstec
                        case "id préstec":
                        case "id prestec":
                        case "5":
                            System.out.print("Inserta el nou id de préstec: ");
                            String nouIdPrestec = scanner.nextLine();
                            prestec.put("id_Prestec", nouIdPrestec);
                            Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar la
                                                                                                            // ruta de
                                                                                                            // la
                                                                                                            // variable
                                                                                                            // estática
                            break;

                        // Opció 6: Cambiar el estado de 'actiu'
                        case "actiu":
                        case "6":
                            System.out.print("El préstec està actiu? s/n: ");
                            String llegiractiu = scanner.nextLine().toLowerCase();
                            boolean actiu = false;
                            while (true) {
                                if (llegiractiu.equals("s")) {
                                    actiu = true;
                                    break;
                                }
                                if (llegiractiu.equals("n")) {
                                    actiu = false;
                                    break;
                                } else {
                                    System.out.println("Si us plau, inserta una opció vàlida");
                                    System.out.println("El préstec està actiu? s/n: ");
                                    llegiractiu = scanner.nextLine().toLowerCase();
                                }
                            }
                            prestec.put("actiu", actiu);
                            Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes()); // Usar la
                                                                                                            // ruta de
                                                                                                            // la
                                                                                                            // variable
                                                                                                            // estática
                            break;

                        // Opció de salir
                        case "tornar":
                        case "0":
                            return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Comprovar si un llibre ja està prestat
    public static boolean llibreJaPrestat(String idLlibre, JSONArray prestecsArray) {
        try {
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i); // Accedim a cada préstec

                // Comprovem si aquest préstec té llibres associats
                if (prestec.has("id_Llibre")) {
                    JSONArray llibres = prestec.getJSONArray("id_Llibre");

                    // Comprovem si el llibre es troba dins d'aquest préstec
                    for (int j = 0; j < llibres.length(); j++) {
                        if (llibres.getString(j).equals(idLlibre)) {
                            return true; // El llibre ja està prestat
                        }
                    }
                }
            }
        } catch (JSONException e) {
            // Gestionem l'excepció de manera eficient
            System.out.println("Error al processar els préstecs: " + e.getMessage() + "\n");
            return false; // Si es produeix un error, considerem que el llibre no està prestat
        }

        // Si el llibre no es troba prestat en cap préstec
        return false;
    }

    public static int comprovarPrestecsActius(String idUser) {
        int count = 0;
        try {
            // Leer el archivo JSON de los préstamos utilizando la ruta centralizada
            JSONArray prestecsArray = new JSONArray();
            File file = new File(filePathPrestecs); // Usar la ruta desde la variable estática

            if (file.exists() && file.length() > 0) {
                // Si el archivo existe y tiene contenido
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs))); // Usar la ruta desde la
                                                                                              // variable estática
                prestecsArray = new JSONArray(content);
            }

            // Recorrer y hacer comprobaciones
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);

                // Comprobar si el objeto JSON tiene la clave "id_User"
                if (prestec.has("id_User")) {
                    // Verificar si el ID del usuario coincide con el proporcionado
                    if (prestec.getString("id_User").equals(idUser) &&
                            LocalDate.now().isBefore(LocalDate.parse(prestec.getString("data_Devolucio")))) {
                        count++;
                    }
                } else {
                    System.out.println("Advertència: El préstec " + i + " no té la clau 'id_User'.");
                }
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al comprobar los préstamos activos: " + e.getMessage());
        }
        return count;
    }

    // Comprobar si el ID de préstamo ya existe
    public static boolean idPrestecExist(JSONArray prestecsArray, String idPrestec) {
        for (int i = 0; i < prestecsArray.length(); i++) {
            try {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                // Verificar si el objeto tiene la clave "id_Prestec"
                if (prestec.has("id_Prestec") && prestec.getString("id_Prestec").equals(idPrestec)) {
                    return true;
                }
            } catch (JSONException e) {
                System.out.println(
                        "Error al verificar el ID del préstec en el índice " + i + ": " + e.getMessage() + "\n");
            }
        }
        return false;
    }

    public static void llistarPrestecs(Scanner scanner) {
        while (true) {
            System.out.println(
                    "\n--- Llistar Préstecs ---\n1. Llistar tots\n2. Llistar préstecs d'un usuari\n3. Llistar préstecs actius\n4. Llistar préstecs fora de termini\n0. Tornar");
            System.out.print("Escull una opció: ");
            String opc = scanner.nextLine().toLowerCase();

            switch (opc) {
                case "1":
                case "tots":
                case "llistar tots":
                    llistarTotsPrestecs(); // Funció per llistar tots els préstecs
                    break;
                case "2":
                case "llistar préstecs d'un usuari":
                case "llistar prestecs d'un usuari":
                    llistarPrestecsUsuari(); // Funció per llistar préstecs d'un usuari
                    break;
                case "3":
                case "llistar préstecs actius":
                case "llistar prestecs actius":
                    llistarPrestecsActius(); // Funció per llistar préstecs actius
                    break;
                case "4":
                case "llistar préstecs fora de termini":
                case "llistar prestecs fora de termini":
                    llistarPrestecsForaTermini(); // Funció per llistar préstecs fora de termini
                    break;
                case "0":
                case "tornar": // Tornar al menú anterior
                    return;
                default:
                    System.out.println("Opció no vàlida");
            }
        }
    }

    public static void llistarTotsPrestecs() {
        try {
            // Leer el archivo JSON de los préstamos utilizando la ruta centralizada
            File file = new File(filePathPrestecs); // Usar la ruta desde la variable estática
            
            // Declarar la variable fuera del bloque condicional
            JSONArray prestecsArray = new JSONArray();
    
            // Verificar si el archivo existe y no está vacío
            if (file.exists() && file.length() > 0) {
                // Leer el contenido del archivo JSON
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
                prestecsArray = new JSONArray(content); // Instancia el JSONArray con el contenido leído
            }
    
            // Si el archivo no existe o está vacío, mostrar mensaje
            if (prestecsArray.length() == 0) {
                System.out.println("No s'han trobat préstecs o el fitxer està buit.");
                return;
            }
    
            // Imprimir cabecera
            System.out.println(
                    "\n---------------------------------------- LLISTAT DE PRÉSTECS ---------------------------------------------");
            System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", "ID Préstec", "Data Devolució", "ID User", "ID Llibre",
                    "Actiu", "Préstec");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------");
    
            // Recorrer y listar todos los préstamos
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", // Imprime con formato
                        prestec.getString("id_Prestec"),
                        prestec.getString("data_Devolucio"),
                        prestec.getString("id_User"),
                        prestec.getJSONArray("id_Llibre").toString(),
                        prestec.getBoolean("actiu"),
                        prestec.getString("data_Prestec"));
            }
    
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir els préstecs: " + e.getMessage() + "\n");
        }
    }
    

    public static void llistarPrestecsActius() {
        try {
            // Leer el archivo JSON de los préstamos utilizando la ruta centralizada
            File file = new File(filePathPrestecs);
            File filellibres = new File(filePathLlibres); // Usar la ruta desde la variable estática
    
            // Verificar si el archivo existe y no está vacío
            if (file.exists() && file.length() > 0) {
                // Leer el contenido del archivo JSON
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
                JSONArray prestecsArray = new JSONArray(content);
    
                // Imprimir cabecera
                System.out.println(
                        "\n-------------------------------------------- LLISTAT DE PRÉSTECS ACTIUS ------------------------------------------------");
                System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", "ID Préstec", "Data Devolució", "ID User", "ID Llibre",
                        "Actiu", "Préstec");
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------------------------");
    
                // Variable para verificar si se encontró al menos un préstamo activo
                boolean foundActiveLoan = false;
    
                // Recorrer y comprobar si el préstamo está activo
                for (int i = 0; i < prestecsArray.length(); i++) {
                    JSONObject prestec = prestecsArray.getJSONObject(i);
                    if (prestec.getBoolean("actiu")) {
                        // Imprimir el préstamo activo con formato
                        System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", 
                                prestec.getString("id_Prestec"),
                                prestec.getString("data_Devolucio"),
                                prestec.getString("id_User"),
                                prestec.getJSONArray("id_Llibre").toString(),
                                prestec.getBoolean("actiu"),
                                prestec.getString("data_Prestec"));
                        foundActiveLoan = true; // Se ha encontrado un préstamo activo
                    }
                }
    
                // Si no se han encontrado préstamos activos
                if (!foundActiveLoan) {
                    System.out.println("No s'han trobat préstecs actius.");
                }
            } else {
                System.out.println("El fitxer de préstecs no existeix o està buit.");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir els préstecs: " + e.getMessage() + "\n");
        }
    }
    

    public static void llistarPrestecsForaTermini() {
        // leer préstamo utilizando la ruta predeterminada
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date avui = new Date();

        try (FileReader prestecsLeer = new FileReader(filePathPrestecs)) {

            int i;

            StringBuilder prestecsJsonContenido = new StringBuilder();
            while ((i = prestecsLeer.read()) != -1) {
                prestecsJsonContenido.append((char) i);
            }
            JSONArray prestecs = new JSONArray(prestecsJsonContenido.toString());
            // print cabecera
            System.out.println(
                    "\n--------------------------------------- LLISTAT DE PRÉSTECS FORA DE TERMINI -------------------------------------------");
            System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", "ID Préstec", "Data Devolució", "ID User", "ID Llibre",
                    "Actiu", "Préstec");
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------------------");
            // recorrer y comprobar si el préstamo está activo y pasa de la fecha de hoy
            for (int k = 0; k < prestecs.length(); k++) {
                JSONObject prestec = prestecs.getJSONObject(k);
                if (prestec.getBoolean("actiu") && sdf.parse(prestec.getString("data_Devolucio")).before(avui)) {
                    String id_prestec = prestec.getString("id_Prestec");
                    String data_devolucio = prestec.getString("data_Devolucio");
                    String id_user = prestec.getString("id_User");
                    String id_llibre = prestec.getJSONArray("id_Llibre").toString();
                    Boolean actiu = prestec.getBoolean("actiu");
                    String data_prestec = prestec.getString("data_Prestec");

                    System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", id_prestec, data_devolucio, id_user, id_llibre,
                            actiu, data_prestec);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void llistarPrestecsUsuari() {
        // leer préstamo utilizando la ruta predeterminada
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix l'id de l'usuari: ");
        String userId = scanner.nextLine();
    
        try {
            // Leer el archivo JSON de los préstamos utilizando la ruta centralizada
            File file = new File(filePathPrestecs); // Usar la ruta desde la variable estática
    
            // Comprobar si el archivo existe y tiene contenido
            if (file.exists() && file.length() > 0) {
                // Leer el contenido del archivo JSON
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
                JSONArray prestecsArray = new JSONArray(content);
    
                System.out.println(
                        "\n-------------------------------------------- LLISTAT DE  PRÉSTECS ACTIUS ------------------------------------------------");
                System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", "ID Préstec", "Data Devolució", "ID User", "ID Llibre",
                        "Actiu", "Préstec");
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------------------------");
    
                // Imprimir el listado de préstamos por usuario
                boolean found = false; // Bandera para verificar si se encontró al menos un préstamo
                for (int i = 0; i < prestecsArray.length(); i++) {
                    JSONObject prestec = prestecsArray.getJSONObject(i);
                    if (prestec.getString("id_User").equals(userId)) {
                        found = true;
                        System.out.printf("%-15s %-20s %-15s %-60s %-20s\n", // Imprime con formato
                                prestec.getString("id_Prestec"),
                                prestec.getString("data_Devolucio"),
                                prestec.getString("id_User"),
                                prestec.getJSONArray("id_Llibre").toString(),
                                prestec.getBoolean("actiu"),
                                prestec.getString("data_Prestec"));
                    }
                }
    
                // Si no se encontraron préstamos para el usuario
                if (!found) {
                    System.out.println("No s'han trobat préstecs per aquest usuari.");
                }
            } else {
                System.out.println("El fitxer de préstecs no existeix o està buit.");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al llegir els préstecs: " + e.getMessage() + "\n");
        }
    }
    

    // Método para agregar un libro
    public static void afegirLlibre(Scanner scanner) {
        try {
            // Verificar que el archivo existe
            File file = new File(filePathLlibres);
            if (!file.exists()) {
                System.out.println("El archivo 'llibres.json' no existe en la ruta: " + filePathLlibres);
                return;
            }

            // Leer los datos existentes utilizando la ruta especificada
            String content = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            JSONObject llibresObj = new JSONObject(content);

            System.out.println("\n-------- AFEGIR LLIBRE --------");
            // Recoger datos del nuevo libro
            System.out.print("Introdueix el nom del llibre: ");
            String nomLlibre = scanner.nextLine();
            System.out.print("Introdueix el nom del autor: ");
            String autor = scanner.nextLine();

            // Validar el nombre del autor
            while (autor.trim().isEmpty() || autor.length() < 2 || autor.matches(".*\\d.*")) {
                System.out.println("Inserida un nom valgut");
                System.out.print("Introdueix el nom del autor: ");
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

            // Añadir el nuevo libro al JSONObject con una clave
            int novaKey = llibresObj.length() + 1;
            llibresObj.put(String.valueOf(novaKey), llibreJson);

            // Agregar el nuevo libro al array
            Files.write(Paths.get(filePathLlibres), llibresObj.toString(4).getBytes());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarLlibre(Scanner scanner) {
        System.out.println("\n-------- MODIFICAR LLIBRE --------");
        // Insertar el nombre del libro que se quiere modificar
        try {
            System.out.print("Inserta el nom del llibre que vulguis modificar: ");
            String nomBuscar = scanner.nextLine().toLowerCase();
            String contenido = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            JSONObject objJson = new JSONObject(contenido);

            // AGARRAR LAS CLAVES Y ITERARLAS
            @SuppressWarnings("unchecked")
            Iterator<String> keys = objJson.keys();
            boolean trobat = false;
            while (keys.hasNext()) {
                String key = keys.next();
                Object valor = objJson.get(key);

                // Si valor está en el JSONObject:
                if (valor instanceof JSONObject) {
                    JSONObject llibre = (JSONObject) valor;
                    if (llibre.getString("nom").toLowerCase().equals(nomBuscar)) {
                        trobat = true;
                        System.out.print("\nQue vols cambiar?\n1)Autor\n2)Nom\n0)Tornar\nOpció:");
                        String opcio = scanner.nextLine().toLowerCase();
                        // En caso de
                        switch (opcio) {
                            case "autor":
                            case "1":
                                System.out.println("Inserta el nou nom de l'autor:");
                                String nouAutor = scanner.nextLine();
                                llibre.put("autor", nouAutor);
                                Files.write(Paths.get(filePathLlibres), objJson.toString(4).getBytes());
                                break;
                            case "nom":
                            case "2":
                                System.out.println("Inserta el nou nom del llibre:");
                                String nouLlibre = scanner.nextLine();
                                ((JSONObject) valor).put("nom", nouLlibre);
                                Files.write(Paths.get(filePathLlibres), objJson.toString(4).getBytes());
                                break;
                            case "tornar":
                            case "0":
                                return;
                        }
                    }
                }
            }
            // Si no se encuentra
            if (!trobat) {
                System.out.println("No s'ha trobat " + nomBuscar + "\n");
            } // Error
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public static void afegirUsuari(Scanner scanner) {
        // Usar la ruta predeterminada para los usuarios
        File file = new File(filePathUsuaris);

        JSONArray usuArray = new JSONArray();

        try {
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));
                usuArray = new JSONArray(content);
            }

            System.out.println("\n-------- AFEGIR USUARI --------");
            System.out.print("Introdueix el teu DNI: ");
            String id = scanner.nextLine();

            // Verificar si el ID ya existe
            if (idExiste(usuArray, id)) {
                System.out.println("Error: El DNI del user ja existeix.\n");
                return;
            }

            // Recoger datos del nuevo usuario
            System.out.print("Introdueix el teu Nom: ");
            String nom = scanner.nextLine();

            System.out.print("Introdueix el teu Cognom: ");
            String cognom = scanner.nextLine();

            System.out.print("Introdueix el teu numero de telefon: ");
            String telefon = scanner.nextLine();

            // Validar el número de teléfono
            if (telefonValid(telefon)) {
                System.out.println("Telèfon valid.\n");
            } else { // Si el teléfono es inválido
                System.out.println("Telèfon invalid. Ha de ser nùmeric y amb 9 dígits.\n");
                return;
            }

            // Crear el objeto JSON para el nuevo usuario
            JSONObject newUser = new JSONObject();
            newUser.put("id", id);
            newUser.put("nom", nom);
            newUser.put("cognom", cognom);
            newUser.put("telefon", telefon);

            // Añadir el nuevo usuario al array
            usuArray.put(newUser);

            // Escribir el array actualizado al archivo
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(usuArray.toString(4));
            }

            System.out.println("Usuari afegit amb èxit.\n");

        } catch (IOException | JSONException e) {
            System.out.println("S'ha produït un error: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    // Comprovar el telefon
    public static boolean telefonValid(String telefon) {
        return telefon.matches("\\d{9}");
    }

    // Comprovar que el dni no existe (id)
    public static boolean idExiste(JSONArray usuArray, String id) {
        for (int i = 0; i < usuArray.length(); i++) {
            try {
                JSONObject user = usuArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    return true;
                }
            } catch (JSONException e) {
                // Gestionem l'error, si la clau "DNI" no existeix
                System.out.println("Error accedint a l'usuari: " + e.getMessage());
            }
        }
        return false;
    }

    public static void eliminarUsuari(Scanner scanner) {
        // Leer el archivo JSON
        try {
            // Usamos la ruta de archivo 'filePathUsuaris' en lugar de la cadena literal
            String filePath = filePathUsuaris;
            FileReader reader = new FileReader(filePath);
            JSONArray usuArray = new JSONArray(new JSONTokener(reader));
    
            boolean usuariEliminat = false;
            System.out.print("Introdueix el ID de l'usuari a eliminar: ");
            String idEliminar = scanner.nextLine();
    
            // Crear un nuevo JSONArray para almacenar los usuarios que no han sido eliminados
            JSONArray newUsuArray = new JSONArray();
    
            // Verificar si se encuentra el usuario y agregar a newUsuArray solo los usuarios que no son el eliminado
            for (int i = 0; i < usuArray.length(); i++) {
                JSONObject usuari = usuArray.getJSONObject(i);
                if (!usuari.getString("id").equals(idEliminar)) {
                    newUsuArray.put(usuari); // Añadir usuario a la nueva lista si no es el eliminado
                } else {
                    usuariEliminat = true; // Si encontramos el usuario, se marca como eliminado
                }
            }
    
            // Si no se encuentra el usuario con el ID proporcionado
            if (!usuariEliminat) {
                System.out.println("No s'ha trobat l'id demanat\n");
            } else {
                // Escribir el nuevo array al archivo JSON
                FileWriter writer = new FileWriter(filePath);
                writer.write(newUsuArray.toString(4));  // Escribir el JSON con formato de 4 espacios
                writer.close();
                System.out.println("Usuari eliminat correctament!");
            }
    
        } catch (IOException | JSONException e) {
            System.out.println("S'ha produït un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    // Listar todos los libros
    public static void llistarTotsLlibres() {
        String filePath = filePathLlibres;

        try (FileReader leer = new FileReader(filePath)) {
            ;

            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i);
            }

            JSONObject llibres = new JSONObject(jsoncontenido.toString());

            // Print de la taula de tots els llibres

            System.out.println("\n------------- LLISTAT DE TOTS ELS LLIBRES -------------------");
            System.out.printf("%-10s %-25s %-25s\n", "ID", "Nom", "Autor");
            System.out.println("-------------------------------------------------------------");

            // Obtenim les dades dels llibres i les printem
            @SuppressWarnings("unchecked")
            Iterator<String> keys = llibres.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject llibre = llibres.getJSONObject(key);

                String id = llibre.getString("ID");
                String nom = llibre.getString("nom");
                String autor = llibre.getString("autor");

                System.out.printf("%-10s %-25s %-25s\n", id, nom, autor);

            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    // Buscar llibre per títol
    public static void buscarLlibrePorTitol(Scanner scanner) {
        try {
            // Leer el archivo JSON de los libros
            JSONObject llibresObject = new JSONObject();
            File fileLibros = new File(filePathLlibres);

            if (fileLibros.exists() && fileLibros.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get(filePathLlibres)));

                try {
                    llibresObject = new JSONObject(content);
                } catch (Exception e) {
                    System.out.println("Error al leer el archivo de libros: El archivo no tiene formato JSON válido.");
                    return;
                }
            }
            // Scanner del llibre a buscar
            System.out.println("\n-------- BUSCAR LLIBRE --------");
            System.out.print("Introdueix el títol del llibre: ");
            String titolLlibre = scanner.nextLine().trim().toLowerCase();

            boolean llibreEncontrat = false;

            // Buscar libro por título en la base de datos de libros
            @SuppressWarnings("unchecked")
            Iterator<String> keys = llibresObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject llibre = llibresObject.getJSONObject(key);
                String idLlibre = llibre.getString("ID");
                String titol = llibre.getString("nom").toLowerCase();

                if (titol.contains(titolLlibre)) { // Usamos contains para una búsqueda parcial
                    System.out.println("Llibre trobat:");
                    System.out.println("ID Llibre: " + idLlibre);
                    System.out.println("Títol: " + llibre.getString("nom"));

                    // Buscar si el libro está prestado
                    boolean llibrePrestado = false;

                    // Leer el archivo JSON de los préstamos
                    JSONArray prestecsArray = new JSONArray();
                    File filePrestecs = new File(filePathPrestecs);
                    if (filePrestecs.exists() && filePrestecs.length() > 0) {
                        String contentPrestecs = new String(
                                Files.readAllBytes(Paths.get(filePathPrestecs)));
                        prestecsArray = new JSONArray(contentPrestecs);
                    }

                    // Verificar si el libro está en préstamo
                    for (int j = 0; j < prestecsArray.length(); j++) {
                        JSONObject prestec = prestecsArray.getJSONObject(j);
                        JSONArray llibresPrestats = prestec.getJSONArray("id_Llibre");

                        for (int k = 0; k < llibresPrestats.length(); k++) {
                            String idLlibrePrestado = llibresPrestats.getString(k);
                            if (idLlibrePrestado.equals(idLlibre)) {
                                // Si el libro está prestado, mostrar la información del préstamo
                                String idUser = prestec.getString("id_User");
                                String dataPrestec = prestec.getString("data_Prestec");
                                String dataDevolucio = prestec.getString("data_Devolucio");

                                System.out.println("Aquest llibre està prestat a:");
                                System.out.println("ID Usuari: " + idUser);
                                System.out.println("Data de Préstec: " + dataPrestec);
                                System.out.println("Data de Devolució: " + dataDevolucio);
                                llibrePrestado = true;
                                break;
                            }
                        }
                        if (llibrePrestado)
                            break;
                    }

                    // Si el libro no está prestado
                    if (!llibrePrestado) {
                        System.out.println("Aquest llibre no està prestat.\n");
                    }

                    llibreEncontrat = true;
                    break;
                }
            }
            // Si no es compleix la funcio de llibreEncontrat
            if (!llibreEncontrat) {
                System.out.println("No s'ha trobat cap llibre amb el títol: " + titolLlibre + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public static void llistarPrestecs() {
        try {
            // Leer el archivo JSON de los préstamos utilizando la ruta predeterminada
            File file = new File(filePathPrestecs);

            // Si el archivo existe y no está vacío
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));

                JSONArray prestecsArray = new JSONArray(content);

                // Mostrar los préstamos en formato de tabla
                if (prestecsArray.length() > 0) {
                    System.out.println(
                            "\n--------------------------------------------------------- Llistat de préstecs ---------------------------------------------------------");
                    // Imprimir el encabezado de la tabla
                    System.out.printf("%-15s%-15s%-50s%-20s%-25s%-10s\n", "ID Préstec", "ID Usuari",
                            "Libros en préstamo",
                            "Fecha de préstamo", "Fecha de devolución", "Actiu");
                    System.out.println(
                            "---------------------------------------------------------------------------------------------------------------------------------------");

                    // Imprimir los datos de cada préstamo
                    for (int i = 0; i < prestecsArray.length(); i++) {
                        JSONObject prestec = prestecsArray.getJSONObject(i);

                        // Extraer los datos de cada préstamo
                        String idPrestec = prestec.optString("id_Prestec", "N/A");
                        String idUser = prestec.optString("id_User", "N/A");
                        JSONArray librosArray = prestec.optJSONArray("id_Llibre");
                        String libros = librosArray != null ? librosArray.join(", ").replace("\"", "") : "[]";
                        String fechaPrestec = prestec.optString("data_Prestec", "N/A");
                        String fechaDevolucio = prestec.optString("data_Devolucio", "N/A");
                        boolean actiu = prestec.optBoolean("actiu", false);

                        // Mostrar los datos en formato de tabla
                        System.out.printf("%-15s%-15s%-50s%-20s%-25s%-10s\n", idPrestec, idUser, libros, fechaPrestec,
                                fechaDevolucio, actiu ? "Sí" : "No");
                    }
                } else {
                    System.out.println("No hi ha préstecs registrats.");
                }
            } else { // Si no existe el archivo
                System.out.println("El fitxer de préstecs està buit o no existeix.");
            }
        } catch (Exception e) { // Error
            System.out.println("Error al llegir el fitxer de préstecs: " + e.getMessage());
        }
    }

    public static boolean eliminarPrestec() throws IOException {
        // Scanner del prestec a eliminar
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n-------- ELIMINAR PRÉSTEC --------");
        System.out.print("Introdueix l'ID del préstec a eliminar: ");
        String idPrestec = scanner.nextLine();

        try {
            // Llegeix el contingut del fitxer JSON
            String archivo = filePathPrestecs;
            String contenido = new String(Files.readAllBytes(Paths.get(archivo)));
            JSONArray prestecs = new JSONArray(contenido);

            boolean trobat = false;
            for (int i = 0; i < prestecs.length(); i++) {
                JSONObject prestec = prestecs.getJSONObject(i);
                if (prestec.has("id_prestec") && prestec.getString("id_prestec").equals(idPrestec) ||
                        prestec.has("id_Prestec") && prestec.getString("id_Prestec").equals(idPrestec)) {
                    prestecs.remove(i); // Elimina el préstamo si se encuentra
                    trobat = true;
                    break;
                }
            }

            if (trobat) {
                // Guarda el contingut actualitzat al fitxer
                Files.write(Paths.get(archivo), prestecs.toString(4).getBytes());
                System.out.println("El préstec amb ID " + idPrestec + " s'ha eliminat correctament.\n");
                return true;
            } else {
                System.out.println("No s'ha trobat cap préstec amb ID " + idPrestec + ".\n");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error en processar el fitxer JSON: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage() + "\n");
        }
        return false;
    }

    // Método para listar los libros ordenados por autor
    public static void llistarLlibresPerAutor() {
        String filePath = filePathLlibres; // Ruta del archivo JSON con los libros

        try (FileReader leer = new FileReader(filePath)) {
            // Leer el contenido del archivo JSON
            StringBuilder jsoncontenido = new StringBuilder();
            int i;
            while ((i = leer.read()) != -1) {
                jsoncontenido.append((char) i); // Concatenar cada carácter al StringBuilder
            }

            JSONObject llibres = new JSONObject(jsoncontenido.toString());

            // Crear una lista para almacenar los libros
            List<JSONObject> llibresList = new ArrayList<>();

            // Obtener el iterador de claves
            Iterator<String> keys = llibres.keys();

            // Recorrer todas las claves del JSONObject
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject libro = llibres.getJSONObject(key);
                llibresList.add(libro);
            }

            // Usamos un ciclo for clásico para realizar la ordenación
            for (int j = 0; j < llibresList.size(); j++) {
                for (int k = j + 1; k < llibresList.size(); k++) {
                    try {
                        String autor1 = llibresList.get(j).getString("autor").toLowerCase();
                        String autor2 = llibresList.get(k).getString("autor").toLowerCase();

                        // Intercambiar los libros si están desordenados por autor
                        if (autor1.compareTo(autor2) > 0) {
                            // Intercambiar los elementos de la lista
                            JSONObject temp = llibresList.get(j);
                            llibresList.set(j, llibresList.get(k));
                            llibresList.set(k, temp);
                        }
                    } catch (JSONException e) {
                        System.err.println("Error al acceder al campo 'autor' de uno de los libros: " + e.getMessage());
                    }
                }
            }

            // Print de la taula
            System.out.println("\n----------------- LLISTAT DE LLIBRES PER AUTOR ----------------");
            System.out.printf("%-15s %-30s %-15s\n", "ID", "Nom", "Autor");
            System.out.println("---------------------------------------------------------------");

            // Iterar sobre la lista de libros ordenados y mostrar los detalles
            for (JSONObject libro : llibresList) {
                try {
                    String id = libro.getString("ID");
                    String nom = libro.getString("nom");
                    String autor = libro.getString("autor");

                    // Mostrar los detalles del libro en formato tabla
                    System.out.printf("%-15s %-30s %-15s\n", id, nom, autor);
                } catch (JSONException e) {
                    System.err.println("Error al acceder a los campos de uno de los libros: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            // Si ocurre un error al leer el archivo JSON, se muestra un mensaje de error
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (JSONException e) {
            // Capturar cualquier error relacionado con la manipulación del JSON
            System.err.println("Error al procesar el contenido del archivo JSON: " + e.getMessage());
        }
    }
}