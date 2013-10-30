package org.crockeo.dogenerator.gui

import javax.swing._
import java.awt._

object Window extends JFrame {
  private def init: Unit = {
    setTitle("dogenerator")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    
    add(Panel)
    
    pack
    setResizable(false)
    setLocationRelativeTo(null)
  }
  
  private def create =
    setVisible(true)
  
  // Creating the window
  init
  create
}