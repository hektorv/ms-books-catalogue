package com.relatos.books.catalogue.persistence;

import com.relatos.books.catalogue.model.BookSearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookEntity> fromCriteria(BookSearchCriteria criteria) {
        return (Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (criteria.getTitle() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("title")), "%" + criteria.getTitle().toLowerCase() + "%"));
            }
            if (criteria.getAuthor() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("author")), "%" + criteria.getAuthor().toLowerCase() + "%"));
            }
            if (criteria.getGenre() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("genre"), criteria.getGenre()));
            }
            if (criteria.getIsbn() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("isbn"), criteria.getIsbn()));
            }
            if (criteria.getYear() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("publishYear"), criteria.getYear()));
            }
            if (criteria.getRating() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("rating"), criteria.getRating()));
            }
            if (criteria.getVisible() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("visible"), criteria.getVisible()));
            }

            return predicate;
        };
    }
}
