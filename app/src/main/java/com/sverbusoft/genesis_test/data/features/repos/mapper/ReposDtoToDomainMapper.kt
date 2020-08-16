package com.sverbusoft.genesis_test.data.features.repos.mapper

import com.sverbusoft.genesis_test.common.mapper.DtoMapper
import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity
import com.sverbusoft.genesis_test.data.features.repos.model.ReposModel

class ReposDtoToDomainMapper: DtoMapper<ReposResponseItem, ReposModel> {
    override fun mapFromObject(source: ReposResponseItem): ReposModel =
        with(source) {
            ReposModel(id, name, full_name, false, url, homepage)
        }

}