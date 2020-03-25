# java11-architecture-api-layer-workshop
* references
    * https://github.com/omniti-labs/jsend
    * https://www.manning.com/books/the-design-of-web-apis
    
## preface
* goals of this workshop:
    * general overview of REST Api
    * investigate how the gateway layer in web application should look like
    * prepare classes that facilitate writing and increase readability of functional tests
* workshops are in
    * `app.gateway.workshop`
    * `app.functional.person.workshop`
    * `app.mockmvc.workshop`

## api
* types of API
    * hardware
        * the social networking mobile application uses the smartphone’s camera to take a photo (via its API)
    * a library
        * use some image library embedded in the application to apply filters to the photo
    * a remote API
        * share photo using a social network server's remote API
* API is an interface: a point where two systems meet and interact
    * it’s an abstraction of the underlying implementation
* example: UI is called called a consumer, and the backend is called a provider
* two kinds: public API and private API
    * public API: provided as a service - you only use them
    * private API: is one you build for yourself (you are your own API provider and API consumer)
    * the public/private question is not a matter of how an API is exposed, but to whom
* questions
    * Who are the users?
    * What can users do?
    * How do they do it?
    * Who can use the API
    * What they can do
    * How they do it
    * What they need to do it
    * What they get in return  
    * to identify missing goals: Where do the inputs come from?
    * to identify missing goals: How are the outputs used?
    * to identify inputs: What do they need to do it?
    * to identify outputs: What do they get in return?
    * is all this really the consumer’s business?