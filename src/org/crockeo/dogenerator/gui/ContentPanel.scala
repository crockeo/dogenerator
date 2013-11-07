package org.crockeo.dogenerator.gui

import org.crockeo.dogenerator.IO

import javax.swing.JOptionPane.{showMessageDialog, ERROR_MESSAGE}
import java.awt.Dimension
import scala.swing._

object ContentPanel extends GridPanel(3, 2) {
  private val actions: Map[String, Action] = Map(
    "srcDialogButton" ->
      new Action("...") {
        def apply: Unit =
          FileChooserDialog.chooseFile match {
            case Some(s) => srcTextArea.text = s
            case None    => return
          }
      },
    "dstDialogButton" ->
      new Action("...") {
        def apply: Unit =
          FileChooserDialog.chooseFile match {
            case Some(s) => dstTextArea.text = s
            case None    => return
          }
      },
    "proceed" ->
      new Action("Generate") {
        def apply: Unit =
          if (!IO.isValid(srcTextArea.text)) showMessageDialog(null, "The file path you entered is invalid.", "Invalid filepath.", ERROR_MESSAGE)
          else                               try { IO.wholeOp(srcTextArea.text, dstTextArea.text, words.text.split(",").toList) }
                                             catch {
                                               case e: IllegalArgumentException => showMessageDialog(null, "The image you've loaded is too small for all of your words.", "Image too small", ERROR_MESSAGE)
                                               case e: Exception => e.printStackTrace; System.exit(1)
                                             }
      }
  )
  
  preferredSize = new Dimension(500, 200)
  
  hGap = 5
  vGap = 7
  
  // srcTextArea
  val srcTextArea = new PlaceholderTextField
  srcTextArea.placeholder = "Source image location"
  contents += srcTextArea
  
  // dstTextArea
  val dstTextArea = new PlaceholderTextField
  dstTextArea.placeholder = "Destination image location"
  contents += dstTextArea
  
  // srcTextArea dialogButton
  val srcDialogButton = new Button("...")
  srcDialogButton.action = actions.apply("srcDialogButton")
  contents += srcDialogButton
  
  // dstTextArea dialogButton
  val dstDialogButton = new Button("...")
  dstDialogButton.action = actions.apply("dstDialogButton")
  contents += dstDialogButton
  
  // words
  val words       = new PlaceholderTextField
  words.placeholder = "Words to be drawn (split by commas)"
  contents += words
  
  // proceed
  val proceed     = new Button
  proceed.action = actions.apply("proceed")
  contents += proceed

}