/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.util.ArrayList;
import java.util.List;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import be.ulb.polytech.infoh400project.model.Person;

/**
 *
 * @author 8Utilisateur
 */
public class FHIRServices {
    
    private final FhirContext ctx;
    
    public FHIRServices(){
        ctx = FhirContext.forR4();
    }
    
    public static be.ulb.polytech.infoh400project.model.Patient getPatient(Patient p){
        Person idperson = new Person();
        idperson.setName(p.getNameFirstRep().getGivenAsSingleString());
        idperson.setSurname(p.getNameFirstRep().getFamily());
        idperson.setDateofbirth(p.getBirthDate());
        
        be.ulb.polytech.infoh400project.model.Patient pat = new be.ulb.polytech.infoh400project.model.Patient();
        pat.setIdperson(idperson);
        pat.setStatus("active");
        
        return pat;
    }
    
    public static Patient getPatient(be.ulb.polytech.infoh400project.model.Patient p){
        Patient pat = new Patient();
        pat.getNameFirstRep().setFamily(p.getIdperson().getSurname());
        List<StringType> givenNames = new ArrayList();
        givenNames.add(new StringType(p.getIdperson().getName()));
        pat.getNameFirstRep().setGiven(givenNames);
        pat.setBirthDate(p.getIdperson().getDateofbirth());
        if( p.getIDPatient() != null )
            pat.setId(String.valueOf(p.getIDPatient()));
        
        return pat;
    }
    
    public List<be.ulb.polytech.infoh400project.model.Patient> searchByFamilyName(String familyName, String serverBase){
        List<be.ulb.polytech.infoh400project.model.Patient> patients = new ArrayList();
        
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        
        Bundle results = client.search().forResource(Patient.class)
                                .where(Patient.FAMILY.matches().value(familyName))
                                .returnBundle(Bundle.class)
                                .execute();
        
        for( Bundle.BundleEntryComponent entry : results.getEntry()){
            patients.add(getPatient((Patient) entry.getResource()));
        }
        
        return patients;
    }
    
}
