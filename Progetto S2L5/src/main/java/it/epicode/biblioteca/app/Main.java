
package it.epicode.biblioteca.app;

import it.epicode.biblioteca.archivio.Archivio;
import it.epicode.biblioteca.entities.Libro;
import it.epicode.biblioteca.entities.Rivista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1) Aggiungi Libro");
            System.out.println("2) Aggiungi Rivista");
            System.out.println("3) Cerca per ISBN");
            System.out.println("4) Rimuovi per ISBN");
            System.out.println("5) Statistiche del Catalogo");
            System.out.println("6) Esci");


            int scelta = scanner.nextInt();
            scanner.nextLine();

            if (scelta == 6) {
                System.out.println("Uscita dal programma.");
                break;
            }

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci ISBN:");
                    String isbnLibro = scanner.nextLine();
                    System.out.println("Inserisci Titolo:");
                    String titoloLibro = scanner.nextLine();
                    System.out.println("Inserisci Anno di Pubblicazione:");
                    int annoLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci Numero Pagine:");
                    int pagineLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci Autore:");
                    String autoreLibro = scanner.nextLine();
                    System.out.println("Inserisci Genere:");
                    String genereLibro = scanner.nextLine();

                    archivio.aggiungiElemento(new Libro(isbnLibro, titoloLibro, annoLibro, pagineLibro, autoreLibro, genereLibro));
                    System.out.println("Libro aggiunto con successo!");
                    break;

                case 2:
                    System.out.println("Inserisci ISBN:");
                    String isbnRivista = scanner.nextLine();
                    System.out.println("Inserisci Titolo:");
                    String titoloRivista = scanner.nextLine();
                    System.out.println("Inserisci Anno di Pubblicazione:");
                    int annoRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci Numero Pagine:");
                    int pagineRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                    String periodicitaStr = scanner.nextLine().toUpperCase();


                    Rivista.Periodicita periodicita;
                    try {
                        periodicita = Rivista.Periodicita.valueOf(periodicitaStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore");
                        break;
                    }

                    archivio.aggiungiElemento(new Rivista(isbnRivista, titoloRivista, annoRivista, pagineRivista, periodicita));
                    System.out.println("Rivista aggiunta con successo!");
                    break;


                case 3:
                    System.out.println("Inserisci ISBN da cercare:");
                    String isbnCerca = scanner.nextLine();
                    System.out.println(archivio.cercaPerIsbn(isbnCerca));
                    break;

                case 4:
                    System.out.println("Inserisci ISBN da rimuovere:");
                    String isbnRimuovi = scanner.nextLine();
                    archivio.rimuoviElemento(isbnRimuovi);
                    System.out.println("Elemento rimosso con successo (se esistente).");
                    break;

                case 5:
                    archivio.statisticheCatalogo();
                    break;

                default:
                    System.out.println("Scelta non valida Riprova.");
            }
        }

        scanner.close();
    }
}
