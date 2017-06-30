# DFASDL - Data Format and Semantics Description Language

[ ![Download](https://api.bintray.com/packages/wegtam/dfasdl/dfasdl-core/images/download.svg) ](https://bintray.com/wegtam/dfasdl/dfasdl-core/_latestVersion)

The DFASDL is a language based upon [XML
Schema](http://www.w3.org/XML/Schema) that can be used to describe data
formats and additionally the semantics of it.

It is used by the Tensei-Data project to describe data structures and to
derive mappings and transformation functions between different structures
automatically.

This repository contains the core module which consists of the xschema 
definition (xsd) and the official specification.

It is cross build for scala 2.11 and 2.12.

Releases are published on bintray and should be synced to jcenter. To use
the bintray repository directly just add the appropriate resolver to your
sbt configuration:

```
resolvers += "DFASDL" at "https://dl.bintray.com/wegtam/dfasdl"
```

The documentation is published using github pages and is available online
at: https://dfasdl.github.io/dfasdl-core/

