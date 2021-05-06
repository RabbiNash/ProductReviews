package dev.nashe.data.repository.sync

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Suppress("FunctionName")
fun SyncScope(dispatcher: CoroutineDispatcher = Dispatchers.Unconfined): CoroutineScope =
        CoroutineScope(SupervisorJob() + dispatcher)