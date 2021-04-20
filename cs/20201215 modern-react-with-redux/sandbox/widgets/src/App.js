import React, { useState } from 'react';
import Accordion from './components/Accordion.js';
import Search from './components/Search.js';
import Dropdown from './components/Dropdown.js'
import Translate from './components/Translate.js'
import Route from './components/Route.js'
import Header from './components/Header.js'

const items = [
  {
    title: "What is React?",
    content: "React is a front-end js framework"
  },
  {
    title: "Why use React?",
    content: "React is popular among engineers"
  },
  {
    title: "How do you use React?",
    content: "You use React by using components"
  },
]

const options = [
  {
    label: 'The Color Red',
    value: 'red',
  },
  {
    label: 'The Color Green',
    value: 'green',
  },
  {
    label: 'A Shade of Blue',
    value: 'blue',
  },
];

const App = () => {
  // state with hook
  const [selected, setSelected] = useState(options[0]);
  const [showDropdown, setShowDropdown] = useState(true);

  // naive approach
  const showAccordion = () => {
    if (window.location.pathname === "/") {
      return <Accordion items={items} />
    }
  }
  const showSearch = () => {
    if (window.location.pathname === "/list") {
      return <Search />
    }
  }
  const showTranslate = () => {
    if (window.location.pathname === "/translate") {
      return <Translate />
    } 
  }
  const showDropdownF = () => {
    if (window.location.pathname === "/dropdown") {
      const dropDown = showDropdown ?  <Dropdown
                                    options={options}
                                    selected={selected}
                                    onSelect={setSelected}
                                    />
                              : null;
      return (
        <React.Fragment>
          <button onClick={() => setShowDropdown(!showDropdown)}>Toggle Dropdown</button>
          {dropDown}
        </React.Fragment>
      );
    }
  }

  return (
    <div>
      <Header />
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
}

export default App;