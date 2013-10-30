package org.crockeo.dogenerator

import org.crockeo.dogenerator.geom.Point

import java.awt.Color

object Main extends App {
  val input = "res/imgs/doge2.jpg"
  val output = "res/imgs/output.png"
    
  val img = IO.loadImage(input)
  IO.saveImage(ImageWriter.write(List("wow", "such good", "very program", "indeed", "doge"), img), output)
}