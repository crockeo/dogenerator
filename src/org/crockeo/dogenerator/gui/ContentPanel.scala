package org.crockeo.dogenerator.gui

import org.crockeo.dogenerator.IO

import java.awt.Dimension
import scala.swing._

object ContentPanel extends GridPanel(2, 2) {
  preferredSize = new Dimension(500, 200)
  
  hGap = 5
  vGap = 7
  
  // srcTextArea
  val srcTextArea = new TextArea("Source location", 1, 64)
  contents += srcTextArea
  
  // dstTextArea
  val dstTextArea = new TextArea("Output location", 1, 64)
  contents += dstTextArea
  
  // words
  val words       = new TextArea("Words (separated by commas)", 1, 1024)
  contents += words
  
  // proceed
  val proceed     = new Button
  proceed.action = new Action("Generate") { def apply: Unit = IO.wholeOp(srcTextArea.text, dstTextArea.text, words.text.split(",").toList) }
  contents += proceed

}