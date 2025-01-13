package com.arketeam.addenplus.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotBlank(message = "Le nom d'utilisateur est requis")
    @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit contenir entre 3 et 20 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Le nom d'utilisateur ne peut contenir que des lettres, "
    		+ "des chiffres, des points, des tirets bas et des tirets")
    private String username;
    
    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 4, message = "Le mot de passe doit contenir au moins 4 caractères")
    private String password;

}
