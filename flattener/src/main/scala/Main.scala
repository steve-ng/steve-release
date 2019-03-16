object Main extends LoggerSupport {
  def main(args: Array[String]): Unit = {
    val x = 10
    logger.info(s"at main with $x")
  }
}

