package br.com.HidroSafe.model.dto;

import br.com.HidroSafe.model.CargoUsuario;
import br.com.HidroSafe.model.Endereco;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDto {

    @Size(min = 8, max = 64, message = "o nome deve ter entre 8 a 64 caracteres")
    @NotBlank(message = "o campo nome é obrigatório")
    private String nomeCompleto;

    @Email(message = "e-mail inválido")
    @NotBlank(message = "o campo email é obrigatório")
    private String email;

    @Size(min = 6, message = "deve ter pelo menos 6 caracteres")
    @NotBlank(message = "o campo senha deve ser obrigatório")
    private String password;

    @Enumerated(EnumType.STRING)
    private CargoUsuario cargo = CargoUsuario.USUARIO;

    @NotNull(message = "o campo endereço é obrigatório")
    private Endereco endereco;
}
