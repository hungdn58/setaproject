package com.example.hoang.datingproject.Utilities;

/**
 * Created by hoang on 4/5/2016.
 */
public class Const {

    public static final String TAB_1_TAG = "Feeds";
    public static final String TAB_2_TAG = "Friends";
    public static final String TAB_3_TAG = "Messages";
    public static final String TAB_4_TAG = "Dating";
    public static final String TAB_5_TAG = "LastFragment";

    public static final String LOG_TAG = "DATING_LOG";

    public static int SPLASH_TIME_OUT = 2000;

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    final public static int PICTURE_CHOOSE = 2;

    public static int COVER_CHOOSE = 3;

    public static int PROFILE_CHOOSE = 4;

    public static int NEW_NOTE = 5;

    public static int RETURN_NEW_NOTE = 6;

    public static final int VIEW_TYPE_ITEM = 0;

    public static final int VIEW_TYPE_LOADING = 1;

    // feed fragment
    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";
    public static final String ID = "id";
    public static final String POST_USERID = "userID";
    public static final String POST_CONTENT = "content";
    public static final String ITEMID = "itemID";
    public static final String REPLY_CONTENT = "content";
    public static final String NEARBY = "nearby";
    public static final String YEAROLD_FROM = "yearold_from";
    public static final String YEAROLD_to = "yearold_to";
    public static final String USERID1 = "userID1";
    public static final String USERID2 = "userID2";
    public static final String FROM_USER = "from";
    public static final String MESSAGE = "message";
    public static final String USERID = "userId";
    public static final String PROFILE_IMAGE = "profileImage";
    public static final String NICK_NAME = "nickname";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";
    public static final String ADDRESS = "address";
    public static final String DESCRIPTION = "description";
    public static final String RESULT = "result";
    public static final String DATA = "data";
    public static final String PROFILE_UID = "uid";

    //url
    public static final String LIST_TIMELINE_URL = "http://192.168.1.74/timeline/list.json?" + OFFSET + "=0" + "&" + LIMIT + "=10";
    public static final String POST_TIMELINE_URL = "http://192.168.1.74/timeline/post";
    public static final String REPLY_TIMELINE_URL = "http://192.168.1.74/timeline/reply";
    public static final String REPORT_TIMELINE_URL = "http://192.168.1.74/timeline/report";
    public static final String DELETE_TIMELINE_URL = "http://192.168.1.74/timeline/delete";
    public static final String SEARCH_LIST_USER_URL = "http://192.168.1.74/search/list.json?" + OFFSET + "=0" + LIMIT + "=10";
    public static final String CHAT_LIST_URL = "http://192.168.1.74/chat/list.json?" + OFFSET + "=0" + LIMIT + "=10";
    public static final String CHAT_LOG_URL = "http://192.168.1.74/chat/chat.json?" + OFFSET + "=0" + LIMIT + "=10";
    public static final String CHAT_SEND_URL = "http://192.168.1.74/chat/send";
    public static final String LIST_NOTIFICATION_URL = "http://192.168.1.74/notification/list.json?" + OFFSET + "=0" + LIMIT + "=10";
    public static final String FOOT_PRINT_URL = "http://192.168.1.74/notification/footprint .json?" + OFFSET + "=0" + LIMIT + "=10";
    public static final String USER_PROFILE_URL = "http://192.168.1.74/user/profile.json";
    public static final String USER_PROFILE_UPDATE_URL = "http://192.168.1.74/user/profile/update";
    public static final String USER_PROFILE_DELETE_URL = "http://192.168.1.74/user/profile/delete";
    public static final String USER_CREATE_URL = "http://192.168.1.74/user/post";
    public static final String USER_LIST_URL = "http://192.168.1.74/user/list.json";
    public static final String USER_UPDATE_ID_URL = "http://192.168.1.74/user/update/";

    public static final String FIRE_BASE_URL = "https://glowing-inferno-4146.firebaseio.com/";
}
