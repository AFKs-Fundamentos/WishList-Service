package com.pcmaster.afk.wishlist.wishlist.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
