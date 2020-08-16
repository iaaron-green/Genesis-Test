package com.sverbusoft.genesis_test.data.features.repos.mapper

import com.sverbusoft.genesis_test.common.mapper.EntityMapper
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem

class ReposModelToEntityMapper : EntityMapper<ReposEntity, ReposResponseItem> {
    override fun mapFromObject(source: ReposEntity): ReposResponseItem =
        with(source) {
            ReposResponseItem(full_name, homepage, id, name, url)
        }

    override fun mapToObject(source: ReposResponseItem): ReposEntity =
        with(source) {
            ReposEntity(id, name, full_name, url, homepage)
        }

}