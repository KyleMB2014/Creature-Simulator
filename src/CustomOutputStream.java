import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
/*
            This class diverts the output from the console to the JTextArea that is
            placed in panel two of the JFrame.
 */
public class CustomOutputStream extends OutputStream {

    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));

        textArea.setCaretPosition(textArea.getDocument().getLength());

        textArea.update(textArea.getGraphics());
    }
}
