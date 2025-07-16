package de.tomasz.smolak;

import de.tomasz.smolak.controller.HashMapController;
import de.tomasz.smolak.model.HashMapService;
import de.tomasz.smolak.view.HashMapView;

/**
 * Die Klasse {@code Main} enthält den Einstiegspunkt der Anwendung.
 * <p>
 * Sie initialisiert das Model, die View und den Controller zur
 * Benutzerverwaltung mit HashMap und startet die grafische Oberfläche.
 * </p>
 *
 * @author Tomasz Smolak
 * @version 1.0
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        HashMapService service = new HashMapService("users.txt");
        HashMapView view = new HashMapView();
        new HashMapController(service, view);
    }
}
