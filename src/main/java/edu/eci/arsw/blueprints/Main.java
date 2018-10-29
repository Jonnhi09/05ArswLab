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
import java.util.List;
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
        Point[] pts = new Point[]{new Point(0, 0), new Point(1, 15), new Point(2, 15), new Point(3, 15), new Point(2, 2), new Point(3, 15)};
        Blueprint bp = new Blueprint("juan", "basic", pts);
        Point[] pts1 = new Point[]{new Point(2, 2), new Point(3, 15), new Point(20, 15)};
        Blueprint bp1 = new Blueprint("juan", "basic1", pts1);
        addBlueprint(bp);
        addBlueprint(bp1);
        //getBlueprint("juan", "basic");
        //getBlueprintWithFilter("juan", "basic");
        getBlueprintByAuthor("juan");
        getBlueprintsWithFilterByAuthor("juan");
        //getAllBlueprints();
    }

    public static void addBlueprint(Blueprint bp) {
        try {
            bps.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getBlueprint(String author, String name) {
        try {
            System.out.println(bps.getBlueprint(author, name));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getBlueprintByAuthor(String author) {
        try {
            System.out.println(bps.getBlueprintsByAuthor(author));
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getAllBlueprints() {
        System.out.println(bps.getAllBlueprints());
    }

    public static void getBlueprintWithFilter(String author, String name) {
        try {
            System.out.print("Blueprint{author=" + author + ", name=" + name + ", filter result:");
            for (Point p : bps.getFilteredBlueprint(author, name)) {
                System.out.print(p.toString());
            }
            System.out.println("}");
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getBlueprintsWithFilterByAuthor(String author) {
        try {
            System.out.print("Blueprint{author=" + author + ", filter result:");
            for (List<Point> lp : bps.getFilteredBlueprintByAuthor(author)) {
                for(Point p : lp){
                    System.out.print(p.toString());
                }                
            }
            System.out.println("}");
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
