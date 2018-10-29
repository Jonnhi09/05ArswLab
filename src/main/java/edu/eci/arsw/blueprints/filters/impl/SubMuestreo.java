/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filters.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Submuestreo")
public class SubMuestreo implements Filter {

    @Override
    public List<Point> filterBlueprints(Blueprint blueprint) {
        ArrayList<Point> pts = new ArrayList(blueprint.getPoints());
        int i = 0;
        for (int p = 0; p < pts.size(); p++) {
            if (i == 1) {
                pts.remove(p);
                i = 0;
            } else {
                i = 1;
            }
        }
        return pts;
    }

    @Override
    public List<List<Point>> filterBlueprints(Set<Blueprint> blueprints) {
        List<List<Point>> listPts = new ArrayList(new ArrayList());
        for (Blueprint bp : blueprints) {
            listPts.add(filterBlueprints(bp));
        }
        return listPts;
    }

}
