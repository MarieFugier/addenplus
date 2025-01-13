package com.arketeam.addenplus.services;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arketeam.addenplus.models.ProfilData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl extends ProfilData implements UserDetails {
		
	public UserDetailsImpl(String nom, String password) {
		super(nom, password);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	public UserDetailsImpl(String codeUnique, String nom, String password) {
		super(nom, password);
		
	    this.setCodeUnique(codeUnique);
		this.setNom(nom);
		this.setPassword(password);

	}
	
	public static UserDetailsImpl build(ProfilData profilData) {
		   
	    return new UserDetailsImpl(
	    	profilData.getCodeUnique(), 
	    	profilData.getNom(), 
	    	profilData.getPassword());
	  }
	
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean equals(Object o) {
		  if (this == o)
		    return true;
		  if (o == null || getClass() != o.getClass())
		    return false;
		  UserDetailsImpl user = (UserDetailsImpl) o;
		    return Objects.equals(getCodeUnique(), user.getCodeUnique());
		  }

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return super.getPassword();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return super.getNom();
		}
		

}
