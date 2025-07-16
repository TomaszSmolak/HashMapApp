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
    private final Map<String, String> users = new HashMap<>();

    /** Pfad zur Datei, in der die Benutzerdaten gespeichert werden. */
    private final String filePath;

    /**
     * Erstellt einen neuen {@code HashMapService} mit dem angegebenen Dateipfad
     * und lädt beim Start bestehende Benutzerdaten.
     *
     * @param filePath Pfad zur Datei mit gespeicherten Benutzerdaten
     */
    public HashMapService(String filePath) {
        this.filePath = filePath;
        loadUsers();
    }

    /**
     * Lädt die Benutzerdaten aus der Datei in die HashMap.
     * Falls die Datei nicht existiert, wird nichts geladen.
     */
    private void loadUsers() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.put(username, password);
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
     * @param username Benutzername
     * @param password Passwort
     */
    public void addUser(String username, String password) {
        users.put(username, password);
    }

    /**
     * Prüft, ob der Benutzername existiert und das Passwort korrekt ist.
     *
     * @param username Benutzername
     * @param password Passwort
     * @return {@code true}, wenn die Zugangsdaten korrekt sind, sonst {@code false}
     */
    public boolean checkLogin(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    /**
     * Entfernt einen Benutzer anhand des Namens.
     *
     * @param username Benutzername
     * @return {@code true}, wenn der Benutzer existierte und entfernt wurde
     */
    public boolean removeUser(String username) {
        return users.remove(username) != null;
    }

    /**
     * Gibt alle gespeicherten Benutzer als Map zurück.
     *
     * @return Eine Map mit Benutzernamen als Schlüssel und Passwörtern als Werte
     */
    public Map<String, String> getAllUsers() {
        return users;
    }

    /**
     * Speichert alle Benutzer in die angegebene Datei.
     * Jeder Eintrag wird im Format {@code benutzername:passwort} gespeichert.
     */
    public void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
}
