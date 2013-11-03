package org.crockeo.dogenerator.gui

import scala.swing._

object MainWindow extends MainFrame {
  title = "dogenerator"
    
  contents = ContentPanel
  pack
  resizable = false
  
  visible = true
}