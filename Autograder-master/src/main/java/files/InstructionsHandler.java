package files;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame(Autograder.prj.getName()+" Instructions");
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.setSize(400,400);
        frame.setVisible(true);
        frame.add(panel);

        JTextArea desc = new JTextArea();
        desc.setText(Autograder.prj.getInstructions());
        desc.setBounds(10, 10,365, 340);
        desc.setEditable(false);
        desc.setLineWrap(true);
        panel.add(desc);
    }
}
