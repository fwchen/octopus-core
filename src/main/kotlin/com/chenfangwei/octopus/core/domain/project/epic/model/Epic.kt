package com.chenfangwei.octopus.core.domain.project.epic.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("epic")
class Epic(private val projectId: String, private val name: String) {

    fun getProjectId(): String {
        return projectId;
    }

    @Version
    var version: Long? = null


    @CreatedDate
    var createdAt: Date? = null

    @LastModifiedDate
    var updatedAt: Date? = null


    companion object {
        fun create(projectId: String, name: String): Epic {
            return Epic(projectId, name)
        }
    }

}
