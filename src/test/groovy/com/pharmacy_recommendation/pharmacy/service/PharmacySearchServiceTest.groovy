package com.pharmacy_recommendation.pharmacy.service

import com.pharmacy_recommendation.pharmacy.cache.PharmacyRedisTemplateService
import com.pharmacy_recommendation.pharmacy.entity.Pharmacy
import org.testcontainers.shaded.com.google.common.collect.Lists
import spock.lang.Specification

class PharmacySearchServiceTest extends Specification {
    private PharmacySearchService pharmacySearchService

    private PharmacyRepositoryService pharmacyRepositoryService = Mock()
    private PharmacyRedisTemplateService pharmacyRedisTemplateService = Mock()

    private List<Pharmacy> pharmacyList

    def setup() {
        pharmacySearchService = new PharmacySearchService(pharmacyRepositoryService ,pharmacyRedisTemplateService)

        pharmacyList = Lists.newArrayList(
                Pharmacy.builder()
                    .id(1L)
                    .pharmacyName("호수온누리약국")
                    .latitude(37.60894036)
                    .longitude(127.029052)
                    .build(),
                Pharmacy.builder()
                    .id(2L)
                    .pharmacyName("돌곶이온누리약국")
                    .latitude(37.61040424)
                    .longitude(127.0569046)
                    .build()
        )
    }

    def "Reids 장애시 DB 이용해 약국 데이터 조회"() {
        when:
        pharmacyRedisTemplateService.findAll() >> []
        pharmacyRepositoryService.findAll() >> pharmacyList
        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 2
    }
}
