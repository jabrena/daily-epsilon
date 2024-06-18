# Daily_Epsilon

In this folder, I will try to solve Math problems from:

- https://twitter.com/Daily_Epsilon

## Gitpod

[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/jabrena/daily-epsilon)

## How to run in local?

```bash
sdk env install

./mvnw clean verify
./mvnw compile exec:java -Dexec.mainClass="edu.jab.math.Problem20220110"

./mvnw prettier:write

./mvnw versions:display-property-updates
./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw dependency:tree
```

## Problems

- [2021](./docs/2021.md)
- [2022](./docs/2022.md)
