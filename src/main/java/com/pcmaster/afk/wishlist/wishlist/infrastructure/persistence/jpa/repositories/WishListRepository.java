package com.pcmaster.afk.wishlist.wishlist.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import com.pcmaster.afk.wishlist.wishlist.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUserId(UserId userId);
}
