package com.invillia.acme.constant

const val ADDRESS_ALREADY_EXISTS = "Address already exists";
const val ENTITY_MUST_HAVE_NULL_ID = "A new entity must have a null id";
const val ENTITY_NOT_FOUND = "There is no entity with id: ";
const val STORE_MUST_HAVE_VALID_ADDRESS = "A store must have a valid address";
const val STORE_NAME_ALREADY_EXISTS = "Store name already exists";
const val NO_STORE_WAS_FOUND = "No store was found"
const val ORDER_MUST_EXIST_FOR_PAYMENT = "An order must be given to be paid"
const val CREDIT_CARD_NUMBER_BAD_FORMATTED = "Credit card number must contain 16 digits"
const val ORDER_MUST_EXIST_FOR_REFUND = "An order must exist for refund"
const val ORDER_ITEMS_MUST_BE_INFORMED_FOR_REFUND = "Order items must be informed for refund"
const val ORDER_MUST_BE_WITH_PAYMENT_PENDING_TO_BE_PAID = "Order must be in payment pending state to be paid"
const val PAYMENT_MUST_BE_IN_PENDING_STATE_TO_BE_CONFIRMED = "Payment must be in payment pending state to be confirmed"
const val ORDER_CONFIRMATION_DATE_EXPIRATION_AND_PAID = "Order's confirmation date must be lower than refund's expiration date and must also be paid"