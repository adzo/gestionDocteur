/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iDao;

import entities.Patient;
import java.util.List;

/**
 *
 * @author selmi
 */
public interface IPatientDao {
    public boolean addPatient(Patient patient);
    public boolean deletePatient(Patient patient);
    public boolean updatePatient(Patient patient);
    public List<Patient> getAllByDocteur();
    public Patient getPatientById(int id);
    public Patient getPatientByLogin(String email, String pass);
}
