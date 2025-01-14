package com.biblioteca00;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        //print del menú
        try{
        System.out.println("gestió de biblioteca");
        System.out.println("1. Llibres");
        System.out.println("2. Usuaris");
        System.out.println("3. Préstecs");
        System.out.println("0. Sortir");
        System.out.print("Escull una opció: ");


        //Elegir una opción del menú principal

        String opcionStr = scanner.nextLine();
        int opcion = Integer.parseInt(opcionStr);
        
        switch(opcion){
            case 1:
            //Opciones si se escoge el apartado llibres
                System.out.println("Gestió de Llibres\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opcStr = scanner.nextLine();
                Integer opc = Integer.parseInt(opcStr);

                //Afegir llibre
                if (opc == 1){
                    afegirLlibre(scanner);
                }else if (opc ==2){
                    System.out.println("Modificar");
                }else if(opc == 3){
                    System.out.println("Eliminar");
                }else if (opc == 4){
                    System.out.println("Llistar");
                }return;
            case 2:
                System.out.println("Usuaris");
            case 3:
                gestionarPrestecs(scanner, prestecsArray);
            case 0:
                System.out.println("Salir");
            default:
                System.out.println("Opció no vàlida");
        }
    }   catch(Exception e){
            System.out.println("Error: "+e.getMessage());
    }   finally{
        scanner.close();
    }
        
    }
    public static void afegirLlibre(Scanner scanner){

        JSONObject llibresjson = new JSONObject();


        try{
        //Escriure nom del llibre
        System.out.print("Escriu el nom del llibre: ");
        String nomllibre = scanner.nextLine();
        System.out.println("El nom del llibre es " + nomllibre);

        //Escriure nom de l'autor
        System.out.print("Escriu el nom de l'autor: ");
        String autor = scanner.nextLine();
        System.out.println("El nom de l'autor és " + autor);

            //bucle per comprobar que autor no sigui buit, 1 caràcter o tingui números.
            
            while (autor.trim().isEmpty()||autor.length()<2||autor.matches(".*\\d.*")){
                System.out.println("Insereix un nom vàlid");
                System.out.print("Escriu el nom de l'autor: ");
                autor = scanner.nextLine();
            }

            System.out.print("El llibre escollit està en préstec? s/n ");
            String siono = scanner.nextLine().toLowerCase();

            //si es que si, que prestec sigui true i si és que no, que préstec sigui false
            boolean prestec = false;
            if (siono.equals("s")){
                prestec = true;

            }else if (siono.equals("n")){
                prestec = false;
            }
            //si no es posa un caràcter vàlid, que torni a demanar una opció
            while (!siono.equals("s")&& !siono.equals("n")){
                System.out.print("Escull una opció vàlida: s (si) o n (no) ");
                System.out.print("El llibre escollit està en préstec? s/n ");
                siono = scanner.nextLine().toLowerCase();
            }
            //Guardar el llibre afegit a l'arxiu llibres.json
            llibresjson.put("nom",nomllibre);
            llibresjson.put("autor",autor);
            llibresjson.put("prestec",prestec);
            Files.write(Paths.get("mavenjson/data/llibres.json"),llibresjson.toString(4).getBytes());
        
        }   catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println(llibresjson);

        }

    // Método para gestionar los préstamos
    public static void gestionarPrestecs(Scanner sc, JSONArray prestecsArray) {
        boolean sortir = false;
    
        // Leer el archivo JSON si existe
        try {
            File file = new File("mavenjson" + File.separator + "data" + File.separator + "prestecs.json");
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get("mavenjson" + File.separator + "data" + File.separator + "prestecs.json")));
                prestecsArray = new JSONArray(content);
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    
        // Definir la clase Prestec (fuera del método para no declararla cada vez)
        class Prestec {
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
    
        while (!sortir) {
            System.out.println("\nGestió de préstecs");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Llistar préstecs");
            System.out.println("4. Eliminar préstec");
            System.out.println("5. Tornar al menú principal");
            System.out.print("Escull una opció: ");
    
            String opc = sc.nextLine();
    
            int opcs = 0;
            try {
                opcs = Integer.parseInt(opc);
            } catch (NumberFormatException e) {
                System.out.println("Error: Si us plau, introdueix un número entre 1 i 5.\n");
                continue;
            }
    
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
    
                    // Validar el préstamo antes de agregarlo
                    if (!validarPrestec(id_prestec, idLlibres, id_user, fechaPrestec, fechaDevolucio, prestecsArray)) {
                        break;
                    }
    
                    // Crear el préstamo como JSONObject
                    JSONObject prestecJson = new JSONObject();
                    prestecJson.put("id_Prestec", id_prestec);
                    prestecJson.put("id_Llibre", new JSONArray(idLlibres));
                    prestecJson.put("id_User", id_user);
                    prestecJson.put("data_Prestec", fechaPrestec.toString());
                    prestecJson.put("data_Devolucio", fechaDevolucio.toString());
    
                    // Añadir el préstamo al array
                    prestecsArray.put(prestecJson);
    
                    // Guardar el JSON en el archivo
                    try (FileWriter file = new FileWriter("mavenjson" + File.separator + "data" + File.separator + "prestecs.json")) {
                        file.write(prestecsArray.toString(4)); // Indentación de 4 espacios
                        System.out.println("El préstec s'ha guardat correctament en prestec.json");
                    } catch (IOException e) {
                        System.out.println("Error en guardar l'arxiu JSON: " + e.getMessage());
                    }
    
                    break;
                case 5:
                    System.out.println("Tornant al menú principal.");
                    sortir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        }
    }
    