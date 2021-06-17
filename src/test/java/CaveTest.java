import org.junit.jupiter.api.Test;

class CaveTest {

    @Test
    void readCaveBuildAndWritePath() {
        CaveReader caveReader = new CaveReader();
        Cave cave = caveReader.readCave();
        System.out.println(cave);
        PathBuilder pathBuilder = new PathBuilder();
        Path path = pathBuilder.buildPath(cave);
        System.out.println(path);
        PathWriter pathWriter = new PathWriter();
        pathWriter.writePath(path);
    }
}