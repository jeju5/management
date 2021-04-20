import React, { useState, useEffect, useRef } from 'react';


const Dropdown = ({label, options, selected, onSelect}) => {
  // state hook
  const [open, setOpen] = useState(false);
  const dropDownRef = useRef();

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
        console.log('Detach')
        document.body.removeEventListener('click', onBodyClickListener);
      }
    );
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
        <label className="label">{label}</label>
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