package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class JAVA_STAT_Bibli {

    public static void main(String[] args) throws FileNotFoundException {

        // Choix de Menu :

        String question = " Choix de Menu : ";
        String[] menu1 = {
                " 1. Lister les fichiers ",
                " 2. Ajouter un fichier ",
                " 3. Supprimer un fichier ",
                " 4. Afficher les informations sur un livre ",
                " 5. Quitter le programme "};

        String[] menu2 = {
                " 1. Nombre de lignes du fichier ",
                " 2. Nombre de mots du fichier "};

        int menuChoisiNum = 0;
        String menuChoisi = "" ;
        ArrayList<String> files = new ArrayList <String>();

        files.add("/home/bnp-renault-005/Bureau/Andrea/GITHUB2/java-project-books/books/Smith/Smith-Richesse_des_Nations_1.txt");

        do {
            // Affichage du Menu principal :
            for (int i = 0; i != menu1.length; i++) {
                System.out.println(menu1[i]);
            }

            // Enreg. du choix de Menu 1 :
            System.out.println(question);

            Scanner scMenu = new Scanner(System.in); // lecture depuis l'entrée standard (clavier)
            menuChoisi = scMenu.nextLine();
            boolean flag = false;

        /*do {
            try {
                menuChoisiNum = Integer.parseInt(menuChoisi) ;
                //menuChoisiNum = scMenu.nextInt();
                flag = true;
            } catch (Exception e) {
                scMenu.nextLine();
                System.out.println("Saisi invalide, vous devez saisir le Numéro du Menu voulu ");
                scMenu = new Scanner(System.in);
            }
        } while (flag == false || menuChoisiNum == 0);
        */
            // En fonction du choix de Menu => definition des actions qui en découlent

            menuChoisiNum = Integer.parseInt(menuChoisi) ;

            String actionMenu = actionARealiser(menuChoisiNum, files, menu2);
            System.out.println(actionMenu);

        } while (menuChoisiNum != 5 || menuChoisiNum !=4);

    }


        public static String actionARealiser(int menuChoisiNum, ArrayList files, String[] menu2) throws FileNotFoundException{

            String res = " ";
            HashMap<String, Integer> resline = new HashMap<>();
            HashMap<String, Integer> resword = new HashMap<>();

            switch (menuChoisiNum) {
                case 1 : {
                    res = "LIST";

                    // Lecture de la Collection de fichier chargé sur case 2
                    for (int i=0; i < files.size() ; i++) {
                          System.out.println( (i + 1) + " : " + files.get(i));
                      }

                    break;
                }
                case 2 : {
                    res = "ADD";
                    // chargement des noms de fichiers saisis dans une ArrayList
                    System.out.println("Saisissez le path du fichier : ");
                    Scanner scListFile = new Scanner(System.in); // lecture fichier saisi sur clavier)
                    String fichierACreer = scListFile.nextLine();

                    //sauvegarde du fichier saisi dans Collection :

                    files.add(fichierACreer);

                    // System.out.println("fichier saisi : " + files);=> Affiche toute la collection files
                    break;
                }
                case 3 : {
                    res = "DEL";
                    // Lecture de la Collection de fichier chargé sur case 2 (idem case 1)
                    for (int i=0; i < files.size() ; i++) {
                        System.out.println( "AVANT :" +(i + 1) + " : " + files.get(i));
                    }
                    // Demande de choix de ArrayList à Supprimer
                    System.out.println("Saisissez le N° du fichier à supprimer : ");
                    Scanner scDelFile = new Scanner(System.in); // lecture n° fichier a delete sur clavier)
                    int ficNumToDelete = scDelFile.nextInt();

                    // Delete de l'ArrayList correspondant à l'index de l'ArrayList choisi
                    files.remove(ficNumToDelete-1);

                    // Display pour s'assurer qu'on a bien deleter l'attendu
                    for (int i=0; i < files.size() ; i++) {
                        System.out.println("APRES : " + (i + 1) + " : " + files.get(i));
                    }

                    break;
                }
                case 4 : {
                    res = "SHOW";
                    // Lecture de la Collection de fichier chargé sur case 2
                    for (int i=0; i < files.size() ; i++) {
                        System.out.println( (i + 1) + " : " + files.get(i));
                    }
                    // Proposer le choix de fichier à consulter
                    System.out.println("Choix de fichier à consulter : ");
                    Scanner scSelFile = new Scanner(System.in); // lecture n° fichier a sélectionner
                    int ficNumToSelect = scSelFile.nextInt();
                    // On Stocke le numéro du nom du fichier sélectionné à consulter :

                    System.out.println(files.get(0));
                    //files.add("/home/bnp-renault-005/Bureau/Andrea/GITHUB2/java-project-books/books/motsAndy.txt");
                    String fileSel = "/home/bnp-renault-005/Bureau/Andrea/GITHUB2/java-project-books/books/motsAndy.txt";

                    //String fileSel = files.get(ficNumToSelect-1) ; // A decommenter
                    //System.out.println(files.get(ficNumToSelect-1)) ; // A decommenter

                    // Affichage du Menu 2 :
                    for (int i = 0; i != menu2.length; i++) {
                        System.out.println(menu2[i]);
                    }
                    System.out.println(" ");
                    // Proposer le type de consultation (Nbr ligne/nbr mots) sur fichier à réaliser
                    System.out.println("Choix de consultation fichier : ");
                    Scanner scFileSelChoice = new Scanner(System.in); // lecture n° du type de consultation voulu
                    int fileSelChoice = scFileSelChoice.nextInt();
                    // En fonction du choix selectionné : Acces utilisation de fonction nbrLineWord avec param adequat
                    if (fileSelChoice == 1) {
                        String selType = "L";
                        resline = nbrLineWord(fileSel, selType); // coder puis copier la fonction du Nbr ligne/Nbr mots
                        System.out.println("Nbr de Lignes " + resline.get("Lignes : "));
                    } else {
                        String selType = "W";
                        resword = nbrLineWord(fileSel, selType);

                        // Afficher tous les éléments de la map des mots différents :
                        System.out.println("****************************");
                        for (String key : resword.keySet() ) {
                            System.out.println("Nbr de mots : " + key + " " + resword.get(key));
                        }
                        System.out.println("****************************");
                        }
                    break;
                }
                default: {
                    System.err.println("QUIT");
                }
            }
            return res;
        }

    public static HashMap<String, Integer> nbrLineWord(String fileSelec, String selType) throws FileNotFoundException {


        // Acces au fichier motAndy.txt
        Scanner scFile = new Scanner(new File(fileSelec));

        // Creation d'une collection 'data'
        ArrayList data = new ArrayList();

        // On crée une instance de HasMap pour le nombre ligne (pour homogeneiser la fonction avec celle de word)
        HashMap<String, Integer> lineCountListe = new HashMap<>();
        // on créé une instance de HashMap pour compter le Nbr d'occurance des mots
        HashMap<String, Integer> wordNumber = new HashMap<>();

        String wordRead = "";
        int wordCount = 0;
        String lineCount = "";
        int lineNumber = 0;

        // Lecture du fichier mot.txt et chargement des mots du fichier dans la HashMap créée

        if (selType.equals("W")) {

            for (int i = 0; scFile.hasNextLine(); i++) {
                // Lire fichier
                wordRead = scFile.nextLine();
                // => worcount local
               // useless=>  wordNumber.get(wordRead);
                // si get null => message vide

                if (wordNumber.containsKey(wordRead)) {
                    wordCount = wordNumber.get(wordRead);
                    wordCount = wordCount + 1;
                    wordNumber.put(wordRead, wordCount);
                    // useless => wordCount = 0;
                } else {
                    wordCount = 1;
                    wordNumber.put(wordRead, wordCount);
                }
            }
            scFile.close();
        }

        if (selType.equals("L")) {
            for (int i = 0; scFile.hasNextLine(); ++i) {
                // Lire fichier et charger collection data
                wordRead = scFile.nextLine();
                data.add(wordRead);




                //lineCount = "Nbr de lignes dans fichier : " + lineNumber;
                //System.out.println(lineCount);

            } lineNumber = data.size();
            lineCountListe.put("Lignes : ", lineNumber);
            scFile.close();
        }

        if (selType.equals("L")) {
            return lineCountListe;
        } else {
            return wordNumber;
        }

    }
}
