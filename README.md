# quarkus-test-resources project

This is a simple Quarkus project, created to demonstrate weird behaviour of the `@PathParam` annotation class
from the JBoss RESTEasy project.

It contains a single unit test class that runs a few tests and demonstrates the problem.

## Background and Problem Description

### Background

#### @PathParam

The `@PathParam` annotation is used to wire a parameter to a function in such a way that it gets its value from
the URL path of the resource called:

```java
@Path("/foo")
public class MyResource {
    @GET
    @Path("bar/{myPathParam}")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@javax.ws.rs.PathParam("myPathParam") String myPathParam) {
        return "Parameter value: " + myPathParam;
    }
}
```

In the above case, when called with `http://<fqdn>/foo/bar/fluff`, the value of the `myPathParam` method parameter would
be `fluff`.

The above use-case uses the standard `javax.ws.rs.PathParam` annotation. However, there is another implementation of
this from the JBoss RestEASY project, namely `org.jboss.resteasy.annotations.jaxrs.PathParam`.

One of the benefits of using the RestEASY version of `@PathParam` is that it allows you to skip passing the URL pattern
variable name to the annotation, given that the method variable name matches the URL pattern variable name.

In other words, the following should be equivalent to the code above:

```java
@Path("/foo")
public class MyResource {
    @GET
    @Path("bar/{myPathParam}")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@org.jboss.resteasy.annotations.jaxrs.PathParam String myPathParam) {
        return "Parameter value: " + myPathParam;
    }
}
```

#### Location of a Resource Class

I discovered the problem when I was trying to create a resource class and place it in my `src/test/` directory instead
of having it as part of the main project in `src/main/`. The reason for doing this was to create a simple resource
class that was not a part of the project that was being made in order to test a `ContainerRequestFilter` class.

In other words, having a resource class in your `src/test/` directory does have a valid use.

### The Problem

There seem to be some issues with using the RestEASY implementation of `@PathParam` and these problems have to do with: 
1) the programming language being used (Kotling vs. Java)
2) the source directory (main vs. test) that the resource 
classes are located in.

Below is a matrix summarizing the behavior of the combination of programming language used, location of class and 
which implementation of `@PathParam` is used.

<table>
<thead>
  <tr>
    <th></th>
    <th></th>
    <th colspan="2">Location of resource class</th>
  </tr>
  <tr>
    <th>Language</th>
    <th>@PathParam<br>implementation</th>
    <th>src/main</th>
    <th>src/test</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td rowspan="2">Java</td>
    <td>javax</td>
    <td bgcolor="LightGreen">Works as expected</td>
    <td bgcolor="LightYellow">resource cannot be found (404)</td>
  </tr>
  <tr>
    <td>jboss</td>
    <td bgcolor="LightCoral">@PathParam method variable value is null</td>
    <td bgcolor="LightYellow">resource cannot be found (404)</td>
  </tr>
  <tr>
    <td rowspan="2">Kotlin</td>
    <td>javax</td>
    <td bgcolor="LightGreen">Works as expected</td>
    <td bgcolor="LightGreen">Works as expected</td>
  </tr>
  <tr>
    <td>jboss</td>
    <td bgcolor="LightGreen">Works as expected</td>
    <td bgcolor="LightCoral">@PathParam method variable value is null</td>
  </tr>
</tbody>
</table>

There are two issues:
1) Java resource classes that are located in the `src/test` directory do not seem to get deployed properly when running
Quarkus tests. This is probably a Quarkus issue.
2) The JBoss implementation does not seem to work when using Java and only works in Kotlin if the resource class is
located in `src/main`.

#### Running the Unit Tests

You can run the unit tests that demonstrate the problems described above with:

```shell script
./gradlew test
```