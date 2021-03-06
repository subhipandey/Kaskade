package io.gumil.kaskade.livedata

import io.gumil.kaskade.Action
import io.gumil.kaskade.State

internal sealed class TestState : State {
    object State1 : TestState()
    object State2 : TestState()
}

internal sealed class TestAction : Action {
    object Action1 : TestAction()
    object Action2 : TestAction()
}