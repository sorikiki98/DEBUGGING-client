package com.example.application.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.application.data.Company;
import com.example.application.data.Product;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertProducts(List<Product> products);

    @Query("SELECT * FROM products")
    public Flowable<List<Product>> getProducts();

    @Query("SELECT * FROM products WHERE id=:productId")
    public Flowable<Optional<Product>> getProduct(int productId);

    @Query("UPDATE products SET isProductInterested=1, numOfInterestedUsers=numOfInterestedUsers+1 WHERE id=:productId")
    public Completable addProductInterest(int productId);

    @Query("UPDATE products SET isProductInterested=0, numOfInterestedUsers=numOfInterestedUsers-1 WHERE id=:productId")
    public Completable removeProductInterest(int productId);
}
