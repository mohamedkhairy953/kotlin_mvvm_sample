package com.example.raseedytask;

public class Constants {

    public static final String DEVICE_TYPE = "2";

    public static class Locale {

        public static final String LANG_CODE_ENGLISH = "en";
        public static final String LANG_CODE_ARABIC = "ar";
    }

    public static class NetworkStatusCodes {

        public static final int CODE_500 = 500;
        public static final int CODE_200 = 200;
        public static final int CODE_401 = 401;
    }

    public static class ApiEndPoints {

        public static final String API_FILTERS = "GetFilters";
        public static final String API_HOME_DATA = "home";
        public static final String API_OFFERS_SLIDER = "offers/slider";
        public static final String API_OFFERS_PRODUCTS_LIST = "offers/list";
        public static final String API_PRODUCT_DETAILS = "product/{productId}/details";
        public static final String API_PRODUCT_REVIEWS = "reviews/{productId}";
        public static final String API_PRODUCTS_LIST = "products";
        public static final String API_MY_ADDRESSES = "myAddresses";
        public static final String API_ADD_ADDRESS_BOOK = "addAddressBook";
        public static final String API_EDIT_ADDRESS_BOOK = "editAddressBook";
        public static final String API_DELETE_ADDRESS_BOOK_WITH_ID = "deleteAddressBook/{address_id}";
        public static final String API_PROFILE_DATA = "myProfile";
        public static final String API_DELETE_ADDRESS_BOOK = "deleteAddressBook";
        public static final String API_ADDRESS_BOOK_DATA = "getBookAddress";
        public static final String API_ADD_REVIEW = "review";
        public static final String API_CANCEL_ORDER_WITH_REASONS = "cancel/reasons";
        public static final String API_CANCEL_ORDER = "cancel/{orderId}";
        public static final String API_GET_CATEGORIES = "subCategories/{categoryId}";
        public static final String API_CHANGE_PASSWORD = "change_password";
        public static final String API_GET_COUNTRIES = "getCountries";
        public static final String API_FORGET_PASSWORD = "forget_password";
        public static final String API_GLOBAL_SEARCH = "search";
        public static final String API_LOGIN = "login";
        public static final String API_MENU_MORE = "more";
        public static final String API_GET_ORDER_DETAILS = "order/{orderId}";
        public static final String API_ORDER_TRACK = "tracks";
        public static final String API_GET_MY_ORDERS = "myOrders";
        public static final String API_ORDERS = "orders";
        public static final String API_PRODUCT_TRACK = "track/{orderId}";
        public static final String API_REGISTER = "createUser";
        public static final String API_RETURN_ORDER_WITH_REASONS = "return/reasons";
        public static final String API_RETURN_ORDER = "return/{orderId}";
        public static final String API_GET_LANGUAGES = "languages";
        public static final String API_GET_CURRENCIES = "currencies";
        public static final String API_COUNTRIES = "countries";
        public static final String API_STORE_PROFILE = "stores/{store_id}";
        public static final String API_STORE_PRODUCTS = "stores/{store_id}/products";
        public static final String API_STORE_REVIEWS = "stores/{store_id}/reviews";

    }

    public static class InteractionKeys {

        public static final String WISHLIST_STATUS_KEY = "WishListStatus";
        public static final String PRODUCT_ID_KEY = "productKey";

    }
}
