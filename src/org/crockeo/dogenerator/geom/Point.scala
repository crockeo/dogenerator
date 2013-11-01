package org.crockeo.dogenerator.geom

class Point(val x: Int, val y: Int) {
  override def toString: String =
    "{ " + x + ", " + y + " }"
  
  def +(p: Point): Point =
    new Point(x + p.x, y + p.y)
  def >(p: Point): Boolean =
    x > p.x || y > p.y
  
  // Relational functions
  def above  (p: Point): Boolean = y <  p.y
  def below  (p: Point): Boolean = y >  p.y
  def onY    (p: Point): Boolean = y == p.y
  def leftOf (p: Point): Boolean = x <  p.x
  def rightOf(p: Point): Boolean = x >  p.x
  def onX    (p: Point): Boolean = x == p.x
}