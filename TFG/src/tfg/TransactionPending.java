/*
 * Proyecto de Blockchain creado por Pepe Blasco 
 * ETSIT - UPV 2017/18
 * 
 */
package tfg;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
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
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
        jLabel1.setText("YOUR PENDING TRANSACTIONS");

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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(93, 93, 93))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
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
                    .addGap(58, 58, 58)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(460, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // get info of the transaction
        String transactionID = jTextField1.getText();
        Transaction wantedTransaction = null;
        Transaction t = null;

        //find the transaction on the pending transaction list
        Iterator<Transaction> it = main.TFG.getPendingTransactions().iterator();
        int positionInList = 0;
        while (it.hasNext()) {
            t = it.next();

            if (t.getID().equals(transactionID)) {
                //check if the user is on the receiver list

                Iterator<String> ite = t.getUsers().iterator();

                while (ite.hasNext()) {
                    positionInList++;
                    if (ite.next().equals(main.getLocalUser().getAddress())) {
                        wantedTransaction = t;

                    }
                }
            }
        }

        if (wantedTransaction == null) {
            System.out.println("there is no transaction available");
        } else {
            System.out.println("there is a transaction: " + wantedTransaction.getID());
            System.out.println("User creator: " + wantedTransaction.getUsers().get(0));

            //get the contents in plain text
            String contents = t.getContents();

            //find user of the creator
            User creatorUser = null;
            Iterator<User> itU = main.TFG.getUsers().iterator();
            while (itU.hasNext()) {

                User u = itU.next();
                System.out.println("List of users, address:: " + u.getAddress());
                System.out.println("List of users, desired address: " + wantedTransaction.getUsers().get(0));
                if (u.getAddress().equals(wantedTransaction.getUsers().get(0))) {
                    creatorUser = u;
                    System.out.println("we have the user: " + u.getAddress());
                }
            }
            try {
                boolean signatureAdded = false;
                boolean moreUsers = false;
                //get the hash of the contents and sign them
                String hashContents = main.findHash(contents);
                byte[] SignedHash = Transaction.encryptSHA(hashContents.getBytes(Charset.forName("UTF-8")), null, main.getLocalUser().getPrivateKey());

                //get the hash of the creator signed and decrypt it
                byte[] decodedUserHash = Transaction.decryptSHA(wantedTransaction.getSignatures().get(0), creatorUser.getPublicKey(), null);
                String originalSignedContents = new String(decodedUserHash, StandardCharsets.UTF_8);

                //compare the hash
                if (originalSignedContents.equals(hashContents)) {
                    //add your signature to the transaction
                    t.getSignatures().set(positionInList, SignedHash);
                    signatureAdded = true;
                } else {
                    System.out.println("different hash when comparing the one I created and the one published by the creator");
                }

                //check if there are users pending to sign
                Iterator<byte[]> ite = t.getSignatures().iterator();
                while (ite.hasNext()) {
                    if (ite.next() == null) {
                        moreUsers = true;
                    }
                }
                System.out.println("More Users / signature Added: " + moreUsers + signatureAdded);

                //if the signature was added and there are no more users, set as mining contents and remove from pending (notify the network)
                if (signatureAdded && !moreUsers) {

                    //set new mining contents and notify the network
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
                    while (itT.hasNext()) {
                        Transaction transactionRemoved = itT.next();
                        if (transactionRemoved.equals(wantedTransaction)) {
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
                }

                //if the signature was added and there are more users, remove from pending and update with the new one
                if (signatureAdded && moreUsers) {

                    //remove from pending transactions, add the new one and notify the network
                    Iterator<Transaction> itT = main.TFG.getPendingTransactions().iterator();
                    while (itT.hasNext()) {
                        Transaction transactionRemoved = itT.next();
                        if (transactionRemoved.equals(wantedTransaction)) {
                            itT.remove();
                        }
                    }

                    main.TFG.getPendingTransactions().add(t);

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

                }
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
                Logger.getLogger(TransactionPending.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //show the list of your pending transactions

        String myAddress = main.getLocalUser().getAddress();

        //sent to another user, pending to verify
        String listOfTransactions = "Transactions sended that are waiting to be verified by the other user:";
        boolean transactionSent = false;
        Iterator<Transaction> it = main.getTFG().getPendingTransactions().iterator();
        while (it.hasNext()) {
            Transaction t = it.next();
            if (t.getUsers().get(0).equals(myAddress)) {
                listOfTransactions = listOfTransactions + "\n--------\n" + t.getID();
                transactionSent = true;
            }
        }

        if (transactionSent == false) {
            listOfTransactions = listOfTransactions + "\n---No sent transactions pending to verify---";
        }

        //sent to my user, pending to verify
        boolean transactionReceived = false;
        boolean userOnList = false;
        listOfTransactions = listOfTransactions + "\n +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \nTransactions received waiting for verification:";
        Iterator<Transaction> it2 = main.getTFG().getPendingTransactions().iterator();
        while (it2.hasNext()) {
            Transaction t = it2.next();
            //check if the user is on the list
            Iterator<String> it3 = t.getUsers().iterator();
            it3.next(); //avoid the '0' position (creator)
            while (it3.hasNext()) {
                if (it3.next().equals(myAddress)) {
                    userOnList = true;
                }
            }
            if (userOnList) {
                listOfTransactions = listOfTransactions + "\n--------\n" + t.getID();
                transactionReceived = true;
            }
        }
        if (transactionReceived == false) {
            listOfTransactions = listOfTransactions + "\n---No received transactions pending to verify---";
        }
        jTextArea1.setText(listOfTransactions);
        jTextArea1.setEditable(false);
        jTextArea3.setEditable(false);
        jButton1.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // get info of the transaction
        String transactionID = jTextField1.getText();
        Transaction wantedTransaction = new Transaction("", null, "", null, 0, 0);
        wantedTransaction.setID("no transaction");
        int positionOnList = 0;

        //find the transaction on the pending transaction list
        Iterator<Transaction> it = main.TFG.getPendingTransactions().iterator();
        while (it.hasNext()) {
            Transaction t = it.next();
            boolean userOnList = false;

            //check if the user is on the list
            Iterator<String> it3 = t.getUsers().iterator();
            while (it3.hasNext()) {
                if (it3.next().equals(main.getLocalUser().getAddress())) {
                    userOnList = true;
                }
                positionOnList++;
            }
            if (t.getID().equals(transactionID) && userOnList) {
                wantedTransaction = t;
            }
        }

        //check if we have a matched transaction
        if (!wantedTransaction.getID().equals("no transaction")) {
            if (positionOnList > 0) {
                jButton1.setEnabled(true);
            }
            System.out.println("Transaction: " + wantedTransaction.getID());
            System.out.println("Creator user: " + wantedTransaction.getUsers().get(0));
            String details = "";
            User creatorUser = null;
            //find user of the creator
            Iterator<User> itU = main.TFG.getUsers().iterator();
            while (itU.hasNext()) {
                User u = itU.next();
                System.out.println("Address of the user on the list: " + u.getAddress());
                if (u.getAddress().equals(wantedTransaction.getUsers().get(0))) {
                    creatorUser = u;
                    System.out.println("Address of matched user: " + u.getAddress());
                }
            }
            details = "Autor: " + wantedTransaction.getUsers().get(0) + " (" + creatorUser.getName() + ") \n Type: To another user \n Date: " + new Date(wantedTransaction.getDate());
            details = details + "\n -----------Contents-----------\n" + wantedTransaction.getContents() + "\n\nList of users: (a cross means they have already signed)";

            //show a list of users and whether or not they have signed
            Iterator<String> itUser = wantedTransaction.getUsers().iterator();
            Iterator<byte[]> itSigned = wantedTransaction.getSignatures().iterator();

            while (itUser.hasNext()) {
                String userAddress = itUser.next();
                byte[] userSign = itSigned.next();
                details = details + "\n";
                if (userSign != null) {
                    details = details + "[x] " + userAddress;
                } else {
                    details = details + "[ ] " + userAddress;
                }
            }

            jTextArea3.setText(details);

        } else {
            jButton1.setEnabled(false);
            jTextArea3.setText("That transaction does not include you (or does not exist) , therefore you can't see it yet.");
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
