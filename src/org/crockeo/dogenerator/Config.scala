package org.crockeo.dogenerator

import java.awt.{Color, Font}
import java.io.File

object Config {
  // Render font size
  val fontSize: Float = 32f
  
  // The location of the font
  val fontLocation: String = "res/fonts/ComicSans.ttf"
    
  // The font itself
  val font: Font = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(fontSize)
  
  // Available colors
  val colors: List[Color] = List(
      Color.cyan,              // Cyan
      Color.yellow,            // Yellow
      Color.magenta,           // Magenta
      new Color(37, 34, 220),  // Blue
      new Color(191, 89, 25),  // Orange
      new Color(215, 18, 108)) // Pink
  
  // Render margin for the border
  val borderMargin: Int = 16
  
  // Doge directory
  val dogeDirectory: String = "res/imgs/"
}