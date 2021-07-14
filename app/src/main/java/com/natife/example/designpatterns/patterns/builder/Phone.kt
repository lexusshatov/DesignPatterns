package com.natife.example.designpatterns.patterns.builder

class Phone private constructor(
    private val model: String,
    private val memory: Int,
    private val height: Int,
    private val width: Int,
    private val produce: String
) {

    class Builder {
        private var model: String = "Default"
        private var memory: Int = 16
        private var height: Int = 1080
        private var width: Int = 640
        private var produce: String = "China"

        fun withModel(model: String): Builder {
            this.model = model
            return this
        }

        fun withMemory(memory: Int): Builder {
            if (memory > 0) {
                this.memory = memory
            }
            return this
        }

        fun withSize(height: Int, width: Int): Builder {
            if (height % 10 == 0 && width % 5 == 0) {
                this.height = height
                this.width = width
            }
            return this
        }

        fun withProduce(produce: String): Builder {
            if (produce.isNotEmpty()) {
                this.produce = produce
            }
            return this
        }

        fun build() = Phone(model, memory, height, width, produce)
    }

    override fun toString(): String {
        return "{ \"model\": $model," +
                "\"memory\": $memory," +
                "\"height\": $height," +
                "\"width\": $width," +
                "\"produce\": $produce }"
    }
}