package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>, JpaSpecificationExecutor<Report> {

    @Override
    List<Report> findAll(Specification specification);

    @Override
    long count(Specification<Report> specification);

/*
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

    */
}
