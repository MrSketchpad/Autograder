package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Program Error");
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.setSize(400,400);
        frame.setVisible(true);
        frame.add(panel);

        JTextArea desc = new JTextArea();
        for (String s:GradeHandler.errors) {
            if (desc.getText().isEmpty())
                desc.setText(s+"\n");
            else desc.setText(desc.getText()+s+"\n");
        }
        desc.setBounds(10, 10,365, 340);
        desc.setEditable(false);
        desc.setLineWrap(true);
        desc.setCaretColor(Color.RED);
        panel.add(desc);
    }
}
