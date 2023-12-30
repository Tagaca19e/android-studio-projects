package marsphotos.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
  val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {
  // Replace Main dispatcher with a test dispatcher before test begins.
  @OptIn(ExperimentalCoroutinesApi::class)
  override fun starting(description: Description) {
    Dispatchers.setMain(testDispatcher)
  }

  // Reset the main dispatcher.
  @OptIn(ExperimentalCoroutinesApi::class)
  override fun finished(description: Description) {
    Dispatchers.resetMain()
  }
}