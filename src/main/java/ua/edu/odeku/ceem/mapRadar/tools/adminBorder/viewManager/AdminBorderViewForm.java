/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.tools.adminBorder.viewManager;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ua.edu.odeku.ceem.mapRadar.tools.PanelTool;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by Aleo on 27.03.2014.
 */
public class AdminBorderViewForm implements PanelTool {
    private JPanel panel1;
    public JTable table;
    public JButton saveButton;
    private JTabbedPane tabbedPane;
    public JTable countryBorderTable;
    public JTable provinceBorderTable;

    @Override
    public JPanel rootPanel() {
        return panel1;
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
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        CellConstraints cc = new CellConstraints();
        panel1.add(panel2, cc.xy(1, 3));
        saveButton = new JButton();
        this.$$$loadButtonText$$$(saveButton, ResourceBundle.getBundle("bundles/button").getString("Save"));
        panel2.add(saveButton);
        tabbedPane = new JTabbedPane();
        panel1.add(tabbedPane, cc.xy(1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        final JScrollPane scrollPane1 = new JScrollPane();
        tabbedPane.addTab(ResourceBundle.getBundle("bundles/label").getString("countryBorder"), scrollPane1);
        countryBorderTable = new JTable();
        scrollPane1.setViewportView(countryBorderTable);
        final JScrollPane scrollPane2 = new JScrollPane();
        tabbedPane.addTab(ResourceBundle.getBundle("bundles/label").getString("provinceBorder_title"), scrollPane2);
        provinceBorderTable = new JTable();
        provinceBorderTable.setToolTipText("");
        scrollPane2.setViewportView(provinceBorderTable);
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
