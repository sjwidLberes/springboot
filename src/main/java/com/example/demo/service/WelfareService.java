package com.example.demo.service;

import com.example.demo.dao.entity.Oder;
import com.example.demo.dao.entity.Welfare;
import com.example.demo.dao.repository.WelfareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WelfareService {


    @Autowired
    private WelfareRepository welfareRepository;

    public Page<Welfare> getAllByOrderId(String orderId, int page, int size){
        Specification<Welfare> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Oder oder = new Oder();
            oder.setId(orderId);
            Path<Oder> $oder = root.get("oder");
            Predicate _oder = criteriaBuilder.equal($oder, oder);
            predicates.add(_oder);

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<Welfare> welfares = welfareRepository.findAll(specification, pageable);
        return welfares;
    }

    @Transactional
    public void updateExpressNumberByKey(String orderId, String openId, String expressNumber) {
        welfareRepository.updateExpressNumberByKey(orderId, openId, expressNumber);
    }
}
