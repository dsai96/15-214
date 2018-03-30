hw1 grading rubric

hw1 Feedback
============

#### Successful use of Git, GitHub, and build automation tools (4/5)
  * Your commit history could be more made more useful. A useful commit history separates work into multiple commits, instead of one or two for the whole assignment. Each commit should have a concise, but descriptive name about what was changed. Committing your work incrementally protects you from data loss or network problems, which might otherwise cause you to lose work or fail to meet the homework deadlines (which are strictly enforced). For more information on writing useful commit messages, see [here](https://git-scm.com/book/ch5-2.html#Commit-Guidelines) or [here](http://chris.beams.io/posts/git-commit/)

#### Basic proficiency in Java (14/20)
  * Instead of repeatedly creating new documents, create each Document once and store it in an array to access it later. Each time a Document is created, there is an expensive network call and computation, which can be avoided by storing a single instance of the document.
  * Instead of doing time consuming operations such as calculating constant values, you should perform these only once on Object initialization and store them for faster method calls later on. Your code calculates during each call to cosine similarity a new denominator, this value should have been created at the constructor and reused.
  * Improper use of object orientation. Make variables local unless they represent object properties or universally meaningful state. (Scanner in Document as field)
  * Avoid using boxed primitives (i.e. `Integer`, `Boolean`, etc.) in your code. Use primitive types when possible, as they avoid unnecessary memory allocations on the heap. See [Effective Java, Item 49: Prefer primitive types to boxed primitives](http://goo.gl/O8t5Tf). (HighestCosSim)
  * You should use the most general static type in variable declarations (e.g. `List` instead of `ArrayList`). (Document:17)
#### Fulfilling the technical requirements of the program specification (15/15)

#### Documentation and code style (10/10)


---

#### Total (43/50)

Late days used: 0 (5 left)

---

#### Additional Notes

Graded by: Tianyu Li (tli2@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/said/blob/master/grades/hw1.md
