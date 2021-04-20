package com.example.demo.service;

import com.example.demo.dao.entity.Oder;
import com.example.demo.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    public Page<Oder> getAll(int page, int size, String id, int type, String startDate, String endDate, int allAccepted) {
        Specification<Oder> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            SimpleDateFormat startSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (id.length() > 0) {
                Path<String> $id = root.get("id");
                Predicate _id = criteriaBuilder.equal($id, id);
                predicates.add(_id);
            }
            if (type != -1) {
                Path<Integer> $type = root.get("type");
                Predicate _type = criteriaBuilder.equal($type, type);
                predicates.add(_type);
            }
            if (startDate.length() > 0) {
                Path<Date> $startDate = root.get("payTime");
                Predicate _startDate = null;
                try {
                    _startDate = criteriaBuilder.greaterThanOrEqualTo($startDate, startSDF.parse(startDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(_startDate);
            }
            if (endDate.length() > 0) {
                Path<Date> $endDate = root.get("payTime");
                Predicate _endDate = null;
                try {
                    _endDate = criteriaBuilder.lessThanOrEqualTo($endDate, startSDF.parse(endDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(_endDate);
            }
            if (allAccepted != -1) {
                Path<Date> $allAccepted = root.get("allAccepted");
                Predicate _allAccepted = criteriaBuilder.equal($allAccepted, allAccepted);
                predicates.add(_allAccepted);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<Oder> oders = orderRepository.findAll(specification, pageable);

        return oders;
    }
}
