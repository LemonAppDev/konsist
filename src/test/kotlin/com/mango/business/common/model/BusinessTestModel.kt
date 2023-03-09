package com.mango.business.common.model

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import java.time.LocalDateTime
import java.util.UUID

@Suppress("LongParameterList")
object BusinessTestModel {

    fun getTask(
        id: TaskId = TaskId(UUID.fromString("93fec652-5ce8-4715-9efb-8083dd2b8379")),
        name: String = "name",
        ownerId: UserId = UserId(UUID.fromString("93fec652-5ce8-4715-9efb-8083dd2b8379")),
        creationDate: LocalDateTime = LocalDateTime.now(),
        projectId: ProjectId? = null,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priority: Priority? = null,
        parentTaskId: TaskId? = null,
        assigneeId: UserId? = null,
        completeDate: LocalDateTime? = null,
    ) = Task(
        id = id,
        name = name,
        ownerId = ownerId,
        creationDate = creationDate,
        projectId,
        description,
        dueDate,
        targetDate,
        priority,
        parentTaskId,
        assigneeId,
        completeDate,
    )

    fun getUUID1(): UUID = UUID.fromString("55e7f2a3-c46a-4d79-876e-ec6010a48af1")
    fun getUUID2(): UUID = UUID.fromString("0399933c-6e93-4ce9-ae9d-a94c3a72456e")
    fun getUUID3(): UUID = UUID.fromString("3d25807b-3a3e-406c-85a2-e75c7a5bbbfc")
    fun getUUID4(): UUID = UUID.fromString("130b7be9-9865-4beb-8299-43e51e658b1b")
    fun getUUID5(): UUID = UUID.fromString("ad02938a-8685-4dda-8dc8-9e1f5c3663a5")

    fun getProjectId1() = ProjectId(UUID.fromString("45fbb70b-4eb8-4a99-a158-49bd508c470c"))
    fun getProjectId2() = ProjectId(UUID.fromString("de39f978-677c-4e42-ba60-446ac924be68"))

    fun getUserId1() = UserId(UUID.fromString("3d5d125b-aedd-4933-a37a-cddbda9f9096"))
    fun getUserId2() = UserId(UUID.fromString("353c8f36-4e6f-41a3-8007-32acbd842049"))

    fun getTaskId1() = TaskId(UUID.fromString("35852e37-6ad9-4a5c-b349-e4f9710394f1"))
    fun getTaskId2() = TaskId(UUID.fromString("3a0d173c-92de-4eac-876a-934d383d7b1e"))
    fun getTaskId3() = TaskId(UUID.fromString("a64fdc2f-511e-45aa-a6ab-f6dce41ff911"))

    fun getCommentId1() = CommentId(UUID.fromString("1f5a1251-1f2e-4f46-b59e-2da71dc3324f"))
    fun getCommentId2() = CommentId(UUID.fromString("4a01e300-f0c9-45b4-9933-a99077f08478"))
}
