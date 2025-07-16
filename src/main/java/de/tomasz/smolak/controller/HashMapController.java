package de.tomasz.smolak.controller;

import de.tomasz.smolak.model.HashMapService;
import de.tomasz.smolak.view.HashMapView;

import javax.swing.*;

/**
 * Der {@code HashMapController} verbindet die Benutzeroberfläche (View)
 * mit der Geschäftslogik (Model) zur Verwaltung von Benutzern über eine HashMap.
 *
 * <p>
 * Er reagiert auf Benutzeraktionen in der GUI, z. B. das Hinzufügen, Löschen
 * oder Testen von Anmeldedaten, und delegiert die Logik an den {@link HashMapService}.
 * </p>
 *
 * @author Tomasz Smolak
 * @version 1.0
 */
public class HashMapController {

    /** Dienstklasse zur Benutzerverwaltung. */
    private final HashMapService service;

    /** Grafische Benutzeroberfläche. */
    private final HashMapView view;

    /**
     * Erstellt einen neuen {@code HashMapController} und verbindet die View mit dem Service.
     *
     * @param service die logische Schicht zur Benutzerverwaltung
     * @param view    die grafische Benutzeroberfläche
     */
    public HashMapController(HashMapService service, HashMapView view) {
        this.service = service;
        this.view = view;

        initController();
        updateUserList();
    }

    /**
     * Initialisiert alle Button-Aktionen (Event Listener).
     */
    private void initController() {
        view.addButton.addActionListener(e -> addUser());
        view.loginButton.addActionListener(e -> testLogin());
        view.deleteButton.addActionListener(e -> deleteUser());
        view.saveButton.addActionListener(e -> saveUsers());
    }

    /**
     * Fügt einen neuen Benutzer mit Benutzernamen und Passwort hinzu.
     * Zeigt eine Erfolgsmeldung und aktualisiert die Benutzerliste.
     */
    private void addUser() {
        String username = view.usernameField.getText().trim();
        String password = new String(view.passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Bitte Benutzername und Passwort eingeben.");
            return;
        }

        service.addBenutzer(username, password);
        showMessage("Benutzer hinzugefügt.");
        updateUserList();
        clearInput();
    }

    /**
     * Prüft die eingegebenen Login-Daten gegen die gespeicherten Benutzer.
     * Gibt Rückmeldung über Erfolg oder Misserfolg.
     */
    private void testLogin() {
        String username = view.usernameField.getText().trim();
        String password = new String(view.passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Bitte Benutzername und Passwort eingeben.");
            return;
        }

        boolean success = service.checkLogin(username, password);

        if (success) {
            showMessage("Login erfolgreich!");
        } else {
            showMessage("Login fehlgeschlagen!");
        }
    }

    /**
     * Löscht den Benutzer mit dem im Eingabefeld angegebenen Namen.
     * Gibt Rückmeldung, ob der Benutzer existierte und gelöscht wurde.
     */
    private void deleteUser() {
        String username = view.usernameField.getText().trim();

        if (username.isEmpty()) {
            showMessage("Bitte einen Benutzernamen zum Löschen eingeben.");
            return;
        }

        boolean removed = service.removeUser(username);

        if (removed) {
            showMessage("Benutzer \"" + username + "\" wurde gelöscht.");
            updateUserList();
            clearInput();
        } else {
            showMessage("Benutzer \"" + username + "\" wurde nicht gefunden.");
        }
    }

    /**
     * Speichert alle aktuellen Benutzerdaten in die Datei.
     * Gibt eine Bestätigungsmeldung aus.
     */
    private void saveUsers() {
        service.saveUsers();
        showMessage("Benutzer wurden gespeichert.");
    }

    /**
     * Aktualisiert die Benutzerliste in der View mit allen gespeicherten Benutzernamen.
     */
    private void updateUserList() {
        view.userListModel.clear();
        for (String username : service.getAllUsers().keySet()) {
            view.userListModel.addElement(username);
        }
    }

    /**
     * Zeigt eine Meldung in einem Dialogfenster an.
     *
     * @param message Die anzuzeigende Nachricht.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }

    /**
     * Leert alle Eingabefelder der View.
     */
    private void clearInput() {
        view.usernameField.setText("");
        view.passwordField.setText("");
    }
}
