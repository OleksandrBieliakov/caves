import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CaveReader {

    private static final String PATH = "data/CAV.IN";

    private void parseFirstLine(String line, Cave cave) {
        String[] parts = line.split(" ");
        cave.setRoomsNumber(Integer.parseInt(parts[0]));
        cave.setExternalRoomsNumber(Integer.parseInt(parts[1]));
    }

    private void parserConnectionLine(String line, Cave cave) {
        String[] parts = line.split(" ");
        cave.addPath(parts[0], parts[1], parts[2].equals("1"));
    }

    public Cave readCave() {
        Cave cave = new Cave();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH))) {
            String line = reader.readLine();
            System.out.println(line);
            parseFirstLine(line, cave);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                parserConnectionLine(line, cave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cave;
    }
}
