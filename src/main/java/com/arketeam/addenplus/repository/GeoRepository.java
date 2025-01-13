package com.arketeam.addenplus.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.arketeam.addenplus.models.Geo;

public interface  GeoRepository extends	CrudRepository<Geo, String>{
	Optional<Geo> findByGeoCodeBarre(String geoCodeBarre);

}
