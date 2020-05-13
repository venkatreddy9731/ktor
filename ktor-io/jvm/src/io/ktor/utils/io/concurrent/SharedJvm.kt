/*
 * Copyright 2014-2020 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.utils.io.concurrent

import io.ktor.utils.io.core.internal.*
import kotlin.properties.*
import kotlin.reflect.*

/**
 * Allows to create mutate property with frozen value.
 *
 * Usage:
 * ```kotlin
 * var myCounter by shared(0)
 * ```
 */
@Suppress("NOTHING_TO_INLINE")
@DangerousInternalIoApi
public actual inline fun <T> shared(value: T): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {
    private var value: T = value

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return this.value
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }
}

/**
 * Allow to create unsafe reference that will never freeze.
 *
 * This reference is allowed to use only from creation thread. Otherwise it will return null.
 */
@DangerousInternalIoApi
public actual fun <T : Any> threadLocal(value: T): ReadOnlyProperty<Any, T?> = object : ReadOnlyProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? = value
}
