package ua.edu.odeku.ceem.mapRadar.tools.geoName.imports;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ua.edu.odeku.ceem.mapRadar.tools.CeemPanel;
import ua.edu.odeku.ceem.mapRadar.tools.geoName.imports.panels.FileChooserForm;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * User: Aleo skype: aleo72
 * Date: 09.11.13
 * Time: 22:24
 */
public class ImportGeoName implements CeemPanel {

    public JPanel panel;
    public JButton importButton;
    public JButton cancelButton;
    public JProgressBar progressBar;
    public FileChooserForm fileChooserPanel;

    public ImportGeoName() {
    }

    @Override
    public JPanel getRootPanel() {
        return (JPanel) $$$getRootComponent$$$();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        panel.setLayout(new FormLayout("left:4dlu:noGrow,fill:429px:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,bottom:p:grow"));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        CellConstraints cc = new CellConstraints();
        panel.add(panel1, cc.xy(2, 5));
        importButton = new JButton();
        this.$$$loadButtonText$$$(importButton, ResourceBundle.getBundle("bundles/strings").getString("button_geoName_import"));
        panel1.add(importButton);
        cancelButton = new JButton();
        this.$$$loadButtonText$$$(cancelButton, ResourceBundle.getBundle("bundles/strings").getString("button_cancel"));
        panel1.add(cancelButton);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel.add(panel2, cc.xy(2, 3));
        progressBar = new JProgressBar();
        panel2.add(progressBar, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel.add(panel3, cc.xy(1, 3));
        fileChooserPanel = new FileChooserForm();
        panel.add(fileChooserPanel.$$$getRootComponent$$$(), cc.xy(2, 1));
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
