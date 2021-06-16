import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {

    @Test
    void readCave() {
        CaveReader caveReader = new CaveReader();
        Cave cave = caveReader.readCave();
        System.out.println(cave);
        PathBuilder pathBuilder = new PathBuilder();
        Path path = pathBuilder.buildPath(cave);
        System.out.println(path);
    }
}