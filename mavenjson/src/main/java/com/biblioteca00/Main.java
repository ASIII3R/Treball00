package com.biblioteca00;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
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

        String opcio = scanner.nextLine().toLowerCase();
        
        switch(opcio){
            case "1":
            case "llibres":
            //Opciones si se escoge el apartado llibres
                System.out.println("Gestió de Llibres\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opc = scanner.nextLine().toLowerCase();

                //Afegir llibre
                switch(opc){
                    case "1":
                    case "afegir":
                        afegirLlibre(scanner);
                    case "2":
                    case "modificar":
                        modificarLlibre(scanner);
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar un llibre");
                    case "4":
                    case "llistar":
                        System.out.println("Llistar llibres\n1. Tots\n2.En préstec\n3.Per autor\n4.Cercar títol\n0.Tornar al menú de llibres");
                        System.out.print("Escull una opció:");
                        String opc14 = scanner.nextLine().toLowerCase(); 
                        
                        switch (opc14){
                            case "1":
                            case "tots":
                                System.out.println("Aquí anirà la funció per llistar tots els llibres");
                            case"2":
                            case"en préstec":
                                System.out.println("Aquí anirà la funció per a llistar els llibres en préstec");
                            case"3":
                            case"per autor":
                                System.out.println("Aquí anirà la funció per a llistar els llibres per autor");
                            case"4":
                            case"cercar títol":
                                System.out.println("Aquí anirà la funció per cercar títol");
                            case"0":
                            case"tornar al menú de llibres":
                            case"tornar":
                                System.out.println("Aquí es tornarà");
                        }
                    } 
            case "2":
            case "usuaris":
                System.out.println("Gestió de Usuaris\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opc2 = scanner.nextLine().toLowerCase();
                switch(opc2){
                    case "1":
                    case "afegir":
                        System.out.println("aquí anirà la funció per afegir usuaris");
                    case "2":
                    case "modificar":
                        modificarUsuaris(args);
                        break;
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar usuaris");
                    case "4":
                    case "llistar":
                        System.out.println("Llistar usuaris\n1. Tots\n2.Amb préstecs actius\n3.Amb préstecs fora de termini\n0.Tornar al menú de usuaris");
                        System.out.print("Escull una opció:");
                        String opc24 = scanner.nextLine().toLowerCase(); 
                        switch(opc24){
                            case "1":
                            case "tots":
                                System.out.println("Aquí anirà la funció per llistar tots els usuaris");
                            case"2":
                            case"en préstec":
                                System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs actius");
                            case"3":
                            case"per autor":
                                System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs fora de termini");
                            case"0":
                            case"tornar al menú de usuaris":
                            case"tornar":
                                System.out.println("Aquí es tornarà");
                                
                        }
                        break;
                }

            case "3":
            case "prestecs":
                System.out.println("Gestió de préstecs\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                String opc3 = scanner.nextLine().toLowerCase();
                switch(opc3){
                    case "1":
                    case "afegir":
                        System.out.println("aquí anirà la funció per afegir préstecs");
                    case "2":
                    case "modificar":
                        System.out.println("Aquí anirà la funció per modificar préstecs");
                    case"3":
                    case"eliminar":
                        System.out.println("Aquí anirà la funció per eliminar préstecs");
                    case "4":
                    case "llistar":
                        System.out.println("Llistar Préstecs\n1. Tots\n2.Llistar préstecs d'un usuari\n3.Llistar préstecs actius\n0.Tornar al menú de préstecs");
                        System.out.print("Escull una opció:");
                        String opc34 = scanner.nextLine().toLowerCase(); 
                        switch(opc34){
                            case "1":
                            case "tots":
                                System.out.println("Aquí anirà la funció per llistar tots els usuaris");
                            case"2":
                            case"llistar préstecs d'un usuari":
                                System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs actius");
                            case"3":
                            case"llistar préstecs actius":
                                System.out.println("Aquí anirà la funció per a llistar els usuaris amb préstecs fora de termini");
                            case"0":
                            case"tornar al menú de usuaris":
                            case"tornar":
                                System.out.println("Aquí es tornarà");
                }
            case "0":
            case "sortir":
                System.out.println("Salir");
            default:
                System.out.println("Opció no vàlida");
            }
        }
    }catch(Exception e){
        System.out.println("Error: "+e.getMessage());
    }finally{
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
    public static void modificarUsuaris(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introdueix l'id de l'usuari que vols modificar");
            String idModificar = scanner.nextLine();

            String contenido = new String(Files.readAllBytes(Paths.get("mavenjson/data/usuaris.json")));

            JSONArray usuarisArray = new JSONArray(contenido);

            boolean idTrobat = false;

            for (int i = 0; i<usuarisArray.length();i++){
            JSONObject usuari = usuarisArray.getJSONObject(i);
            if (usuari.getString("id").toLowerCase().equals(idModificar)){
                idTrobat = true;

                
            }
            if (idTrobat) {
                System.err.println("Quina dada vols modificar de l'usuari: \n1) ID\n2) Nom\n3) Cognom\n4) Telefon");

                String opcio = scanner.nextLine().toLowerCase();
                switch(opcio){

                    case "id":
                    case "1":
                    case"ID":
                        System.out.print("Inserta el nou ID: ");
                        String nouId = scanner.nextLine();
                        usuari.put("id",nouId);
                        Files.write(Paths.get("mavenjson/data/usuaris.json"),usuarisArray.toString().getBytes());
                        break;

                    case "nom":
                    case "2":
                    case"NOM":
                        System.out.print("Inserta el nou nom: ");
                        String nouNom = scanner.nextLine();
                        usuari.put("nom",nouNom);
                        Files.write(Paths.get("mavenjson/data/usuaris.json"),usuarisArray.toString().getBytes());
                        break;
                    case "cognom":
                    case "3":
                    case"COGNOM":
                        System.out.print("Inserta el nou ID: ");
                        String nouCognom = scanner.nextLine();
                        usuari.put("id",nouCognom);
                        Files.write(Paths.get("mavenjson/data/usuaris.json"),usuarisArray.toString().getBytes());
                        break;
                    case "telefon":
                    case "4":
                    case"TELEFON":
                        System.out.print("Inserta el nou telefon: ");
                        String nouTelefon = scanner.nextLine();
                        usuari.put("id",nouTelefon);
                        Files.write(Paths.get("mavenjson/data/usuaris.json"),usuarisArray.toString().getBytes());
                        break;

                }
            }
        }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }


        
    }
}
