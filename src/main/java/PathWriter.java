import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PathWriter {

    private static final String PATH = "data/CAV.OUT";

    public void writePath(Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PATH))) {
            writer.write(path.roomsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
