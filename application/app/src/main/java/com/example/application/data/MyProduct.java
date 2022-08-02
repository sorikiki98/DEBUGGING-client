package com.example.application.data;

public class MyProduct {
    private final int productInterestId;

    private final int productId;

    private final int userId;

    private final String productName;

    MyProduct(
            int productInterestId,
            int productId,
            int userId,
            String productName
    ) {
        this.productInterestId = productInterestId;
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
    }
}
