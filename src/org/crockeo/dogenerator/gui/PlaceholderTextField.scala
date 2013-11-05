package org.crockeo.dogenerator.gui

import scala.swing.TextField

import java.awt.{RenderingHints, Graphics2D, Color}

class PlaceholderTextField extends TextField {
  private var placeholderText: String = ""
    
  def placeholder: String = placeholderText
  def placeholder_=(s: String): Unit =
    placeholderText = s
  
  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    
    if (placeholder.isEmpty || !text.isEmpty) return
    else {
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON)
      g.setColor(Color.gray)
      g.drawString(placeholder, 3, (g.getFontMetrics.getMaxAscent / 2) + (size.getHeight.toInt / 2))
    }
  }
}