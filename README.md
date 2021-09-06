# Element Games

## Description
An E-commerce website for video games.

## Live URL
An instance of the application is deployed at https://shop.mananw.com/

## Dockerhub Repo
the images are published on commit at [mananwalia959/element-games](https://hub.docker.com/r/mananwalia959/element-games)


## Pipelines
|Name| Pipeline|
| --- | --- |
|Build and Deploy |[![Pipeline](https://github.com/mananwalia959/element-games/actions/workflows/pipeline.yml/badge.svg)](https://github.com/mananwalia959/element-games/actions/workflows/pipeline.yml)|

## Required Env variables
|ENV VARIABLE NAME | WHERE TO GET IT | PURPOSE |
|---| --- | --- |
| JWT_SECRET | a random string (preferably long and diificult to guess) | For Signing and verifying our jwt tokens|

