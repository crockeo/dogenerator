package org.crockeo.dogenerator.gui

import javax.swing.JFileChooser

object FileChooserDialog {
  def chooseFile: Option[String] = {
    val fc = new JFileChooser
    fc.showOpenDialog(null) match {
      case JFileChooser.APPROVE_OPTION => Some(fc.getSelectedFile.getPath)
      case JFileChooser.CANCEL_OPTION  => None
      case default                     => None
    }
  }
}