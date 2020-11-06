package sandbox

import cats.data.State

object StateExample extends App {

  val a = State[Int, String] { state =>
    (state, s"The state is $state")
  }
  // a: cats.data.State[Int,String] = cats.data.IndexedStateT@6ceace82

  // Get the state and the result:
  val (stateRun, resultRun) = a.run(10).value
  println(s"$stateRun : $resultRun")
  // state: Int = 10
  // result: String = The state is 10

  // Get the state, ignore the result:
  val stateRunS = a.runS(10).value
  println(stateRunS)
  // state: Int = 10

  // Get the result, ignore the state:
  val resultRunA = a.runA(10).value
  println(resultRunA)
  // result: String = The state is 10

  val step1 = State[Int, String] { num =>
    val ans = num + 1
    (ans, s"Result of step1: $ans")
  }
  // step1: cats.data.State[Int,String] = cats.data.IndexedStateT@76122894

  val step2 = State[Int, String] { num =>
    val ans = num * 2
    (ans, s"Result of step2: $ans")
  }
  // step2: cats.data.State[Int,String] = cats.data.IndexedStateT@1eaaaa5d

  val both = for {
    a <- step1
    b <- step2
  } yield (a, b)
  // both: cats.data.IndexedStateT[cats.Eval,Int,Int,(String, String)] = cats.data.IndexedStateT@47a10835

  val (stateBoth, resultBoth) = both.run(20).value
  println(s"$stateBoth : $resultBoth")

  //------------------
  val getDemo = State.get[Int]
  // getDemo: cats.data.State[Int,Int] = cats.data.IndexedStateT@6ffe574a

  println(getDemo.run(10).value)
  // res3: (Int, Int) = (10,10)

  val setDemo = State.set[Int](30)
  // setDemo: cats.data.State[Int,Unit] = cats.data.IndexedStateT@4168bec2

  println(setDemo.run(10).value)
  // res4: (Int, Unit) = (30,())

  val pureDemo = State.pure[Int, String]("Result")
  // pureDemo: cats.data.State[Int,String] = cats.data.IndexedStateT@6812d576

  println(pureDemo.run(10).value)
  // res5: (Int, String) = (10,Result)

  val inspectDemo = State.inspect[Int, String](n => s"$n!")
  // inspectDemo: cats.data.State[Int,String] = cats.data.IndexedStateT@37c08614

  println(inspectDemo.run(10).value)
  // res6: (Int, String) = (10,10!)

  val modifyDemo = State.modify[Int](_ + 1)
  // modifyDemo: cats.data.State[Int,Unit] = cats.data.IndexedStateT@4242cae6

  println(modifyDemo.run(10).value)

}
