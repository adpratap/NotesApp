package com.noreplypratap.data.mappers

import com.noreplypratap.data.model.DataNotes
import com.noreplypratap.domain.model.DomainNotes

fun DomainNotes.toDataNotes(): DataNotes {
    return DataNotes(
        id,
        title,
        body,
        date
    )
}

fun DataNotes.toDomainNotes(): DomainNotes {
    return DomainNotes(
        id,
        title,
        body,
        date
    )
}