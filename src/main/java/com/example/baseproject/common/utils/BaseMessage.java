package com.example.baseproject.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseMessage {
    OK(200, "Thành công"),
    SYSTEM_ERROR(500, "Có lỗi xảy ra"),
    EMPTY_LIST(200, "Danh sách trống"),
    EMPTY_INPUT(200, "Đầu vào trống"),
    MOVIE_NOT_EXIST(400, "Phim này không tồn tại"),
    COUNTRY_NOT_EXIST(400, "Quốc gia này không tồn tại"),
    GENRE_NOT_EXIST(400, "Thể loại này không tồn tại"),
    TAG_NOT_EXIST(400, "Thẻ này không tồn tại"),
    PROFILE_NOT_EXIST(400, "Hồ sơ này không tồn tại"),
    USER_NOT_EXIST(400, "Người dùng này không tồn tại"),

    COUNTRY_NAME_EXISTED(400, "Tên quốc gia đã tồn tại"),
    COUNTRY_SLUG_EXISTED(400, "Url quốc gia đã tồn tại"),
    COUNTRY_DUPLICATE(400, "Thông tin quốc gia đã bị trùng lặp"),

    GENRE_NAME_EXISTED(400, "Tên thể loại đã tồn tại"),
    GENRE_SLUG_EXISTED(400, "Url thể loại đã tồn tại"),
    GENRE_DUPLICATE(400, "Thông tin thể loại đã bị trùng lặp"),

    TAG_NAME_EXISTED(400, "Tên thẻ đã tồn tại"),
    TAG_SLUG_EXISTED(400, "Url thẻ đã tồn tại"),
    TAG_DUPLICATE(400, "Thông tin thẻ đã bị trùng lặp"),

    PROFILE_NAME_EXISTED(400, "Tên hồ sơ đã tồn tại"),
    PROFILE_SLUG_EXISTED(400, "Url hồ sơ đã tồn tại"),
    PROFILE_DUPLICATE(400, "Thông tin hồ sơ đã bị trùng lặp"),
    ;

    private final int status;
    private final String message;
}
