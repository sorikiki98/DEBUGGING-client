package com.example.application.product;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Product;

public interface ProductItemContract {
    interface Presenter extends BasePresenter {
        void setProductId(int productId);

        void loadProduct();

        void toggleProductInterest(int productId);
    }

    interface View extends BaseView {

        void showProduct(Product product);

        void showErrorMessage(String message);

    }
}
