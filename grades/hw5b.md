hw5b Feedback
============


#### Core Framework Implementation (53/70)
##### Technical Requirements
Your framework meets the technical requirements to a reasonable degree. Good job!
##### Correctly Working Framework and Testing
* Your framework GUI throws exceptions when I press "Submit and Start" without first selecting a data plugin.
* You did not write any unit tests for your framework.

##### General Design
The general design of your framework is reasonable, but we identified the following issue:
* The data plugins have direct access to parts of the framework internals that should only be accessible to the framework UI. For example, data plugins can register other plugins, and get a list of available plugins. Look at recitation 9 for one way to hide the the framework internals.

#### Sample Plugins (15/30)
* You only implemented one sample visualization plugin. You were required to implement at least two visualization plugins.
* Your framework throws a `NullPointerException` that prevents it from doing anything default CSV file and pie chart plugin.

#### Gradle run target (0/5)
* Your build fails to compile with gradle because you neither included the `.jar` files for your dependencies nor did you include Maven dependencies in your `build.gradle`.

#### High quality documentation and style (11/20)
##### Documentation
* You did not provide a `README` file explaining how to use your framework (as specified in the homework handout).

##### Style
* Some class fields do not use the most restrictive access modifier. ([link](https://github.com/CMU-15-214/Team22/blob/27f6c7ed4f35d14f7491c018ce77e6f111a50cb6/hw5b/edu/cmu/cs/cs214/hw5/framework/gui/GUIFrameworkImpl.java#L57-L58))
* Avoid using magic numbers in your code. Declare variables as `static final` constants at the top of the file. ([link](https://github.com/CMU-15-214/Team22/blob/27f6c7ed4f35d14f7491c018ce77e6f111a50cb6/hw5b/edu/cmu/cs/cs214/hw5/framework/gui/GUIFrameworkImpl.java#L189))
* `static` fields were used incorrectly. `core` should be a local variable in `main` and passed to the helper function. ([link](https://github.com/CMU-15-214/Team22/blob/27f6c7ed4f35d14f7491c018ce77e6f111a50cb6/hw5b/edu/cmu/cs/cs214/hw5/main/Main.java#L17))

#### Presentation/Demo (35/35)
##### Framework Description
Your presentation of the framework description fully meets our expectations, good job!
##### Talk Quality
The quality of your presentation fully met our expectations, good job!
##### Timing
The length of your presentation met our expectations, good job!
##### Demo
Your live demo met our expectations.

---


#### Total (114/160)


Late days used: 1 (1 left for hw5)


---


#### Additional Notes


Graded by: Tuan Anh Le (tpl@andrew.cmu.edu)


To view this file with formatting, visit the following page: https://github.com/CMU-15-214/said/blob/master/grades/hw5b.md