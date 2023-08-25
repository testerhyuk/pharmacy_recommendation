package com.pharmacy_recommendation.pharmacy.service;

import com.pharmacy_recommendation.api.dto.DocumentDto;
import com.pharmacy_recommendation.api.dto.KakaoApiResponseDto;
import com.pharmacy_recommendation.api.service.KakaoAddressSearchService;
import com.pharmacy_recommendation.direction.entity.Direction;
import com.pharmacy_recommendation.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRecommendationService {
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())){
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address : {}", address);
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directionList);
    }
}
