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

        String opcion = scanner.nextLine().toLowerCase();
        
        switch(opcion){
            case "1":
            case "llibres":
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
                    eliminarLLibres(args);;
                }else if (opc == 4){
                    System.out.println("Llistar");
                }return;
            case "2":
            case "usuaris":
                System.out.println("Usuaris");
            case "3":
            case "prestecs":
                System.out.println("Préstecs");
            case "0":
            case "sortir":
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

        public static void eliminarLLibres(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Introduieix el nom del llibre que vols eliminar: ");

            String llibreEliminar = scanner.nextLine().toLowerCase();

            try {
                String contingut = new String(Files.readAllBytes(Paths.get("mavenjson/data/llibres.json")));
                
                JSONArray llibresArray = new JSONArray(contingut);

                boolean eliminat = false;
                for (int i =0;i<llibresArray.length();i++){
                    JSONObject llibre = llibresArray.getJSONObject(i);
                    if (llibre.getString("nom").equals(llibreEliminar)){
                        llibresArray.remove(i);
                        eliminat = true;
                        break;
                    }else{
                        System.out.println("Nom incorrecte del llibre o no existeix");
                    }
                }
                if (eliminat) {
                    Files.write(Paths.get("mavenjson/data/llibres.json"), llibresArray.toString().getBytes());
                    System.out.println("El llibre " + llibreEliminar + " ha sigut eliminat.");
                }
            } catch (Exception e) { 
                System.out.println("Error: "+e.getMessage());
            }
        






            
            
        }
}
