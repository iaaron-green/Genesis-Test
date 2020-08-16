package com.sverbusoft.genesis_test.common.mapper

interface EntityMapper<S, D> {

    fun mapFromObject(source: S): D

    fun mapFromObjects(sources: Collection<S>) = sources.map { mapFromObject(it) }

    fun mapToObject(source: D): S

    fun mapToObjects(sources: Collection<D>) = sources.map { mapToObject(it) }
}