package com.sverbusoft.genesis_test.data.features.repos.mapper

import com.sverbusoft.genesis_test.common.mapper.EntityMapper
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity
import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.model.ReposModel

class ReposModelToEntityMapper : EntityMapper<ReposEntity, ReposModel> {
    override fun mapFromObject(source: ReposEntity): ReposModel =
        with(source) {
            ReposModel(id, name, full_name, true, url, homepage)
        }

    override fun mapToObject(source: ReposModel): ReposEntity =
        with(source) {
            ReposEntity(id, name, full_name, url, homepage)
        }

}