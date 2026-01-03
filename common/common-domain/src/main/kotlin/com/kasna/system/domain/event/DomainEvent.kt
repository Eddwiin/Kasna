package com.kasna.system.domain.event

interface DomainEvent<T> {
    fun fire()
}