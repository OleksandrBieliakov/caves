import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Path {

    private final LinkedList<Room> rooms = new LinkedList<>();

    private int numberOfDifficultConnections;

    public static Path copy(Path path) {
        Path copy = new Path();
        copy.getRooms().addAll(path.getRooms());
        copy.setNumberOfDifficultConnections(path.getNumberOfDifficultConnections());
        return copy;
    }

    public int numberOfRooms() {
        return rooms.size();
    }

    public boolean doesNotContain(Room room) {
        return !rooms.contains(room);
    }

    public void setEntrance(Room room) {
        rooms.add(room);
    }

    public Room getEntrance() {
        return rooms.getFirst();
    }

    public void appendRoom(Room room, boolean isDifficultConnection) {
        rooms.addLast(room);
        if (isDifficultConnection) {
            numberOfDifficultConnections++;
        }
    }

    public void increaseNumberOfDifficultConnections() {
        numberOfDifficultConnections++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Room room : rooms) {
            stringBuilder.append(room.getName());
            stringBuilder.append(" ");
        }
        String roomsString = stringBuilder.toString();
        return "Path{" +
                "rooms=" + roomsString +
                ", numberOfDifficultConnections=" + numberOfDifficultConnections +
                '}';
    }
}
