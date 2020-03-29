/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bunnyspa
 */
public class MainDialog extends javax.swing.JDialog {

    private static final String PATH = new File("").getAbsolutePath();
    private static final String URL_JAR = "https://github.com/Bunnyspa/GFChipCalc/releases/latest/download/GFChipCalc.jar";

    /**
     * Creates new form MainDialog
     */
    public MainDialog() {
        initComponents();
        setCenter();
    }

    private void setCenter() {
        setLocationRelativeTo(null);
    }

    public void start() {
        download();
        System.exit(0);
    }

    private void download() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(URL_JAR).openStream());
                FileOutputStream fileOS = new FileOutputStream("GFChipCalc.download")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            int retval = JOptionPane.showConfirmDialog(this,
                    "An error has occurred while downloading. Would you like to visit the webpage instead?",
                    "Error", JOptionPane.YES_NO_OPTION);
            if (retval == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            openWeb(this);
            return;
        }
        new File("GFChipCalc.jar").delete();
        new File("GFChipCalc.download").renameTo(new File("GFChipCalc.jar"));
        runProgram();
    }

    private static void runProgram() {
        try {
            String exePath = PATH + "\\GFChipCalc.jar";
            ProcessBuilder process = new ProcessBuilder("java", "-jar", exePath);
            process.directory(new File(PATH + "\\"));
            process.start();
        } catch (IOException ex) {
            Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void openWeb(Component c) {
        try {
            Desktop.getDesktop().browse(new URI(URL_JAR));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(c,
                    "An error has occurred while downloading.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        aLabel.setText("Downloading GFChipCalc.jar");
        aLabel.setPreferredSize(new java.awt.Dimension(500, 16));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JButton cancelButton;
    // End of variables declaration//GEN-END:variables
}
