# Wyszukiwanie wzorców w plikach tekstowych

## Spis treści
- [Opis projektu](#opis-projektu)
- [Funkcjonalności](#funkcjonalności)
- [Instrukcja obsługi](#instrukcja-obsługi)
- [Wymagania](#wymagania)
- [Przykładowe dane](#przykładowe-dane)

## Opis projektu
Projekt służy do przeszukiwania plików tekstowych (.txt) w bieżącym katalogu w poszukiwaniu wzorca podanego przez użytkownika. Wzorzec może zawierać `*` jako dowolny ciąg znaków (w tym ciąg pusty) oraz `?` jako dokładnie jeden dowolny znak. Aplikacja posiada interfejs graficzny oparty na bibliotece Swing.

## Funkcjonalności
- Wczytywanie wzorca z interfejsu użytkownika.
- Przeszukiwanie wszystkich plików .txt w bieżącym katalogu.
- Obsługa symboli `*` i `?` w wzorcu.
- Wyświetlanie wyników w interfejsie graficznym:
    - Nazwa pliku.
    - Numer linii.
    - Znaleziony fragment.

## Instrukcja obsługi
### Uruchomienie aplikacji
1. Sklonuj repozytorium na swój lokalny komputer:
    ```sh
    git clone https://github.com/frajewski/Wyszukiwanie-tekstu.git
    ```
2. Przejdź do katalogu projektu:
    ```sh
    cd Wyszukiwanie-tekstu
    ```
3. Skompiluj kod źródłowy:
    ```sh
    javac -d out -cp src/main/java src/main/java/PatternSearch.java
    ```
4. Uruchom aplikację:
    ```sh
    java -cp out PatternSearch
    ```

## Wymagania
- Java Development Kit (JDK) w wersji 8 lub wyższej.
- Biblioteka Swing (wbudowana w JDK).

## Przykładowe dane
W repozytorium znajduje się przykładowy plik `example.txt` do testowania funkcjonalności aplikacji.

**example.txt**
