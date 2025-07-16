package de.tomasz.smolak.view;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse {@code HashMapView} stellt die grafische Benutzeroberfläche
 * für eine einfache Benutzerverwaltung auf Basis einer HashMap dar.
 *
 * <p>
 * Sie enthält Eingabefelder für Benutzername und Passwort sowie Buttons
 * zum Hinzufügen, Löschen, Login-Test und Speichern. Zusätzlich wird eine
 * Liste aller registrierten Benutzer angezeigt.
 * </p>
 *
 * @author Tomasz Smolak
 * @version 1.0
 */
public class HashMapView extends JFrame {

    /** Textfeld zur Eingabe des Benutzernamens. */
    public JTextField usernameField;

    /** Passwortfeld zur Eingabe des Passworts. */
    public JPasswordField passwordField;

    /** Button zum Hinzufügen eines Benutzers. */
    public JButton addButton;

    /** Button zum Testen von Benutzername und Passwort. */
    public JButton loginButton;

    /** Button zum Löschen eines Benutzers. */
    public JButton deleteButton;

    /** Button zum Speichern aller Benutzerdaten in eine Datei. */
    public JButton saveButton;

    /** Datenmodell für die Liste der registrierten Benutzer. */
    public DefaultListModel<String> userListModel;

    /** Liste zur Anzeige der registrierten Benutzer. */
    public JList<String> userList;

    /**
     * Erstellt ein neues Fenster und initialisiert alle GUI-Komponenten.
     */
    public HashMapView() {
        setTitle("HashMap Benutzerverwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // zentriert das Fenster
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    /**
     * Initialisiert und platziert alle grafischen Komponenten im Fenster.
     */
    private void initComponents() {
        // Hauptpanel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        // Eingabebereich oben
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Benutzername:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Passwort:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button-Leiste in der Mitte
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Hinzufügen");
        loginButton = new JButton("Login testen");
        deleteButton = new JButton("Benutzer löschen");
        saveButton = new JButton("Speichern");

        buttonPanel.add(addButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Benutzerliste unten
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registrierte Benutzer"));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
    }
}
