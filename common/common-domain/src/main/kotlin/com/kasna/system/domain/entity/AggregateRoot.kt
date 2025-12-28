package com.kasna.system.domain.entity

abstract class AggregateRoot<ID>(override val id: ID): BaseEntity<ID>(id)