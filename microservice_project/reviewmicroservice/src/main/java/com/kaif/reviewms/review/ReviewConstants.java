package com.kaif.reviewms.review;

public class ReviewConstants {
    private ReviewConstants() {}

    public static final String ID_PATH = "/{reviewId}";
    public static final String PAGINATION_PATH = "/pagination/{page}/{pageSize}";
    public static final String SORTED_PATH = "/sorted/{field}";
    public static final String FILTER_PATH = "/filter";
    public static final String STATS_AVG_PATH = "/stats/average-rating";
}