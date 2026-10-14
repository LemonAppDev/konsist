package kotlinx.coroutines.flow

class MutableStateFlow<T>(
    override var value: T,
) : StateFlow<T>
