package org.crockeo.dogenerator

import org.crockeo.dogenerator.geom.{Point, Rectangle}

import java.awt.image.BufferedImage
import java.awt.{Graphics2D, Color, Font}
import java.io.File

object ImageWriter {
  // Writing text to an image
  def writeText(trip: (String, Point, Color), image: BufferedImage): BufferedImage = {
    val (text, pos, color) = trip
    val g = image.getGraphics.asInstanceOf[Graphics2D]
    
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
    def genUntilNoCollide: List[Point] = {
      val genPairs: List[(Point, Rectangle)] =
        texts.map(a => Random.randomPoint(a, new Point(image.getWidth, image.getHeight), image.getGraphics))
      def anyCollide: Boolean =
        (for { a <- genPairs
        	   b <- genPairs
        } yield a._2.intersects(b._2)).reduceLeft((a, b) => a && b)
        
      if (anyCollide) genUntilNoCollide
      else            genPairs.map(a => a._1)
    }
    
    def genTrips: List[(String, Point, Color)] = {
      def genTripsRaw(pairs: List[(String, Point)], n: Int): List[(String, Point, Color)] =
        if (n >= Config.colors.length) genTripsRaw(pairs, 0)
        else if (pairs.isEmpty)        List()
        else                           (pairs.head._1, pairs.head._2, Config.colors(n)) +: genTripsRaw(pairs.tail, n + 1)
      genTripsRaw(texts.zip(genUntilNoCollide).toList, 0)
    }
    
    writeTextList(genTrips, image)
  }
}