package com.rombsquare.mentalmath.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rombsquare.mentalmath.Diff
import com.rombsquare.mentalmath.Operation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameVM(op: Operation, diff: Diff) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState

    private val _solved = MutableStateFlow<Int>(0)
    val solved: StateFlow<Int> = _solved

    private val _task = MutableStateFlow<String>("<task>")
    val task: StateFlow<String> = _task

    private val _remainingTime = MutableStateFlow<Int>(100)
    val remainingTime: StateFlow<Int> = _remainingTime

    private var userAnswer: Int? = null
    private var engine: Engine = Engine(op.string, diff.string)
    private var startTime = System.currentTimeMillis()

    init {
        _task.value = engine.task
        startStopwatch()
    }

    fun startStopwatch() {
        startTime = System.currentTimeMillis()

        viewModelScope.launch {
            while (_remainingTime.value > 0) {
                val now = System.currentTimeMillis()
                _remainingTime.value = 100 - (now - startTime).toInt() / 1000
                delay(100L)
            }

            _uiState.value = UiState.FinishGame
        }
    }

    fun submitAnswer(answer: Int) {
        userAnswer = answer
        val state = engine.check(answer)

        if (!state) {
            _uiState.value = UiState.ShowWrongAnswerDialog
            return
        }

        _solved.value = engine.solved
        _task.value = engine.task
    }

    fun idle() {
        _uiState.value = null
    }
}

@Suppress("UNCHECKED_CAST")
class GameVMFactory(private val op: Operation, private val diff: Diff) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameVM(op, diff) as T
    }
}