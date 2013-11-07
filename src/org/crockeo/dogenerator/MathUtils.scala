package org.crockeo.dogenerator

object MathUtils {
  def cleanSqrt(n: Int): Int =
    Math.sqrt(n.toDouble).toInt
}