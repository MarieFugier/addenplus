package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arketeam.addenplus.models.Geo;
import com.arketeam.addenplus.repository.GeoRepository;

@Service
public class GeoServiceImpl implements GeoService{
	@Autowired
	private GeoRepository geoRepository;

	@Override
	public void save(Geo geo) {
		 geoRepository.save(geo);
	}

	@Override
	public Optional<Geo> getByGeoCodeBarre(String geoCodeBarre) {
		// TODO Auto-generated method stub
		return geoRepository.findByGeoCodeBarre(geoCodeBarre);
	}
	
	
}
