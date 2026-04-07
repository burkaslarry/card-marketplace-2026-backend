package com.elitetrader.controller

import com.elitetrader.model.*
import com.elitetrader.service.MockDataService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/verify")
class VerificationController(private val mockDataService: MockDataService) {

    /**
     * Simulates RSA card authentication.
     * Phase 1 demo only — no real RSA integration.
     */
    @PostMapping("/rsa")
    fun verifyCard(@RequestBody req: VerificationRequest): ApiResponse<VerificationResult> {
        // Simulate processing delay indication (actual delay handled on frontend)
        val result = mockDataService.simulateVerification(req)
        return ApiResponse(success = true, data = result)
    }

    /**
     * Simulates AI pre-grading analysis.
     * Phase 1 demo only — no real CV model.
     */
    @PostMapping("/grade")
    fun gradeCard(@RequestBody req: GradingRequest): ApiResponse<GradingResult> {
        val result = mockDataService.simulateGrading(req)
        return ApiResponse(success = true, data = result)
    }

    @GetMapping("/health")
    fun health() = mapOf(
        "status" to "UP",
        "mode" to "demo",
        "version" to "0.1.0",
    )
}
