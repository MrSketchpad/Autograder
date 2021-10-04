package files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public abstract class Project {
    private String name;
    private String instructions;
    private int points;
    private final int id;
    public Project(String name, String instructions, int points, int id) {
        this.name = name;
        this.instructions = instructions;
        this.points = points;
        this.id = id;
    }

    public abstract void input(BufferedWriter writer, List<String> code) throws IOException;
    public abstract int calculatePoints(List<String> code, List<String> outputs);
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getId() {
        return id;
    }
    public static String getRandomString() {
        byte[] bytes = new byte[7];
        new Random().nextBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
