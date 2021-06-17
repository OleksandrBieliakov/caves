import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Cave {

    private int roomsNumber;
    private int externalRoomsNumber;
    private int numberOfCorridors;
    private int numberOfDifficultCorridors;
    private int minimumNumberOfDifficultCorridors = 0;

    private final HashMap<String, Room> rooms = new HashMap<>();

    public void addPath(String room1Name, String room2Name, boolean isDifficultCorridor) {
        Room room1 = rooms.get(room1Name);
        if (room1 == null) {
            room1 = new Room();
            room1.setName(room1Name);
            rooms.put(room1Name, room1);
        }
        Room room2 = rooms.get(room2Name);
        if (room2 == null) {
            room2 = new Room();
            room2.setName(room2Name);
            rooms.put(room2Name, room2);
        }
        room1.connect(room2, isDifficultCorridor);
        numberOfCorridors++;
        if(isDifficultCorridor) {
            numberOfDifficultCorridors++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Room cave : rooms.values()) {
            stringBuilder.append(cave.toString());
            stringBuilder.append("\n");
        }
        String roomsString = stringBuilder.toString();
        return "Cave{\n" +
                "roomsNumber=" + roomsNumber +
                ", externalRoomsNumber=" + externalRoomsNumber +
                ", numberOfCorridors=" + numberOfCorridors +
                ", numberOfDifficultCorridors=" + numberOfDifficultCorridors +
                ", rooms:\n" + roomsString +
                '}';
    }
}
