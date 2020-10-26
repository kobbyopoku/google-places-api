/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.PlaceTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author godwin.duah
 */
@Repository
public interface TypeRepository extends CrudRepository<PlaceTypes, Long>{
    
}
