package model

import java.time.Year

data class Console(
    val consoleId: Int,
    val name: String,
    val company: String,
    val releaseYear: Int,
    val cost: Double,
    val isSupported: Boolean
) {
    init {
        require(consoleId >= 0) { "consoleId must not be negative" }
        require(name.isNotBlank()) { "name must not be blank" }
        require(company.isNotBlank()) { "company must not be blank" }
        require(releaseYear in 1972..Year.now().value + 1) { "releaseYear must be between 1972 and next year" }
        require(cost.isFinite() && cost >= 0.0) { "cost must be a finite number, zero or more" }    }

    override fun toString(): String =
        """
        |($consoleId) $name
        |Company: $company | Released: $releaseYear
        |Cost: ${"%.2f".format(cost)} | Supported: ${if (isSupported) "Yes" else "No"}
        """.trimMargin()
}