/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iDao;

import entities.Fiche;
import entities.Patient;
import entities.User;
import java.util.List;

/**
 *
 * @author selmi
 */
public interface IFicheDao {
    public boolean addFiche(Fiche fiche);
    public List<Fiche> getFiches(User patient);
    public boolean updateFiche(Fiche fiche);
    public boolean deleteFiche (Fiche fiche);
}
