package com.sverbusoft.genesis_test.common.mapper

interface DtoMapper<S, D> {

    fun mapFromObject(source: S): D

    fun mapFromObjects(sources: Collection<S>) = sources.map { mapFromObject(it) }
}