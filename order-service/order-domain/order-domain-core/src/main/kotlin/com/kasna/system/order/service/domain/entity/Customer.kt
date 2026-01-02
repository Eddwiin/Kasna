package com.kasna.system.order.service.domain.entity

import com.kasna.system.domain.entity.AggregateRoot
import com.kasna.system.domain.valueobject.CustomerId

class Customer(customerId: CustomerId): AggregateRoot<CustomerId>(customerId)