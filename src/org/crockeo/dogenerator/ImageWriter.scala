package org.crockeo.dogenerator

import org.crockeo.dogenerator.geom.{Point, Rectangle}
import java.awt.image.BufferedImage
import java.awt.{RenderingHints, Graphics2D, Color, Font}
import java.io.File

object ImageWriter {
  // Writing text to an image
  def writeText(trip: (String, Point, Color), image: BufferedImage): BufferedImage = {
    val (text, pos, color) = trip
    val g = image.getGraphics.asInstanceOf[Graphics2D]
    
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                       RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
    g.setFont(Config.font)
    g.setColor(color)
    g.drawString(text, pos.x, pos.y)
    
    image
  }
  
  // Writing a list of texts to an image
  def writeTextList(texts: List[(String, Point, Color)], image: BufferedImage): BufferedImage =
    texts.foldLeft(image)((a, b) => writeText(b, a))
  
  // The front end for writing a list of texts to the screen
  def write(texts: List[String], image: BufferedImage): BufferedImage = {
    def toLength[T](l: List[T], target: Int): List[T] =
      if (l.length >= target) l.take(target)
      else                    toLength(l ++ l, target)
    
    def genTrips(ls: List[String], ps: List[Point], cs: List[Color]): List[(String, Point, Color)] =
      if (ls.length == ps.length && ps.length == cs.length) (for (n <- 0 until ls.length)
                                                             yield (ls(n), ps(n), cs(n))).toList
      else throw new Exception("List lengths do not match.")
    
    writeTextList(genTrips(texts,
                           Random.randomValidPoints(texts, new Point(image.getWidth, image.getHeight), image.getGraphics).map(a => a._1),
                           toLength(Config.colors, texts.length)),
                  image)
  }
}