package com.elitetrader.controller

import com.elitetrader.model.ApiResponse
import com.elitetrader.model.CardCategory
import com.elitetrader.model.MarketCard
import com.elitetrader.service.MockDataService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/market")
class MarketController(private val mockDataService: MockDataService) {

    @GetMapping("/summary")
    fun getSummary() = ApiResponse(success = true, data = mockDataService.getMarketSummary())

    @GetMapping("/cards")
    fun getCards(
        @RequestParam(required = false) category: CardCategory?,
    ): ApiResponse<List<MarketCard>> {
        val cards = mockDataService.getMarketCards().let { all ->
            if (category != null) all.filter { it.category == category } else all
        }
        return ApiResponse(success = true, data = cards)
    }

    @GetMapping("/cards/{id}")
    fun getCard(@PathVariable id: String): ApiResponse<MarketCard> {
        val card = mockDataService.getMarketCards().firstOrNull { it.id == id }
            ?: return ApiResponse(success = false, error = "Card not found: $id")
        return ApiResponse(success = true, data = card)
    }
}
