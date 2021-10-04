package files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getSource().toString().substring(
                e.getSource().toString().indexOf("selectedItemReminder"), e.getSource().toString().length()-1);
        name = name.replaceAll("selectedItemReminder=", "");
        for (Project prj: Autograder.projects) {
            if (prj.getName().equals(name)) {
                Autograder.prj = prj;
                break;
            }
        }
    }
}
