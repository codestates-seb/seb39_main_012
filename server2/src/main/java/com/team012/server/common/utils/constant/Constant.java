package com.team012.server.common.utils.constant;

public enum Constant {
    DELETE_RESERVATION_SUCCESS("예약 삭제 성공"),
    UNDEFINED("미정"),
    CONFIRM("확정"),
    ROLE_COMPANY("ROLE_COMPANY"),
    ROLE_CUSTOMER("ROLE_CUSTOMER"),

    NUMBERS_OF_RESERVATIONS_AVAILABLE("예약 가능한 마리수 : "),
    REVIEW_WRITTEN("리뷰 작성 완료"),
    MODIFIED_REVIEW_SUCCESS("리뷰 수정 완료"),
    DELETE_REVIEW_SUCCESS("리뷰 삭제 완료"),
    REVIEW_IMAGE_LIST("리뷰에 달린 파일들 : {}"),
    DOG_NAME_LOG("dogName = {}"),
    DOG_TYPE_LOG("dogType = {}"),
    DOG_IMAGE_URL("dogImageUrl = {}"),

    CREATE_SUCCESS("create success"),
    MODIFIED_SUCCESS("modified success"),
    DELETE_SUCCESS("delete success"),
    USER_JOIN_SUCCESS("회원가입 완료"),
    WRITE_AGAIN_PLEASE("다시 입력해주세요"),
    HERE_IS_YOUR_EMAIL("찾으시는 이메일입니다. "),
    CHECK_YOUR_PASSWORD("비밀번호를 확인해주세요. "),
    HERE_IS_YOUR_PASSWORD("찾으시는 비밀번호입니다. "),
    CHANGE_YOUR_PASSWORD_PLEASE(" 꼭 비밀번호를 변경해주세요."),
    CHOOSE_YOUR_CORRECT_DOG_ID("정확한 본인의 강아지 Id를 선택해주세요.");

    private final String message;

    Constant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
