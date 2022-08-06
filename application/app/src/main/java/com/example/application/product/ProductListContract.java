package com.example.application.product;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Company;
import com.example.application.data.Product;

import java.util.List;

public interface ProductListContract {
    interface Presenter extends BasePresenter {
        void loadProducts();

        void toggleProductInterest(int productId);
    }

    interface View extends BaseView {

        void showProducts(List<Product> products);

        void showErrorMessage(String message);

    }
}
