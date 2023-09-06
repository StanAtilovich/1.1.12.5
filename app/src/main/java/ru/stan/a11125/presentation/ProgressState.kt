package ru.stan.a11125.presentation

sealed class ProgressState{
    object Loading: ProgressState()
    object Success: ProgressState()
}