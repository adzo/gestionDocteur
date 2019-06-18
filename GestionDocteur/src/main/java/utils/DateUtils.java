/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Abdelaziz
 */
public  class DateUtils {
    
    //Formatting de la date reçu de la requête SQL et la mettre sous la forme de
    // "jour de semaine" le "date et mois en lettre"
    public static String dateToStringLong(Date userDate){
        
        String[] months = {"Janvier","Fevrier","Mars","Avril","Mai","Juin",
        "Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        String[] days = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    
        
        Calendar cal = Calendar.getInstance();
        if(userDate != null){
        cal.setTime(userDate);
        }
//        final DateFormat dateFormat;
//        dateFormat = DateFormat.getDateTimeInstance(); 
//        
        String jour, month, year, dayOfTheWeek;
        jour= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        month= months[cal.get(Calendar.MONTH)];
        year= String.valueOf(cal.get(Calendar.YEAR));
        dayOfTheWeek = days[cal.get(Calendar.DAY_OF_WEEK)-1];
        
        
        return dayOfTheWeek+" le "+jour+" "+month+" "+year;
    }
    
    public static String dateToString(Date userDate){
        
        String[] months = {"Janvier","Fevrier","Mars","Avril","Mai","Juin",
        "Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        String[] days = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    
        
        Calendar cal = Calendar.getInstance();
        if(userDate != null){
        cal.setTime(userDate);
        }
//        final DateFormat dateFormat;
//        dateFormat = DateFormat.getDateTimeInstance(); 
//        
        String jour, month, year, dayOfTheWeek;
        jour= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        month= months[cal.get(Calendar.MONTH)];
        year= String.valueOf(cal.get(Calendar.YEAR));
        dayOfTheWeek = days[cal.get(Calendar.DAY_OF_WEEK)-1];
        
        
        return jour+" "+month+" "+year;
    }
    
    //Récupération de la date du Date picker de l'interface JavaFX
    public static Date localDateToString(LocalDate dateLocale){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, dateLocale.getMonthValue()-1);
        cal.set(Calendar.DATE, dateLocale.getDayOfMonth());
        cal.set(Calendar.YEAR, dateLocale.getYear());
        return cal.getTime();
        }
    
//    public static LocalDate dateToLocalDate(Date date){
//        LocalDate resultat;
//        resultat = new LocalDate();
//        
//        return resultat;
//    }
}
