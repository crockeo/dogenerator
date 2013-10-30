package org.crockeo.dogenerator.gui

import org.crockeo.dogenerator._

import java.awt.image.BufferedImage
import java.awt.event._
import java.awt._
import javax.swing._

object Panel extends JPanel {
  private object PanelListener extends ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      e.getSource match {
        case `srcImageLoc`  => ???
        case `dstImageLoc`  => ???
        case `words`        => ???
        case `createButton` => IO.saveImage(ImageWriter.write(words.getText.split(",").toList, IO.loadImage(srcImageLoc.getText)), dstImageLoc.getText)
      }
    }
  }
  
  private val srcImageLoc : JTextArea = new JTextArea("Source image")
  private val dstImageLoc : JTextArea = new JTextArea("Destination image.")
  private val words       : JTextArea = new JTextArea("Words (split by commas)")
  private val createButton: JButton   = new JButton("Create")
  
  def init: Unit = {
    srcImageLoc.setSize(50, 15)
    add(srcImageLoc)
    
    dstImageLoc.setMinimumSize(new Dimension(50, 15))
    add(dstImageLoc)
    
    add(words)
    
    createButton.addActionListener(PanelListener)
    add(createButton)
  }
  
  def create: Unit =
    setVisible(true)
  
  init
  create
}