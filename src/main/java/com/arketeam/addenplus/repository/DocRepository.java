package com.arketeam.addenplus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.arketeam.addenplus.models.Doc;

public interface  DocRepository extends	CrudRepository<Doc, String>{
	
	@Query("SELECT d FROM Doc d INNER JOIN Article a ON d.docParent = a.artCode WHERE d.docDefault = :docDefault AND a.artCodeBarre = :artCodeBarre")
	Optional<Doc> findByDocDefaultAndArtCodeBarre(@Param("docDefault") short docDefault, @Param("artCodeBarre") String artCodeBarre);
}
