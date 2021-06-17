import java.util.ArrayList;
import java.util.List;

public class PathBuilder {

    private int counter = 0;

    private int numberOfRooms;
    private int minimumNumberOfDifficultCorridors;

    public Path buildPath(Cave cave) {
        numberOfRooms = cave.getRoomsNumber();
        minimumNumberOfDifficultCorridors = cave.getMinimumNumberOfDifficultCorridors();
        Path path = new Path();
        Room firstRoom = cave.getRooms().get("1");
        path.setEntrance(firstRoom);
        path = nextRoom(path, firstRoom);
        System.out.println("nextRoom calls: " + counter);
        return path;
    }

    private boolean containsPathWithLessOrSameDifficultConnections(List<Path> pathList, Path previousPath) {
        int numberOfDifficultConnections = previousPath.getNumberOfDifficultConnections() + 1;
        boolean isPathWithLessOrSameDifficultConnections = false;
        for (Path path : pathList) {
            if(path.getNumberOfDifficultConnections() <= numberOfDifficultConnections) {
                isPathWithLessOrSameDifficultConnections = true;
                break;
            }
        }
        return isPathWithLessOrSameDifficultConnections;
    }

    private Path bestPath(List<Path> pathList) {
        if (pathList.size() == 0) {
            return null;
        }
        Path bestPath = pathList.get(0);
        int smallestNumberOfDifficultConnections = bestPath.getNumberOfDifficultConnections();
        int currentNumberOfDifficultConnections;
        for (Path path : pathList) {
            currentNumberOfDifficultConnections = path.getNumberOfDifficultConnections();
            if (currentNumberOfDifficultConnections < smallestNumberOfDifficultConnections) {
                bestPath = path;
                smallestNumberOfDifficultConnections = currentNumberOfDifficultConnections;
            }
        }
        return bestPath;
    }

    private Path nextRoom(Path pathPrevious, Room previousRoom) {
        counter++;

        if (pathPrevious.numberOfRooms() == numberOfRooms) {
            Room entrance = pathPrevious.getEntrance();
            if (!previousRoom.connected(entrance)) {
                return null;
            }
            Path path = Path.copy(pathPrevious);
            if ((entrance.getConnectedTo1() == previousRoom && entrance.isDifficult1()) ||
                    (entrance.getConnectedTo2() == previousRoom && entrance.isDifficult2()) ||
                    (entrance.getConnectedTo3() == previousRoom && entrance.isDifficult3())) {
                path.increaseNumberOfDifficultConnections();
            }
            return path;
        }

        List<Path> pathList = new ArrayList<>();

        if (pathPrevious.doesNotContain(previousRoom.getConnectedTo1())) {
            Path path = Path.copy(pathPrevious);
            Room nextRoom = previousRoom.getConnectedTo1();
            path.appendRoom(nextRoom, previousRoom.isDifficult1());
            Path path1 = nextRoom(path, nextRoom);
            if (path1 != null && path1.getNumberOfDifficultConnections() == minimumNumberOfDifficultCorridors) {
                return path1;
            }
            if (path1 != null) {
                pathList.add(path1);
            }
        }
        if (pathPrevious.doesNotContain(previousRoom.getConnectedTo2()) &&
                !(previousRoom.isDifficult2() && containsPathWithLessOrSameDifficultConnections(pathList, pathPrevious))) {
            Path path = Path.copy(pathPrevious);
            Room nextRoom = previousRoom.getConnectedTo2();
            path.appendRoom(nextRoom, previousRoom.isDifficult2());
            Path path2 = nextRoom(path, nextRoom);
            if (path2 != null && path2.getNumberOfDifficultConnections() == minimumNumberOfDifficultCorridors) {
                return path2;
            }
            if (path2 != null) {
                pathList.add(path2);
            }
        }
        if (pathPrevious.doesNotContain(previousRoom.getConnectedTo3()) &&
                !(previousRoom.isDifficult3() && containsPathWithLessOrSameDifficultConnections(pathList, pathPrevious))) {
            Path path = Path.copy(pathPrevious);
            Room nextRoom = previousRoom.getConnectedTo3();
            path.appendRoom(nextRoom, previousRoom.isDifficult3());
            Path path3 = nextRoom(path, nextRoom);
            if (path3 != null && path3.getNumberOfDifficultConnections() == minimumNumberOfDifficultCorridors) {
                return path3;
            }
            if (path3 != null) {
                pathList.add(path3);
            }
        }
        return bestPath(pathList);
    }
}
