package com.biblioteca;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class main {
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
                    System.out.println("Eliminar");
                }else if (opc == 4){
                    System.out.println("Llistar");
                }return;
            case "2":
            case "usuaris":
                System.out.println("Usuaris");
                System.out.println("Gestió d'Usuaris\n1. Llistat d'usuaris\n2. Llistat d'usuaris amb préstecs actius\n3. Llistat d'usuaris amb préstecs fora de termini\n0. Tornar al menú principal");
                System.out.print("Escull una opció: ");

                String uopcStr = scanner.nextLine();
                Integer uopc = Integer.parseInt(uopcStr);

                if (uopc == 1){
                    System.out.println("LListat d'usuaris");
                    llistarUsuaris();
                }else if (uopc ==2){
                    System.out.println("Llistat d'usuaris amb préstecs actius");
                }else if(uopc == 3){
                    System.out.println("Llistat d'usuaris amb préstecs fora de termini");
                }return;

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

        public static void llistarUsuaris() {

            try {
                String contingut = new String(Files.readAllBytes(Paths.get("mavenjson/data/usuaris.json")));
    
                System.out.println("Llistat d'Usuaris");
    
                JSONArray usuarisArray = new JSONArray(contingut);
                //Imprimir Llistat 
                for (int i = 0; i < usuarisArray.length(); i++) {
                    JSONObject usuari = usuarisArray.getJSONObject(i);
                    int id = usuari.getInt("id");
                    String nom = usuari.getString("nom");
                    String cognom = usuari.getString("cognom");
                    int telefon = usuari.getInt("telefon");
    
                    System.out.printf("%-4d %-15s %-15s %-10d%n", id, nom, cognom, telefon);
                }
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            
        }

        public static void llistarUsuarisPrestecsActius() {

            try {
                String contingutusuaris = new String(Files.readAllBytes(Paths.get("mavenjson/data/usuaris.json")));

                String contingutprestecs = new String(Files.readAllBytes(Paths.get("mavenjson/data/prestecs.json")));

                Localdate data_actual = LocalDate.now();

                System.out.println("Llistat d'Usuaris amb préstecs actius");

                JSONArray usuarisArray = new JSONArray(contingutusuaris);
                JSONArray prestecsArray = new JSONArray(contingutprestecs);

                for (int i = 0; i < usuarisArray.length(); i++) {

                    JSONObject usuariPrestecActiu = new JSONObject();

                    int id = usuariPrestecActiu.getInt("id");
                    String nom = usuariPrestecActiu.getString("nom");
                    String cognom = usuariPrestecActiu.getString("cognom");
                    int telefon = usuariPrestecActiu.getInt("telefon");
    
                    System.out.printf("%-4d %-15s %-15s %-10d%n", id, nom, cognom, telefon);

                    boolean prestecActiu = false;

                    for (int j = 0; j < prestecsArray.length(); j++) {

                        JSONObject prestec = prestecsArray.getJSONObject;
                        String idUsuariP = prestec.getString("id_user");
                        Localdate dataDeDevolucio = LocalDate.parse(prestec.getString("data_devolució"))


                        if (idUsuariP.equals(id) && dataDeDevolucio.isAfter(data_actual)) {
                            prestecActiu = true;
                            break;
                        }
                    }

                    if (prestecActiu) {
                        System.out.printf("%-4d %-15s %-15s %-10d%n", id, nom, cognom, telefon);

                    }






                }
                
            } catch (Exception e) {
            }
            
        }
        public static void modificarUsuaris(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String idModificar = scanner.nextLine();

            try {


                
            } catch (Exception e) {
            }


            
        }
}
