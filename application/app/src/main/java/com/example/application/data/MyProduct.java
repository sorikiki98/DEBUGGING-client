package com.example.application.data;

import java.util.Objects;

public class MyProduct {
    public final int productInterestId;

    public final int productId;

    public final int userId;

    public final String productName;

    public final String thumbnail;

    public final int numOfInterestedUsers;

    MyProduct(
            int productInterestId,
            int productId,
            int userId,
            String productName,
            String thumbnail,
            int numOfInterestedUsers
    ) {
        this.productInterestId = productInterestId;
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        this.thumbnail = thumbnail;
        this.numOfInterestedUsers = numOfInterestedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyProduct myProduct = (MyProduct) o;
        return productInterestId == myProduct.productInterestId && productId == myProduct.productId && userId == myProduct.userId && numOfInterestedUsers == myProduct.numOfInterestedUsers && Objects.equals(productName, myProduct.productName) && Objects.equals(thumbnail, myProduct.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productInterestId, productId, userId, productName, thumbnail, numOfInterestedUsers);
    }
}
