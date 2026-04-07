package com.elitetrader.service

import com.elitetrader.model.*
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random

@Service
class MockDataService {

    // ─── Market ─────────────────────────────────────────────────────────────────

    fun getMarketCards(): List<MarketCard> = listOf(
        MarketCard("messi-wc-gold", "Messi World Cup Gold", CardCategory.FOOTBALL,
            12480.0, 4.82, 11.3, 84200.0,
            listOf(10900.0, 11200.0, 11000.0, 11800.0, 12100.0, 11900.0, 12300.0, 12480.0), 10, 2022),
        MarketCard("ronaldo-cl", "Ronaldo Champions League", CardCategory.FOOTBALL,
            4520.0, -1.42, 3.8, 32100.0,
            listOf(4800.0, 4720.0, 4600.0, 4580.0, 4500.0, 4490.0, 4530.0, 4520.0), 9, 2018),
        MarketCard("mbappe-wc", "Mbappé World Cup 2022", CardCategory.FOOTBALL,
            1820.0, 2.15, -0.9, 18400.0,
            listOf(1720.0, 1740.0, 1700.0, 1760.0, 1790.0, 1800.0, 1810.0, 1820.0), null, 2022),
        MarketCard("sabo-manga", "One Piece Manga Sabo", CardCategory.ONE_PIECE,
            3840.0, 7.21, 18.4, 51300.0,
            listOf(3100.0, 3200.0, 3350.0, 3400.0, 3600.0, 3700.0, 3780.0, 3840.0), 10),
        MarketCard("luffy-gear5", "Luffy Gear 5 Alt Art", CardCategory.ONE_PIECE,
            2230.0, 5.67, 22.1, 39800.0,
            listOf(1700.0, 1820.0, 1900.0, 2000.0, 2100.0, 2180.0, 2210.0, 2230.0)),
        MarketCard("zoro-paramount", "Zoro Paramount War", CardCategory.ONE_PIECE,
            1540.0, -0.78, 6.2, 14200.0,
            listOf(1480.0, 1500.0, 1520.0, 1510.0, 1490.0, 1510.0, 1535.0, 1540.0), 10),
        MarketCard("charizard-base", "Base Set Charizard", CardCategory.POKEMON,
            8250.0, 1.84, 4.7, 120400.0,
            listOf(7900.0, 7950.0, 8000.0, 8100.0, 8050.0, 8150.0, 8200.0, 8250.0), 9, 1999),
        MarketCard("dark-charizard", "Dark Charizard 1st Ed.", CardCategory.POKEMON,
            2820.0, -2.34, -1.1, 22800.0,
            listOf(3000.0, 2980.0, 2950.0, 2900.0, 2870.0, 2840.0, 2830.0, 2820.0), null, 2000),
        MarketCard("pikachu-illus", "Pikachu Illustrator Promo", CardCategory.POKEMON,
            44800.0, 0.45, 2.3, 8200.0,
            listOf(43500.0, 43800.0, 44100.0, 44200.0, 44500.0, 44600.0, 44750.0, 44800.0), 7, 1998),
    )

    fun getMarketSummary(): MarketSummary = MarketSummary(
        totalVolume24h = 318000.0,
        activeListings = 1204,
        verifiedPct = 98.2,
        avgSettlementHours = 3.8,
        cards = getMarketCards(),
    )

    // ─── Vault Listings ──────────────────────────────────────────────────────────

    fun getVaultListings(): List<VaultListing> = listOf(
        VaultListing("vlt-001", "Messi World Cup Gold", "Panini Prizm · 2022 FIFA · /10",
            CardCategory.FOOTBALL, 10, 11800.0, 13500.0, true, "🇦🇪 UAE",
            SellerTier.ELITE, "2h 14m", CardRarity.ICONIC, 18, "2022", 12),
        VaultListing("vlt-002", "Base Set Charizard", "1st Edition · Shadowless · PSA 9",
            CardCategory.POKEMON, 9, 7900.0, 9000.0, true, "🇺🇸 USA",
            SellerTier.ELITE, "4h 52m", CardRarity.ICONIC, 24, "1999", 149),
        VaultListing("vlt-003", "Manga Sabo 1st Edition", "One Piece · Alt Art · OP-06",
            CardCategory.ONE_PIECE, 10, 3600.0, 4200.0, true, "🇯🇵 Japan",
            SellerTier.VERIFIED, "1d 3h", CardRarity.ULTRA_RARE, 11, "2023", 38),
        VaultListing("vlt-004", "Ronaldo Champions League", "Topps Chrome · 2018 · Gold Refractor",
            CardCategory.FOOTBALL, 9, 4200.0, 5000.0, true, "🇬🇧 UK",
            SellerTier.VERIFIED, "6h 30m", CardRarity.ULTRA_RARE, 7, "2018", 55),
        VaultListing("vlt-005", "Pikachu Illustrator", "CoroCoro 1998 · CGC 7 · Unique",
            CardCategory.POKEMON, 7, 42000.0, null, true, "🇯🇵 Japan",
            SellerTier.ELITE, "3d 12h", CardRarity.ICONIC, 6, "1998", 1),
        VaultListing("vlt-006", "Luffy Gear 5 Alt Art", "One Piece · OP-07 · Secret Rare",
            CardCategory.ONE_PIECE, null, 2100.0, 2500.0, true, "🇸🇬 Singapore",
            SellerTier.VERIFIED, "18h 45m", CardRarity.RARE, 9, "2023"),
        VaultListing("vlt-007", "Mbappé World Cup 2022", "Panini Prizm · Silver · /75",
            CardCategory.FOOTBALL, null, 1650.0, 2000.0, false, "🇫🇷 France",
            SellerTier.STANDARD, "12h 00m", CardRarity.RARE, 5, "2022"),
        VaultListing("vlt-008", "Dark Charizard 1st Ed.", "Team Rocket Set · Holo · NM",
            CardCategory.POKEMON, null, 2700.0, 3100.0, true, "🇩🇪 Germany",
            SellerTier.VERIFIED, "2d 6h", CardRarity.ULTRA_RARE, 13, "2000"),
        VaultListing("vlt-009", "Zoro Paramount War", "One Piece · OP-02 · PSA 10",
            CardCategory.ONE_PIECE, 10, 1480.0, 1800.0, true, "🇭🇰 Hong Kong",
            SellerTier.VERIFIED, "5h 10m", CardRarity.RARE, 8, "2022", 22),
    )

    // ─── RSA Verification (Demo) ─────────────────────────────────────────────────

    fun simulateVerification(req: VerificationRequest): VerificationResult {
        val results = listOf(
            VerificationResult(VerificationStatus.VERIFIED, 98.7,
                "ETR-2024-0829-A7F3", "PSA-REF-8812034",
                "Mint", "Sharp", "52 / 48", "51 / 49",
                Instant.now(), req.cardName ?: "Scanned Card",
                "All authenticity markers confirmed. Print registration within tolerance.",
                2341),
            VerificationResult(VerificationStatus.NEEDS_MANUAL_REVIEW, 74.2,
                "ETR-2024-0914-B2D8", "PSA-REF-9913021",
                "Near Mint", "Near Mint", "58 / 42", "54 / 46",
                Instant.now(), req.cardName ?: "Scanned Card",
                "Minor centering variance flagged. Recommend physical expert review.",
                2891),
            VerificationResult(VerificationStatus.RISK_FLAG, 31.5,
                "ETR-2024-1003-C9E1", "ETR-FLAG-0042",
                "Good", "Slightly Worn", "63 / 37", "60 / 40",
                Instant.now(), req.cardName ?: "Scanned Card",
                "Significant print inconsistencies detected. Do NOT list until authenticated.",
                3102),
        )
        return results[Random.nextInt(results.size)]
    }

    // ─── AI Grading (Demo) ───────────────────────────────────────────────────────

    fun simulateGrading(req: GradingRequest): GradingResult {
        val results = listOf(
            GradingResult(GradingScores(9.0, 8.5, 8.5, 9.5), 8, 9,
                GradingRecommendation.SEND_TO_PSA,
                "Strong candidate for PSA 8–9. Surface exceptional, minor corner wear at bottom-left.",
                1820, "~\$85 Express"),
            GradingResult(GradingScores(7.5, 7.0, 8.0, 8.0), 7, 8,
                GradingRecommendation.REVIEW_AGAIN,
                "Possible PSA 7–8. Centering needs improvement. Consider re-scanning in better lighting.",
                2140, "~\$50 Standard"),
            GradingResult(GradingScores(9.5, 9.0, 9.5, 9.5), 9, 10,
                GradingRecommendation.SEND_TO_PSA,
                "Excellent PSA 9–10 candidate. Near-perfect centering and sharp corners.",
                1540, "~\$300 Walkthrough"),
        )
        return results[Random.nextInt(results.size)]
    }
}
