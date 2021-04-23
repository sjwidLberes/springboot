package com.example.demo.service;

import com.example.demo.dao.entity.Microboss;
import com.example.demo.dao.entity.MicrobossState;
import com.example.demo.dao.repository.MicrobossRepository;
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
public class MicrobossService {


    @Autowired
    private MicrobossRepository microbossRepository;

    public Microboss findById(int id){
        Microboss microboss = microbossRepository.findOne(id);
        return microboss;
    }

    public Page<Microboss> getAllByValidateState(int state, int page, int size) {
        Specification<Microboss> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (state != -1) {
                MicrobossState microbossState = new MicrobossState();
                microbossState.setId(state);
                Path<MicrobossState> $state = root.get("state");
                Predicate _state = criteriaBuilder.equal($state, microbossState);
                predicates.add(_state);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<Microboss> microbosses = microbossRepository.findAll(specification, pageable);

        return microbosses;
    }

    @Transactional
    public void updateValidationStateById(int id, int state, String stateDesc){
        microbossRepository.updateValidationStateById(id, state, stateDesc);
    }
}
