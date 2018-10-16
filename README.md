# Kanvas

**WORK IN PROGRESS**

Make canvas easier to use in Kotlin 😊 

# ✨ Use shapes & write less code to do more magic ✨

[![arc](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_arc.gif)](https://github.com/florent37/MyLittleCanvas)

```kotlin
val canvasAnimator = CanvasAnimator(this)

val background = RectShape(this) { view ->
   color = Color.parseColor("#6fbf73")
   cornerRadius = 16.dpToPx(context)

   left = 100f
   width = view.width / 2f
   top = 100f
   height = view.height / 3f
}


fun animate(){
    canvasAnimator
            .play(background.animate().right.to(this.width.toFloat()))
            .start()
}

override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.draw(background)
}
```

# Examples


[![arc](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_arc.gif)](https://github.com/florent37/MyLittleCanvas)
[![dots](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/dots_sample.gif)](https://github.com/florent37/MyLittleCanvas)
[![slider](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/slider_sample.gif)](https://github.com/florent37/MyLittleCanvas)
[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_tree.gif)](https://github.com/florent37/MyLittleCanvas)

# Available shapes

| Shapes    | link       |
|-----------|------------|
| Rect      |            |
| Circle    |            |
| Text      |            |
| Arc       |            |
| Line      |            |
| Triangle  |            |
| Drawable  |            |
| Path      |            |

# Animation

Follow the example of [SwitchView](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/SwitchView.java)

Shape animations are executed by an instance of `ShapeAnimator` attached to your view

```java
val canvasAnimator = CanvasAnimator(this)
```

All animated methods of shapes are wrapped into the method `.animate()`

For example, for a `CircleShape`, you can animate his position (centerX) using
```java
myCircleShape.animate().centerX.to(15);
```

Then use your `ShapeAnimator` to execute this animation

```java
shapeAnimator.play(myCircleShape.animate().centerX.to(15);)
    .setDuration(500)
    .start()
```


# Added support methods to Canvas

`drawArc(centerX, centerY, circleRadius, startAngle, sweepAngle, paint)`

`drawArc(center: PointF, circleRadius, startAngle, sweepAngle, paint)`

`drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint)`

`drawOval(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint)`

`drawLine(start: PointF, end: PointF, paint)`

`drawRoundRect(left, top, right, bottom, rx, ry, paint)`


# Added support methods to Path

`addRoundRect(left, top, right, bottom, rx, ry, dir: Path.Direction) `

`addRoundRect(left, top, right, bottom, radiiArray, dir: Path.Direction)`

`arcTo(centerX, centerY, circleRadius, startAngle, sweepAngle, forceMoveTo)`

`arcTo(center: PointF, circleRadius, startAngle, sweepAngle, forceMoveTo)`

`arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo)`
