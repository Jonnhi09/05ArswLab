/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Point;
import java.util.List;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("InMemory")
    BlueprintsPersistence bpp = null;

    @Autowired
    @Qualifier("Submuestreo")
    Filter filter = null;

    /**
     *
     * @param bp
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     *
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        return bpp.getBlueprintsByAuthor(author);
    }

    /**
     *
     * @return
     */
    public Set<Blueprint> getAllBlueprints() {
        return bpp.getAllBlueprints();
    }

    public List<Point> getFilteredBlueprint(String author, String name) throws BlueprintNotFoundException {
        return filter.filterBlueprints(getBlueprint(author, name));
    }
    
    public List<List<Point>> getFilteredBlueprintByAuthor(String author) throws BlueprintNotFoundException {
        return filter.filterBlueprints(getBlueprintsByAuthor(author));
    }
}
