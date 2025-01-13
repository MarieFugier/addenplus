package com.arketeam.addenplus.models;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Where(clause = "PRO_CODE <> '*'")
@Entity
@Table(name = "f_profils_uti")
public class ProfilData {

	public final static int TEM_BLOCAGE_NON_BLOQUE = 0;
	public final static int TEM_BLOCAGE_BLOQUE = 1;

	@Id
	@GenericGenerator(name = "codeUnique", //
			parameters = @Parameter(name = "prefix", value = "PRO"), //
			strategy = "com.arketeam.addenplus.models.generator.DataIdGenerator")
	@GeneratedValue(generator = "codeUnique")
	@Column(name = "PRO_CODE", length = 8)
	private String codeUnique;

	@Column(name = "PRO_ACC_NOM", length = 320)
	private String nom;

	@Column(name = "PRO_PASSWORD", length = 120)
	private String password;

	public ProfilData(String nom, String password) {
		super();
		this.nom = nom;
		this.password = password;
	}

	public ProfilData() {
		super();
	}
	
}
