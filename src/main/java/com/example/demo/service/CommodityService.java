package com.example.demo.service;

import com.example.demo.config.AliYunOssConfig;
import com.example.demo.dao.entity.Commodity;
import com.example.demo.dao.entity.CommodityType;
import com.example.demo.dao.repository.CommodityRepository;
import com.example.demo.dao.repository.CommodityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private AliYunOssService aliYunOssService;
    @Autowired
    private CommodityTypeRepository commodityTypeRepository;

    @Transactional
    public Commodity saveCommodity(Commodity commodity, Map parameters, MultiValueMap<String, MultipartFile> multipartFiles, int thumbCount, int detailCount, int introCount, int shareCount) {
        CommodityType commodityType = commodityTypeRepository.findOne(commodity.getType().getId());
        commodity.setType(commodityType);

        commodity = commodityRepository.save(commodity);
        int id = commodity.getId();

        List images = getImageUrls(id, parameters, multipartFiles, thumbCount, "thumb");
        commodity.setThumbImageUrl(images.toString());
        images = getImageUrls(id, parameters, multipartFiles, detailCount, "detail");
        commodity.setDetailImageUrl(images.toString());
        images = getImageUrls(id, parameters, multipartFiles, introCount, "intro");
        commodity.setIntroduceImageUrl(images.toString());
        images = getImageUrls(id, parameters, multipartFiles, shareCount, "share");
        commodity.setShareImageUrl(images.toString());

        commodity = commodityRepository.save(commodity);

        return commodity;
    }

    private List getImageUrls(int id, Map parameters, MultiValueMap<String, MultipartFile> multiFiles, int size, String key) {
        List images = new ArrayList();
        Object url;
        for (int i = 0; i < size; i++) {
            url = parameters.get(key + i);
            if (url != null){
                images.add(((String[])url)[0]);
                continue;
            }

            List<MultipartFile> multipartFiles = multiFiles.get(key + i);
            for (MultipartFile file : multipartFiles) {
                if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().length() == 0) {
                    throw new RuntimeException("请选择文件后上传");
                } else if (file.getSize() > 1024 * 1024 * 10) {
                    throw new RuntimeException("上传图片大小不能超过10M");
                } else {
//                    url = aliYunOssService.addObjectToOssByMultipartFile(AliYunOssConfig.getBucketWeifen(), "commodity/" + id + "/" + key, UUID.randomUUID().toString(), file);
//                    images.add(url);
                    continue;
                }
            }
        }

        return images;
    }

    public Page<Commodity> getAllByNameAndOnShelf(String name, int onShelf, int page, int size) {
        Specification<Commodity> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                Path<String> $name = root.get("name");
                Predicate _name = criteriaBuilder.like($name, "%" + name + "%");
                predicates.add(_name);
            }

            if (onShelf != -1) {
                Path<Integer> $onShelf = root.get("onShelf");
                Predicate _onShelf = criteriaBuilder.equal($onShelf, onShelf);
                predicates.add(_onShelf);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<Commodity> commodities = commodityRepository.findAll(specification, pageable);

        return commodities;
    }

    public Commodity findById(int id) {
        Commodity result = commodityRepository.findOne(id);
        return result;
    }

    @Transactional
    public void updateOnShelfById(int id, boolean onShelf) {
        commodityRepository.updateOnShelfById(id, onShelf);
    }


}
