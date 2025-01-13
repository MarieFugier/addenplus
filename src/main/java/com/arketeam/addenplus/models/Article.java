package com.arketeam.addenplus.models;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "f_article")
public class Article {
	@Id
	@GenericGenerator(name = "artCode", //
			parameters = @Parameter(name = "prefix", value = "ART"), //
			strategy = "com.arketeam.addenplus.models.generator.DataIdGenerator")
	@GeneratedValue(generator = "artCode")
	@Column(name = "ART_CODE", length = 12)
	private String artCode;
	@OneToMany
	@JoinColumn(name="DOC_PARENT")
    private Set<Doc> artDocument; 
	//@OneToMany(mappedBy="docParent")
    //private List<Doc> artDocument;
	//@OneToOne(mappedBy="docParent")
	//private Doc artDocument;
	
	@ManyToOne
    @JoinColumn(name="ART_GEO_CODE", referencedColumnName="GEO_CODE")
    private Geo artGeo; 
	
    @Column(name = "ART_GEO_CODE", length = 12, insertable = false, updatable = false)
    private String artGeoCode;
	
	@Column(name = "ART_CODE_BARRE", length = 12)
	private String artCodeBarre;
	
	@Column(name = "ART_CEE_CODE", length = 10)
	private String artCeeCode;
	
	@Column(name = "ART_CEI_CODE", length = 10)
	private String artCeiCode;
	
	@Column(name = "ART_CLE_CODE", length = 10)
	private String artCleCode;
	
	
	public Article(String artGeoCode, String artCodeBarre) {
		super();
		this.artGeoCode = artGeoCode;
		this.artCodeBarre = artCodeBarre;
	}
	
	public Article(String artGeoCode, String artCodeBarre, String artCeeCode, String artCeiCode, String artCleCode) {
		this.artGeoCode = artGeoCode;
		this.artCodeBarre = artCodeBarre;
		this.artCeeCode = artCeeCode;
        this.artCeiCode = artCeiCode;
        this.artCleCode = artCleCode;
        
    }


/*
	ART_SOE_CODE
	ART_GROUPE
	ART_DATEACHAT
	ART_PRIX_ACHAT
	ART_VAL_ACHAT
	ART_NUM_IMMO
	ART_DATE_INVENT
	ART_NUM_COMMANDE
	ART_FIN_GARANT
	ART_CONTR_INV
	ART_PRIX_TTC
	ART_NUM_FACT
	ART_PRIX_MAINT
	ART_PRIX_ASSU
	ART_PRIX_LOC
	ART_MISE_SERVICE
	ART_COMMENTAIRES
	ART_COM_CODE
	ART_LIE_CODE
	ART_NUM_SERIE
	ART_NUM_CLEF
	ART_CLI_CODE
	ART_CAE_CODE
	ART_CAI_CODE
	ART_CME_CODE
	ART_CMI_CODE
	ART_CODE_BARRE
	ART_PRIX_LEAS
	ART_IMMO
	ART_HOL_CODE
	ART_CODE_INTERNE
	ART_TEM_DEM
	ART_TSUP
	ART_SOI_CODE
	ART_GDE_CODE
	ART_GDE_TAUX
	ART_NUM_MANDAT
	ART_DATE_MANDAT
	ART_HORS_SERVICE
	ART_DESCR_1
	ART_DESCR_2
	ART_TVA_REEL
	ART_TEM_TYPE
	ART_DATE_CREAT
	ART_DATE_MODIF
	ART_VALEUR_TP
	ART_QUANTITE
	ART_BUD_CODE
	ART_SER_CODE
	ART_CHEM_IMG
	ART_SSR_CODE
	ART_MOD_CODE
	ART_NUM_BL
	ART_DATE_BL
	ART_DEB_GARANT
	ART_MONTANT_TVA
	ART_VALEUR_MARCHE
	ART_VALEUR_ASSURANCE
	ART_TYT_CODE
	ART_CHEM_IMG_WEB
	ART_DATE_COMMANDE
	ART_ACHAT_ZP1_CODE
	ART_ACHAT_ZP2_CODE
	ART_LONGUEUR
	ART_LARGEUR
	ART_HAUTEUR
	ART_POIDS
	ART_SURFACE
	ART_VOLUME
	ART_PARENT
	ART_COMMISSION
	ART_VALEUR_COMPTABLE
	ART_ENSEMBLE_LIBELLE
	ART_UN_AXP_CODE
	ART_DEUX_AXP_CODE
	ART_TROIS_AXP_CODE
	ART_QUATRE_AXP_CODE
	ART_CINQ_AXP_CODE
	ART_TRA_CODE
	ART_RAD_CODE
	ART_NATURE_BIEN
	ART_TEM_ART_PE
	ART_ITE_CODE
	ART_OLD_CODE_BARRE
	*/

}
