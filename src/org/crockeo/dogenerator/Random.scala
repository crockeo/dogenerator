package org.crockeo.dogenerator

import org.crockeo.dogenerator.geom.{Rectangle, Point}

import java.awt.image.BufferedImage
import java.awt.Graphics

object Random {
  private val r: java.util.Random = 
    new java.util.Random(System.currentTimeMillis)
  
  // Generating a random int
  def randomInt(bound: Int): Int =
    r.nextInt(bound)
    
  // Generating a random image
  def randomImage(bound: Int): BufferedImage = {
    def randomImageName(bound: Int): String =
      Config.dogeDirectory + "doge" + randomInt(bound) + ".jpg"
    IO.loadImage(randomImageName(bound))
  }
    
  // Generating a random point
  def randomPoint(minbound: Point, maxbound: Point): Point =
    new Point(randomInt(maxbound.x - 1), randomInt(maxbound.y - 1)) + minbound
  
  // Generating a random point within the bound including the size of the sentence
  def randomPoint(s: String, bound: Point, g: Graphics): (Point, Rectangle) = {
    val fm = g.getFontMetrics(Config.font)
    val size = new Point(fm.stringWidth(s), fm.getHeight)
    
    val rp = randomPoint(new Point(Config.borderMargin, (size.y / 2) + Config.borderMargin),
                         new Point(bound.x - size.x - Config.borderMargin, bound.y - (size.y / 2) - Config.borderMargin))
                         
    (rp, new Rectangle(rp, size))
  }
  
  // Generating a list of random points
  def randomPoints(ss: List[String], bound: Point, g: Graphics): List[(Point, Rectangle)] =
    ss.map(s => randomPoint(s, bound, g))
    
  // Generating a list of valid points (not overlapping)
  def randomValidPoints(ss: List[String], bound: Point, g: Graphics): List[(Point, Rectangle)] = {
    def genUntilCollide(pairs: List[(Point, Rectangle)]): List[(Point, Rectangle)] = {
      def anyCollide: Boolean =
        pairs.flatMap(a => pairs.map(b => a._2.intersects(b._2))).reduceLeft((a, b) => a && b)
      
      if (anyCollide) genUntilCollide(randomPoints(ss, bound, g))
      else            pairs
    }
    
    genUntilCollide(genUntilCollide(randomPoints(ss, bound, g)))
  }
}