# jsontransform


This Application is expose a REST API to retrieve ML features from a given JSON object using a supplied feature configuration.

## Running application locally


### Prerequisites

- #### Java
    - Version `1.8.x`

- #### Gradle
    - Version > 4.x
    - Gradle install instructions: `brew install gradle`


### Local Build
-   `gradle clean build`
-   

### Testing

- This application exposes a `POST` endpoint `/api/transform`, which accepts a Feature configuration and input json and returns the json matching the jstl expression.
- Uses the JSON processing library which allows performing JSON transformations using a specific syntax - https://github.com/schibsted/jslt. 
<img width="1206" alt="image" src="https://user-images.githubusercontent.com/107821516/174653063-a73095d0-0f7b-49a9-91f9-51603a63f7e4.png">
