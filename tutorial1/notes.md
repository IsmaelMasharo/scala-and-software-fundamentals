## **Introduction to build tools**

* scripting languages: uses statements, enough to run.
* no scripting language: run with **entry points**: def main (main name is just a convention since you can specify the entry point when compiling the file)

#### Java Steps for compile and create jar (java archive)

1. Create folder:  name it Main (can be any name) **capitalized**
2. Create Main file: IT NEEDS TO HAVE THE SAME NAME THAN THE FOLDER, jdk requires that.
3. Add some code:

```java
public class Main {
    /*bellow is the entry point*/
    public static void main(String[] args) {
        System.out.println("hello world!");
    }
}
```

4. Compile the file: **javac Main.java** : creates a compiled class **Main.class**
5. Run the program: **java Main** : uses the .class file
6. See what's inside the .class file: cat Main.class : is not human readable.
7. Modify Main.java: ex, take arguments to print

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(java.util.Arrays.toString(args));
    }
}
```

8. Compile the file .java again: in order to see the changes
9. Execute the program again: java Main args to print
10. Create a **jar**: **jar cfe app.jar Main Main.class** : cfe stands for *create file entrypoint* - app is the name you name it - specify the Main entry point
11. Run the jar: **java -jar app.jar args to pass**



#### Scala steps for compile file

1. Create folder: can have any name, no requirements. Name **Program**
2. Create file: no requirements: Name **main.scala**
3. Add some code

```scala
object Program {
    def main(args: Array[String]): Unit = {
        println(args.mkString("[", ", ", "]"))
    }
}
```

4. Compile the file: **scalac main.Scala** : creates two files, in order to be compatible with java: **Program.class** , **Program$class**. Takes the name of the object/class
5. Run it: **scala Program hello world**

#### Sbt steps

1. Create folder
2. Run sbt: creates a bunch of stuff : it create two folder: project and target
2. 1. Whar is target: sbt will use this file to allocate all the compiled files and all the stuf it needs 

3. Inside project folder create *build.properties* : this is for specifying the sbt version
4. Run sbt again: to upadte the version
5. Create a file: name it SbtProgram and copy all code from main.scala
6. Compile sbt: enter sbt shell and type *compile* : it will create the compile version of the projet inside *target/scala{version}* folder
7. Run the file: inside the sbt shell type **run hello world**. It runs directly without need to create a jar. If somethings gets modify in the file sbt handles it and the next time you run the file it compiles again behind the scenes
8. Add tilda: **~run** : waits, if update the file it runs again

* sbt clean: removes the files in target folder

* For a fresh start: cd, cd .sbt, find . -name target -exec rm -r "{}" \;

#### Sbt in a conventional scala structure folder

1. create the layout **src/main/scala**
2. create a file inside
3. add the same code:
```scala
object Program1 {
    def main(args: Array[String]): Unit = {
        println("first")
    }
}

object Program2 {
    def main(args: Array[String]): Unit = {
        println("second")
    }
}
```

4. Create a file configuration for sbt: **build.sbt** : what is inside should be separated with one blank line per configuration. Include the following. The last line cleans the consolo when tilda is set and a change is made in a file.
```scala
version := "1.3.6"

scalaVersion := "2.12.10"

triggeredMessage in ThisBuild := Watched.clearWhenTriggered
```

5. Run sbt from scratch: it will generate project and target and so on.

6. Run *compile* from within sbt shell: it will generate the .class files.

7. Execute *run*: it will ask wich of the two main classes to execute.

