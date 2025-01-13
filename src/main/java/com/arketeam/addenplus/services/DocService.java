package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import com.arketeam.addenplus.models.Doc;

public interface DocService {
	
	void save(Doc doc);
	Optional<Doc> getDefaultDocsByArtCodeBarre(String artCodeBarre);
}
