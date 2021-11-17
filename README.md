# codigician-api

# Getting Started

You can use docker compose to run the application.
```bash
# go to build directory
$ cd build/
# start couchbase container
$ docker compose up
# after couchbase initialized
# run the couchbase.sh to initialize bucket
$ sh couchbase.sh
```

After you complete the steps above you can goto `localhost:8091` to see couchbase ui.
```
http://localhost:8091
    Username: Administrator
    Password: password
```

You can run the application in debug mode or you can run directly.

# Question Code Testing

There is no need to have code templates. We can directly use expectation outputs and inputs. Client will write the
solution code. We run the code with some inputs and if the output is what we expected then that means the test is
passed. While we run these commands we need to have that information as a json or we will have it like an array. Also
the runtime is important for benchmark.

# Container Structure Design

![container structure](.docs/png/container-structure.png)

# Priority

1. Remote code execution
2. Question bank (Editorial) (Unit tests, benchmark tests)
3. Auth, User (Score, Progress)
4. Discussion
5. Tutorial