package com.example.application.product;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Product;

import java.util.List;

public class ProductSearchContract {
    interface Presenter extends BasePresenter {
        void filterProducts(String keyword);
    }

    interface View extends BaseView {
        void showFilteredProducts(List<Product> products);
    }
}
