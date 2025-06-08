package br.com.HidroSafe.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.HidroSafe.controller.UsuarioController.UsuarioFilter;
import br.com.HidroSafe.model.Usuario;
import jakarta.persistence.criteria.Predicate;

public class UsuarioSpecification {

    public static Specification<Usuario> withFilters(UsuarioFilter filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.nomeCompleto() != null && !filtro.nomeCompleto().isBlank()) {
                predicates.add(
                        cb.like(
                            cb.lower(root.get("nomeCompleto")), "%" + filtro.nomeCompleto().toLowerCase() + "%"));
            }

            if (filtro.email() != null && !filtro.email().isBlank()) {
                predicates.add(
                        cb.like(
                            cb.lower(root.get("email")), "%" + filtro.email().toLowerCase() + "%"));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);

            return cb.and(arrayPredicates);

        };
    }
}
