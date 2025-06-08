package br.com.HidroSafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Denuncia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 64, message = "o assunto deve ter entre 5 e 24 caracteres")
    @NotBlank(message = "o campo assunto é obrigatório")
    private String assunto;

    @Size(min = 12, max = 200, message = "a descrição deve ter entre 12 e 200 caracteres")
    @NotBlank(message = "o campo descrição é obrigatório")
    private String descricao;

    @NotNull(message = "o campo logradouro é obrigatório")
    private String logradouro;
}
