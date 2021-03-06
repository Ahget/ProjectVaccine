/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.view;

import be.ulb.polytech.infoh400project.controller.PatientJpaController;
import be.ulb.polytech.infoh400project.controller.VaccinJpaController;
import be.ulb.polytech.infoh400project.controller.VaccinationJpaController;
import be.ulb.polytech.infoh400project.model.Patient;
import be.ulb.polytech.infoh400project.model.Person;
import be.ulb.polytech.infoh400project.model.Vaccin;
import be.ulb.polytech.infoh400project.model.Vaccination;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tristan
 */
public class AddMeetingWindow extends javax.swing.JFrame {
    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("be.ulb.polytech.infoh400project_Vaccination_jar_1.0-SNAPSHOTPU");
    private final VaccinationJpaController vaccinationCtrl = new VaccinationJpaController(emfac);
    private final VaccinJpaController vaccinCtrl = new VaccinJpaController(emfac);
    private final PatientJpaController patientCtrl = new PatientJpaController(emfac);
    Vaccination vaccination = null;
    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Patient patient = null;

    /**
     * Creates new form AddMeetingWindow
     */
    public AddMeetingWindow(Person p) {
        initComponents();
        patient = p.getPatientList().get(0);
    }
  
     private List<Vaccin> getVaccinList(){
        List<Vaccin> vaccins = vaccinCtrl.findVaccinEntities();
        EntityListModel<Vaccin> model = new EntityListModel(vaccins);
        return vaccins;

    }
    public void getVaccination(){
        vaccination = new Vaccination();
        Integer vacc_Id = Integer.parseInt(Vaccin_TextField.getText());
        vaccination.setIdvaccin(vaccinCtrl.findVaccin(vacc_Id));
        vaccination.setIDPatient(patientCtrl.findPatient(patient.getIDPatient()));
        vaccination.setVaccinationState((short)0);
       
        try {
            vaccination.setDataTime(fmt.parse(Date_TextField.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(AddMeetingWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        textField1 = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Vaccin_TextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Date_TextField = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Add meeting for vaccination");

        jLabel2.setText("Vaccin :");

        Vaccin_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vaccin_TextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Date and Time : ");

        Date_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date_TextFieldActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Date_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(Vaccin_TextField))))
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Vaccin_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Date_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save)
                    .addComponent(Cancel))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Vaccin_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vaccin_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Vaccin_TextFieldActionPerformed

    private void Date_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Date_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Date_TextFieldActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        getVaccination();
        vaccinationCtrl.create(vaccination);
        this.dispose();
        
    }//GEN-LAST:event_SaveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed

        this.dispose();
        
    }//GEN-LAST:event_CancelActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField Date_TextField;
    private javax.swing.JButton Save;
    private javax.swing.JTextField Vaccin_TextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
