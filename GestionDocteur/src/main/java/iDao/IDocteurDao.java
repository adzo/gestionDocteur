/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iDao;

import entities.Docteur;
import java.util.List;



/**
 *
 * @author selmi
 */
public interface IDocteurDao {
    public boolean addDocteur(Docteur docteur);
    public boolean deleteDocteur(Docteur docteur);
    public boolean updateDocteur(Docteur docteur);
    public List<Docteur> getAll();
    public Docteur getDocteurById(int id);
    public Docteur getDocteurbyLogin(String email, String pass);
}
