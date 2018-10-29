/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jonathan Prieto
 */
public class Main {

    private static BlueprintsServices bps;

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        bps = ac.getBean(BlueprintsServices.class);
        Point[] pts = new Point[]{new Point(0, 0), new Point(1, 15)};
        Blueprint bp = new Blueprint("juan", "basic", pts);
        Point[] pts1 = new Point[]{new Point(0, 0), new Point(1, 15)};
        Blueprint bp1 = new Blueprint("juan", "basic1", pts1);
        addBluePrint(bp);
        addBluePrint(bp1);
        getBluePrint("juan", "basic");
        getBluePrintByAuthor("juan");
        getAllBluePrints();
    }

    public static void addBluePrint(Blueprint bp) {
        try {
            bps.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getBluePrint(String author, String name) {
        try {
            System.out.println(bps.getBlueprint(author, name));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getBluePrintByAuthor(String author) {
        try {
            System.out.println(bps.getBlueprintsByAuthor(author));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getAllBluePrints() {
        System.out.println(bps.getAllBlueprints());
    }
}
