/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author pepeb
 */
public class TransactionPending extends javax.swing.JFrame {

    /**
     * Creates new form TransactionPending
     */
    public TransactionPending() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        jLabel3.setText("Your pending transactions:");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("SEND TRANSACTION TO THE NETWORK");

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setText("VERIFY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setText("CLOSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText("Pepe Blasco - ETSIT UPV 2017/18");
        jLabel9.setToolTipText("");

        jLabel2.setText("Your pending transactions:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("Verify a transaction:");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(430, 430, 430)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton3))
                                .addComponent(jLabel2))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel1)
                            .addContainerGap(61, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jSeparator1)
                            .addContainerGap()))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(8, 8, 8)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(460, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // get info of the transaction
            String transactionID = jTextField1.getText();
            Transaction wantedTransaction = null;
            Transaction t = null;

            //find the transaction on the pending transaction list
            Iterator<Transaction> it = main.TFG.getPendingTransactions().iterator();
            while (it.hasNext()) {
                t = it.next();

                if (t.getID().equals(transactionID) && t.getUserReciever().equals(main.getLocalUser().getAddress())) {
                    wantedTransaction = t;
                }
            }

            //add to the network after encrypted with private key            
            PrivateKey privKey = main.getLocalUser().getPrivateKey();
            Cipher encrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, privKey);
            byte[] encryptedContents = encrypt.doFinal(t.getEncryptedContents());

            t.setEncryptedContents(encryptedContents);

            main.TFG.getCurrentMiningContents().add(t);

            Iterator<String> itH = main.getTFG().getHosts().iterator();
            while (itH.hasNext()) {
                String host = itH.next();
                try {
                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                        TCPclient.sendNewContent(host);
                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(manage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //remove from pending transactions and notify the network
            Iterator<Transaction> itT = main.TFG.getPendingTransactions().iterator();
            while (itT.hasNext()){
                Transaction transactionRemoved = itT.next();
                if(transactionRemoved.equals(wantedTransaction)){
                    itT.remove();
                }
            }
            
            Iterator<String> itR = main.getTFG().getHosts().iterator();
            while (itR.hasNext()) {
                String host = itR.next();
                try {
                    if (!host.equals(InetAddress.getLocalHost().getHostAddress())) {
                        TCPclient.sendNewPendingTransaction(host);
                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(manage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(TransactionPending.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //show the list of your pending transactions

        String myAddress = main.getLocalUser().getAddress();

        String listOfTransactions = "";

        Iterator<Transaction> it = main.getTFG().getPendingTransactions().iterator();
        while (it.hasNext()) {
            Transaction t = it.next();
            if (t.getUserReciever().equals(myAddress)) {
                listOfTransactions = listOfTransactions + "\n--------\n" + t.getID();
            }
        }
        if (listOfTransactions.equals("")) {
            listOfTransactions = "You have no pending transactions.";
        }
        jTextArea1.setText(listOfTransactions);
    }//GEN-LAST:event_formWindowActivated

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // get info of the transaction
        String transactionID = jTextField1.getText();
        Transaction wantedTransaction = null;

        //find the transaction on the pending transaction list
        Iterator<Transaction> it = main.TFG.getPendingTransactions().iterator();
        while (it.hasNext()) {
            Transaction t = it.next();

            if (t.getID().equals(transactionID) && t.getUserReciever().equals(main.getLocalUser().getAddress())) {
                wantedTransaction = t;
            }
        }

        //check if we have a matched transaction
        boolean transactionMatches = !wantedTransaction.equals(null);

        if (transactionMatches) {
            jButton1.setEnabled(true);
            try {
                String details = "";
                User creatorUser = null;
                //find user of the creator
                Iterator<User> itU = main.TFG.getUsers().iterator();
                while (itU.hasNext()) {
                    User u = itU.next();
                    if (u.getAddress().equals(wantedTransaction.getUserCreator())) {
                        creatorUser = u;
                    }
                }

                //decrypt content with creator's public key
                Cipher decrypt = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                decrypt.init(Cipher.DECRYPT_MODE, creatorUser.getPublicKey());
                String decryptedMessage = new String(decrypt.doFinal(wantedTransaction.getEncryptedContents()), StandardCharsets.UTF_8);

                details = "Autor: " + wantedTransaction.getUserCreator() + " (" + creatorUser.getName() + ") \n Type: To the network\n Date: " + new Date(wantedTransaction.getDate()) + "\n Contents: \n" + decryptedMessage;
                jTextArea3.setText(details);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(TransactionPending.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            jButton1.setEnabled(false);
            jTextArea3.setText("That transaction does not exist or you are not the receiver of it.");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransactionPending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionPending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionPending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionPending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionPending().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
