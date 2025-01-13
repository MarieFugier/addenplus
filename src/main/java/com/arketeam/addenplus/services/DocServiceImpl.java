package com.arketeam.addenplus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arketeam.addenplus.models.Doc;
import com.arketeam.addenplus.repository.DocRepository;

import jakarta.transaction.Transactional;;

@Service
public class DocServiceImpl implements DocService{

	@Autowired
	private DocRepository docRepository;

	@Override
	@Transactional
	public void save(Doc doc) {
		 docRepository.save(doc);
	}
	
	public Optional<Doc> getDefaultDocsByArtCodeBarre(String artCodeBarre) {
	      return docRepository.findByDocDefaultAndArtCodeBarre((short)1, artCodeBarre);
	}


}
