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
        //print del menú
    try{
        while(funcionament){
        System.out.println("gestió de biblioteca\n1. Llibres\n2. Usuaris\n3. Préstecs\n0. Sortir");
        System.out.print("Escull una opció: ");

                // Escollir una opció del menú principal
        switch(opcio){
            case "1":
            case "llibres":
            //Obrir menu Gestió Llibres si es selecciona la opció 1
                menuGestióLlibres();
                break;

            case "2":
            case "usuaris":
            //Obrir menu Gestió Usuaris si es selecciona la opció 2
                menuGestióUsuaris();
                break;

            case "3":
            case "prestecs":
            //Obrir menú gestió préstecs si es selecciona la opció 3
                menuGestióPréstecs();
                break;

            //Si la opció es 0, sortir
            case "0":
            case "sortir":
                funcionament = false;
                break;
            default:
                System.out.println("Opció no vàlida");
            
        }}
    }catch(Exception e){
        System.out.println("Error: "+e.getMessage());
    }finally{
        scanner.close();
    }
}       
    
    public static boolean menuGestióLlibres() {
        while (true) { 
            System.out.println("Gestió de Llibres\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
            Scanner scanner = new Scanner(System.in);
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                //Afegir llibre
                switch(opc){
                    case "1":
                    case "afegir":
                        afegirLlibre(scanner);
                        break;
                    case "2":
                    case "modificar":
                        modificarLlibre(scanner);
                        break;
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar un llibre");
                        break;
                    case "4":
                    case "llistar":
                        menuLlistarLlibres();  
                        break;
                    case"0":
                    case"tornar":
                    case"tornar al menú principal":
                        return true;
                    } 
        }
    }
    public static boolean menuLlistarLlibres(){
        while (true){
            System.out.println("Llistar llibres\n1. Tots\n2.En préstec\n3.Per autor\n4.Cercar títol\n0.Tornar al menú de llibres");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escull una opció:");
            String opc = scanner.nextLine().toLowerCase(); 
            switch (opc){
                case "1":
                case "tots":
                    System.out.println("Aquí anirà la funció per llistar tots els llibres");
                    break;
                case"2":
                case"en préstec":
                    System.out.println("Aquí anirà la funció per a llistar els llibres en préstec");
                    break;
                case"3":
                case"per autor":
                    System.out.println("Aquí anirà la funció per a llistar els llibres per autor");
                    break;
                case"4":
                case"cercar títol":
                    System.out.println("Aquí anirà la funció per cercar títol");
                    break;
                case"0":
                case"tornar al menú de llibres":
                case"tornar":
                return true;

        }}
    }
    public static boolean menuGestióUsuaris() {
        while (true) { 
            System.out.println("Gestió de Usuaris\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
            Scanner scanner = new Scanner(System.in);
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                //Afegir llibre
                switch(opc){
                    case "1":
                    case "afegir":
                        System.out.println("Aquí anirà la funció per afegir usuaris");;
                        break;
                    case "2":
                    case "modificar":
                        System.out.println("Aquí anirà la funció per modificar usuaris");
                        break;
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar un Usuari");
                        break;
                    case "4":
                    case "llistar":
                        menuLlistarUsuaris();  
                        break;
                    case"0":
                    case"tornar":
                    case"tornar al menú principal":
                    return true;
                    } 
        }
    }
    public static boolean menuLlistarUsuaris(){
        while (true) { 
            System.out.println("Llistar usuaris\n1. Tots\n2.Amb préstecs actius\n3.Amb préstecs fora de termini\n0.Tornar al menú de usuaris");
            System.out.print("Escull una opció:");
            Scanner scanner = new Scanner(System.in);
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
                    return true;
                        }
        }}
    public static boolean menuGestióPréstecs() {
        while (true) { 
            System.out.println("Gestió de Préstecs\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
            Scanner scanner = new Scanner(System.in);
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                //Afegir llibre
                switch(opc){
                    case "1":
                    case "afegir":
                        System.out.println("Aquí anirà la funció per afegir préstecs");
                        break;
                    case "2":
                    case "modificar":
                        System.out.println("Aquí anirà la funció per modificar préstecs");
                        break;
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar un llibre");
                    case "4":
                    case "llistar":
                        menuLlistarPréstecs();  
                        break;
                    case"0":
                    case"tornar":
                    case"tornar al menú principal":
                        return true;
                    } 
        }
    }
    public static boolean menuLlistarPréstecs(){
        while (true){
            Scanner scanner = new Scanner(System.in);
        System.out.println("Llistar Préstecs\n1. Tots\n2.Llistar préstecs d'un usuari\n3.Llistar préstecs actius\n0.Tornar al menú de préstecs");
        System.out.print("Escull una opció:");
        String opc = scanner.nextLine().toLowerCase(); 
            switch(opc){
                case "1":
                case "tots":
                    System.out.println("Aquí anirà la funció per llistar tots els usuaris");
                    break;
                case"2":
                case"llistar préstecs d'un usuari":
                    System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs actius");
                    break;
                case"3":
                case"llistar préstecs actius":
                    System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs fora de termini");
                    break;
                case"0":
                case"tornar al menú de usuaris":
                case"tornar":
                    return true;
                }
        }
        
    }

    public static void afegirLlibre(Scanner scanner){

        JSONObject llibresjson = new JSONObject();


        try{
        //Escriure nom del llibre
        System.out.print("Escriu el nom del llibre: ");
        String nomllibre = scanner.nextLine();
        System.out.println("El nom del llibre es " + nomllibre);

            // Escriure nom de l'autor
            System.out.print("Escriu el nom de l'autor: ");
            String autor = scanner.nextLine();
            System.out.println("El nom de l'autor és " + autor);

            // bucle per comprobar que autor no sigui buit, 1 caràcter o tingui números.

            while (autor.trim().isEmpty() || autor.length() < 2 || autor.matches(".*\\d.*")) {
                System.out.println("Insereix un nom vàlid");
                System.out.print("Escriu el nom de l'autor: ");
                autor = scanner.nextLine();
            }

            //CREACIÓ DE L'ID
            Random random = new Random();
            String numerofinal = "";
        
            for (int i = 0; i<6;i++){
                int numeroRandom = random.nextInt(10);
                numerofinal += numeroRandom;
        }

        String id = numerofinal;
            //Guardar el llibre afegit a l'arxiu llibres.json
            llibresjson.put("nom",nomllibre);
            llibresjson.put("autor",autor);
            llibresjson.put("ID",id);
            Files.write(Paths.get("mavenjson/data/llibres.json"),llibresjson.toString(4).getBytes());
        
        }   catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println(llibresjson);

    }

    public static void modificarLlibre(Scanner scanner) {
        // Insertar el nom del llibre que es vol modificar
        System.out.print("Inserta el nom del llibre que vulguis modificar: ");
        String nomBuscar = scanner.nextLine().toLowerCase();

        try {

            String contingut = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json")));
            JSONObject objectLlibres = new JSONObject(contingut);
            JSONArray llibresArray = new JSONArray("llibres");

            boolean trobat = false;
            for (int i = 0; i < llibresArray.length(); i++) {
                JSONObject llibre = llibresArray.getJSONObject(i);
                if (llibre.getString("nom").equals(nomBuscar)) {
                    trobat = true;
                    System.out.println("L'has trobat!");
                    break;
                } else {
                    System.out.println("No l'has trobat");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());// COMPROBAR PORQUÉ DA ERROR LO QUE DEBERIA ESTAR BIEN
        }

    public static void modificarLlibre(Scanner scanner){
        //Insertar el nom del llibre que es vol modificar
        System.out.print("Inserta el nom del llibre que vulguis modificar: ");
        String nomBuscar = scanner.nextLine().toLowerCase();


        try {

            String contingut = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json")));
            JSONObject objectLlibres = new JSONObject(contingut);
            JSONArray llibresArray = new JSONArray("llibres");

            boolean trobat = false;
            for (int i =0;i<llibresArray.length();i++){
                JSONObject llibre = llibresArray.getJSONObject(i);
                if (llibre.getString("nom").equals(nomBuscar)){
                    trobat = true;
                    System.out.println("L'has trobat!");
                    break;
                }else{
                    System.out.println("No l'has trobat");
                }
            }
        } catch (Exception e) { 
            System.out.println("Error: "+e.getMessage());//COMPROBAR PORQUÉ DA ERROR LO QUE DEBERIA ESTAR BIEN
        }
    }
}
