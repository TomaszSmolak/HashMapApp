package de.tomasz.smolak.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Die Klasse {@code HashMapService} verwaltet Benutzerdaten
 * in einer {@link HashMap} und bietet Funktionen zum Hinzufügen,
 * Entfernen, Prüfen sowie zum Speichern und Laden der Daten
 * aus einer Datei.
 *
 * <p>
 * Die Daten werden als Schlüssel-Wert-Paare im Format
 * {@code benutzername:passwort} gespeichert.
 * </p>
 *
 * @author Tomasz Smolak
 * @version 1.0
 */
public class HashMapService {

    /** Interne Map zur Speicherung der Benutzer. */
    private final Map<String, String> benutzer = new HashMap<>();

    /** Pfad zur Datei, in der die Benutzerdaten gespeichert werden. */
    private final String dateiPfad;

    /**
     * Erstellt einen neuen {@code HashMapService} mit dem angegebenen Dateipfad
     * und lädt beim Start bestehende Benutzerdaten.
     *
     * @param dateiPfad Pfad zur Datei mit gespeicherten Benutzerdaten
     */
    public HashMapService(String dateiPfad) {
        this.dateiPfad = dateiPfad;
        ladeBenutzerListe();
    }

    /**
     * Lädt die Benutzerdaten aus der Datei in die HashMap.
     * Falls die Datei nicht existiert, wird nichts geladen.
     */
    private void ladeBenutzerListe() {
        File datei = new File(dateiPfad);
        if (!datei.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String benutzername = parts[0].trim();
                    String passwort = parts[1].trim();
                    benutzer.put(benutzername, passwort);
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    /**
     * Fügt einen neuen Benutzer hinzu oder überschreibt das Passwort,
     * falls der Benutzername bereits existiert.
     *
     * @param benutzername Benutzername
     * @param passwort Passwort
     */
    public void addBenutzer(String benutzername, String passwort) {
        benutzer.put(benutzername, passwort);
    }

    /**
     * Prüft, ob der Benutzername existiert und das Passwort korrekt ist.
     *
     * @param benutzername Benutzername
     * @param passwort Passwort
     * @return {@code true}, wenn die Zugangsdaten korrekt sind, sonst {@code false}
     */
    public boolean checkLogin(String benutzername, String passwort) {
        return benutzer.containsKey(benutzername) && benutzer.get(benutzername).equals(passwort);
    }

    /**
     * Entfernt einen Benutzer anhand des Namens.
     *
     * @param benutzername Benutzername
     * @return {@code true}, wenn der Benutzer existierte und entfernt wurde
     */
    public boolean removeUser(String benutzername) {
        return benutzer.remove(benutzername) != null;
    }

    /**
     * Gibt alle gespeicherten Benutzer als Map zurück.
     *
     * @return Eine Map mit Benutzernamen als Schlüssel und Passwörtern als Werte
     */
    public Map<String, String> getAllUsers() {
        return benutzer;
    }

    /**
     * Speichert alle Benutzer in die angegebene Datei.
     * Jeder Eintrag wird im Format {@code benutzername:passwort} gespeichert.
     */
    public void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiPfad))) {
            for (Map.Entry<String, String> entry : benutzer.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
}
