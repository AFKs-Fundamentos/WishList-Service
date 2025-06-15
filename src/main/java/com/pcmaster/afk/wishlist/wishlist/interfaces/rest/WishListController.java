package com.pcmaster.afk.wishlist.wishlist.interfaces.rest;

import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.DeleteProductOfWishListCommand;
import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByIdQuery;
import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByUserIdQuery;
import com.pcmaster.afk.wishlist.wishlist.domain.model.valueobjects.UserId;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListCommandService;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListQueryService;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.CreateWishListResource;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.WishListResource;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform.CreateWishListCommandFromResourceAssembler;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform.WishListResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/wishlist", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "WishList", description = "WishList Management Endpoints")
public class WishListController {

    private final WishListQueryService wishListQueryService;
    private final WishListCommandService wishListCommandService;

    public WishListController(WishListQueryService wishListQueryService, WishListCommandService wishListCommandService){
        this.wishListQueryService = wishListQueryService;
        this.wishListCommandService = wishListCommandService;
    }

    @Operation(
            summary = "Add a new WishList",
            description = "Add a new product for user wishlist",
            operationId = "createWishList",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateWishListResource.class)
                            )
                    ),
                    @ApiResponse (
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<WishListResource> createWishList(@RequestBody CreateWishListResource resource){

        var createWishListCommand = CreateWishListCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var wishListId = this.wishListCommandService.handle(createWishListCommand);

        if(wishListId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getWishByIdQuery = new GetWishListByIdQuery(wishListId);
        var optionalWish = this.wishListQueryService.handle(getWishByIdQuery);

        var wishResource = WishListResourceFromEntityAssembler.toResourceFromEntity(optionalWish.get());
        return new ResponseEntity<>(wishResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch all wishlist for user",
            description = "Fetch all product by wishlist of user",
            operationId = "getWishListByUserId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishListResource.class)
                            )
                    )
            }
    )
    @GetMapping("/user")
    public ResponseEntity<List<WishListResource>> getByUserId(@RequestParam(name = "userId") Long uId){

        if(uId == null){
            return ResponseEntity.badRequest().build();
        }

        UserId userId = new UserId(uId);

        var getWishListByUserIdQuery = new GetWishListByUserIdQuery(userId);
        var wishlist = this.wishListQueryService.handle(getWishListByUserIdQuery);

        var wishlistResource = wishlist.stream()
                .map(WishListResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(wishlistResource);
    }

    @Operation(
            summary = "Delete product of wishlist",
            description = "Delete a product of wishlist by user",
            operationId = "deleteProductOfWishList",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful delete",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishListResource.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<?> deleteProductOfWishList(@PathVariable Long userId, Long productId){
        var deleteProduct = new DeleteProductOfWishListCommand(userId, productId);
        this.wishListCommandService.handle(deleteProduct);
        return ResponseEntity.ok().build();
    }
}
