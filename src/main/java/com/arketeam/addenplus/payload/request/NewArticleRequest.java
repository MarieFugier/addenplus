package com.arketeam.addenplus.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewArticleRequest {
	
	private String artGeoCode;
	private String artCodeBarre;

}
