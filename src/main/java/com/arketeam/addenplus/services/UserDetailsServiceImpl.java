package com.arketeam.addenplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arketeam.addenplus.models.ProfilData;
import com.arketeam.addenplus.repository.ProfilDataRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  ProfilDataRepository profilDataRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
    ProfilData profilData = profilDataRepository.findByNom(nom)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nom));

    return UserDetailsImpl.build(profilData);
  }

}
