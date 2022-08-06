package com.example.application.data.source.remote;

import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.data.Product;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products")
    Flowable<List<Product>> getProducts(@Header("Authorization") String token);

    @GET("products/{product_id}")
    Flowable<Optional<Product>> getProduct(@Path("product_id")int productId, @Header("Authorization") String token);

    @POST("products/interest/{product_id}")
    Completable addProductInterest(@Path("product_id") int productId, @Header("Authorization") String token);

    @DELETE("products/interest/{product_id}")
    Completable removeProductInterest(@Path("product_id") int productId, @Header("Authorization") String token);
}
