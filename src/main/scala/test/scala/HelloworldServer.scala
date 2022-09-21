package test.scala

import io.grpc.Server
import io.grpc.netty.NettyServerBuilder
import test.hello._

import java.util.logging.Logger
import scala.concurrent.ExecutionContext

object App {
  val logger: Logger = Logger.getLogger(classOf[App].getName)
  val port = 50051

  def main(args: Array[String]): Unit = {
    val server = new HelloworldServer(ExecutionContext.global)
    server.start
    server.blockUntilShutdown
  }
}
class HelloworldServer(executionContext: ExecutionContext) { self =>
  private[this] var server: Server = null

  def start(): Unit = {
    server = NettyServerBuilder
      .forPort(App.port)
      .addService(GreeterGrpc.bindService(new HelloworldService, executionContext))
      .build()
      .start()
    App.logger.info("Starting server on port: " + App.port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }
  def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }
}
