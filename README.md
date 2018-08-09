# Kanvas

Make canvas easier to use in Kotlin 😊 

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