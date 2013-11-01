package org.crockeo.dogenerator

import org.crockeo.dogenerator.gui.Window
import org.crockeo.dogenerator.geom.Point

import java.awt.Color

object Main extends App {
  val test = true
  val newGenerator = false
  
  if (!test) Window
  else {
    val sourceImage = "res/imgs/doge3.jpg"
    val destinImage = "res/imgs/output.png"
    val words: List[String] = List(
      "wow"           ,
      "such test"     ,
      "very antialias",
      "new color"     ,
      "much orange"   ,
      "very color"
    )
    
    IO.saveImage(ImageWriter.write(words, IO.loadImage(sourceImage)), destinImage)
  }
}