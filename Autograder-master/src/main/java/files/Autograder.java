package files;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Autograder {
    public static List<Project> projects = new ArrayList<>();
    public static JFrame frame = new JFrame("Python Autograder v1.0");
    public static JPanel panel = new JPanel();
    public static JLabel codeLabel = new JLabel("Enter code here:");
    public static JLabel outputLabel = new JLabel("Process Output:");
    public static JLabel projectsLabel = new JLabel("Select Project:");
    public static JTextArea codeField = new JTextArea();
    public static JTextArea outputField = new JTextArea();
    public static JButton grade = new JButton("Grade");
    public static JButton instructions = new JButton("Instructions");
    public static JButton viewError = new JButton("View Error");

    public static String code = "";
    public static Project prj;

    public static void registerProject(Project prj) {
        projects.add(prj);
    }
    public static void start() {
        prj = projects.get(0);
        panel.setLayout(null);

        grade.setBounds(10,670,150,25);
        grade.addActionListener(new GradeHandler());
        panel.add(grade);

        outputLabel.setBounds(10, 530, 100, 10);
        panel.add(outputLabel);

        codeLabel.setBounds(10, 5, 100, 10);
        panel.add(codeLabel);

        codeField.setBounds(10,20,450,450);
        codeField.setLineWrap(true);
        File f = new File("autograder.py");
        if (f.exists()) {
            try {
                for (String s : Files.readAllLines(f.toPath()))
                    codeField.setText(codeField.getText() + s + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        panel.add(codeField);
        outputField.setBounds(10,550,450,100);
        outputField.setLineWrap(true);
        outputField.setEditable(false);
        outputField.setBackground(frame.getBackground());
        panel.add(outputField);

        viewError.setVisible(false);
        viewError.setBounds(180,670,150,25);
        viewError.addActionListener(new ErrorHandler());
        panel.add(viewError);

        frame.setSize(500,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        frame.setVisible(true);
        frame.add(panel);

        List<String> projectNames = new ArrayList<>();
        for (Project prj:projects)
            projectNames.add(prj.getName());
        String[] newProjectNames = projectNames.toArray(new String[]{});
        JComboBox<String> projectsList = new JComboBox<>(newProjectNames);
        projectsList.setBounds(10, 495, 200, 25);
        projectsList.setName("Select Project");
        projectsList.addActionListener(new ComboBoxHandler());
        panel.add(projectsList);

        instructions.setBounds(230, 495, 150, 25);
        instructions.addActionListener(new InstructionsHandler());
        instructions.setVisible(true);
        panel.add(instructions);

        projectsLabel.setBounds(10, 480, 100, 10);
        panel.add(projectsLabel);
    }
}
