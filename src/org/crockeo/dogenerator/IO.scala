package org.crockeo.dogenerator

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

object IO {
  // Loading a file
  def loadImage(fp: String): BufferedImage =
    ImageIO.read(new File(fp))
    
  // Writing a file
  def saveImage(bi: BufferedImage, fp: String): Unit =
    ImageIO.write(bi, "png", new File(fp))

  // Performing the whole operation
  def wholeOp(srcFile: String, dstFile: String, words: List[String]): Unit =
    saveImage(ImageWriter.write(words, loadImage(srcFile)), dstFile)
    
  // Checking if a file path is valid
  def isValid(fp: String): Boolean =
    new File(fp).exists
}