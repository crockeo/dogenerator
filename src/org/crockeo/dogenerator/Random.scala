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
    
  // Generating the path to a random doge image
  def randomImageName(bound: Int): String =
    Config.dogeDirectory + "doge" + randomInt(bound) + ".jpg"
    
  // Generating a random image
  def randomImage(bound: Int): BufferedImage =
    IO.loadImage(randomImageName(bound))
    
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
}