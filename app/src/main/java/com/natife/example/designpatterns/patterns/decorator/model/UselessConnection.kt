package com.natife.example.designpatterns.patterns.decorator.model

import com.natife.example.designpatterns.patterns.decorator.base.BaseConnection


class UselessConnection : BaseConnection() {
    override val canConnect: Boolean = false
    override val canDisconnect: Boolean = false
    override val canReconnect: Boolean = false
}