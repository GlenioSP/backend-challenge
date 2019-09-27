package com.invillia.acme.repository.specification

import com.invillia.acme.domain.Address
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Predicate

class AddressSpecification {

    fun addressContains(street: String?, city: String?, state: String?, zipCode: String?, number: Short?): Specification<Address> {
        return Specification { root, cq, cb ->
            val predicates = mutableListOf<Predicate>();
            if (street != null) predicates.add(cb.equal(root.get<String>("street"), street.toLowerCase()))
            if (city != null) predicates.add(cb.equal(root.get<String>("city"), city.toLowerCase()))
            if (state != null) predicates.add(cb.equal(root.get<String>("state"), state.toLowerCase()))
            if (zipCode != null) predicates.add(cb.equal(root.get<String>("zipCode"), zipCode.toLowerCase()))
            if (number != null) predicates.add(cb.equal(root.get<Short>("number"), number))

            if (predicates.isEmpty()) predicates.add(cb.equal(root.get<Long>("id"), -1))

            cb.and(*predicates.toTypedArray())
        }
    }
}