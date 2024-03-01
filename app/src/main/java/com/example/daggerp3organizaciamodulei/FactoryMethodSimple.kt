package com.example.daggerp3organizaciamodulei

// Интерфейс Shape
interface Shape {
    fun draw()
}

// Класс Circle, реализующий интерфейс Shape
class Circle : Shape {
    override fun draw() {
        println("Drawing a circle")
    }
}

// Класс Square, реализующий интерфейс Shape
class Square : Shape {
    override fun draw() {
        println("Drawing a square")
    }
}

// Фабрика ShapeFactory
class ShapeFactory {
    fun createShape(type: String): Shape? {
        return when (type) {
            "Circle" -> Circle()
            "Square" -> Square()
            else -> null
        }
    }
}

// Пример использования Factory Method
fun main() {
    val shapeFactory = ShapeFactory()

    val circle = shapeFactory.createShape("Circle")
    circle?.draw()

    val square = shapeFactory.createShape("Square")
    square?.draw()
}