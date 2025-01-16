package com.biblioteca00;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
                    System.out.println("Aquí anirà la funció per modificar un llibre");
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

            // Users
            switch (opc) {
                case "1":
                case "afegir":
                    afegirUsuari(scanner);
                    break;
                case "2":
                case "modificar":
                    System.out.println("Aquí anirà la funció per modificar usuaris");
                    break;
                case "3":
                case "eliminar":
                    eliminarUsuari(scanner);
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
                    modificarPrestec(scanner);
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
    
            // Captura de datos del préstamo
            System.out.print("Introdueix el ID del préstec: ");
            String idPrestec = scanner.nextLine();
            
            // Verificar que el ID del préstamo no exista
            if (idPrestecExist(prestecsArray, idPrestec)) {
                System.out.println("Error: El ID del préstec ja existeix.");
                return;
            }
    
            // Verificar si el llibre ja està prestat
            System.out.print("Introdueix el ID del llibre: ");
            String idLlibre = scanner.nextLine();
            
            if (llibreJaPrestat(idLlibre, prestecsArray)) {
                System.out.println("Error: El llibre ja està prestat.");
                return;
            }
    
            // Comprovem el límit de préstecs d'un usuari
            System.out.print("Introdueix el ID de l'usuari: ");
            String idUser = scanner.nextLine();
    
            if (comprovarPrestecsActius(idUser) >= 4) {
                System.out.println("Error: L'usuari ja té 4 préstecs actius.");
                return;
            }
    
            // Sol·licitar dates
            System.out.print("Introdueix la data del préstec (yyyy-mm-dd): ");
            String fechaPrestecStr = scanner.nextLine();
            LocalDate fechaPrestec = LocalDate.parse(fechaPrestecStr);
    
            System.out.print("Introdueix la data de devolució (yyyy-mm-dd): ");
            String fechaDevolucioStr = scanner.nextLine();
            LocalDate fechaDevolucio = LocalDate.parse(fechaDevolucioStr);


    
            // Crear un JSONObject directamente en el método
            JSONObject prestecJson = new JSONObject();
            prestecJson.put("id_Prestec", idPrestec);
            prestecJson.put("id_Llibre", new JSONArray().put(idLlibre)); // Usamos JSONArray para manejar múltiples libros
            prestecJson.put("id_User", idUser);
            prestecJson.put("data_Prestec", fechaPrestec.toString());
            prestecJson.put("data_Devolucio", fechaDevolucio.toString());

            // Verificar si los préstamos tienen la clave "id_User" ignorando mayúsculas/minúsculas
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);

                // Comprobar si el JSON tiene la clave "id_User" (sin importar mayúsculas/minúsculas)
                if (prestec.has("id_user") || prestec.has("id_User")) {
                    String idUserKey = prestec.has("id_user") ? "id_user" : "id_User";  // Usamos la clave correcta
                    String idUserValue = prestec.getString(idUserKey);
                    System.out.println("ID de usuario: " + idUserValue);
                } else {
                    System.out.println("El préstamo " + i + " no tiene la clave 'id_User'.");
                }
            }
    
            // Agregar el nuevo préstamo a la lista de préstamos
            prestecsArray.put(prestecJson);
    
            // Guardar los préstamos al archivo JSON
            try (FileWriter fileWriter = new FileWriter("mavenjson/data/prestecs.json")) {
                fileWriter.write(prestecsArray.toString(4));
                System.out.println("Préstec afegit correctament!");
            }
    
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarPrestec(Scanner scanner){

        //Demanar l'id a buscar, llegir el contingut i crear prestecsArray
        try {
            System.out.println("inserta l'id del préstec que vulguis modificar");
            String idBuscar = scanner.nextLine().toLowerCase();
            String contenido = new String(Files.readAllBytes(Paths.get("mavenjson/data/prestecs.json")));
            JSONArray prestecsArray = new JSONArray(contenido);

            boolean trobat = false;


            //Recorrer prestecsArray i comprobar si id_prestec es igual a idBuscar
            for (int i = 0; i<prestecsArray.length();i++){
                JSONObject prestec = prestecsArray.getJSONObject(i);
                if (prestec.getString("id_prestec").toLowerCase().equals(idBuscar)){
                    trobat = true;
                    System.out.println("Que vols cambiar?\n1)Data devolució\n2)ID Llibre\n3)Data préstec\n4)ID Usuari\n5)ID Préstec\n0Tornar");
                    String opcio = scanner.nextLine().toLowerCase();
                    switch(opcio){

                        //Opció 1. demanar la data de devolució i fer un put per canviarla per la que hi ha
                        case "data devolució":
                        case "1":
                        case"data devolucio":
                            System.out.print("Inserta la nova data de devolució (yyyy-mm-dd): ");
                            String novaDataDevolucio = scanner.nextLine();
                            prestec.put("data_devolucio",novaDataDevolucio);
                            Files.write(Paths.get("mavenjson/data/prestecs.json"),prestecsArray.toString(4).getBytes());
                            break;
                        
                        //Opció 2. Demanar l'id del llibre, si existeix modificarlo per el que digui
                        case "id llibre": //hacer que solamente se cambie uno de los dos IDs !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        case "2":
                            JSONArray idLlibreArray = prestec.getJSONArray("id_llibre");
                            System.out.print("Ids d'aquest llibre: ");
                            for (int j = 0; j<idLlibreArray.length();j++){
                                System.out.println((j+1)+")"+idLlibreArray.getString(j));
                            }
                            System.out.print("Inserta el número de l'ID que vulguis modificar: ");
                            int llista = scanner.nextInt()-1;
                            scanner.nextLine();
                            if (llista >= 0 && llista < idLlibreArray.length()){
                                System.out.print("Inserta el nou ID del llibre: ");
                                String nouIDLlibre = scanner.nextLine();
                                idLlibreArray.put(llista,nouIDLlibre);
                                prestec.put("id_llibre",idLlibreArray);
                                Files.write(Paths.get("mavenjson/data/prestecs.json"),prestecsArray.toString(4).getBytes());
                                break;
                            }else{
                                System.out.println("ID del llibre no vàlid");
                                break;
                            }
                        //Opció 3. Demanar la data de préstec i canviarla per la que hi ha
                        case "data préstec":
                        case "data prestec":
                        case "3":
                            System.out.print("Inserta la nova data de préstec (yyyy-mm-dd): ");
                            String novaDataPrestec = scanner.nextLine();
                            prestec.put("data_prestec",novaDataPrestec);
                            Files.write(Paths.get("mavenjson/data/prestecs.json"),prestecsArray.toString(4).getBytes());
                            break;
                        
                        //Opció 4. Demanar el nou id de usuari. Com només hi ha un, canviarlo per el nou
                        case "id usuari":
                        case "4":
                            System.out.print("Inserta el nou id de usuari: ");
                            String nouIdUser = scanner.nextLine();
                            prestec.put("id_user",nouIdUser);
                            Files.write(Paths.get("mavenjson/data/prestecs.json"),prestecsArray.toString(4).getBytes());
                            break;
                        
                        //Opció 5. Demenar el nou id de préstec. Com només hi ha un, canviarlo per el nou
                        case "id préstec":
                        case "id prestec":
                        case "5":
                            System.out.print("Inserta el nou id de préstec: ");
                            String nouIdPrestec = scanner.nextLine();
                            prestec.put("id_prestec",nouIdPrestec);
                            Files.write(Paths.get("mavenjson/data/prestecs.json"),prestecsArray.toString(4).getBytes());
                            break;
                        case "tornar":
                        case "0":
                            return;
                        }
                }
            }
        }catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    // Comprovar si un llibre ja està prestat
    public static boolean llibreJaPrestat(String idLlibre, JSONArray prestecsArray) {
        try {
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);  // Accedim a cada préstec
    
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
            System.out.println("Error al processar els préstecs: " + e.getMessage());
            return false; // Si es produeix un error, considerem que el llibre no està prestat
        }
    
        // Si el llibre no es troba prestat en cap préstec
        return false;
    }
    
    public static int comprovarPrestecsActius(String idUser) {
        int count = 0;
        try {
            String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/prestecs.json")));
            JSONArray prestecsArray = new JSONArray(content);
    
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
    
                // Comprovem si l'objecte JSON té la clau "id_User"
                if (prestec.has("id_User")) {
                    // Comprovar si el ID de l'usuari coincideix amb el proporcionat
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
                System.out.println("Error al verificar el ID del préstec en el índice " + i + ": " + e.getMessage());
            }
        }
        return false;
    }
    
    // Listar préstamos en el JSON
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

    // Método para agregar un libro
    public static void afegirLlibre(Scanner scanner) {
        try {
            // Leer los datos existentes
            String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json")));
            JSONObject llibresObj = new JSONObject(content);

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

            //Añadir el nou llibre al JSONObject amb una clau
            int novaKey = llibresObj.length()+1;
            llibresObj.put(String.valueOf(novaKey),llibreJson);

            // Agregar el nuevo libro al array
            Files.write(Paths.get("mavenjson/data/llibres.json"),llibresObj.toString(4).getBytes());
        }catch (Exception e) { 
            System.out.println("Error: "+e.getMessage());}
        }

    public static void modificarLlibre(Scanner scanner){
        //Insertar el nom del llibre que es vol modificar
        try {
            System.out.print("Inserta el nom del llibre que vulguis modificar: ");
            String nomBuscar = scanner.nextLine().toLowerCase();
            String contenido = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json"))); //Llegir llibres.json
            JSONObject objJson = new JSONObject(contenido);

            //AGAFAR LES CLAUS I ITERARLES
            Iterator <String> keys = objJson.keys();
            boolean trobat = false;
            while (keys.hasNext()){
                String key = keys.next();
                Object valor = objJson.get(key);

                //Si valor està al JSONObject:
                if (valor instanceof JSONObject){
                    JSONObject llibre = (JSONObject)valor;
                    if (llibre.getString("nom").toLowerCase().equals(nomBuscar)){
                        trobat = true;
                        System.out.print("Que vols cambiar?\n1)Autor\n2)Nom\n0)Tornar\nOpció:");
                        String opcio = scanner.nextLine().toLowerCase();
                        switch (opcio){
                            case"autor":
                            case"1":
                                System.out.println("Inserta el nou nom de l'autor:");
                                String nouAutor = scanner.nextLine();
                                llibre.put("autor",nouAutor);
                                Files.write(Paths.get("mavenjson/data/llibres.json"),objJson.toString(4).getBytes());
                                break;
                            case "nom":
                            case"2":
                                System.out.println("Inserta el nou nom del llibre:");
                                String nouLlibre = scanner.nextLine();
                                ((JSONObject)valor).put("nom",nouLlibre);
                                Files.write(Paths.get("mavenjson/data/llibres.json"),objJson.toString(4).getBytes());
                                break;
                            case"tornar":
                            case"0":
                                return;    
                    }
                }
                }
            }
            if(!trobat){
                System.out.println("No s'ha trobat "+nomBuscar);
            }            
        } catch (Exception e) { 
            System.out.println("Error: "+e.getMessage());
        }
    }

    public static void afegirUsuari(Scanner scanner) {
        JSONArray usuArray = new JSONArray();
        File file = new File("mavenjson/data/usuaris.json");

        try {
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get("mavenjson/data/usuaris.json")));
                usuArray = new JSONArray(content);
            }

            System.out.print("Introdueix el teu DNI: ");
            String id = scanner.nextLine();

            if (idExiste(usuArray, id)) {
                System.out.println("Error: El DNI del user ja existeix.");
                return;
            }

            System.out.print("Introdueix el teu Nom: ");
            String nom = scanner.nextLine();

            System.out.print("Introdueix el teu Cognom: ");
            String cognom = scanner.nextLine();

            System.out.print("Introdueix el teu numero de telefon: ");
            String telefon = scanner.nextLine();

            if (telefonValid(telefon)) {
                System.out.println("Telèfon valid.");
            } else {
                System.out.println("Telèfon invalid. Ha de ser nùmeric y amb 9 dígits.");
                return;
            }

            JSONObject newUser = new JSONObject();
            newUser.put("id", id);
            newUser.put("nom", nom);
            newUser.put("cognom", cognom);
            newUser.put("telefon", telefon);

            usuArray.put(newUser);

            // Escrivim l'array actualitzat al fitxer
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(usuArray.toString(4));
            }

            System.out.println("Usuari afegit amb èxit.");

        } catch (IOException | JSONException e) {
            System.out.println("S'ha produït un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // comprovar telefono
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
    public static void eliminarUsuari(Scanner scanner){
        //Llegir el arxiu json
        try {
            FileReader reader = new FileReader("mavenjson/data/usuaris.json");
            JSONArray usuArray = new JSONArray(new JSONTokener(reader));

            System.out.print("Introdueix el ID de l'usuari a eliminar: ");
            String idEliminar = scanner.nextLine();
            for (int i = 0; i<usuArray.length();i++){
                JSONObject usuari = usuArray.getJSONObject(i);
                if (usuari.getString("id").equals(idEliminar)){
                    usuArray.remove(i);
                    break;
                }
            }
            System.out.println("No s'ha trobat l'id demanat\n");
            FileWriter writer = new FileWriter("mavenjson/data/usuaris.json");
            writer.write(usuArray.toString(4));
            writer.close();

        } catch (IOException | JSONException e) {
                System.out.println("S'ha produït un error: " + e.getMessage());
                e.printStackTrace();        }
    }
}