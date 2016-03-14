package com.example.rajsaraogi.xenisis.database;

import android.provider.BaseColumns;

public class DatabaseSchema {
    public static abstract class Events implements BaseColumns{
        public static final String TABLE_NEAME = "events";
        public static final String EVENT_ID = "event_id";
        public static final String DEPARTMENT_ID = "dept_id";
        public static final String EVENT_NAME ="event_name";
        public static final String EVENT_DESCRIPTION="event_desc";
        public static final String EVENT_PRICE="event_price";
        public static final String COORDINATE_NAME="coordinate_name";
        public static final String COORDINATE_MOBILE_NUM="coordinate_mobile";
        public static final String IMAGE_NAME="image_name";
    }
}
