package com.arketeam.addenplus.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewDocRequest {
	
	private String docParent;
	private String docTitle;
	private String docPath;
	private Short docDefault;
	
}
