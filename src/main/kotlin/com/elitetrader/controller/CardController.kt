package com.elitetrader.controller

import com.elitetrader.model.*
import com.elitetrader.service.MockDataService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vault")
class CardController(private val mockDataService: MockDataService) {

    @GetMapping("/listings")
    fun getListings(
        @RequestParam(required = false) category: CardCategory?,
        @RequestParam(required = false, defaultValue = "BID_HIGH") sortBy: String,
    ): ApiResponse<List<VaultListing>> {
        val listings = mockDataService.getVaultListings().let { all ->
            if (category != null) all.filter { it.category == category } else all
        }.let { filtered ->
            when (sortBy.uppercase()) {
                "BID_LOW"  -> filtered.sortedBy { it.currentBid }
                "BID_HIGH" -> filtered.sortedByDescending { it.currentBid }
                "GRADE"    -> filtered.sortedByDescending { it.psaGrade ?: 0 }
                else       -> filtered.sortedByDescending { it.currentBid }
            }
        }
        return ApiResponse(success = true, data = listings)
    }

    @GetMapping("/listings/{id}")
    fun getListing(@PathVariable id: String): ApiResponse<VaultListing> {
        val listing = mockDataService.getVaultListings().firstOrNull { it.id == id }
            ?: return ApiResponse(success = false, error = "Listing not found: $id")
        return ApiResponse(success = true, data = listing)
    }

    @PostMapping("/listings/{id}/bid")
    fun placeBid(
        @PathVariable id: String,
        @RequestBody req: PlaceBidRequest,
    ): ApiResponse<PlaceBidResponse> {
        val listing = mockDataService.getVaultListings().firstOrNull { it.id == id }
            ?: return ApiResponse(success = false, error = "Listing not found: $id")

        if (req.bidAmount <= listing.currentBid) {
            return ApiResponse(
                success = false,
                error = "Bid must be higher than current bid of ${listing.currentBid} USDT",
            )
        }

        val escrowRef = "ESC-${System.currentTimeMillis()}-${id.takeLast(4).uppercase()}"
        return ApiResponse(
            success = true,
            data = PlaceBidResponse(
                success = true,
                message = "Bid placed successfully. Funds held in escrow until seller confirms.",
                newBid = req.bidAmount,
                escrowRef = escrowRef,
            ),
        )
    }
}
