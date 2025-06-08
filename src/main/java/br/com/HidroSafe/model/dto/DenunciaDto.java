package br.com.HidroSafe.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DenunciaDto {

    @Size(min = 5, max = 24, message = "o assunto deve ter entre 5 e 24 caracteres")
    @NotBlank(message = "o campo assunto é obrigatório")
    private String assunto;

    @Size(min = 12, max = 200, message = "a descrição deve ter entre 12 e 200 caracteres")
    @NotBlank(message = "o campo descrição é obrigatório")
    private String descricao;

    @NotNull(message = "o campo logradouro é obrigatório")
    private String logradouro;
}
