import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PatternSearch extends JFrame {

    private final JTextField patternField;
    private final JTextArea resultArea;

    public PatternSearch() {
        setTitle("Pattern Search");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tworzenie panelu z wejściem użytkownika
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        patternField = new JTextField();
        JButton searchButton = new JButton("Szukaj");

        inputPanel.add(new JLabel("Wzorzec: "), BorderLayout.WEST);
        inputPanel.add(patternField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        // Tworzenie obszaru do wyświetlania wyników
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Dodanie paneli do ramki
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Akcja dla przycisku wyszukiwania
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPattern();
            }
        });
    }

    private void searchPattern() {
        String patternInput = patternField.getText();
        String regexPattern = patternToRegex(patternInput);

        resultArea.setText("");

        try (Stream<Path> paths = Files.walk(Paths.get("."))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> searchInFile(path, regexPattern));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String patternToRegex(String pattern) {
        return pattern.replace("?", ".").replace("*", ".*");
    }

    private void searchInFile(Path filePath, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    resultArea.append(String.format("Plik: %s, Linia: %d, Znaleziono: %s%n",
                            filePath, lineNumber, matcher.group()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatternSearch frame = new PatternSearch();
            frame.setVisible(true);
        });
    }
}
