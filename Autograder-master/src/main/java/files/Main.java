package files;

public class Main {
    public static void main(String[] args) {
        for (Projects s:Projects.values()) {
            Autograder.registerProject(s.getProject());
        }
        Autograder.start();
    }
}
