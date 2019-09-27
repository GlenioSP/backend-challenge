package com.invillia.acme.utils

object DataSecurityUtils {

    fun verifyCreditCardNumber(cardNumber: String): String? {
        if (cardNumber.length == 16 && cardNumber.matches("[0-9]+".toRegex())) {
            return cardNumber
        }
        return null
    }

    fun maskCreditCardNumber(cardNumber: String): String {
        val newCardNumber: String = cardNumber.substring(0..4)
        return "$newCardNumber-XXXX-XXXX-XXXX"
    }
}