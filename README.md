# 🌍 Travel Journal (JavaFX)

A desktop application developed in Java and JavaFX that allows users to create and manage a digital travel journal.

The application lets users document trips day by day, add images, store locations, navigate through pages, and save or load journals using JSON files.

## Features

* Create and manage a travel journal
* Daily travel pages
* Travel presentation page
* Add and remove images
* Store locations and coordinates
* Page navigation system
* Save journal to JSON
* Load existing journal from JSON
* JavaFX graphical user interface
* Observer-based update system

## Technologies

* Java
* JavaFX
* JSON Simple
* Object-Oriented Programming (OOP)
* MVC Architecture

## Project Structure

```text
src/
└── carnetDeVoyage/
    ├── exception/
    ├── outils/
    ├── pages/
    ├── vues/
    └── Main.java
```

## Main Concepts

* JavaFX UI development
* File persistence with JSON
* Design patterns (Singleton, Observer)
* Dynamic page management
* Image management
* Date management
* Geographic location storage

## Requirements

* Java 11 or higher
* JavaFX SDK
* JSON Simple library

## Run the Application

After configuring JavaFX and dependencies:

```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar TravelJournal.jar
```

## Future Improvements

* Interactive map integration
* PDF export
* Cloud synchronization
* Multiple journals management
* Advanced image gallery

## Author

Personal academic project developed in Java and JavaFX to practice software design, GUI development, and data persistence.

## License

Educational project.
