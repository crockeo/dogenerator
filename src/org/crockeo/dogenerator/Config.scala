package org.crockeo.dogenerator

import java.awt.{Color, Font}
import java.io.File

object Config {
  // Render font size
  val fontSize: Float = 24f
  
  // The location of the font
  val fontLocation: String = "res/fonts/ComicSans.ttf"
    
  // The font itself
  val font: Font = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(fontSize)
  
  // Available colors
  val colors: List[Color] = List(Color.cyan, Color.yellow, Color.magenta)
  
  // Render margin for the border
  val borderMargin: Int = 4
  
  // Doge directory
  val dogeDirectory: String = "res/imgs/"
}