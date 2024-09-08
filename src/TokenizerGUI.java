import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Eugene Andreu Cuello && Jasper Ajias
public class TokenizerGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("'@' Tokenizer");
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel textLabel = new JLabel("Enter Text:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(textLabel, gbc);

        JTextArea textField = new JTextArea(2, 50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(new JScrollPane(textField), gbc);

        JButton button = new JButton("Tokenize");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        frame.add(button, gbc);

        JTextArea resultTextField = new JTextArea(2, 50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(new JScrollPane(resultTextField), gbc);
        resultTextField.setEditable(false);

        JLabel phase1Label = new JLabel("Phase 1 Output:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(phase1Label, gbc);

        JLabel phase2Label = new JLabel("Phase 2 Output: (Granular Breakdown)");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(phase2Label, gbc);

        JTextArea phase1Area = new JTextArea(10, 25);
        phase1Area.setLineWrap(true);
        phase1Area.setWrapStyleWord(true);
        phase1Area.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(new JScrollPane(phase1Area), gbc);

        JTextArea phase2Area = new JTextArea(10, 25);
        phase2Area.setLineWrap(true);
        phase2Area.setWrapStyleWord(true);
        phase2Area.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(new JScrollPane(phase2Area), gbc);

        Tokenizer tokenizer = new Tokenizer();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();

                tokenizer.tokenize(inputText, "@");
                resultTextField.setText(tokenizer.getResult());
                phase1Area.setText(tokenizer.getTokens());
                phase2Area.setText(tokenizer.getGranularBreakdown());
            }
        });

        frame.setVisible(true);
    }
}
