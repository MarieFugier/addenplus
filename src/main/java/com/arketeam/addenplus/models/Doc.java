package com.arketeam.addenplus.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "f_documents")
public class Doc {
	@Id
	@GenericGenerator(name = "docCode", //
			parameters = @Parameter(name = "prefix", value = "DOC"), //
			strategy = "com.arketeam.addenplus.models.generator.DataIdGenerator")
	@GeneratedValue(generator = "docCode")
	@Column(name = "DOC_CODE", length = 12)
	private String docCode;
	
	@Column(name = "DOC_PARENT", length = 12)
	private String docParent;
		
	@Column(name = "DOC_TITLE", length = 100)
	private String docTitle;
	
	@Column(name = "DOC_PATH", length = 2048)
	private String docPath;
	
	@Column(name = "DOC_DEFAULT")
	private short docDefault;

	public Doc(String docParent, String docTitle, String docPath, short docDefault) {
		super();
		this.docParent = docParent;
		this.docTitle = docTitle;
		this.docPath = docPath;
		this.docDefault = docDefault;
	}

	public Doc(String docCode, String docParent, String docTitle, String docPath, short docDefault) {
		super();
		this.docCode = docCode;
		this.docParent = docParent;
		this.docTitle = docTitle;
		this.docPath = docPath;
		this.docDefault = docDefault;
	}
	
	

	
}
