hw3 Feedback
============

#### Demonstrate mastery of earlier learning goals, especially the concepts of information hiding and polymorphism, software design based on informal specifications, testing, and testing best practices. (26/50)

* Information hiding (8/10)

    * You expose various information of your cryptarithm representation in its public API because you have a separate solver class. Apply the information expert heuristics here to decrease exposure.

* Compliance with specification (10/20)

    * Your solution does not solve cryptarithms.

* Testing practices (8/10)

    * __minor__ You did not test exceptions, this is important because you want to make sure that they are being thrown under the appropriate conditions.

    * __minor__ JUnit includes a @Before annotation that lets you run code that runs before your tests are run.  This lets you initialize your test class and/or create test objects in one place, which is easier and more consistent than creating separate test objects in every different test methods.

    * Tests should be split up into the smallest testable parts of a program. Try to avoid testing multiple methods in one test case or just testing a method that calls the rest.

    * __minor__ You have methods that are never called. E.g. solve()


* Java coding best practices and style (0/10)

    * Your commit messages are not very descriptive of the changes you make. The first line of a commit message should always contain a concise description of the specific changes you made, and you should commit sufficiently regularly that it's easy to describe your changes with such a concise, specific message.  In practice commit messages are often very important to track changes in a project. Please attempt more descriptive changes in the future.

    * You committed all or most of your code in a single big commit. It is good practice (and also a good backup strategy) to commit regularly at milestones while you are still developing the solution.

    * __minor__, You should have comments/documentation for your fields, even if they are private ([link](https://github.com/CMU-15-214/said/blob/master/homework/3/src/main/java/edu/cmu/cs/cs214/hw3/SolveCryptarithm.java#L13)). This is important because it allows the reader to understand the invariants in your classes. 

    * You have long, poorly documented methods. It is good practice to either comment large blocks of code from a high level, or better still, write small methods that are self-documenting.

    * Please remove commented out code or dead code, your submissions should be finalized. These include unused imports, private methods, variables, and initializations. ([link](https://github.com/CMU-15-214/said/blob/master/homework/3/src/test/java/edu/cmu/cs/cs214/hw3/SolveCryptarithmTest.java#L25))

    * __minor__ It's better to import your homework 2 code by adding it to the build path and importing it, instead of copying and pasting it into your code for homework 3.

#### Use inheritance and delegation, and design patterns effectively to achieve design flexibility and code reuse (20/30)

* Your solution does not implement generic typing but instead focuses on one type. You should be using Java’s built in ([generic type system](https://en.wikipedia.org/wiki/Generics_in_Java)) to implement generic behavior.

* Your cryptarithm solver's return value is not reusable for anything other than printing the solution. This makes your program not testable separately and is generally bad practice.

#### Discuss the relative advantages and disadvantages of alternative design choices (7/20)

* Your rationale for using the Iterator Pattern is incorrect because you misinterpreted the design of the other design patterns. The Template Method and Command Patterns do not require you to store all permutations in memory.

* You used primitive arrays as the input type for your permutation generator but did not argue about the trade-offs. (or any other limitation to generality)

* You did not provide client code for interacting with your APIs.

* __minor__, It's better to describe an API with actual interface code or UML class diagrams rather than prose.

* You did not describe an API for the Template Method design pattern, as required by the assignment. 

---

#### Total (53/100)

Late days used: 2 (3 left)

---

#### Additional Notes

Graded by: Marco A. Peyrot (mpeyrotc@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/said/blob/master/grades/hw3.md
