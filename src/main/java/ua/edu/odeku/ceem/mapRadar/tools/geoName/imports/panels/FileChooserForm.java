package ua.edu.odeku.ceem.mapRadar.tools.geoName.imports.panels;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ua.edu.odeku.ceem.mapRadar.settings.Settings;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * User: Aleo skype: aleo72
 * Date: 09.11.13
 * Time: 22:28
 */
public class FileChooserForm {
    private JPanel panel;
    private JLabel label;
    private JTextField fileNameTextField;
    private JButton chooserFileButton;
    private JButton helpButton;

    private String fileName;
    private File file;
    private JFileChooser fileChooser = new JFileChooser();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles/strings", Settings.Program$.MODULE$.locale());

    {
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;

                String name = f.getName();
                if (!name.endsWith(".txt"))
                    return false;
                name = name.substring(0, name.length() - ".txt".length()).toUpperCase();
                for (String iso : Locale.getISOCountries()) {
                    if (name.equals(iso.toUpperCase()))
                        return true;
                }

                return false;
            }

            @Override
            public String getDescription() {
                return "*.txt";
            }
        });
    }

    public FileChooserForm() {
        $$$setupUI$$$();


        chooserFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = fileChooser.showDialog(FileChooserForm.this.panel, resourceBundle.getString("frame_fileChooser_choose-file"));
                if (res == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    fileName = file.getName();
                    fileNameTextField.setText(file.getAbsolutePath());
                }
            }
        });


        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, resourceBundle.getString("message_geoName_for-import"), resourceBundle.getString("title_help"), JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public File getFile() {
        return file;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:7dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow"));
        panel.setBorder(BorderFactory.createTitledBorder(ResourceBundle.getBundle("strings_uk_UA").getString("title_border_import-geoName")));
        label = new JLabel();
        label.setHorizontalAlignment(2);
        label.setHorizontalTextPosition(2);
        this.$$$loadLabelText$$$(label, ResourceBundle.getBundle("strings_uk_UA").getString("string_label_for_import_geoNames"));
        CellConstraints cc = new CellConstraints();
        panel.add(label, cc.xy(3, 1));
        fileNameTextField = new JTextField();
        fileNameTextField.setColumns(10);
        fileNameTextField.setEditable(false);
        fileNameTextField.setText("...");
        panel.add(fileNameTextField, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel.add(panel1, cc.xy(7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        chooserFileButton = new JButton();
        chooserFileButton.setHorizontalAlignment(4);
        this.$$$loadButtonText$$$(chooserFileButton, ResourceBundle.getBundle("strings_uk_UA").getString("gui_button_file_chooser"));
        panel1.add(chooserFileButton);
        helpButton = new JButton();
        this.$$$loadButtonText$$$(helpButton, ResourceBundle.getBundle("strings_uk_UA").getString("button_help-?"));
        panel1.add(helpButton);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
