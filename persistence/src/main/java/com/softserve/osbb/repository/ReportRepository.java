package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import org.springframework.data.jpa.convert.threetenbp.ThreeTenBackPortJpaConverters;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>, JpaSpecificationExecutor<Report> {

    @Override
    List<Report> findAll(Specification specification);


    @StaticMetamodel(Report.class)
    class Report_ {
        public static volatile SingularAttribute<Report, String> name;
        public static volatile SingularAttribute<Report, String> description;
        public static volatile SingularAttribute<Report, LocalDateTime> creationDate;
    }


    class ReportSpecifications {

         private ReportSpecifications(){
            //
        }

        public static Specification<Report> getRecentReports() {
            return (root, query, cb) -> {
                LocalDateTime date = LocalDateTime.now().minusYears(2);
                return cb.greaterThan(root.get(Report_.creationDate), date);
            };
        }

        public static Specification<Report> getAllBetweenDates(LocalDateTime startFrom,
                                                               LocalDateTime endTo) {
            return (root, query, cb) -> {
                return cb.between(root.get(Report_.creationDate), startFrom, endTo);
            };

        }

        public static Specification<Report> titleOrDescriptionContains(String searchTerm) {
            return (root, query, cb) -> {

                String searchTermPattern = getSearchTermPattern(searchTerm);

                return cb.or(cb.like(root.get(Report_.name), searchTermPattern),
                        cb.like(root.get(Report_.description), searchTermPattern));
            };
        }

        public static String getSearchTermPattern(String searchTerm) {

            if (searchTerm == null || searchTerm.isEmpty()) {
                return "%";
            }
            return "%" + searchTerm + "%";
        }
    }
}
