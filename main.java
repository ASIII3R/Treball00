package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject; 
import java.io.IOException;


public class main {
    public static void main(String[] args) {

        String filePath = "./data/usuaris.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gestió de biblioteca");
        System.out.println("1. Llibres");
        System.out.println("2. Usuaris");
        System.out.println("3. Préstecs");
        System.out.println("0. Sortir");
        System.out.print("Escull una opció: ");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Llibres");
                break;
            case 2:
                System.out.println("Usuaris");
                System.out.println("Gestió d'Usuaris\n1. Llistat d'usuaris\n2. Llistat d'usuaris amb préstecs actius\n3. Llistat d'usuaris amb préstecs fora de termini\n0. Tornar al menú principal");
                System.out.print("Escull una opció: ");

                int opc = scanner.nextInt();

                switch (opc) {
                    case 1:
                        System.out.println("Llistat d'usuaris");
                        JSONArray usuariosArray = new JSONArray(content);
                    
                        for (int i = 0; i < usuariosArray.length(); i++) {
                            JSONObject usuari = usuariosArray.getJSONObject(i);
                            int id = usuari.getInt("id");
                            String nom = usuari.getString("nom");
                            String cognom = usuari.getString("cognom");
                            int telefon = usuari.getInt("telefon");
                            System.out.println("Usuario{id:" + id + ", nom:'" + nom + "', cognom:'" + cognom + "', telefon:'" + telefon + "'}");
                        }
                        break;
                    case 2:
                        System.out.println("LListat d'usuaris amb préstecs actius");
                        break;
                    case 3:
                        System.out.println("Llistat d'usuaris amb préstecs fora de termini");
                        break;
                    case 0:
                        System.out.println("Tornar al menú principal");
                        break;
                }
                break;
            case 3:
                System.out.println("Préstecs");
                break;
            case 0:
                System.out.println("Sortir");
                break;
            default:
                System.out.println("Opció no vàlida");
        }

        scanner.close();
    }
}
