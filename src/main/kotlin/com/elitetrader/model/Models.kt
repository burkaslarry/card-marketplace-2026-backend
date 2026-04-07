package com.elitetrader.model

import java.time.Instant

// ─── Shared Enums ─────────────────────────────────────────────────────────────

enum class CardCategory { FOOTBALL, ONE_PIECE, POKEMON }
enum class CardRarity { ICONIC, ULTRA_RARE, RARE, UNCOMMON }
enum class SellerTier { ELITE, VERIFIED, STANDARD }
enum class VerificationStatus { VERIFIED, NEEDS_MANUAL_REVIEW, RISK_FLAG }
enum class GradingRecommendation { SEND_TO_PSA, REVIEW_AGAIN, GOOD_RAW_CONDITION }

// ─── Market Models ─────────────────────────────────────────────────────────────

data class MarketCard(
    val id: String,
    val name: String,
    val category: CardCategory,
    val currentPrice: Double,
    val change24h: Double,
    val change7d: Double,
    val volume24h: Double,
    val sparkline: List<Double>,
    val psaGrade: Int? = null,
    val year: Int? = null,
)

data class MarketSummary(
    val totalVolume24h: Double,
    val activeListings: Int,
    val verifiedPct: Double,
    val avgSettlementHours: Double,
    val cards: List<MarketCard>,
)

// ─── Vault / Listing Models ────────────────────────────────────────────────────

data class VaultListing(
    val id: String,
    val cardName: String,
    val subtitle: String,
    val category: CardCategory,
    val psaGrade: Int?,
    val currentBid: Double,
    val buyNowPrice: Double?,
    val isVerified: Boolean,
    val sellerRegion: String,
    val sellerTier: SellerTier,
    val timeLeft: String,
    val rarity: CardRarity,
    val bids: Int,
    val year: String,
    val population: Int? = null,
)

data class PlaceBidRequest(
    val listingId: String,
    val bidAmount: Double,
    val buyerEmail: String,
)

data class PlaceBidResponse(
    val success: Boolean,
    val message: String,
    val newBid: Double? = null,
    val escrowRef: String? = null,
)

// ─── Verification Models ───────────────────────────────────────────────────────

data class VerificationRequest(
    val cardName: String? = null,
    val imageBase64: String? = null,
)

data class VerificationResult(
    val status: VerificationStatus,
    val confidenceScore: Double,
    val serialId: String,
    val referenceId: String,
    val surface: String,
    val corners: String,
    val centeringLr: String,
    val centeringTb: String,
    val timestamp: Instant,
    val cardName: String,
    val notes: String,
    val processingMs: Long,
)

// ─── Grading Models ────────────────────────────────────────────────────────────

data class GradingRequest(
    val imageBase64: String? = null,
    val cardName: String? = null,
)

data class GradingScores(
    val centering: Double,
    val corners: Double,
    val edges: Double,
    val surface: Double,
)

data class GradingResult(
    val scores: GradingScores,
    val estimatedGradeMin: Int,
    val estimatedGradeMax: Int,
    val recommendation: GradingRecommendation,
    val notes: String,
    val processingMs: Long,
    val psaSubmitEstimate: String,
)

// ─── Generic Response ──────────────────────────────────────────────────────────

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null,
    val timestamp: Instant = Instant.now(),
)
