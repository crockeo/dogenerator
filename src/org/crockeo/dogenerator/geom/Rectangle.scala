package org.crockeo.dogenerator.geom

class Rectangle(val pos: Point, val size: Point) {
  private val tl = new Point(pos.x, pos.y)
  private val tr = new Point(pos.x + size.x, pos.y)
  private val br = new Point(pos.x + size.x, pos.y + size.y)
  private val bl = new Point(pos.x, pos.y + size.y)
  
  def top   : Point = new Point(pos.x + (size.x / 2), pos.y)
  def bottom: Point = new Point(pos.x + (size.x / 2), pos.y +  size.y)
  def left  : Point = new Point(pos.x               , pos.y + (size.y / 2))
  def right : Point = new Point(pos.x + size.x      , pos.y + (size.y / 2))
  
  def intersects(r: Rectangle): Boolean =
    top.above(r.bottom) && bottom.below(r.top) && left.leftOf(r.right) && right.rightOf(r.left)
}