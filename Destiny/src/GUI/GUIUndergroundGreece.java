package GUI;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;

import Graph.Node;
import Metro.Astar;
import Metro.Metro;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author adrian
 */
public class GUIUndergroundGreece extends javax.swing.JFrame {

    /**
     * Creates new form GUIMetro
     */

    private final Metro myMetro = new Metro();
    private final Astar myAstar = new Astar(myMetro);

    public GUIUndergroundGreece() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        fromBox = new javax.swing.JComboBox<>();
        fromText = new javax.swing.JLabel();
        toBox = new javax.swing.JComboBox<>();
        toText = new javax.swing.JLabel();
        routeScrollPane = new javax.swing.JScrollPane();
        stationsTxt = new javax.swing.JEditorPane();
        panelImage = new javax.swing.JPanel();
        undergroundImage = new javax.swing.JLabel();
        searchRouteButton = new javax.swing.JButton();
        routeTxt = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fromBox.setModel(new javax.swing.DefaultComboBoxModel<>(myMetro.getGraph().getNames()));

        fromText.setText("From");

        toBox.setModel(new javax.swing.DefaultComboBoxModel<>(myMetro.getGraph().getNames()));

        toText.setText("To");

        stationsTxt.setEditable(false);
        stationsTxt.setFont(new java.awt.Font("Monaco", 2, 13)); // NOI18N
        routeScrollPane.setViewportView(stationsTxt);

        undergroundImage.setIcon(new javax.swing.ImageIcon("/Users/adrian/Developer/GitHub/IA/PracticaIA/MetroGrecia/src/GUI/planometro.jpeg")); // NOI18N

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageLayout.createSequentialGroup()
                .addComponent(undergroundImage)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageLayout.createSequentialGroup()
                .addComponent(undergroundImage)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        searchRouteButton.setText("Search for route");
        searchRouteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecimalFormat df = new DecimalFormat("0.00");

                if(fromBox.getSelectedItem().toString().equals(toBox.getSelectedItem().toString()))
                    stationsTxt.setText("Please, select different stations\n");
                else{
                    searchRouteButtonActionPerformed(evt);
                    Stack<Node> stack = myAstar.getSortestPath(fromBox.getSelectedItem().toString(), toBox.getSelectedItem().toString());
    
                    StringBuilder textToSet = new StringBuilder();
                    Node saved = stack.pop();
                    List<Integer> myLines = saved.getStation().getLine();
                    int lastLine = 0;
                    double totalDistance = 0;

                    for(Integer line : myLines){
                        if(stack.peek().getStation().getLine().contains(line)){
                            lastLine = line;
                            break;
                        }
                    }
                    stack.push(saved);
                    textToSet.append("Line: "+lastLine+"\n");
                    while(!stack.isEmpty()){
                        if(stack.peek().getActual()!=null){
                            totalDistance += stack.peek().getActual().getDistance();
                        }
                        if(!stack.peek().getStation().getLine().contains(lastLine)){
                            myLines = stack.peek().getStation().getLine();
                            for(Integer line : myLines){
                                if(stack.peek().getActual().getN1().getStation().getLine().contains(line)){
                                    lastLine = line;
                                    break;
                                }
                            }
                            textToSet.append("Transfer to line: "+lastLine+"\n");
                        }
                        textToSet.append("- "+stack.pop().getStation().getName()+"\n");
                    }
                    textToSet.append("Total distance: "+df.format(totalDistance)+" km");
                    stationsTxt.setText(textToSet.toString());
                    System.err.println(textToSet.toString());
                    myMetro.getGraph().clearNodes();
                }

            }
        });

        routeTxt.setText("Route");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fromText, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(toText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(routeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routeScrollPane)
                    .addComponent(searchRouteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(toBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchRouteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(routeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(routeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>                        

    private void searchRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

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
            java.util.logging.Logger.getLogger(GUIUndergroundGreece.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIUndergroundGreece.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIUndergroundGreece.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIUndergroundGreece.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIUndergroundGreece().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> fromBox;
    private javax.swing.JLabel fromText;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel panelImage;
    private javax.swing.JScrollPane routeScrollPane;
    private javax.swing.JLabel routeTxt;
    private javax.swing.JButton searchRouteButton;
    private javax.swing.JEditorPane stationsTxt;
    private javax.swing.JComboBox<String> toBox;
    private javax.swing.JLabel toText;
    private javax.swing.JLabel undergroundImage;
    // End of variables declaration                   
}
