https://www.udemy.com/course/react-redux/

# Section 1: Let's Dive In!
* What is App Function?
  * App function is a react component that returns jsx.
  * JSX is javascript xml. It directs reacts to show dom elements or react components. react components are in /component directory.
    ```js
    /* app.js */
    export default function App() {
      const [language, setLanguage] = useState("ru");
      const [text, setText] = useState("");
  
      return (
        <div>                                                               /* <-- DOM Element */
          <Field label="Enter English" onChange={setText} value={text} />   /* <-- React Component */
          <Languages language={language} onLanguageChange={setLanguage} />
          <hr />
          <Translate text={text} language={language} />
        </div>
      );
    }
    ```
  * What is State in React? (useState)
    * State is a data system that react keeps track of.
* How does it render on the browser?
  * Order: "index.html -> bundle.js -> index.js -> app.js"
  * Browser first loads public/index.html
  * Browser then makes another requests to bundle.js.
  * bundle.js bundles index.js, app.js and react.js, which gets loaded on the browser.
  * among three index.js is loaded first.
    ```js
    /* index.js */
    
    import React from "react";
    import ReactDOM from "react-dom";
    import App from "./App";

    ReactDOM.render(<App />, document.getElementById("root"));
    /* Call 'App' Component from app.js and render it into 'root' DOM element */
    ```
  * What is React & ReactDOM?
    * React libraray is 'reconciler' that takes care of how component works.
    * ReactDOM library is 'renderer' that renders into HTML DOM elements.
    ```
    import React from "react";
    import ReactDOM from "react-dom";
    ```
* Creating a React Project
  * Install node.js and Start a react project 'myapp'
    ```
    https://nodejs.org/en/download/
    node -v
    
    npm install -g create-react-app
    create-react-app {project-name}
    ```
  * what is babel?
    * this resolves conflicts that can come from different java script version.
    * create-react-app by default includes babel.
  * how does 'create-react-app' looks like
    ```
    {project}
        /nodemodules      <-- project dependencies file
        /public           <-- stores static file (html, images and music files)
        /src              <-- source codes
        package.json      <-- project package dependencies
        package-lock.json <-- project package dependencies (tree of full & exact version)
    ```
* Starting a React Project
  ```
  npm start    <-- this launches react application (npm = node package manager for node.js apps)
  ```
* Import in index.js
  ```js
  // Import Syntax of ES2015
  import React from 'react'          /* import what's inside of nodemodules/react */
  import ReactDOM from 'react-dom';  /* import what's inside of nodemodules/react-dom */

  // Import Syntax of CommonJS
  const React = require('react');
  ```
* App function in index.js
  * 'funtion()' is same as '() => '. This is arrow function in ES2015 arrow function.
  ```js
  // Create react components
  const App = () => {
   return <div>Hi There!</div>
  }

  ReactDOM.render(
   <App />,                         /* App component that returns jsx */
   document.querySelector('#root')  /* get a DOM element with id 'root' */
  )
  ```
 
# Section 2: Building Content with JSX
* Babel
  * babel website offers online sandbox: babeljs.io
  * if you put App component in the sandbox babel sandbox shows how it internally changes it into javascript call
    ```js
    const App = () => {
     return <div>Hi There!</div>
    }
    ```
  * This is how App component in jsx is translated into javascript
    ```js
    const App = () => {
      return React.createElement("div", null, "Hi There!");
    };
    ```
  * In short, we write into 'jsx' for simplicity. This gets rendered into javascript by babel, which eventually creates DOM element.
  * Then why use 'jsx' instead of making 'React.creaateElement(..)' call by my self
    * => It is not mandatory, but it simplifies our code.
* JSX Syntax: Inline Styling
  ```
  "DoubleCurly-CamelCase-Quote-Comma"
  <4 RULES>
  1. 'double curly' the double quote.
  2. 'camel case' the hyphen in the property name.
  3. 'single(or double) quote' the property value. (single tick the property value if you are inject a reference into the property string value)
  4. 'comma or remove' the semicolon
  ```
  * HTML
    ```html
    <div style="background-color: blue; color: white;"></div>
    ```
  * JSX
    ```jsx
    <div style={{backgroundColor: 'blue', color: 'white'}}></div>
    ```
  * single quote or double quote?
    * javascript doesn't differentiate ' and ".
    * By convention in the community
      * Single quote the non-jsx property.
      * Double quote the jsx property. (I think it is better to double quote everything because some js syntax requires double quote anyway)
        ```js
        const App = () => {
         return (
          <div>
           <label id="label" for="name">
            Enter Name:
           </label>
           <input id="name" type="text" />
           <button style={{backgroundColor: 'blue', color: 'white'}}>
            Submit
           </button>
          </div>
         )
        }
        ```
* JSX Syntax: Class naming
  ```
  <1 RULE>
  put 'className' instead of 'class'. (we don't want to confuse javascript. javascript class vs html class?)
  ```
  * HTML
    ```html
    <div class="big-reminder"></div>
    ```
  * JSX
    ```jsx
    <div className="big-reminder"></div>
    ```
  * Likewise JSX wants to avoid confusing keywords. For example 'for' can be placed in <label> element while it also means javascript 'for' loop. In this case   'HtmlFor' is advised.
    * Use dev tools to monitor these errors.
    ```
    <label for="big-reminder"></div>
    <label htmlFor="big-reminder"></div>
    ```
    
* JSX Syntax: JSX can reference the javascript variables with {} (single curly braces)
  ```js
  // Create react components
  const App = () => {
   const buttonText = 'Click me';  /* <== js variable defined */

   return (
    <div>
     <label className="label" for="name">
      Enter Name:
     </label>
     <input id="name" type="text" />
     <button style={{backgroundColor: 'blue', color: 'white'}}>
      {buttonText}  /* <== js variable referenced */
     </button>
    </div>
   )
  }
  ```
  * JSX can refer to 
    * javascript string, integer, integer and array
      ```js
      const someVal = 'Click'
      const someVal = 123123
      const someVal = true
      const someVal = [123,123] /* this gets concatenated to 123123 */

      return (
       <button style={{backgroundColor: 'blue', color: 'white'}}>
        {someVal}
       </button>   
      )
      ```
    * javascript function
      ```js
      const someFunc = () = > { return 'Click'}

      return (
       <button style={{backgroundColor: 'blue', color: 'white'}}>
        {someFunc()}
       </button>   
      )
      ```
  * JSX can not refer to 
    * javascript object itself. (it can refer to a compatiable property in javascript object)
      ```js
      /* errors out */
      const someVal = { text: 'Click Me', number: 3123 }
      
      return (
       <button style={{backgroundColor: 'blue', color: 'white'}}>
        {someVal}
       </button>   
      )
      ```
      ```js
      /* works */
      const someVal = { text: 'Click Me', number: 3123 }
      
      return (
       <button style={{backgroundColor: 'blue', color: 'white'}}>
        {someVal.text}
       </button>   
      )
      ```
# Section 3: Communicating with Props
* Let's use semantic-ui (get url from https://cdnjs.com/libraries/semantic-ui) and include it in index.html
  ```html
  /* index.html */
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
  ```
* Let's install faker js for easy image import. When you do npm command you have to be in project level. npm install adds the package to your project. (not entire mac). After you do npm install, you will see that package in package.json and package-lock.json.
  ```
  npm install --save faker
  ```
* When you find a jsx code that is duplicated or complicated, it is a good sign for creating a seperate component.
  * create src/ComponentDetail.js and export it(component file is ususally capitalized)
    ```js
    import React from 'react';

    const CommentDetail = props => {
     return (
      <div className="comment">
       <a href="/" className="avatar">
        {/*
         src={faker.image.avatar()} 
         if faker doesn't work use src="https://source.unsplash.com/random"
        */}
        <img alt="avatar" src={props.img} />
       </a>
       <div className="content">
        <a href="/" className="author">
         {props.author}
        </a>
        <div className="metadata">
         <span className="date">
          {props.timeAgo}
         </span>
        </div>
        <div className="text">
         {props.text}
        </div>
       </div>
      </div>
     )
    }

    export default CommentDetail; // you need export statement so that you can import this component somewhere else
    ```
    ```js
    /* index.js */
    import ApprovalCard from './ApprovalCard';
    ```
  * Alternatively you could have defined this component in index.js as well.
  * You can export more than 1 components within a js file.
* Props: React system for passing property(=data) from Parent Component to Child Component
  ```js
  /* index.js */
  App = () => {
    ...
    <CommentDetail author="SAM" date="Dec.1" />  /* you are passing author, date to ComponentDetail using Props system. */
    <CommentDetail author="PAM" date="Dec.4" />
    ...
  }
  ```
  ```js
  /* ComponentDetail.js */
  const CommentDetail = props => {  /* you have to pass props to use Props system. */
    ...
    {props.author}
    {props.date}
    ...
  }
  ```
* Passing component using Props. Just nest it.
  ```js
  /* index.js*/
  <ApprovalCard>
   <ComponentDetail />
  </ApprovalCard>
  ```
  ```js
  /* ApprovalCard.js*/
  ...
   {props.children}  /* just like value properties, component properties are passed into 'props' object. specifically 'props.children' */
  ```
# Section 4: Structuring Apps with Class-Based Components
* How React used to be
  ```
  Funtionial Components: produces jsx (no access to LifeCycle and State System)
  Class Components: produces jsx with access to LifeCycle and State System.
  ```
* How React is now
  ```
  Function Components
   - produces jsx
   - use Hooks Systems to run code at specific point of time
   - use Hooks Systems to get access to State System.
  Class Components: produces jsx with access to LifeCycle and State System.
  ```
* Now that React Function Components are similar to Class Components, which one should we learn and use?
  * need to learn both because of legacy codes.
  * many companies have project that uses both.
* Geolocation
  * https://developer.mozilla.org/en-US/docs/Web/API/Geolocation_API
    ```
    window.navigator.geolocation.getCurrentPosition()
    ```
* Implementation with Functional Component
  ```js
  /* index.js */
  ...
  
  const App = () => {

   window.navigator.geolocation.getCurrentPosition(
    (position) => console.log(position), /* successful call back */
    (err) => console.log(err) /* failed call back */
   )

   return (
    <div>
     Latitude:
    </div>
   );
   
   ...
  ```
  * Now you want to put latitude in the jsx that App returns. However, it is tricky to wait for geolocation call before you render the UI.
* Implementation with Class Component
  * Must be javascript class
  * Must Extend React.Component
  * Must define a 'render' method that returns some JSX.
  ```js
  /* index.js */
  ...
  
  class App extends React.Component {
   render () {
    return (
      <div>
      Latitude:
     </div>
    );
   }
  }
  
  ...
  ```
# Section 5: State in React Components
* How React State system work?
  ```
  1. Only usable with React Class Component (or React Functional Component with Hook)
  2. State is a js object
  3. updtaing state triggers Class Component to rerender itself.
  4. State must be initialized when Class Component is created
  5. State must be updated with 'setState'
  ```
* State initialization
  * initialize in React class component constructor
  * don't forget to call 'super(prop)'
* State & setState
  * always use this.setState({key: value})
  * never do 'thi s.state = value' when updating the state
    * only exception to 'this.state = value' is when initializing the state
  ```js
  import React from 'react';
  import ReactDOM from 'react-dom';

  class App extends React.Component {

    // constructor is automatically called with props
    constructor(prop) {
      console.log("const")
      super(prop);
      this.state = {
        latitude : null,
        errorMsg : null
      };

      window.navigator.geolocation.getCurrentPosition(
        position => {
            this.setState({
              latitude: position.coords.latitude
            })
        },
        err => {
          this.setState({
            errorMsg: err.message
          })
        }
      )
    }

    render () {
      console.log("render")

      const errorBar = this.state.errorMsg ? "Error: " + this.state.errorMsg : null;

      return (
        <div>
          Latitude: {this.state.latitude}
          <br />
          {errorBar}
        </div>
      );
    }
  }

  ReactDOM.render(
    <App />,
    document.querySelector('#root')
  )
  ```
# Section 6: Understanding Lifecycle Methods
* What is LifeCycle Methods?
  * This is a method that calls at certain point of time with respect to life cycle of a component
  ```
  "constructed -> redered -> mounted -> updated -> unmounted"
  ```
  ```
                           <use case>
  constructor()           'good for onetime setup'
  ↓
  reder()                 'return jsx. don't do anything else'
  ↓
  componentDidMount()     'good for data loading'
  ↓
  componentDidUpdate()    'good for additional data loading upon state/props change'
  ↓
  componentWillUnmount()  'good for clean up; especially non-react stuff'
  
  * There are other lifecycle methods as well, but they are rarely used for specific cases. Ignore them for now.
  ```
* Use ComponentDidMount for data loading
  ```js
    componentDidMount() {
    window.navigator.geolocation.getCurrentPosition(
      position => {
          this.setState({
            latitude: position.coords.latitude
          })
      },
      err => {
        this.setState({
          errorMsg: err.message
        })
      }
    )
  }
  ```
* There are two ways of initializing state
  * Inside the constructor
    ```js
    class App extends React.Component {
      // constructor
      constructor(prop) {
        super(prop);
        // state init
        this.state = {
          latitude : null,
          errorMsg : null
        };
      }
    }
    ```
  * Outside the constructor
    ```js
    class App extends React.Component {
      // constructor
      constructor(prop) {
        super(prop);
      }

      // state initalization
      state = {
        latitude : null,
        errorMsg : null
      };
    }
    ```
    * In this case constructor is not needed if there is no special logic in it because babel takes care of it.
* Do I need a constructor?
  * If you try in babel, you will notice that 'state initalization outside of a constructor lets babel to create a constructor for you like this
    ```js
    class App extends React.Component {
      // state initalization
      state = {
        latitude : null,
        errorMsg : null
      };
    }
    ```
    ```js
    /* babel try out with state-init outside the constructor */
    
    class App extends React.Component {
      constructor(...args) {
        super(...args);

        _defineProperty(this, "state", {
          latitude: null,
          errorMsg: null
        });
      }

    }
    ```
* What is default props?
  * you can define a 'defaultProps' at component level. This is used when props is not passed from parent component.
  ```js
  import React from 'react';

  const Spinner = (props) => {
      return (
        <div className="ui active dimmer">
          <div className="ui big text loader">{props.message}</div>
        </div>
      ) 
  }

  // default props
  Spinner.defaultProps = {
    message: "Default Props: Loading..."
  };

  export default Spinner;
  ```
* index.js
  ```js
  import React from 'react';
  import ReactDOM from 'react-dom';
  import SeasonDisplay from './SeasonDisplay';
  import Spinner from './Spinner';

  import "semantic-ui-css/semantic.min.css";

  class App extends React.Component {
    // state initalization
    state = {
      latitude : null,
      errorMsg : null
    };

    componentDidMount() {
      window.navigator.geolocation.getCurrentPosition(
        position => {
            this.setState({
              latitude: position.coords.latitude
            })
        },
        err => {
          this.setState({
            errorMsg: err.message
          })
        }
      )
    }

    /*
    inside the React Class, defining a function is a little different
    you don't define like a vanila javascript. you follow React rules. 

    methodName(param1, param2) {
      ///
    }
    */
    renderContent() {
      if (this.state.latitude) {
        return <SeasonDisplay latitude = {this.state.latitude} />

      } else if (this.state.errorMsg) {
        return <div>Error: {this.state.errorMsg}</div>

      } else {
        return <Spinner message="Please Accept the location request" />

      }
    }

    render() {
      console.log("render");

      return (
        <div className="index">
          {this.renderContent()}
        </div>
      )

    }
  }

  ReactDOM.render(
    <App />,
    document.querySelector('#root')
  )
  ```
* ScreenDisplay.js
  ```js
  import './SeasonDisplay.css';
  import React from 'react';


  const seasonConfig = {
   summer : {
    seasonMsg: "Let's hit the beach",
    iconName: "sun"
   },
   winter : {
    seasonMsg: "Let's build a snowman",
    iconName: "snowflake"
   },
  }

  const getSeason = (lat, month) => {
   //  Northern hemisphere == positive latitude
   const isNorthernHemisPhere = 0 < lat;

   if (2 < month && month < 9) {
    // Mar ~ Aug: Northern hemisphere is summer
    return isNorthernHemisPhere ? 'summer' : 'winter';
   } else {
    // Sep ~ Feb: Norther hemisphere is winter
    return isNorthernHemisPhere ? 'winter' : 'summer';
   }
  }

  const SeasonDisplay = (props) => {
   const season = getSeason(props.latitude, new Date().getMonth());
   console.log(props.latitude);
   console.log(season);

   const {seasonMsg, iconName} = seasonConfig[season];               /* js: how to assign many variable from object */

   return (
    <div className={`season-display ${season}`}>              {/* good practice: highest container's className and component's name match */}
     <i className={`massive ${iconName} icon icon-left`} />  {/* backtick and dollar allows you to inject string variable inside the string */}
     <h1>{seasonMsg}</h1>
     <i className={`massive ${iconName} icon icon-right`} />
    </div>
   );
  }

  export default SeasonDisplay;
  ```
# Section 7: Handling User Input with Forms and Events
* initial components are not necessarily have to be defined in index.js
  ```js
  import React from 'react';
  import ReactDOM from 'react-dom';

  import App from './components/App';

  ReactDOM.render(
    <App />,
    Document.querySelector('#root')
  );
  ```
  * what's essential in index.js is 'ReactDOM.render()'
  * index.js is for 'initial rendering'
* Functional Component vs Class Component (Syntax)
  * Functional Component: Define a javascript function that returns jsx.
  ```js
  import React from 'react';

  const App = () => {
    return (
      <div>App</div>
    )
  }

  export default SearchBar;
  ```
* Class Component: Define a javascript class that
  * 1) extends React.Component
  * 2) implements render() that returns jsx.
  * 3) initalizes State. (state init is optional)
  ```js
  class App extends React.Component {
    // state initalization
    state = {
      key1 : val1,
      key2 : val2
    };

    componentDidMount() {
     //
    }
    
    render() {
     // 
    }
  }
  ```
* How to use Event callback method
  * don't put () on event callback method. you are just passing the reference. If you put (). Then you are passing the method return of onInputChange method.
  ```js
  class A extends React.Component {
    onInputChange(event) {
     //
    }

    render() {
      ...
      <input type="text" onChange={this.onInputChange}/>
      ...
    }
  }
  ```
  * Alternatively you can use arrow function for onEvent method
  ```js
  ...
  <input type="text" onChange={(e)=>{console.log(e);}/>
  ...
  ```
* Controlled vs Uncontrolled?
  * Controlled Element: Store data in the react (preferable because you want to manage data with React)
    ```js
    <input value={this.state.term} onChange{ (event) => {this.setState{term: event.target.value}} } />
    ```
  * Uncontrolled Element: Store data in the DOM
    ```js
    <input value="my value" />
    ```
  
* what is 'this' in javascript?
  * 'this' references the 'leftside of period'
    ```js
    class Car {
      setDriveSound(sound) {
        this.sound = sound;
      }

      drive() {
       return this.sound;
      }
    }

    const car = new Car();
    car.setDriveSound("vroom");

    // this returns "vroom" becase what is leftside of .drive()?  It is car.
    car.drive();    

    // javascript class is simply a javascript object in certain form
    // You don't have to go through new keyword.
    const truck = {
      sound : "put!put!"
      driveMyTruck: car.drive // driveMyTruck is simply a reference to drive method. (context of car is gone)
    }

    // this returns "put!put!"
    // truck.driveMyTruck() = truck.'reference to drive method in car' = truck.drive()
    // truck is borrowing car.drive method. 
    truck.driveMyTruck()
    ```
  * Problem with this.state
    * When you submit a form, the onFormSubmit is called as onFormSubmit(). Then the context of 'this' is lost at this point.
    ```js
    class SearchBar extends React.Component  {
      onFormSubmit(event) {
        event.preventDefault(); 
        console.log(this.state.term);
      }
      
      render() {
       ...
        <form className="ui form" onSubmit={this.onFormSubmit}>
       ...
      }
    }
    ```
* solution with 'this'
  * bind the "class method" to "the class" in the constructor
    ```js
    class Car {
      constructor() {
        /*
        How bind method works.
        f.bind(B)
        returns B.f where B is binded to f.
        */
        this.drive = this.drive.bind(this);
      }
    
      setDriveSound(sound) {
        this.sound = sound;
      }

      drive() {
       return this.sound;
      }
    ```
  * use arrow function (feature of arrow function doesn't have `this` by nature and `this` in the arrow function is class where arrow function is defined)
    * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/Arrow_functions
    * why define arrow function in constructor? Arrow function doesn't have `this` by nature. You can put arrow function in constructor to give this scope.
    * In short: since arrow function doesn't have `this`. It takes `this` as the class where it is defined. When you created inside of constructor, it is referencing to class.
    ```js
    class Car {
      setDriveSound(sound) {
        this.sound = sound;
      }
      drive = () => {
        return this.sound;
      }
    }
    ```
  * in context of our React project (two ways)
    ```js
    class SearchBar extends React.Component  {
      onFormSubmit = (event) => {
        console.log(this.state.term);
      }
      
      render() {
       ...
        <form className="ui form" onSubmit={() => {this.onFormSubmit}}>
       ...
      }
    }
    ```
    ```js
    class SearchBar extends React.Component  {
      onFormSubmit(event) {
        event.preventDefault(); 
        console.log(this.state.term);
      }
      
      render() {
       ...
        <form className="ui form" onSubmit={() => {this.onFormSubmit()}}>
       ...
      }
    }
    ```
* Invoking callbacks from child to parent
  ```js
  class App extends React.Component {

    onSearchBarSubmit(term) {
      console.log(term);
    }


    render() {
      return (
        <div className="ui container" style={{ marginTop: '20px'}}>
          <SearchBar onSearchBarSubmit={this.onSearchBarSubmit}/>
        </div>
      )
    }
  }
  ```
  ```js
  class SearchBar extends React.Component  {
    ...
    onFormSubmit = (event) => {
      event.preventDefault(); 
      this.props.onSearchBarSubmit(this.state.term);
    }
  }
  ```
* So current flow is
  * Change Input
  * 'OnChange()' does setState
  * state update triggers render()
  * input is created with a value from a state `value={this.state.term}` (and of course input is newly created thus doesn't call onChange, creating infinite loops)
  
# Section 8: Making API Requests with React
* you can create APIs in `unsplash.com` for development purposes.
* Two common ways of using APIs in React is by axios(3rd package) or fetch(built into modern browser).
  * fetch is very low-level. axios is preferred.
  ```
  npm install --save axios
  ```
* making api request without `async` keyword
  ```js
  onSearchBarSubmit(term) {
    const response = axios.get(
      'https://api.unsplash.com/search/photos',
      {
        params: { query: term},
        headers: {
          Authorization: 'Client-ID 29438u02398u4032'
        }
      }
    ).then((response) => {
      console.log(response.data.results);
      ...
    })
  }
  ```
* method with `async` and `await` keyword.
  * If you want to use await in the function. You need to define that function with 'async' keyword. 'asnyc' allows 'promise-based' behavior of the function.
  ```js
    async onSearchBarSubmit(term) {
    const response = await axios.get(
      'https://api.unsplash.com/search/photos',
      {
        params: { query: term},
        headers: {
          Authorization: 'Client-ID 29438u02398u4032'
        }
      }
    );
    console.log(response.data.results);
  }
  ```
* problem with naive approach to call back.
  * The code below throws an error because the caller of `this` in `setState` is `props` from `this.props.onSearchBarSubmit(this.state.term);` 
  ```js
  onFormSubmit = (event) => {
    event.preventDefault(); 
    this.props.onSearchBarSubmit(this.state.term);
  }
  ```
  ```js
  async onSearchBarSubmit(term) {
    const response = await axios.get(
      'https://api.unsplash.com/search/photos',
      {
        params: { query: term},
        headers: {
          Authorization: 'Client-ID Lj6c2xq0LVwuufH0l0yLy8o63Kop_IUBSY9mQf7boXY'
        }
      }
    );

    this.setState({
      images: response.data.results
    });
  }
  ...
  <SearchBar onSearchBarSubmit={this.onSearchBarSubmit}/>
  ```
* solution: binding!
  * since arrow function doesn't have `this`. It finds `this` from where the arrow function is defined.
  * note how you put async on arrow function.
  ```js
  async onSearchBarSubmit = async (term) => {
    ...
    this.setState({
      images: response.data.results
    });
  }
  ```

# Section 9: Building Lists of Records
* `key` in react.
  * Consider a list of 30 divs and showing 3divs in UI. now you have to include 1div to UI, showing 4divs. React has to have a identifier for each div to properly and efficiently manage divs. `key` acts as an identifier for each DOM element (<div> in this case)
  ```js
  import React from 'react';

  const ImageList = (props) => {

    const images = props.images.map( (i) => {
      return <img key={i.id} src={i.urls.regular} alt={`alt ${i.id}`}/>
    });

    return (
    <div>
      <div>Image List</div>
      {images}
    </div>);
  };

  export default ImageList;
  ```
* destructure in js: you can destructure any js object with property names. (note how `i` is destructured)
  ```js
  const images = props.images.map( (i) => {
    return <img key={i.id} src={i.urls.regular} alt={`alt ${i.desciption}`}/>
  });
  ```
  ```js
  const images = props.images.map( ({id, urls, desciption}) => {
    return <img key={id} src={urls.regular} alt={`alt ${desciption}`}/>
  });
  ```
  
# Section 10: Using Ref's for DOM Access
* Importing css file
  * look at import statement `import './ImageList.css';`. unlike importing a component, you are importing a file.
  * look at how you name the className of highest container (However, naming is just recommendation)
  ```js
  // src/components/ImageList.js
  import React from 'react';
  import './ImageList.css';

  const ImageList = (props) => {
    ...
    return (
    <div>
      <div className="image-list">Image List</div>
      {images}
    </div>);
  };
  ```
  ```css
  // src/components/ImageList.css
  .image-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  }
  ...
  ```
* The challenge now is to organize pictures with proper size. Your plan is to crunch some large photos so it behaves like flickr.
  ```
  .image-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 10px;
    grid-auto-rows: 150px;
  }

  .image-list img {
    width: 250px;
    grid-row-end: span 3;
  }
  ```
  * `display: grid` divides the screen into grids(cells).
  * `grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));` places child `<div>s` into a repeated manner where each column's min-width is 250px and max-width is 1ft. height is adjusted proportionally to the width.
  * `grid-auto-rows: 150px;` defines the height of each row
  * `grid-row-end` defines how many rows each item can span to when it exceeds the size of each container.

* Props System works similar way to Class Component as it was to Functional Component. You don't have to do explicitly pass it on to function paramater as you did in Functional Component.
  ```js
  const images = props.images.map( ({id, urls, desciption}) => {
    return <ImageCard key={id} src={urls.regular} alt={desciption}/>
  });
  ```
  ```js
  import React from 'react';

  class ImageCard extends React.Component {

    render() {
      return (
        <div>
          <img
            key={this.props.image.id}
            alt={this.props.image.description}
            src={this.props.image.urls.regular}
          />
        </div>
      )
    }
  }

  export default ImageCard;
  ```
* Let's define `grid-row-end` dynamically with React
  * How do I get access to DOM elements from React?
    * You can do `document.querySelector(...)`
    * but better approach is using React Ref
      ```js
      import React from 'react';

      class ImageCard extends React.Component {

        constructor(props) {
          super(props);
          this.imageRef = React.createRef();
        }

        render() {
          const { description, urls} = this.props.image;

          return (
            <div>
              <img
                ref={this.ImageCard}
                alt={description}
                src={urls.regular}
              />
            </div>
          );
        }
      }

      export default ImageCard;
      ```
* After ImageCard is loaded, let's get the height of each img
  ```js
  componentDidMount() {
    console.log(this.imageRef); // imageReft is { current: img } where img is a reference to the <img />
    console.log(this.imageRef.current.clientHeight);
  }
  ```
  * If you take a look at the result of console logs, you will see 0s. It is because even though ImageCard is mounted on UI, img is not mounted yet.
* Let's add a listener on img
  ```js
  componentDidMount() {
    // imageRef.current is <img>. Let's add a 'load' listener on the <img>
    this.imageRef.current.addEventListener('load', this.setSpans);
  }
  
  setSpans = () => {
    // call back function
  }
  ```
* Let's calculate the spans and set it
  ```js
  import React from 'react';

  class ImageCard extends React.Component {

    constructor(props) {
      super(props);
      this.imageRef = React.createRef();

      this.state = {
        spans: 0
      };
    }

    /* after this ImageCard is mounted on UI */
    componentDidMount() {
      // imageReft is { current: img }
      console.log(this.imageRef);
      // this is 0 because you haven't loaded img yet. ImageCard is mounted but not img.
      console.log(this.imageRef.current.clientHeight);

      // imageRef.current is <img>. Let's add a load listener on the <img>
      this.imageRef.current.addEventListener('load', this.setSpans);
    }

    setSpans = () => {
      const height = this.imageRef.current.clientHeight;
      /*
      Math.ceil(1.12) => 2  'touch the ceiling'
      Math.ceil(1) => 1  'ceiling already touched the ceiling'
      Math.ceil(-1.12) => -1  'touch the ceiling. ceiling is at above'
      Math.ceil(-1) => -1  'touch the ceiling. ceiling is at above'
      Math.floor(1.12) => 1 'touch the floor'
      */
      const calculatedSpans = Math.ceil(height / 10) // If the value is 150.1, you want to give two spans
      this.setState({ spans: calculatedSpans})
    }

    render() {
      const { description, urls} = this.props.image;

      return (
        <div style={{ gridRowEnd: `span ${this.state.spans}` }}>
          <img
            ref={this.imageRef}
            alt={description}
            src={urls.regular}
          />
        </div>
      );
    }
  }

  export default ImageCard;
  ```
  
# Section 11: Let's Test Your React Mastery!
* Skipped

# Section 12: Understanding Hooks in React
* There are two types of hooks: Primitive Hooks & Custom Hooks (naming is not official but it helps understand the concept)
  * Primitive Hook
    Basic Hooks
     - useState
     - useEffect
     - useContext
     - Additional Hooks
    Additional Hooks
     - useReducer
     - useCallback
     - useMemo
     - useRef
     - useImperativeHandle
     - useLayoutEffect
     - useDebugValue
  * Primitive Hook
    - Custom named hooks
* You can take props by destructuring
  ```js
  <Accordion items={items} titles={titles}/>
  ```
  ```js
  const Accordion = ({ items, titles }) => {
   return(
    ...
   );
  }
  ```
* What if you don't want to return a container element?
  * Let's take code below as an example. For some reason (css possibly) you might not want to return rendered Items inside of <div>
    ```js
    return(
      <div className="accordion ui styled">
        {renderedItems}
      </div>
    );
    ```
  * You can use React.Fragment
    ```js
    return(
      <React.Fragment className="accordion ui styled">
        {renderedItems}
      </React.Fragment>
    );
    ```
* Challenge: How would you save a 'index of clicked element' in the state of a functional component?
  * useState!
  ```js
  import { useState } from 'react'; // import a state system to functional component
  ...
  const Accordion = ({ items }) => {
    const [activeIndex, setActiveIndex] = useState(null); // define [stateName, stateSetter]
  
    const onTitleClick = (index) => {
      setActiveIndex(index); // assing a value to state with stateSetter
    };
    ...
    return(
      <div className="accordion ui styled">
        <h1>{activeIndex}</h1> // get a state value.
      </div>
    );
  }
  ```
* `useState` allows functional component to use 'state'
  * syntax
  ```js
  const [stateName, stateSetter] = useState(stateInitialValue);
  ```
* `useEffect` allows functional component to use 'life cycle'
  * tip: render vs mount? 
    * render: transformation react code into DOM
    * mount: embedment rendered DOM into DOM tree.
  * syntax
  ```js
  /*
  - effectFunction is function that is triggered after rendered.
  - effectCondition is a optional argument. It is an array of 'arguments of the functional component' that defines when to execute 'effectFunction'.
  */
  useEffect(effectFunction, effectCondition);
  
  const FuncComp = (b,c,d,e,f) => {
    useEffect(a, []);      // execute 'a' after the first render of the FuncComp and no more.
    useEffect(a);          // execute 'a' after the first render of the FuncComp and all re-render (whenever b,c,d,e or f are changed then it re-renders.
    useEffect(a, [b]);     // execute 'a' after the first render of the FuncComp and when 'b' is changed.
    useEffect(a, [b,c]);   // execute 'a' after the first render of the FuncComp and when 'b' or 'c' is changed.
    useEffect(a, [b,c,d]); // execute 'a' after the first render of the FuncComp. and when 'b', 'c' or 'd' is changed.
         
    return ...
  }
  ```
  * limitation to useEffect: "effect function can't be defined with async keyword"
    * not allowed
      ```js
      useEffect(async () => {
        await axios.get(...)
      }, [term]);
      ```
    * allowed: however, you can define async inside the effect function and execute it.
      * named
      ```js
      useEffect(() => {
        const search = async () => {
          await axios.get('URL');
        }
        search();
      }, [term]);
      ```
      * nameless
      ```js
      useEffect(() => {
        (async () => {
          await axios.get('URL');
        })();
      }, [term]);
      ```
* allowed async example
  ```js
  useEffect(async () => {
    await axios.get(...)
  }, [term]);
  ```
  ```js
    useEffect(() => {
    const getWikipedia = async () => {
      await axios.get('getWikipediaURL');
    };

    getWikipedia();
    ...
  }, [term]);
  ```
  
* js if/else block tip
  * `0, "", null` are all considered as `false`
  ```js
  if ("") {
      console.log("if");
  } else {
      console.log("else"); // else is logged
  }
  ```
* challenge: how would you handle html elements in the string?
  ```
  example
  "<span class="searchmatch">Money</span> is any item or verifiable record that is generally accepted as payment for goods and services and repayment of debts, such as taxes, in a particular"
  ```
* React has `dangerouslysetinnerhtml`
  * when `r.snippet` in `<div>{r.snippet}</div>` has html elements such as `<span>`
  * use case: `<div dangerouslySetInnerHTML={{ __html: r.snippet}} />`
 
* useEffect cleanup
  * useEffect function can return a 'clean up function'. this gets executed at update or unmount. (acts as `componentDidUnmount` & `componentWillUnmount`)
  ```js
  const FuncComp = ({term}) => {  
     useEffect(() => {
      // use effect
      return (()=> { // clean up logic to execute when term is updated or FuncComp is unmounted });
     }, [term]);
  }
  ```
* search with timeout using useEffectCleanup
  ```js
  const timeOutId = setTimeout(() => {
    if (term) {
      getWikipedia();
    }
  }, 500);

  const expireTimeOut = () => {
    clearTimeout(timeOutId);
  }

  return expireTimeOut;
  ```
* js debouncing
  * watch #169 clip.

* As you pass props/state to child component, you can pass hooked state to child component
  ```js
  const App = () => {
    // state with hook
    const [selected, setSelected] = useState(options[0]);

    return (
      <div>
        {/* <Accordion items={items}/> */}
        {/* <Search /> */}
        <Dropdown
          options={options}
          selected={selected}
          onSelect={setSelected}
          />
      </div>
    );
  }
  ```
  ```js
  const Dropdown = ({options, selected, onSelect}) => {
   ...
  }
  ```
* ternary expression on className injection (based on boolean variable open)
  ```js
  <div className={`ui selection dropdown ${open ? 'visible active' : ''}`} onClick={() => {setOpen(!open)}}>
  ```
  
* how would you close an option when other DOM element outside of options is clicked?
  * click event will bubble up toward `<body>`
  * set a click event listener on `<body>` that takes care of open/close logic
  * good news is you can add a listener to parent 'DOM element' in child component.
  ```js
  const Dropdown = ({options, selected, onSelect}) => {
  // state hook
  const [open, setOpen] = useState(false);

  // effect(render) with hook
  useEffect(() => {
    document.body.addEventListener('click', () => {
      setOpen(false);
    })
  }, []);
  ```
* Now you added a listener, but this doesn't solve our problem because even when you click `<div>` the event bubbles up to `<body>`, triggering setOpen(false).
  * remember you used `this.imageRef = React.createRef();` to access DOM element from class component? you can do a similar job by using `useRef` hook.
  ```js
  import React, { useState, useEffect, useRef } from 'react';

  const Dropdown = ({options, selected, onSelect}) => {
    // state hook
    const [open, setOpen] = useState(false);
    
    // ref hook 
    const dropDownRef = useRef();

    // effect hook
    useEffect(() => {
      document.body.addEventListener('click', (e) => {
        if (dropDownRef.current && dropDownRef.current.contains(e.target)) {
          // do nothing (when drop down is clicked)
        } else {
          setOpen(false);
        }
      })
    }, []);

    const renderedOptions = options.map(
      (option) => {
        if (option.value === selected.value) {
          // display selected item on the very top label, but not in options
          return null;
        }

        return (
          <div key={option.value} className="item" onClick={() => onSelect(option)}>
            {option.label}
          </div>
        );
      }
    );

    return (
      <div className="ui form" ref={dropDownRef}>
        <div className="field">
          <label className="label">Select a Color</label>
          <div className={`ui selection dropdown ${open ? 'visible active' : ''}`} onClick={() => {setOpen(!open)}}>
            <i className="dropdown icon"></i>
            <div className="text">{selected.label}</div>
            <div className={`menu ${open ? 'visible transition' : ''}`}>{renderedOptions}</div>
          </div>
        </div>
      </div>
    );
  }

  export default Dropdown;
  ```
* Now you want to implemewnt show/hide toggle button of 'Dropdown' in App.js
  * you can do it with ternary expression on `showDropDown` state hook.
  ```js
   const App = () => {
    ...
    return (
      <div>
        <button onClick={() => setShowDropdown(!showDropdown)}>Toggle Dropdown</button>
        // <Accordion items={items}/> */}
        // <Search /> */}

        { showDropdown ? 
          <Dropdown
            options={options}
            selected={selected}
            onSelect={setSelected}
          /> : null
        }
      </div>
   );
  }
  ```
 * However this leaves a problem on effect hook with event listener we created.
 * Dropdown is set to `null` upon `showDropdown=false`. `dropDownRef` also becomes `null`. and `dropDownRef.current.contains(e.target)` throws a null pointer exception when you click some DOM elements. Because you added precondition `dropDownRef.current &&`. The exception isn't thrown but it is always nice to clear up unused event listener(s).
 ```js
    // effect hook
    useEffect(() => {
      document.body.addEventListener('click', (e) => {
        if (dropDownRef.current && dropDownRef.current.contains(e.target)) {
          // do nothing (when drop down is clicked)
        } else {
          setOpen(false);
        }
      })
    }, []);
 ```
 ```js
    // effect(render) with hook
  useEffect(() => {
    // define listener
    const onBodyClickListener = (e) => {
      if (dropDownRef.current && dropDownRef.current.contains(e.target)) {
        // do nothing (when drop down is clicked)
      } else {
        setOpen(false);
      }
    }
    // attach
    document.body.addEventListener('click', onBodyClickListener);

    return (
      () => {
        // detach with clean up
        document.body.removeEventListener('click', onBodyClickListener);
      }
    );
  }, []);
  ```
* js class method, function syntax (wrt asyc)
  * method vs function
    ```
    method is associated with an object while function is not
    ```
  * class method. class method is not a function so don't use `function` keyword
    ```js
    class Car {
      constructor(brand, model) {
       this.brand = brand;
       this.model = model;
      }

      drive() {
        return 'Broom Broom';
      }
    }
    ```
  * class method with arrow syntax. (why would you do this though?)
    ```js
    class Car {
      constructor(brand, model) {
       this.brand = brand;
       this.model = model;
       this.drive = () => {
         return 'Broom Broom';
       }
    }
    ```
  * with `function` keyword
    ```js
    function drive() {
      return 'Broom Broom';
    }
    ```
    ```js
    async function drive() {
      const result = await driveAPI();
      return result
    }
    ```
  * arrow function
    ```js
    drive = () => {
      return 'Broom Broom';
    }
    ```
    ```js
    drive = async () => {
      const result = await driveAPI();
      return result
    }
    ```
* You are making too many api requests. Can you do debouncing?
  * approach is simple you use two Effect hooks.
    * #1 useEffect: when language or text changes -> set a 3sec timer that updates `finalizedText`
    * #2 useEffect: when `finalized text` changes -> make api call
  * example
    ```js
    import React, {useState, useEffect} from 'react';
    import axios from 'axios';

    const googleTranslateApiKey = "AIzaSyCHUCmpR7cT_yDFHC98CZJy2LTms-IwDlM";

    const Convert = ({ language, text }) => {
      const [translated, setTranslated] = useState('')
      const [finalizedText, setFinalizedText] = useState(text);

      // 'text is being entered' => set/cancel a timer to update 'finalized text'
      useEffect(
        () => {
          // set a timer to update 'finalized text'
          const timerId = setTimeout(
            () => {
              setFinalizedText(text);
            },
            2000
          );

          // cancel a timer you created above because 'text' is newly updated
          return (
            () => {
              clearTimeout(timerId);
            }
          )
        },
        [text]
      );

      // 'text is finalized or language is selected' => make api call and update translated.
      useEffect(
        () => {
          const translate = async () => {
            const { data } = await axios.post(
              'https://translation.googleapis.com/language/translate/v2',
              {},
              {
                params: {
                  q: finalizedText,
                  target: language.value,
                  key: googleTranslateApiKey
                }
              }
            );
            // console.log(data.data.translations);
            setTranslated(data.data.translations[0].translatedText);
          }
          translate();
        },
        [language, finalizedText]
      );

      return (
        <div>
          <h1 className="ui header">{translated}</h1>
        </div>
      );
    }

    export default Convert;
    ```
  * Findings
    ```js
    const Convert = ({ language, text }) => {
     const [translated, setTranslated] = useState('')
     const [finalizedText, setFinalizedText] = useState(text);
     ...
    ```
    * functional component execution != render
    * functional component is executed everytime props are changed, but this doesn't mean it is rendered everytime
    * hooks are called every render. at the initial render `finalizedText` will be same as what `text` is. However when you enter some keys into input to change `text`, it doesn't update finalizedText while text is being updated.
    * because prop change itself dosn't triggers a render.
    ```
    1) prop change triggers functional component
    2) prop change itself doesn't trigger render
    ```
# Section 13: Navigation From Scratch
* React Router can easily implmenet navigation feature, but this will be convered in the later section.
* `window.location` is a js object that browser holds while you navigate across pages
* one naive approach
  ```js
  const showAccordion = () => {
    if (window.location.pathname === "/") {
      return <Accordion items={items} />
    }
  }
  ...
  return (
   <div>
     {showAccordion()}
   </div>
  );
  ```
* or creating Route.js approach (better)
  * `children` in props is the element is sandwiched inside.
  ```js
  const Route = ( {path, children } ) => {
    return window.location.pathname === route
      ? component
      : null;
  };

  export default Route;
  ```
  ```js
  ...
  return (
    <div>
      <Header />
      <Route path="/">
        <Accordion items={items} />
      </Route>
      ...
      <Route path="/dropdown">
        <button onClick={() => setShowDropdown(!showDropdown)}>Toggle Dropdown</button>
        {
          showDropdown  ?  <Dropdown options={options}
                                    selected={selected}
                                    onSelect={setSelected}
                          />
                        : null
        }
        </Route>
    </div>
  );
  ```
* Implementing navigation bar in the header (use `<a>`)
  ```js
  import React from 'react';

  const Header = () => {
    return (
      <div className="ui secondary pointing menu">
        <a href="/" className="item">
          Accordion
        </a>
        <a href="/list" className="item">
          Search
        </a>
        ...
      </div>
    );
  };

  export default Header;
  ```
* Downside on this approach of using `<a href />`?
  * inefficiency
    ```
    user clicks <a> link -> the browser is routed to the url that href states -> the whole page refreshes -> load entire `html, css, scripts...
    ```
  * what is ideal is
    ```
    user clicks a link -> detect url change 
                       -> change url without refreshing the page
                       -> each Route re-renders
    ```
*  `<Link />` approach
   * `window.history.pushState({}, '', `/translate`)` switches what's after origin(domain).
   * `event.preventDefault();` suppresses full browser reload
   * `window.dispatchEvent(new PopStateEvent('popState'));` dispatches a custom event so that you can detect url change without refreshing the browser.
   ```js
   import React from 'react';

   const Link = ({ className, href, children}) => {

     const onClickLink = (event) => {
       event.preventDefault();
       window.history.pushState({}, '', href);
       window.dispatchEvent(new PopStateEvent('popstate'));
     }

     return (
       <a className={className} href={href} onClick={onClickLink}>
         {children}
       </a>
     );
   };

   export default Link;
   ```
   ```js
   import { useEffect, useState } from "react";

   const Route = ( {path, children } ) => {

     const [currentPath, setCurrentPath] = useState(window.location.pathname);

     const onPathChange = () => {
       console.log("CHANGED" + window.location.pathname);
       setCurrentPath(window.location.pathname); // this updates a state just to trigger 'render' (state update -> render)
     }

     useEffect( () => { 
       window.addEventListener('popstate', onPathChange);

       return () => {
         // we don't want to keep adding the event listener everytime it re-renders
         window.removeEventListener('popstate', onPathChange);  
       }
     }, []) // run an effect only once;

     return (
       currentPath === path ? children
                            : null);
   };

   export default Route;
   ```

   ```js
   // App.js
   ...
     return (
    <div>
      <div className="ui secondary pointing menu">
        <Link href="/" className="item">
          Accordion
        </Link>
        <Link href="/list" className="item">
          Search
        </Link>
        <Link href="/dropdown" className="item">
          Dropdown
        </Link>
        <Link href="/translate" className="item">
          Translate
        </Link>
      </div>
      <Route path="/">
        <Accordion items={items} />
      </Route>
      <Route path="/list">
        <Search />
      </Route>
      <Route path="/translate">
        <Translate />
      </Route>
      <Route path="/dropdown">
        <button onClick={() => setShowDropdown(!showDropdown)}>Toggle Dropdown</button>
        {
          showDropdown  ?  <Dropdown options={options}
                                    selected={selected}
                                    onSelect={setSelected}
                          />
                        : null
        }
        </Route>
    </div>
   );
   ```
# Section 14: Hooks in Practice
* Optional Tutorial. Just watched.
* Custom Hook example
  * First, `useVideos` gives back videos searched with 'buildings'.
  * `search` function returned from `useVideos` are also called `onFormSubmit`.
  * upon executing `search`, request to youtube api is made and `videos` state in `useVideo` is updated.
  * `<App />` is rerendered since `<App />` imported `useVideo` thus `videos` state is part of `<App />`
  ```js
  // App.js
  ...
  import useVideos from '../hooks/useVideos';

  const App = () => {
    // useVideos return 
    const [videos, search] = useVideos('buildings');

    return (
      <div className="ui container">
        <SearchBar onFormSubmit={search} />
        <div className="ui grid">
          <div className="ui row">
            <div className="eleven wide column">
              <VideoDetail video={selectedVideo} />
            </div>
            <div className="five wide column">
              <VideoList onVideoSelect={setSelectedVideo} videos={videos} />
            </div>
          </div>
        </div>
      </div>
    );
  };

  export default App;

  ```
  ```js
  // src/hooks/useVideo.js
  
  import { useState, useEffect } from 'react';
  import youtube from '../apis/youtube';

  const useVideos = (defaultSearchTerm) => {
    const [videos, setVideos] = useState([]);

    // search with default term only once
    useEffect(() => {
      search(defaultSearchTerm);
    }, []);

    const search = async (term) => {
      const response = await youtube.get('/search', {
        params: {
          q: term,
        },
      });

      setVideos(response.data.items);
    };

    // 
    return [videos, search];
  };

  export default useVideos;

  ```
# Section 15: Deploying a React App
* Deploying React App doesn't require a VM (lightweight)
* Deployment Option1: Vercel (https://vercel.com/)
* Deployment Option2: Netlify (https://www.netlify.com/)

# Section 16: On We Go...To Redux!
* Redux: js state(data) management library.
* Redux lifecycle easy explanation  
  ```
  ActionCreator --(Action)--> Dispatch --(Action)--> Reducer --> Store
  ```
  * Action Creator: Function that creates 'Action'
     ```js
     const createPolicy = (policyName, policyAmount) => {
       return {
          type: "CREATE_POLICY",
          payload: {
            name: policyName,
            amountSaved: policyAmount
          }
       }
     }
    
     const createClaim = (reason, amount) => {
       return {
          type: "CREATE_CLAIM",
          payload: {
            reasonClaimed: reason,
            amountClaimed: amount
          }
       }
     }
     ```
  * Action: 'state change request' js object
    ```js
    {
      type: "CREATE_POLICY",
      payload: {
        name: policyName
        amount: policyAmount
    }
    ```
  * Dispatch: Function that delivers 'Action' to 'Reducers"
    ```js
    const action1 = createPolicy("PO1", 1000);
    const action1 = createPolicy("PO332", 100);
    const action2 = createClaim("Buy Rolex", 300); 
    
    store.dispatch(action);
    console.log(store.getState());
    
    /* console.log will return
     {
      myMoney: 900,
      myPolicies: ["PO1", "PO332"}]
     }
     */
    ```
  * Reducers: Function that updates state
     ```js
     const policyReducer = (prevPolicies = [], action) => { // [] will be the very first policies state of the store
        if (action.type == 'CREATE_POLICY') {
          return [...prevPolicies, action.payload.policyName]
        }
        return prevPolicies;
     }
     
     const moneyReducer = (prevMoney = 100, action) => {  // 100 will be the very first money state of the store
        if (action.type == 'CREATE_POLICY') {
          return prevMoney + action.payload.amountSaved;
        } else if (action.type == 'CREATE_CLAIM') {
          return prevMoney - action.payload.amountClaimed;
        }
        return prevMoney;
     }
     ```
  * Store: storage of state.
     ```js
     const { createStore, combineReducers } = Redux;
     
     const myReducers = combineReducers({
        myMoney: moneyReducer,
        myPolicies: policyReducer
     })
     
     const store = createStore(reducers);
     ``` 
# Section 17: Integrating React with Redux
* Redux is a state management library
  * React-Redux is a library that integrates Redux with React.
    ```
    npm install --save redux
    npm install --save react-redux
    ```
  * Design Proposal of 'song' app
    * 'song' app design without redux
      ```js
      <App /> state = { songs, selectedSong }, onSongSelect()
        <SongList /> props = { songs, onSongSelect }  
        <SongDetail /> props = { selectedSong } 
      ```
    * 'song' app design with redux
      ```js
      ActionCreators: selectSong
      Reducers: songListReducer, selectedSongReducer
      ```
  * Brief overview of React-Redux
    ```js
    Provider (redux-store is defined in here)
       |          ↑
      App         connect (connect allows SongList to communicate with Provider)
       |          ↑
     SongList  →→→→
    
    Connect (redux-actionCreators are defined here. It communicates with Provider)     
    ```
* project structure with redux
  ```
  /src
    /actions    -- redux-actionCreators
    /reducers   -- redux-reducers
    /components -- react-components
    index.js
  ```
* defining actions
  ```js
  export const selectSong = (songName) => {
      return {
         type: 'SONG_SELECTED',
         payload: songName
      };
  };
  ```
* defining reducers and combining them with 'combineReducers'
  ```js
  import { combineReducers } from 'redux';
  
  const songsReducer = () => {
      return [
          {title: "Surfing", duration: "4:01"},
          {title: "Safari", duration: "5:01"},
          {title: "Starkist", duration: "3:01"},
          {title: "Milk", duration: "1:01"}
      ];
  }
  
  const selectedSongReducer = (selectedSong = null, action) => {
      if (action.type === "SONG_SELECTED") {
          return action.payload;
      }
  
      return selectedSong;
  }
  
  export default combineReducers({
      songs: songsReducer,
      selectedSong: selectedSongReducer
  })
  ```
* tip: named-export vs default export
  * default-export/import
  ```js
  // car.js
  export default const buyCar = () => {
    //
  }
  ```
  ```js
  import a from 'car';
  // a refers to buyCar
  ```
  * named-export/import
  ```js
  // car.js
  export const buyCar = () => {
    //
  }
  
  export const stealCar = () => {
    //
  }
  ```
  ```js
  import { buyCar, stealCar } from 'car';
  ```
  * named-import with alias
    * lets say you have buyCars in factory.js and dealer.js. You can use alias to differentiate them using `as`.
  ```js
  import { buyCar as buyCarFromFactory, stealCar } from 'factory';
  import { buyCar as buyCarFromDealer } from 'car';
  ```
* Setting up Provider
  * put App under Provider
  * pass create store with reducers and pass it to Provider
  ```js
  // index.js
  
  import React from 'react';
  import ReactDOM from 'react-dom';
  import { Provider } from 'react-redux';
  import { createStore } from 'redux';
  
  import App from './components/App';
  import reducers from './reducers/reducers';
  
  ReactDOM.render(
      <Provider store={createStore(reducers)}>
          <App />
      </Provider>,
      document.querySelector('#root')
  );
  ```
* Setting up connect
  ```js
  // SongList.js
  
  import React from 'react';
  import { connect } from 'react-redux';
  
  class SongList extends React.Component {
      render() {
          return <div>SongList</div>;
      }
  }
  
  export default connect()(SongList);
  ```
  * connect() returns a function. Let's say f is returned. Then connect()(SongList) is same as f(SongList)
* connect & mapStateToProps
  * connect function may take mapStateToProps as the first argument.
  * mapStateToProps is a function that takes the whole state in the redux-store and returns some of them as props
  ```js
  class SongList extends React.Component {
      render() {
          return <div>SongList</div>;
      }
  }
  
  const mapStateToProps = (state) => {
      return {
          songs: state.songs
      };
  }
  
  export default connect(mapStateToProps)(SongList);
  ```
* tip: js object with ES6
    ```js
    const tiger = "tyron";
    const lion = "lily"
    
    const a = { tiger:  tiger, lion: lion}
    const b = { tiger, lion }
    
    // a and b will look identical
    ```
    * in ES6 JS, if the key-name is the same as the name of the variable that the value refers to. You can just pass

* connect & actionCreators
  * connect function may take an object of actionCreator functions as the second argument.
  ```js
  // SongList.js
  import { selectSong } from '../actions/actions';
   
  class SongList extends React.Component {
    ...
    return (
      <div className="item" key={song.title}>
          <button className="ui button primary" onClick={() => this.props.selectSong(song)}>Select</button>
      </div>
    );
  }
  ...
  export default connect(
      mapStateToProps,
      { selectSong: selectSong }
  )(SongList);
  ```
  * tip
    * state or actionCreators that are passed with connect will be part of `this.props`. And this will be the same for either in functional component or class component.
    * executing actionCreators that are passed is `dispatch` lifecycle of redux lifecycle. 
  * Question: but why do we import and pass it as a prop with connect? why not just use it right away after import?
    * using the actionCreator right away have no connection to redux store itself.
    * actionCreator will send the action to reducers when passed with connect function.
  * Summary of connect()
    ```
    1. connect method allows js component to talk to redux storage.
    2. connect's first argument is mapStateToProp function.
    3. connect's second argument is an object of actionCreator function(s).
    4. connect returns a function that takes a React Component
    5. In this React Component, you can access mapped-props and action-creators as this.props.x
    
    ex)
    connect(
      mapStateToProps,
      {
        a1: actionCreator1,
        a2: actionCreator2
      }
    )(SomeComponent)
    ```
    

# Section 18: Async Actions with Redux Thunk
* redux-thunk
  ```
  npm install --save redux-thunk
  ```
  * what is redux-thunk?
    * It is a middleware that helps us make request in a redux app.
* incorrect approach to async actionCreators
  * this will end up with an error because an action of an actionCreators should be a plain js object. Using `async` and `await` turns the action above into complicated js code (not a plain object). It will make more sense if you try babel.js sandbox.
  ```js
  // actions.js
  
  const requestedJson = axios.create({
      baseURL: 'https://jsonplaceholder.typicode.com'
  })
  
  export const fetchPosts = async () => {
      const response = await requestedJson.get('/posts');
  
      return {
          type: "FETCH_POST",
          payload: response
      };
  };
  ```
* how about without `async` & `await`? this will end up with unfetched data because axios call is not awaited.
  ```js
  // actions.js
  
  const requestedJson = axios.create({
      baseURL: 'https://jsonplaceholder.typicode.com'
  })
  
  export const fetchPosts = () => {
      const promise = requestedJson.get('/posts');
  
      return {
          type: "FETCH_POST",
          payload: promise
      };
  };
  ```
* Rules of redux-actionCreator
  * ActionCreator must return a plain-object Action. Action must have a `type`, and it may have a `payload`.

* Rules of redux-thunk actionCreator
  * redux-thunk-ActionCreator can return a plain-object Action as well as a "dispatcher function"
    * If Action is a plain-object, Action must have a `type`, and it may have a `payload`.
    * dispatcher-function is a function that dispatches.
      * It can take 'dispatch' or 'dispatch and getState'
      * dispatcher-function shouldn't return an action. Instead, it should dispatch an action. If it returns an action, it doesn't get dispatched automatically.
      ```js
      ex1: (dispatch) => { dispatch(someActionCreator()) }
      ex2: (dispatch, getState) => { dispatch(someActionCreator()) }
      ```
      * you can make dispatcher-function `async` so that it can handle `async` api calls.

* Usage of Redux-Thunk
  * init Store with redux-thunk
  ```js
  // index.js
  ...
  import { createStore, applyMiddleWare } from 'redux';
  import thunk from 'redux-thunk';
  ...
  
  ReactDOM.render(
    <Provider store={createStore(reducers, applyMiddleWare(thunk))}>
      <App />
    </Provider>,
    document.getElementById('root')
  );
  ```
  * define an actionCreator that returns a dispatcher-function.
  ```js
  // actions.js
  export const fetchPostsThunk = () => {
    return ( async ( dispatch ) => {
      const response = await fetchedJson.get('/posts');

      dispatch({
        type: "FETCH_POST",
        payload: response
      })
    });
  };
  ```
  * use actionCreator
  ```js
  // PostList.js
  ...
  class PostList extends React.Component {
  
      componentDidMount() {
          this.props.fetchPostsThunk();
      }
  
      render() {
          return <div>Post List</div>
      }
  }
  
  export default connect(
      null,
      { fetchPostsThunk }
  )(PostList)
  ```
  
# Section 19: Redux Store Design
* Rules of Reducers
  1. Must return something other than undefined
     * in javascript, null is an assigned value, meaning 'nothing'. undefined is unassigned value.
  2. Must keep the logic of "coming up with new value for the state" outside of the reducer.  
     * For example, if you need an api calls to determine the new state value. Then put that logic inside the dispatcher-function of an redux-thunk-actionCreator.
  3. Must not mutate the prev state object.
     * clone the prev state -> mutate the cloned state -> return the cloned state.

* js tips
  * !== and re-render
    * reducers inside compares state with prevState using !==. If you mustate the prevState, then reducer will consider as no-update and the app won't re-render.
  ```js
  a = ['1', '2']

  a === a (true)
  a === ['1', '2'] (false)
  
  === compares the reference of the pointer  
  ``` 
  ```js
  overriding ...
  java script ... operator works in top to bottom order. If you override something in the bottom. The property will be overridden
  
  A = {
    "a": 1,
    "b": 12,
    "c": 123,
  }
  
  B = {
    ...A,
    "b" : 1234
  }
  // B.b will be 1234
  
  C = {
    "b" : 12345,
    ...A
  } 
  // C.b will be 12
  ```
* Reducer Good Practice
  * array type state
    * to add a property: use ...
      ```js
      [ ...prevState, "a" ]
      ``` 
    * to update a property: use map
    * to remove a property: use filter (because filter returns a new object)
  * object type state
    * to add a property: use ...
      ```
      { ...prevState, a: "a" }
      ```
    * to update a property: use ...
      ```js
      A = {
        a : "a",
        b : "b",
        c : "c"        
      }
      
      B = {
        ...A,
        'b' : 'bbb'
      }
      
      B will be {
        a : "a",
        b : "bbb",
        c : "c"
      }
      ```
    * to remove a property: use ... with undefined (because filter returns a new object)
      ```js
      A = {
        a : "a",
        b : "b",
        c : "c"        
      }
      
      B = {
        ...A,
        'b' : undefined
      }
      
      B will be {
        a : "a",
        c : "c"
      }
      ```
      * otherwise you can use `_omit(object, property)` _.omit(A, "b")

* define a different actionCreator to fetch user with id
  * tip: with `` ` ` ``(backticks) and `${}` (dollar and curlybraces), you can inject a reference to a string inside the string.
  ```js
  export const fetchUsers = (id) => {
      return (
          async (dispatch) => {
              const response = await jsonplaceholder.get(`/users/${id}`)
              
          }
      )
  }
  ```
  
* The second argument of mapStateToProps.
  * you can put ownProps of a Component as the second argument of the mapStateToProps function.
  * In PostList, pass userId as props to UserHeader.
    ```js
    // PostList.js
    ...
    <UserHeader userId={post.userId} />
    ...
    ```
  * In UserHeader, use userId that is passed in `mapStateToProps` function.
    ```js
    // UserHeader.js
    ...
    const mapStateToProps = (state, ownProps) => {
        return {
            user: state.users.find( (user) => (user.id === ownProps.userId))
        }
    }
    ...
    ```

* Handling user fetch.
  * PostList.js
    ```js
    import UserHeader from './UserHeader';
    
    ...
      <UserHeader userId={post.userId} />
    ...
    ```
  * UserHeader.js
    ```js
    ...
    class UserHeader extends React.Component {
    
        componentDidMount() {
            this.props.fetchUser(this.props.userId);
        }
    
        render() {
            if (!this.props.user) {
                return <div>Loading!</div>
            }
    
            return(
                <div className="header">
                    {this.props.user.name}
                </div>
            );
        }
    }
    
    const mapStateToProps = (state, ownProps) => {
        return {
            user: state.users.find((user) => (user.id === ownProps.userId))
        }
    }
    
    export default connect(
        mapStateToProps,
        {
            fetchUser: fetchUser
        }
    )(UserHeader);
    ```
  * actions.js
    ```js
    export const fetchUser = (id) => {
        return (
            async (dispatch) => {
                const response = await jsonplaceholder.get(`/users/${id}`)
                dispatch({
                    type: "FETCH_USER",
                    payload: response.data
                })
            }
        )
    }
    ```
  * usersReducer.js (user reducer)
    ```js
    ...
    const usersReducer = (state = [], action) => {
        switch (action.type) {
            case 'FETCH_USER':
                return [...state, action.payload];
            default:
                return state;
        }
    }
    ```
  * reducers.js (combine reducers)
    ```js
    ...
    export default combineReducers({
        posts: postsReducer,
        users: usersReducer
    });
    ```
  * this user-fetch architecture has an issue. There are 100 posts in `<PostList />`. This means you are passing `posts` to `<UserHeader />` 100 times. Each `<UserHeader />` will trigger `fetchUser` as part of ComponentDidMount. The problem is: there are only 10 users and this App will call 100 apis to get 10 users. How can we optimized it?

* Solution 1: Optimization by Memoization
  * Lodash: Lodash is a js library that provides many helper functions.
    `npm install --save lodash`
    
  * _.memoize(f)
    ```js
    var adder = _.memoize(
      function add(a, b) {
            return a + b;
          }
    );
    
    adder(20, 5); // this will actually do 20+5 and return 25. 25 will be memoized
    adder(20, 5); // this will return memoized 25
    adder(20, 5); // this will return memoized 25
    ```
  
  * fetchUser with memoize
    * you shouldn't memoize a nameless function because it will return new function everytime its used and memoize can't return the value of a different function even though the function is logically identical.
    * redux-thunk Action-Creator can return an Action or Dispatcher-Function. Should we memoize the Action-Creator or Dispatcher-Function?
      1. let's say we memoize the Action-Creator, what is the return value? It is the dispatcher function. In this case, this dispatcher function will be memoized in the cache. This is not what we want because we want to memoize the api calls to prevent multiple&duplicate api calls.
      2. let's say we memoize the Dispatcher-Function, what is the return value? It is the api response. This is what we want to memoize.
    ```js
    // actions.js
    import _ from 'lodash';
    
    export const fetchUserWithMemoization = (id) => (dispatch) => _fetchUser(id, dispatch);
    
    const _fetchUser = _.memoize(
        async (id, dispatch) => {
           const response = await jsonplaceholder.get(`/users/${id}`)
           dispatch({
               type: "FETCH_USER",
               payload: response.data
           })
        }
    );
    ```
    * fetchUserWithMemoization is an Action-Creator that takes an id and return a Dispatcher-Function
    * Dispatcher function takes dispatch and executes _fetchUser with id and dispatch
    * _fetchUser makes async api calls and memoizes the value.
    
* Solution 2: Optimization by redesigned action-creator.
  * The redux-thunk dispatcher-function can take "dispatch" or "dispatch and getState" as argument(s).
    ```js
    const actionCreator = (dispatch) => { ... }      
    const actionCreator2 = (dispatch, getState) => { ... }
    ```
  * The redux-thunk can dispatch a "dispatcher-function". The action that "dispatcher-function" dispatches will be dispatched in order.
    ```js
    store.dispatch( actionCreator1() ); // dispatch a dispatcher-function
     
    export const actionCreator1 = () => (dispatch) => {
        dispatch( actionCreator2() ) // dispatches action2
        dispatch( actionCreator3() ) // dispatches a dispathcer-function that dispatches action4 => action3
    
        dispatch( {                  // dispatches action1
            type: "THUNK_TEST",
            payload: "action1"
        });
    }
    
    // actionCreator2 returns action2
    export const actionCreator2 = () => {
        return {
            type: "THUNK_TEST",
            payload: "action2"
        }
    }
    
    // actionCreator3 returns a dispatcher-function
    export const actionCreator3 = () => {
    
        return (dispatch) => {
            dispatch( actionCreator4() );
    
            dispatch({
                type: "THUNK_TEST",
                payload: "action3"
            });
        }
    }
    
    // actionCreator4 returns action4
    export const actionCreator4 = () => {
        return {
            type: "THUNK_TEST",
            payload: "action4"
        }
    }
    ```
  * If you do a console.log in reducers. It will show that actions are dispatched in `action2 -> action4 -> action3 -> action1` order.
    
  * Example in our app
    ```js
    export const fetchPostsAndUsers = () => async (dispatch, getState) => {
        await dispatch(fetchPosts());  // fetchPosts() is a dispatcher-function that dispatches FETCH_POST action. FETCH_POST will be dispatched.
    
        // get unique userIds from posts
        const userIds = getState().posts.map(p => p.userId);
        const uniqueUserIds = _.uniq(userIds);
    
        // fetch user
        uniqueUserIds.forEach(
            id => dispatch(fetchUser(id)) // // fetchUser() is a dispatcher-function that dispatches FETCH_USER action. FETCH_USER will be dispatched.
        );
    }
    ```
    * js tip: `==` vs `===`
      * `==` compares value, but not type. `==` does the auto conversion of types.
      * `===` compares value and type.
        ```
        5 == "5"  // true
        5 === "5" // false
        5 === 5   // true
        ```
      * for objects `==` and `===` compares the reference.
    * js tip:
      * javascript is a dynamic programming language.
        * this means that the type of a variable is determined dynamically(in run time). Type errors can be found in run time.
      * java is a static programming language.
        * this means that the type of a variable is determined statically(in compile time). Type errors can be found in compile time.

# Section 20: Navigation with React Router
* react-router-dom?
  `npm install --save react-router-dom`
  * react-router: this contains a core items for router
  * react-router-dom: this is the best package for react apps in browser. (react-router-dom = react-router + DOM routing elements )
* using react-router
  ```js
  import { Router, BrowserRouter } from 'react-router-dom';

  const App = () => {
    return (
      <div>
        <BrowserRouter>
          <div>
            <Route path="/" exact component={PageOne}>
            <Route path="/p2" component={PageTwo}>
          </div>
        </BrowserRouter>
      </div>
    );
  };
  ```
  * react-router-dom matches the path portion of URL
    `https://localhost:3000/33/3/a234`
    * `https://localhost:3000` is the domain of the URL
    * `33/3/a234` is a path in the URL, and this is what react-router-dom takes as a path.
* what is `exact` in the `<Route>`?
  * this property does a exact matching. without `exact` in the following example. If you visit `/p2` then the browser will render PageOne and PageTwo
    ```js
    ...
      <Route path="/" exact component={PageOne}>
      <Route path="/p2" component={PageTwo}>
    ...
    ```
  * this is because the router first extracts the path and does the comparison with .contains by default
    `extractedPath.contains(path)`
  * with exact keyword. you will do the exact matching
    `extractedPath.equals(path)`
* Don't use `<a>` with react-router-dom
  `<a href="/p2">Navigation to P2</a>`
  * example of bad navigation
    ```
    1. user clicks <a href="/p2" />
    2. browser makes a request to `www.domain/p2`
    3. react server responds with index.html file.
    4. browser receives index.html and dumps older index.html.
    ```
    * if you check chrome browser inspect Network. you will see that the browser makes a request to localhost:3000(react server) and localhost:300(react server) responds with index.html bundled with react components.
    * this is inefficient. If we can prevent dumping exising html elements, it would be perfect.
* Solution: use `<Link>` with react-router-dom
  `<Link to="/p2">Navigation to P2</Link>`
  * `<Link>` prevents the full reloading of the browser.
* Creating a Header that supports navigation
  ```js
  import { Link } from 'react-router-dom';

  const Header = () => {
    return (
      <div className="ui secondary pointing menu">
        <Link to="/" className="item">
          HOME
        </Link>
        <Link to="/streams/new" className="item">
          NEW
        </Link>
        <Link to="/streams/show" className="item">
          SHOW
        </Link>
        <Link to="/streams/edit" className="item">
          EDIT
        </Link>
        <Link to="/streams/delete" className="item">
          DELETE
        </Link>
        <div className="right menu">
          <Link to="/" className="item">
            All Streams
          </Link>
        </div>
      </div>
    )
  }
  ```
* Using a header
  * Rule: put `<Link>` inside `<BrowserRouter>`. If you put `<Link>` outside of `<BrowserRouter>`, the app will throw an error.
  * In this example below, the app will always show Header because it is not part of Route. Consider Route as an if/else block that shows components when the path condition(s) are met.
  ```js
  const App = () => {
    return (
      <div>
        <BrowserRouter>
          <div>
            <Header />
            <Route path="/" exact component={StreamList} />
            <Route path="/streams" exact component={StreamList} />
            <Route path="/streams/new" exact component={StreamCreate} />
            <Route path="/streams/show" exact component={StreamShow} />
            <Route path="/streams/edit" exact component={StreamEdit} />
            <Route path="/streams/delete" exact component={StreamDelete} />
          </div>
        </BrowserRouter>
      </div>
    )
  }
  ```
# Section 21: Handling Authentication with React
* What is OAuth?
  * OAuth: Authentication of a user using 3rd party Service Provider.
    * ex) Accepting user with Google account
* OAuth in JS App and Google
  ```js
  1. JS App: User tries login
  2. JS App: Google javascript client makes a request to Google Server.
  3. Google: Google displays popup asking user's consent
  4. Google: User Accepts.
  5. Google: Sends authorization token back to JS App.
  6. JS App: Google javascript client invokes a callback to React App with authorization token
  ```
* Setting up Google Auth
  * visit `console.cloud.google.com`
    * Create new Project 'Streamy'
    * Configure OAuth consent screen
      * configure localhost:3000 as Authorized Javascript origins

* import Google apis js library
  ```html
  // index.html
  <head>
    ...
    <script src="https://apis.google.com/js/api.js"></script>
  </head>
  ```
  * this library provides gapi to window object. (window object represents an open window in a browser.)
    * if you go to the browser console and try gapi. It will show a google api client object.
    * from React you can access it by `window.gapi`
* Using Google Auth2 library
  ```js
  // GoogleAuth.js
  class GoogleAuth extends React.Component {

    constructor(prop) {
      super(prop);

      this.state = {
        isSignedIn: null
      }

    }

    componentDidMount() {
      window.gapi.load('client:auth2', () => {
        window.gapi.client.init({
          clientId: 'your-google-api-client-id',
          scope: 'email'
        }).then(() => {
          this.auth = window.gapi.auth2.getAuthInstance();
          this.onAuthChange();
          this.auth.isSignedIn.listen(this.onAuthChange);
        })
      });
    }

    onAuthChange = () => {
      this.setState({
        isSignedIn: this.auth.isSignedIn.get()
      })
    }

    renderAuthButton = () => {
      if (this.state.isSignedIn === null) {
        return <div>Please Sign In</div>;
      } else if (this.state.isSignedIn === true) {
        return <button onClick={ () => {this.auth.signOut()} }>sign out</button>
      } else {
        return <button onClick={ () => {this.auth.signIn()} }>sign in</button>
      }
    }

    render() {
      return this.renderAuthButton()
    }
  };

  ```
  * `load('client:auth2' ...)` downloads google auth2 moudles. The second parameter is a call back function.
  * `gapi.client.init()` returns a promise. With `then()`, you download the auth instance and set the state.

* Sign-In and Sign-Out with redux
  ```js
  // actions.js

  export const signIn = () => {
  return {
    type: 'SIGN_IN'
  }
}

  export const signOut = () => {
    return {
      type: 'SIGN_IN'
    }
  }
  ```
  ```js
  // authReducer.js
  const authReducer = (
    state = {
      isSignedIn: null
    }, 
    action
  ) => {

    switch(action.type) {
      case "SIGN_IN":
        return {
          isSignedIn: true
        }
      case "SIGN_OUT":
        return {
          isSignedIn: false
        }
      default:
        return state;
    }
  }

  export default authReducer;
  ```

