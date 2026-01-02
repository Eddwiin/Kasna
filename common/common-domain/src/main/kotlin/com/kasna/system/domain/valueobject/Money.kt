package com.kasna.system.domain.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

data class Money(val amount: BigDecimal) {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }

    fun isGreaterThanZero(): Boolean {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0
    }

    fun isGreaterThan(money: Money): Boolean {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0
    }

    fun add(money: Money): Money {
        return Money(setScale(this.amount.add(money.getAmount())))
    }

    fun subtract(money: Money): Money {
        return Money(setScale(this.amount.subtract(money.getAmount())))
    }

    fun multiply(multiplier: Int): Money {
        return Money(setScale(this.amount.multiply(BigDecimal(multiplier))))
    }

    fun getAmount(): BigDecimal? {
        return amount
    }

    private fun setScale(input: BigDecimal): BigDecimal {
        return input.setScale(2, RoundingMode.HALF_EVEN)
    }
}