package org.crockeo.dogenerator

import org.crockeo.dogenerator.geom.{Rectangle, Point}

import java.awt.image.BufferedImage
import java.awt.Graphics
import java.util.Random

object Generator {
  private val r: Random = new Random(System.currentTimeMillis)

  // Getting the size of an image in Point form
  def imageSize(image: BufferedImage): Point =
    new Point(image.getWidth, image.getHeight)

  // Generating a single point
  def generatePoint(line: String, bounds: (Point, Point), g: Graphics): Point = {
    val (minBound, maxBound) = {
      val fm = g.getFontMetrics(Config.font)
      val size = new Point(fm.stringWidth(line), fm.getHeight)

      ((new Point(bounds._1.x + Config.borderMargin         , bounds._1.y + Config.borderMargin + (size.y / 2)), 
        new Point(bounds._2.x - Config.borderMargin - size.x, bounds._2.y - Config.borderMargin - (size.y / 2))))
    }
    
    new Point(r.nextInt(maxBound.x - minBound.x), r.nextInt(maxBound.y - minBound.y)) + minBound
  }

  // Generating a list of points
  def generatePoints(lines: List[String], image: BufferedImage): List[Point] = {
    def generateBounds(n: Int): (Point, Point) = {      
      // Generating row widths
      def widths(a: Int, d: Int): List[Int] =
        if (a <= d) List(a)
        else        d +: widths(a - d, d)
     
      // Getting the row of the number
      def getRow(ws: List[Int], n: Int): Int =
        if (ws.isEmpty || n <= 0) -1
        else                       1 + getRow(ws.tail, n - ws.head)
      
      // Getting the column of the number
      def getCol(ws: List[Int], n: Int): Int =
        if (getRow(ws, n) == 0)              n - 1
        else                                      getCol(ws, n - ws.head)
      
      val ws = widths(lines.length, MathUtils.cleanSqrt(lines.length))
      val (x, y) = (getCol(ws, n), getRow(ws, n))
      val (w, h) = (imageSize(image).x / ws(x), imageSize(image).y / (ws.length))
      
      (new Point( x * w      , y * h),
       new Point((x * w) + w, (y * h) + h))
    }
    
    (for (n <- 0 until lines.length)
     yield generatePoint(lines(n), generateBounds(n + 1), image.getGraphics)).toList
  }
}