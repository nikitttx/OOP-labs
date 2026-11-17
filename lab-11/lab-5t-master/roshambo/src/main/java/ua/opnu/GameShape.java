package ua.opnu;

public class GameShape {
    private String name;

    public GameShape() {
        this.name = "";
    }
    public GameShape(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Game shape";
    }
}
