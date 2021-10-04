package files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeHandler implements ActionListener {
    public static List<String> errors = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        Autograder.code = Autograder.codeField.getText();
        Autograder.outputField.setText("");
        String s;
        try {
            String fileName = "autograder.py";
            if (Autograder.code.contains(".py")) {
                fileName = Autograder.code;
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter("autograder.py"));
                out.write(Autograder.code);
                out.close();
            }

            File file = new File(fileName);
            if (!file.exists()) {
                System.err.println("Could not find file specified.");
                return;
            }
            List<String> allContents = Files.readAllLines(file.toPath());

            ProcessBuilder builder = new ProcessBuilder("py",fileName);
            Process p = builder.start();
            BufferedReader error = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            Autograder.prj.input(write, allContents);
            write.flush();
            p.getOutputStream().close();

            Output.print("Output:");
            List<String> outputs = new ArrayList<>();
            BufferedReader input = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            while ((s = input.readLine()) != null) {
                Output.print(s);
                outputs.add(s);
            }
            boolean printed = false;
            while ((s = error.readLine()) != null) {
                printed = true;
                errors.add(s);
            }
            if (printed) {
                Output.print("The program produced an error. Check your code and try again.");
                Autograder.viewError.setVisible(true);
            } else Autograder.viewError.setVisible(false);
            int points = Autograder.prj.calculatePoints(allContents, outputs);
            if (Autograder.prj.getPoints()==points) Output.print("You completed the assignment successfully with "+points+"/"+Autograder.prj.getPoints()+" points!");
            else Output.print("You scored "+points+"/"+Autograder.prj.getPoints()+" points. Look over your code and try again.");
        }
        catch (IOException exc) {
            Scanner scan = new Scanner(System.in);
            System.err.println("There was an internal error while testing python file. Enter view to view stack trace.");
            if (scan.hasNext() && scan.nextLine().equals("view"))
                exc.printStackTrace();
        }
    }
}
