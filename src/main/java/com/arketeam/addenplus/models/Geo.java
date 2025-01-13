package com.arketeam.addenplus.models;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "f_geographique")
public class Geo {
	@Id
	@GenericGenerator(name = "geoCode", //
			parameters = @Parameter(name = "prefix", value = "GEO"), //
			strategy = "com.arketeam.addenplus.models.generator.DataIdGenerator")
	@GeneratedValue(generator = "geoCode")
	@Column(name = "GEO_CODE", length = 12)
	private String geoCode;

	@Column(name = "GEO_CODE_BAR", length = 12)
	private String geoCodeBarre;
	
	@Column(name = "GEO_NUMPIECE", length = 20)
	private String geoNumpiece;
	
	@Column(name = "GEO_ETAGE", length = 10)
	private String geoEtage;
	
	@Column(name = "GEO_BAT_CODE", length = 8)
	private String geoBatCode;
	
	@Column(name = "GEO_LONG")
	private float geoLong;
	
	@Column(name = "GEO_LARG")
	private float geoLarg;
	
	@Column(name = "GEO_HAUT")
	private float geoHaut;
	
	@Column(name = "GEO_SURF")
	private float geoSurf;
	
	

	public Geo(String geoBatCode, String geoCodeBarre) {
		super();
		this.geoBatCode = geoBatCode;
		this.geoCodeBarre = geoCodeBarre;
	}
	
	
	
	/*
	GEO_CODE_BAR
	GEO_TSUP
	GEO_EDIT
	GEO_STOCK
	GEO_LOCATAIRE
	GEO_PLAN
	GEO_NTU_CODE
	GEO_RDS_CODE
	GEO_DESCR
	GEO_VOLUME
	GEO_PRIX_M2
	GEO_PRIX_M3
	GEO_TYG_CODE
	GEO_TAUX_OCCUPATION
	GEO_ID_VISIT
*/
}
