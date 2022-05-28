This readme is also [available in polish](./README.pl-PL.md)

# beever-sample

A simple project demonstrating usage of the Beever library.

To run a test you'll need to have a running Postgres, Redis and MongoDB - the
easiest way to set them up is to use docker (with the `test-docker.sh` shell
script we prepared).


## Beever Basics

Beever is a framework based on [Vert.X][1] designed for creating REST API
servers. It's well-suited for creating both simple and very complex
systems. Greatly simplifies and speeds up the development process. Beever is
focused on the domain of the problem rather than the logical layers. It
provides tools for comfortable unit tests. The convention over configuration
principle is applied, so there's not much to configure and many things "happen"
automatically according to the predetermined convention.

The basic element in creating a server using Beever is a Handler.  A handler is
a single class, implementing the `BeeverHandle` interface. It performs an
action that must be simple and testable (unit tests). It has to be non-blocking
by convention, so can't block the event loop for more than a couple seconds.
The execution time is not affected by other handlers or asynchronous operations
called from that handler.


### Handlers

Handlers are simple, short and **non-blocking** "methods". Each handler can be
called by "name". Input and output - JSON Handler can be exposed as REST end
point by adding annotation `@POST`, `@GET`.


#### Names of the handlers

Each handler name should end with the word `Handler`. Each REST handler uses a
special name convention: `<PREFIX><Name>Handler`, where `<PREFIX>` is
substituted for one of the following Role specifiers:

- `Public` - for end points anyone can call
- `<RoleName>` e.g. `User` or `Admin` - names the `role` of the logged user.
  More about users in the [Authorization](#authorization) section.

Example: `PublicSomeImportantCallHandler` - public (no user required) end point
with path: `/some/important/call`.


<!-- The following level 3 heading did not have appropriate (or any for that
matter) content associated with it and as such has been commented out until it
gets updated.  --> 

<!-- ### Input and Output of the handler -->

### Authorization

Beever uses a technique similar to [JWT][2] to achieve authorization/authentication.
To interact with fields of this key some annotations can be used.


[1]: https://en.wikipedia.org/wiki/Vert.x
[2]: https://en.wikipedia.org/wiki/JSON_Web_Token 
