package com.example.baseproject.common.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constants {
    public static final String AUTHORIZATION = "Authorization";


    public static final class REDIS_KEY {
        // Bloom Filter
        public static final String BLOOM_FILTER = "BLOOM";
        public static final String BLOOM_LIST_COUNTRY_ID = "BLOOM_LIST_COUNTRY_ID";
        public static final String BLOOM_LIST_GENRE_ID = "BLOOM_LIST_GENRE_ID";
        public static final String BLOOM_LIST_TAG_ID = "BLOOM_LIST_TAG_ID";
        public static final String BLOOM_LIST_PROFILE_ID = "BLOOM_LIST_PROFILE_ID";

        // Country key
    }

    public final static class VARIABLE_TASK {
        public final static String ERROR_MESSAGE = "errorMessage";
        public final static String SUCCESS = "success";
        public static final String COMMAND = "command";

        public final static String USER_ID = "userId";
        public final static String STATUS = "status";

    }

    public static final class BPM {
        public static final String CREATE_NEW_USER = "create_new_user";
    }

}

