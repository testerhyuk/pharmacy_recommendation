package com.pharmacy_recommendation.direction.service

import com.pharmacy_recommendation.api.dto.DocumentDto
import com.pharmacy_recommendation.pharmacy.dto.PharmacyDto
import com.pharmacy_recommendation.pharmacy.service.PharmacySearchService
import spock.lang.Specification

class DirectionServiceTest extends Specification {
    private PharmacySearchService pharmacySearchService = Mock()

    private DirectionService directionService = new DirectionService(pharmacySearchService)

    private List<PharmacyDto> pharmacyDtoList

    def setup() {
        pharmacyDtoList = new ArrayList<>()
        pharmacyDtoList.addAll(
                PharmacyDto.builder()
                    .id(1L)
                    .pharmacyName("돌곶이온누리약국")
                    .pharmacyAddress("주소1")
                    .latitude(37.61040424)
                    .longitude(127.0569046)
                    .build(),
                PharmacyDto.builder()
                    .id(2L)
                    .pharmacyName("호수온누리약국")
                    .pharmacyAddress("주소2")
                    .latitude(37.60894036)
                    .longitude(127.029052)
                    .build()
        )
    }

    def "buildDirectionList - 결과 값이 거리 순으로 정렬"() {
        given:
        def addressName = "서울 성북구 종암로10길"
        double inputLatitude = 37.5960650456809
        double inputLongitude = 127.037033003036

        def documentDto = DocumentDto.builder()
            .addressName(addressName)
            .latitude(inputLatitude)
            .longitude(inputLongitude)
            .build()

        when:
        pharmacySearchService.searchPharmacyDtoList() >> pharmacyDtoList

        def results = directionService.buildDirectionList(documentDto)

        then:
        results.size() == 2
        results.get(0).targetPharmacyName == "호수온누리약국"
        results.get(1).targetPharmacyName == "돌곶이온누리약국"
    }

    def "거리 반경 10km 이내만 검색"() {
        given:
        pharmacyDtoList.add(
                PharmacyDto.builder()
                        .id(3L)
                        .pharmacyName("경기약국")
                        .pharmacyAddress("주소3")
                        .latitude(37.3825107393401)
                        .longitude(127.236707811313)
                        .build())

        def addressName = "서울 성북구 종암로10길"
        double inputLatitude = 37.5960650456809
        double inputLongitude = 127.037033003036

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        pharmacySearchService.searchPharmacyDtoList() >> pharmacyDtoList

        def results = directionService.buildDirectionList(documentDto)

        then:
        results.size() == 2
        results.get(0).targetPharmacyName == "호수온누리약국"
        results.get(1).targetPharmacyName == "돌곶이온누리약국"
    }
}
