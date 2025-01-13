package com.arketeam.addenplus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.arketeam.addenplus.models.ProfilData;

@Repository
public interface ProfilDataRepository extends //
		PagingAndSortingRepository<ProfilData, String>, //
		CrudRepository<ProfilData, String>, //
		JpaSpecificationExecutor<ProfilData> {
/*
public interface ProfilDataRepository extends JpaRepository<ProfilData, String> {
*/

	Optional<ProfilData> findByNom(String nom);

	Boolean existsByNom(String nom);
	
	List<ProfilData> findAll();

}
