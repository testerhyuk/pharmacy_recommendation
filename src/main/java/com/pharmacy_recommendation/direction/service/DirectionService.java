package com.pharmacy_recommendation.direction.service;

import com.pharmacy_recommendation.api.dto.DocumentDto;
import com.pharmacy_recommendation.direction.entity.Direction;
import com.pharmacy_recommendation.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectionService {
    private static final int MAX_SEARCH_COUNT = 3; // 최대 검색 갯수
    private static final double RADIUS_KM = 10.0;// 고객 주소 기반 반경 10km

    private final PharmacySearchService pharmacySearchService;

    public List<Direction> buildDirectionList(DocumentDto documentDto) {
        if(Objects.isNull(documentDto)) return Collections.EMPTY_LIST;

        // 약국 데이터 조회
        return pharmacySearchService.searchPharmacyDtoList()
                .stream()
                .map(pharmacyDto ->
                    Direction.builder()
                            .inputAddress(documentDto.getAddressName())
                            .inputLatitude(documentDto.getLatitude())
                            .inputLongitude(documentDto.getLongitude())
                            .targetPharmacyName(pharmacyDto.getPharmacyName())
                            .targetAddress(pharmacyDto.getPharmacyAddress())
                            .targetLatitude(pharmacyDto.getLatitude())
                            .targetLongitude(pharmacyDto.getLongitude())
                            .distance(
                                    calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                            pharmacyDto.getLatitude(), pharmacyDto.getLongitude())
                            )
                            .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .sorted(Comparator.comparing(Direction::getDistance))
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());

        // 거리 계산 후 sort

    }

    // Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371;

        return earthRadius * Math.acos(
                Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)
        );
    }
}
