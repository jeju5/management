import React from 'react';
import { useState } from 'react';

const Accordion = ({ items }) => {
  // this is not array assignment. This is a syntax of multiple-variable initialization.
  // 
  const [activeIndex, setActiveIndex] = useState(null);

  const onTitleClick = (index) => {
    console.log("Title Clicked", index);
    setActiveIndex(index);
  };

  const renderedItems = items.map((item, index) => {

    const active = (index === activeIndex) ? 'active' : '';

    return (
      <React.Fragment key={item.title}>
        <div
          className={`title ${active}`}
          onClick={() => onTitleClick(index)}
        >
          <i className="dropdown icon"></i>
          {item.title}
        </div>
        <div className={`content ${active}`}>
          <p>{item.content}</p>
        </div>
      </React.Fragment>
    )
  });
  
  return(
    <div className="accordion ui styled">
      {renderedItems}
      <h1>{activeIndex}</h1>
    </div>
  );
}

export default Accordion;