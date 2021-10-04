package files;

import java.util.ArrayList;
import java.util.List;

public class Output {
    public static List<String> outputs = new ArrayList<>();
    public static void print(String s) {
        outputs.add(s);
        if (!Autograder.outputField.getText().isEmpty())
            Autograder.outputField.setText(Autograder.outputField.getText()+"\n"+s);
        else Autograder.outputField.setText(s);
    }
}
