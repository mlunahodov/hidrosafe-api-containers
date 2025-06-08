package br.com.HidroSafe.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.HidroSafe.model.CargoUsuario;
import br.com.HidroSafe.model.Denuncia;
import br.com.HidroSafe.model.Endereco;
import br.com.HidroSafe.model.Usuario;
import br.com.HidroSafe.repository.DenunciaRepository;
import br.com.HidroSafe.repository.EnderecoRepository;
import br.com.HidroSafe.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DatabaseSeeder {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    public void init() {

        Endereco endereco1 = Endereco.builder()
            .logradouro("Rua P")
            .bairro("Bairro O")
            .cep("01101-002")
            .cidade("São Paulo")
            .estado("São Paulo")
            .build();

        Endereco endereco2 = Endereco.builder()
            .logradouro("Rua Z")
            .bairro("Bairro W")
            .cep("01101-020")
            .cidade("São Paulo")
            .estado("São Paulo")
            .build();

        var enderecos = List.of(endereco1, endereco2);

        var usuarios = List.of(
            Usuario.builder()
                .nomeCompleto("Thiago Oliveira")
                .email("thiago@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .cargo(CargoUsuario.USUARIO)
                .endereco(endereco1)
                .build(),

            Usuario.builder()
                .nomeCompleto("Helena Silva")
                .email("helena@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .cargo(CargoUsuario.ADMIN)
                .endereco(endereco2)
                .build()
            );

        var denuncias = List.of(
            Denuncia.builder()
                .assunto("Possível alagamento")
                .descricao("Há pessoas jogando uma quantidade grande de lixo próximo aos bueiros da minha rua")
                .logradouro("Rua das flores, 123 - Centro")
                .build(),

            Denuncia.builder()
                .assunto("Vazamento de água no bueiro")
                .descricao("Tem água saindo de dentro da tampa do bueiro")
                .logradouro("Rua do bolo")
                .build()
        );

        enderecoRepository.saveAll(enderecos);
        usuarioRepository.saveAll(usuarios);
        denunciaRepository.saveAll(denuncias);
    }
}
