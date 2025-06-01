package com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates;

import com.pcmaster.afk.wishlist.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.CreateWishListCommand;
import com.pcmaster.afk.wishlist.wishlist.domain.model.valueobjects.ProductId;
import com.pcmaster.afk.wishlist.wishlist.domain.model.valueobjects.UserId;
import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishList extends AuditableAbstractAggregateRoot<WishList> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "productId", column = @Column(name = "product_id")),
    })
    private ProductId productId;

    public WishList(Long userId, Long productId){
        this.userId = new UserId(userId);
        this.productId = new ProductId(productId);
    }

    public WishList(){}

    public WishList(UserId userId, ProductId productId){
        this();
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId(){
        return userId.userId();
    }

    public Long getProductId(){
        return productId.productId();
    }

    public WishList(CreateWishListCommand command){
        this.userId = new UserId(command.userId());
        this.productId = new ProductId(command.productId());
    }
}
