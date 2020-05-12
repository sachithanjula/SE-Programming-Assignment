package lk.smartken.liftsimulation.model;

public class Lift {

    int currentFloor;
    int person;
    String direction;

    LiftStatus liftStatus;

    public Lift() {
    }

    public Lift(int cur_floor, LiftStatus liftStatus) {
        this.currentFloor = cur_floor;
        this.liftStatus = liftStatus;
    }

    public Lift(int currentFloor, int person, String direction, LiftStatus liftStatus) {
        this.currentFloor = currentFloor;
        this.person = person;
        this.direction = direction;
        this.liftStatus = liftStatus;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public LiftStatus getLiftStatus() {
        return liftStatus;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setLiftStatus(LiftStatus liftStatus) {
        this.liftStatus = liftStatus;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "currentFloor=" + currentFloor +
                ", person=" + person +
                ", direction='" + direction + '\'' +
                ", liftStatus=" + liftStatus +
                '}';
    }
}
