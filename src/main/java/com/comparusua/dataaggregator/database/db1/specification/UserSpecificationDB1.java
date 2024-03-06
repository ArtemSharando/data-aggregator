package com.comparusua.dataaggregator.database.db1.specification;

import com.comparusua.dataaggregator.api.dto.UserFilterDto;
import com.comparusua.dataaggregator.database.db1.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserSpecificationDB1 implements Specification<User> {

    private UserFilterDto userFilterDto;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        String username = userFilterDto.username();
        String surname = userFilterDto.surname();
        String name = userFilterDto.name();
        if (username != null) {
            predicates.add(criteriaBuilder.equal(root.get("username"), username));
        }
        if (name != null) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
        if (surname != null) {
            predicates.add(criteriaBuilder.equal(root.get("surname"), surname));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
