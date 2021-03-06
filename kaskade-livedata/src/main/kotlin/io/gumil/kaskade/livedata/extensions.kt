/*
 * Copyright 2018 Miguel Panelo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gumil.kaskade.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.gumil.kaskade.Action
import io.gumil.kaskade.Kaskade
import io.gumil.kaskade.State
import kotlin.reflect.KClass

fun <A : Action, S : State> Kaskade<A, S>.stateLiveData(initialAction: A? = null): LiveData<S> {
    return createLiveData(MutableLiveData(), initialAction)
}

fun <A : Action, S : State> Kaskade<A, S>.stateDamLiveData(
    initialAction: A? = null,
    vararg excludedStates: KClass<out S>
): DamLiveData<S> {
    val damLiveData: DamLiveData<S> = createLiveData(DamLiveData(), initialAction)
    damLiveData.exclude(*excludedStates)
    return damLiveData
}

private fun <A : Action, S : State, L : MutableLiveData<S>> Kaskade<A, S>.createLiveData(
    state: L,
    initialAction: A?
): L {
    onStateChanged = {
        state.postValue(it)
    }

    initialAction?.let { process(it) }

    return state
}