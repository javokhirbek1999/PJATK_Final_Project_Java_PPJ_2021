package javokhirbek.student.developer;

public class Place extends GameMap{
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public Place() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
