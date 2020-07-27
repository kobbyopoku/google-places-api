package app.repository;

import app.model.PlacesData;
import app.model.PlacesRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author godwin.duah
 */

@Repository
public interface PlaceRepository extends CrudRepository<PlacesData, Long>{
    
}
