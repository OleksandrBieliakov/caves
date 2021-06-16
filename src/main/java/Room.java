import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

    private String name;

    private Room connectedTo1;
    private boolean isDifficult1;

    private Room connectedTo2;
    private boolean isDifficult2;

    private Room connectedTo3;
    private boolean isDifficult3;

    public boolean connected(Room room) {
        return connectedTo1 == room || connectedTo2 == room || connectedTo3 == room;
    }

    public void connect(Room room, boolean pathBad) {
        if (connected(room)) {
            return;
        }
        if (connectedTo1 == null) {
            connectedTo1 = room;
            isDifficult1 = pathBad;
        } else if (connectedTo2 == null) {
            connectedTo2 = room;
            isDifficult2 = pathBad;
        } else {
            connectedTo3 = room;
            isDifficult3 = pathBad;
        }
        room.connect(this, pathBad);
    }

    public int numberOfDifficultConnections() {
        int numberOfDifficultConnections = 0;
        if (isDifficult1) {
            numberOfDifficultConnections++;
        }
        if (isDifficult2) {
            numberOfDifficultConnections++;
        }
        if (isDifficult3) {
            numberOfDifficultConnections++;
        }
        return numberOfDifficultConnections;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", numberOfDifficultConnections=" + numberOfDifficultConnections() +
                ", connectedTo1=" + connectedTo1.getName() +
                ", isDifficult1=" + isDifficult1 +
                ", connectedTo2=" + connectedTo2.getName() +
                ", isDifficult2=" + isDifficult2 +
                ", connectedTo3=" + connectedTo3.getName() +
                ", isDifficult3=" + isDifficult3 +
                '}';
    }
}
