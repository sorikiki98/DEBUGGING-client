package com.example.application.data;

public class MyProduct {
    public final int productInterestId;

    public final int productId;

    public final int userId;

    public final String productName;

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
