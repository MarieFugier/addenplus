package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import com.arketeam.addenplus.models.Geo;

public interface GeoService {
	
	void save(Geo geo);
	Optional<Geo> getByGeoCodeBarre(String geoCodeBarre);
    
}
