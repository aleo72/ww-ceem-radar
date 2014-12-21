/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.utils.gui.forms;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ua.edu.odeku.ceem.mapRadar.tools.geoName.models.GeoNamesWithNameAndCoordinates;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * User: Aleo Bakalov
 * Date: 29.01.14
 * Time: 13:00
 */
public class LocationForm {
    private JPanel panel1;
    public JTextField latTextField;
    public JTextField lonTextField;
    public JComboBox<GeoNamesWithNameAndCoordinates> locationNameComboBox;
    public JButton locationHelp;

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
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        CellConstraints cc = new CellConstraints();
        panel1.add(panel2, cc.xy(1, 1));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("bundles/label").getString("radarManager_airspace_edit_latitude"));
        panel2.add(label1, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        latTextField = new JTextField();
        latTextField.setEditable(false);
        panel2.add(latTextField, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label2 = new JLabel();
        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("bundles/label").getString("radarManager_airspace_edit_longitude"));
        panel2.add(label2, cc.xy(5, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        lonTextField = new JTextField();
        lonTextField.setEditable(false);
        panel2.add(lonTextField, cc.xy(7, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:grow,center:max(d;4px):noGrow"));
        panel1.add(panel3, cc.xy(1, 3));
        locationNameComboBox = new JComboBox();
        locationNameComboBox.setEditable(true);
        panel3.add(locationNameComboBox, cc.xy(3, 2));
        final JLabel label3 = new JLabel();
        this.$$$loadLabelText$$$(label3, ResourceBundle.getBundle("bundles/label").getString("radarManager_airspace_locationName"));
        panel3.add(label3, cc.xy(1, 2));
        locationHelp = new JButton();
        this.$$$loadButtonText$$$(locationHelp, ResourceBundle.getBundle("bundles/strings").getString("button_help-?"));
        panel3.add(locationHelp, cc.xy(5, 2));
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
        return panel1;
    }
}
