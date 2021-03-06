package com.sverbusoft.genesis_test.data.features.repos.mapper

import com.sverbusoft.genesis_test.common.mapper.EntityMapper
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel

class ReposModelToEntityMapper : EntityMapper<ReposEntity, ReposModel> {
    override fun mapFromObject(source: ReposEntity): ReposModel =
        with(source) {
            ReposModel(
                id,
                name,
                full_name,
                language,
                true,
                url,
                homepage
            )
        }

    override fun mapToObject(source: ReposModel): ReposEntity =
        with(source) {
            ReposEntity(id, name, full_name, language, url, homepage)
        }

}