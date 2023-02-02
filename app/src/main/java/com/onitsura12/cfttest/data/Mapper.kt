package com.onitsura12.cfttest.data

import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel

class Mapper {
    companion object {
        fun toDatabaseDetails(cardDetails: CardDetails): CardDetailsDatabaseModel {
            return CardDetailsDatabaseModel(
                number = null,
                id = cardDetails.id,
                scheme = cardDetails.scheme,
                type = cardDetails.type,
                brand = cardDetails.brand,
                prepaid = cardDetails.prepaid,
                country = cardDetails.country,
                bank = cardDetails.bank
            )
        }

        fun toCardDetails(cardDetailsDatabase: CardDetailsDatabaseModel): CardDetails {
            return CardDetails(
                id = cardDetailsDatabase.id,
                scheme = cardDetailsDatabase.scheme,
                type = cardDetailsDatabase.type,
                brand = cardDetailsDatabase.brand,
                prepaid = cardDetailsDatabase.prepaid,
                country = cardDetailsDatabase.country,
                bank = cardDetailsDatabase.bank
            )
        }
    }
}