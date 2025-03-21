package it.epicode.biblioteca.archivio;

import it.epicode.biblioteca.entities.ElementoCatalogo;
import it.epicode.biblioteca.exceptions.ElementoNonTrovatoException;
import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new IllegalArgumentException("ISBN già presente nel catalogo.");
        }
        catalogo.add(elemento);
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato."));
    }

    public void rimuoviElemento(String isbn) {
        catalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof it.epicode.biblioteca.entities.Libro &&
                        ((it.epicode.biblioteca.entities.Libro) e).getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void statisticheCatalogo() {
        long numLibri = catalogo.stream().filter(e -> e instanceof it.epicode.biblioteca.entities.Libro).count();
        long numRiviste = catalogo.stream().filter(e -> e instanceof it.epicode.biblioteca.entities.Rivista).count();
        OptionalInt maxPagine = catalogo.stream().mapToInt(ElementoCatalogo::getNumeroPagine).max();
        double mediaPagine = catalogo.stream().mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0);

        System.out.println("Numero di libri: " + numLibri);
        System.out.println("Numero di riviste: " + numRiviste);
        System.out.println("Elemento con più pagine: " + maxPagine.orElse(0));
        System.out.println("Media pagine: " + mediaPagine);
    }
}
