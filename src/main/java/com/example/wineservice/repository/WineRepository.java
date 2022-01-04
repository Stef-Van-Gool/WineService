package com.example.wineservice.repository;

import com.example.wineservice.model.Wine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WineRepository extends MongoRepository<Wine, String> {
    List<Wine> findWinesByCountry(String country);
    List<Wine> findWinesByCountryAndRegion(String country, String region);
    List<Wine> findWinesByGrapeName(String grapeName);
    Wine findWineByNameAndCountryAndRegion(String name, String country, String region);
}
