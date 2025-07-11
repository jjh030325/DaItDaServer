package io.github.jjh030325.daitdaserver.Enum;

import lombok.Getter;

/*
 * 상품의 종류를 정의하는 열거형 Enum 입니다.
 * 상품을 크게 10종류의 타입으로 나누어 관리하기 위한 열거형입니다.
 * */
@Getter
public enum eType {
    FOOD("식품"),           // 식품: 가공식품, 간식, 음료, 신선식품 등
    CLOTHES("의류"),        // 의류: 티셔츠, 바지, 원피스, 아우터 등 패션 관련 품목
    ELECTRONICS("전자기기"), // 전자기기: 스마트폰, 노트북, 태블릿, 가전제품 등
    BOOK("도서"),           // 도서: 소설, 교재, 참고서, 전자책 등
    HOUSEWARE("생활용품"),  // 생활용품: 청소도구, 수납용품, 주방도구 등
    TOY("장난감"),          // 장난감: 아동용 장난감, 퍼즐, 피규어 등
    BEAUTY("뷰티"),        // 뷰티: 화장품, 스킨케어, 뷰티기기 등
    FURNITURE("가구"),     // 가구: 의자, 책상, 침대, 수납장 등
    PET("반려동물"),        // 반려동물: 사료, 장난감, 펫용품 등
    ETC("기타");           // 기타: 위 분류에 속하지 않는 잡화류

    private final String label;

    eType(String label) {
        this.label = label;
    }
}
