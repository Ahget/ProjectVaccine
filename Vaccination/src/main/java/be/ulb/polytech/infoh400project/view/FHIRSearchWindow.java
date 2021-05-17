/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.view;

import be.ulb.polytech.infoh400project.services.FHIRServices;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import be.ulb.polytech.infoh400project.controller.PatientJpaController;
import be.ulb.polytech.infoh400project.controller.PersonJpaController;
import be.ulb.polytech.infoh400project.model.Patient;
import be.ulb.polytech.infoh400project.controller.GlobalProperties;


/**
 *
 * @author 8Utilisateur
 */
public class FHIRSearchWindow extends javax.swing.JFrame {

    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("be.ulb.polytech.infoh400project_Vaccination_jar_1.0-SNAPSHOTPU");
    private final PatientJpaController patientCtrl = new PatientJpaController(emfac);
    private final PersonJpaController personCtrl = new PersonJpaController(emfac); 
    private final FHIRServices fhirServices = new FHIRServices();
    
   /**
     * Creates new form FHIRSearchWindow
     */
    public FHIRSearchWindow() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fhirHostTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        familyNameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchResultsList = new javax.swing.JList<>();
        doSearchButton = new javax.swing.JButton();
        saveToDatabaseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Search Patient");

        jLabel2.setText("FHIR Host:");

        fhirHostTextField.setText(GlobalProperties.getProperties().getProperty("fhir.defaultHost"));

        jLabel3.setText("Family Name:");

        jScrollPane1.setViewportView(searchResultsList);

        doSearchButton.setText("Search");
        doSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doSearchButtonActionPerformed(evt);
            }
        });

        saveToDatabaseButton.setText("Save to Database");
        saveToDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToDatabaseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fhirHostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(familyNameTextField)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doSearchButton))
                            .addComponent(saveToDatabaseButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fhirHostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(familyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveToDatabaseButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doSearchButtonActionPerformed
        List<Patient> patients = fhirServices.searchByFamilyName(familyNameTextField.getText(), fhirHostTextField.getText());
        EntityListModel<Patient> listModel = new EntityListModel(patients);
        searchResultsList.setModel(listModel);
    }//GEN-LAST:event_doSearchButtonActionPerformed

    private void saveToDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToDatabaseButtonActionPerformed
        if( searchResultsList.getSelectedIndex() < 0 ){
            return;
        }
        EntityListModel<Patient> model = (EntityListModel) searchResultsList.getModel();
        Patient selected = model.getList().get(searchResultsList.getSelectedIndex());
        
        personCtrl.create(selected.getIdperson());
        patientCtrl.create(selected);
        
        dispose();
    }//GEN-LAST:event_saveToDatabaseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton doSearchButton;
    private javax.swing.JTextField familyNameTextField;
    private javax.swing.JTextField fhirHostTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveToDatabaseButton;
    private javax.swing.JList<String> searchResultsList;
    // End of variables declaration//GEN-END:variables
}
