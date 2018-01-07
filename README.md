# Simulant Traffic Generator for Lobstrosity

This is a traffic generator that creates "realistic" load for a
lobste.rs instance. It's part of the
[Monolith to Microservices](https://n6consulting.com/workshop/monolith-to-microservices/)
workshop.

This depends on Datomic Free. With a minor change to project.clj, it
will work with Datomic Pro as well.

# One-time Setup

Before we can get started, we need to set up some fake item and
category data. In a real implementation, you would get this from the
system under test. For our purposes, a big pile of fake data will
suffice.

1. Populate a corpus of links, users, and comments under `corpus`.

# Walkthrough

1. Start Datomic with          `scripts/start-transactor.sh`
1. Install the schema          `scripts/run install-schema`
1. Make a model                `scripts/run make-model`
1. Make a different model      `scripts/run make-model -m "Another model"`
1. See the models              `scripts/run list-models`
1. View the model's parameters `scripts/run list-model-parameters`
1. Reduce the abandon rate     `scripts/run set-model-parameter abandon-rate 8`
1. View the new parameters     `scripts/run list-model-parameters`
1. Make an activity stream     `scripts/run make-activity`
1. View the activity stream    `scripts/run list-activities`

# Introducing the Parts

## Basic schema

Some one-time setup is needed. `simtest.database` has a simple
migrations framework to install the schema. The schema definitions are
in `resources/simulant/schema.edn` and `resources/simtest.edn`

## The Model

The model is expressed in `simtest.model`. There are three parts to the
model itself:

1. State transitions, represented as `shopper-transitions`. This
   function returns a sparse Markov matrix that we use to create a
   random walk through the commerce system.
1. Control parameters that adjust various probabilities. These are
   read from a model entity in the database. They change the Markov
   transition probabilities. They also affect how heavily traffic will
   focus on "hot" items and categories.
1. Category and item data. In a full system, this would either be
   extracted from the target system or pushed into it at simulation
   start. We've got a data generator that spoofs up a pile of
   identifiers. You should have run this as part of the one-time
   setup. If not, please go back and do that now.

## Generator

The generator is cleverly named `simtest.generator`. It divides
roughly into three sections.

1. Creating an activity stream for a single agent, based on the model.
1. Building a population of agents and their activity streams.
1. The command line interface functions and their helpers.

## Execution/Capture

Not implemented yet.

## Validation

Not implemented yet.


## License

Copyright © 2018, N6 Consulting LLC

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
