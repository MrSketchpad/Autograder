package files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public enum Projects {
    USING_IF_STATEMENT(new Project("Using If Statements",
            "Complete the lab by using an if statement to print something "+
            "if a user's input is equal to 'Print'"
    , 15, 4) {
        @Override
        public void input(BufferedWriter writer, List<String> code) throws IOException {
            writer.write("Print");
        }
        @Override
        public int calculatePoints(List<String> code, List<String> outputs) {
            boolean foundPrint = false;
            boolean foundInput = false;
            boolean foundIf = false;
            if (!outputs.isEmpty()) {
                for (String s:code) {
                    if (s.contains("print("))
                        foundPrint = true;
                    if (s.contains("input("))
                        foundInput = true;
                    if (s.contains("if"))
                        foundIf = true;
                }
            }
            for (String s:outputs) {
                if (s.contains("Print") && foundInput && foundPrint && foundIf) return 15;
            }
            return 0;
        }
    }),
    TAKING_USER_INPUT(new Project("Taking User Input",
            "Complete the lab by printing a the user's input using the "+
            "the input() function."
    , 15, 3) {
        @Override
        public void input(BufferedWriter writer, List<String> code) throws IOException {
            writer.write(randomId);
        }
        @Override
        public int calculatePoints(List<String> code, List<String> outputs) {
            boolean foundPrint = false;
            boolean foundInput = false;
            if (!outputs.isEmpty()) {
                for (String s:code) {
                    if (s.contains("print(")) {
                        foundPrint = true;
                    } if (s.contains("input("))
                        foundInput = true;
                }
            }
            for (String s:outputs) {
                if (s.contains(randomId) && foundInput && foundPrint) return 15;
            }
            return 0;
        }
    }),
    STRING_COMBINATION(new Project("String Combination",
            "Complete the lab by printing a string that uses the + or "+
            ", operators to combine multiple stings."
    , 10, 2) {
        @Override
        public void input(BufferedWriter writer, List<String> code) {

        }
        @Override
        public int calculatePoints(List<String> code, List<String> outputs) {
            if (!outputs.isEmpty()) {
                for (String s:code) {
                    if (s.contains("print(")) {
                        if (s.contains("\",\"") || s.contains("\"+\""))
                            return 10;
                    }
                }
            }
            return 0;
        }
    }),
    PRINT(new Project("Printing Something",
            "Complete the lab by printing something using the print() "+
            "function."
    , 5, 1) {
        @Override
        public void input(BufferedWriter writer, List<String> code) {

        }
        @Override
        public int calculatePoints(List<String> code, List<String> outputs) {
            if (!outputs.isEmpty()) {
                for (String s:code) {
                    if (s.contains("print(")) return 5;
                }
            }
            return 0;
        }
    }),
    ;
    private final Project prj;
    public static String randomId = Project.getRandomString();
    Projects(Project prj) {
        this.prj = prj;
    }
    public Project getProject() {
        return prj;
    }
}