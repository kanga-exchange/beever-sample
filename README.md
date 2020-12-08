# beever-sample

Simple project showing how to use beever library.

## Beever Basics

### Handlers

Handlers are simple, short and **not blocking** "methods". Each handler can be called by "name". Input and output - JSON Handler can be exposed as REST end point by adding annotation `@POST`, `@GET`.

#### Names

Each handler name should end with word `Handler`. Each REST handlers use special name convention:
`PREFIX` `Name` `Handler`

PREFIX determines required Role to call this end point:

- `Public` - everyone can call
- `RoleName` - example: `User` or `Admin` - it names the `role` of the logged user. More about users in dedicated section.

Example:
`PublicSomeImportantCallHandler` - public (no user required) end point with path: `/some/important/call`
