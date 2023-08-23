package com.pharmacy_recommendation.api.service

import com.pharmacy_recommendation.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {
    @Autowired
    private KakaoAddressSearchService kakaoAddressSearchService

    def "address 파라미터 값이 null이면, reqeustAddressSearch 메서드는 null 리턴"() {
        given:
        String address = null

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result == null
    }

    def "주소값이 valid라면 requestAddressSearch는 정상적으로 document 반환"() {
        given:
        String address = "서울시 성북구 종암로 10길"

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result.documentDtoList.size() > 0
        result.metaDto.totalCount > 0
        result.documentDtoList.get(0).addressName != null
    }
}
