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
        case `srcImageLoc`  => return
        case `dstImageLoc`  => return
        case `words`        => return
        case `createButton` => IO.saveImage(ImageWriter.write(words.getText.split(",").toList, IO.loadImage(srcImageLoc.getText)), dstImageLoc.getText)
      }
    }
  }
  
  override def getPreferredSize: Dimension =
    new Dimension(300, 200)

  private val srcImageLoc : JTextArea = new JTextArea("Source image")
  private val dstImageLoc : JTextArea = new JTextArea("Destination image.")
  private val words       : JTextArea = new JTextArea("Words (split by commas)")
  private val createButton: JButton   = new JButton("Create")
  
  def init: Unit = {
    setLayout(new GridBagLayout)
    val c: GridBagConstraints = new GridBagConstraints

    c.fill = GridBagConstraints.HORIZONTAL

    // srcImageLoc
    c.gridx      = 0
    c.gridy      = 0
    c.weightx    = 0.5
    c.weighty    = 0.3
    c.gridwidth  = 1
    c.gridheight = 1
    add(srcImageLoc, c)
    
    // dstImageLoc
    c.gridx      = 1
    c.gridy      = 0
    c.weightx    = 0.5
    c.weighty    = 0.3
    c.gridwidth  = 1
    c.gridheight = 1
    add(dstImageLoc, c)
    
    // words
    c.gridx      = 0
    c.gridy      = 1
    c.weightx    = 1
    c.weighty    = 0.3
    c.gridwidth  = 2
    c.gridheight = 1
    add(words, c)
    
    // createButton
    c.gridx      = 0
    c.gridy      = 2
    c.weightx    = 1
    c.weighty    = 0.3
    c.gridwidth  = 2
    c.gridheight = 1
    createButton.addActionListener(PanelListener)
    add(createButton, c)
  }

  init
}